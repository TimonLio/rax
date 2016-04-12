package erp.action;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.InvoiceItem;
import erp.model.Sell;
import erp.model.SellItem;
import erp.service.SellItemService;
import erp.service.SellService;
import erp.service.WareService;

public class SellItemImportAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(SellItemImportAction.class);

	private SellService sellService;
	private SellItemService sellItemService;
	private WareService wareService;

	private int sellId;
	private Sell sell;
	private String sellContent;
	private List<InvoiceItem> itemList;
	private List<SellItem> sellItemList;
	private double totalPrice;
	private double totalExFee;

	@Override
	public String input() throws Exception {
		sell = sellService.getSellById(sellId);
		if (sell != null) {
			sellItemList = sellItemService.listBySell(sell);
			for (SellItem item : sellItemList) {
				item.setWare(wareService.getWareById(item.getWareId()));
			}
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		// AJAX 方法
		try {
			itemList = new ArrayList<InvoiceItem>();
			totalPrice = 0;
			totalExFee = 0;
			InvoiceItem item = null;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = "([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})";
			String feeStr = "\\(含快递[\\s]*:([\\d\\.]+)\\)";
			String priceStr = "([\\d\\.]+)[\\s]+([\\d]+)[\\s]+";
			String idStr = "投诉维权[\\s]?([\\s\\S]*)?";
			String subClassStr = "([^\\(]*):[\\s]+([\\S]*)";

			Pattern datePat = Pattern.compile(dateStr);
			Pattern feePat = Pattern.compile(feeStr);
			Pattern pricePat = Pattern.compile(priceStr);
			Pattern idPat = Pattern.compile(idStr);
			Pattern subClassPat = Pattern.compile(subClassStr);
			
			String[] infos = sellContent.split("\n");
			ArrayList<String> infoArray = new ArrayList<String>();
			for (int i = 0; i < infos.length; i++) {
				if (infos[i].trim().length() > 0) {
					infoArray.add(infos[i].trim());
				}
			}
			
			Matcher matcher;
			for (int i = 0; i < infoArray.size(); i++) {
				Date date = new Date();
				int num = 1;
				String name = "";
				String byerId = "";
				String byerName = "";
				double price = 0;
				double exFee = 0;
				double total = 0;
				boolean itemClosed = false;

				String info = infoArray.get(i);
				logger.debug("info[" + i + "]:" + info);

				matcher = datePat.matcher(info);
				if (matcher.find()) {
					//logger.debug("datePat matched <info[" + i + "]:" + info + ">");
					try {
						date = formatter.parse(matcher.group(1));
					} catch (Exception ex) {}
					
					name = infoArray.get(i + 1);
					
					for (int j = i + 1; j < infoArray.size(); j++) {
						info = infoArray.get(j);
						if (datePat.matcher(info).find()) break;
						
						matcher = subClassPat.matcher(info);
						if (matcher.find()) {
							logger.debug("subClassPat matched <info[" + j + "]:" + info + ">");
							
							if (!matcher.group(1).equals("商家编码")) {
								name += " " + matcher.group(2);
							}
						}
						
						matcher = pricePat.matcher(info);
						if (matcher.find()) {
							//logger.debug("pricePat matched <info[" + j + "]:" + info + ">");
							try {
								price = Double.valueOf(matcher.group(1));
							} catch (Exception ex) {}
							
							try {
								num = Integer.valueOf(matcher.group(2));
							} catch (Exception ex) {}
							
							//logger.debug("price:" + price + " num:" + num);
						}
						
						matcher = idPat.matcher(info);
						if (matcher.find()) {
							//logger.debug("idPat matched <info[" + j + "]:" + info + ">");
							
							byerId = matcher.group(1).trim();
							byerName = infoArray.get(j + 1);
							
							//logger.debug("byerId:" + byerId + " byerName:" + byerName);
							
							itemClosed = (infoArray.get(j + 2).indexOf("交易关闭") > -1);
							
							// ID founded, need break
							break;
						}
					}
					
					// Remove item name tail with price and id
					matcher = pricePat.matcher(name);
					if (matcher.find()) {
						logger.debug("pricePat matched <name:" + name + ">" + " group:" + matcher.group());
						name = name.substring(0, name.indexOf(matcher.group())).trim();
					}

					if (itemClosed) continue;
					
					boolean foundFee = false;
					for (int j = i + 1; j < infoArray.size(); j++) {
						info = infoArray.get(j);
						if (datePat.matcher(info).find()) break;

						matcher = feePat.matcher(info);
						if (matcher.find()) {
							foundFee = true;
							try {
								exFee = Double.valueOf(matcher.group(1));
							} catch (Exception ex) {}
							infos = infoArray.get(j - 1).trim().split("\\s");
							try {
								total = Double.valueOf(infos[infos.length - 1]);
							} catch (Exception ex) {}
						}
						
						matcher = pricePat.matcher(info);
						if (foundFee && matcher.find()) {
							String extName = "";
							double extPrice = 0;
							int extNumber = 1;

							extName = infoArray.get(j - 1);

							try {
								extPrice = Double.valueOf(matcher.group(1));
							} catch (Exception ex) {}

							try {
								extNumber = Integer.valueOf(matcher.group(2));
							} catch (Exception ex) {}

							item = new InvoiceItem();
							item.setDate(date);
							item.setByerId(byerId);
							item.setByerName(byerName);
							item.setExFee(0);
							item.setName(extName);
							item.setNumber(extNumber);
							item.setPrice(extPrice);
							itemList.add(item);
						}
					}

					item = new InvoiceItem();
					item.setName(name);
					item.setDate(date);
					item.setByerId(byerId);
					item.setNumber(num);
					item.setByerName(byerName);
					item.setPrice(price);
					item.setExFee(exFee);
					itemList.add(item);
					totalExFee += exFee;
					totalPrice += total;
				}
			}

			for (InvoiceItem it : itemList) {
				logger.debug("日期:" + formatter.format(it.getDate()) + " 宝贝名称:"
						+ it.getName() + " 单价:" + it.getPrice() + " 数量:"
						+ it.getNumber() + " 快递费:" + it.getExFee() + " 买家ID:"
						+ it.getByerId() + " 买家姓名:" + it.getByerName());
			}
			logger.debug("运费:" + totalExFee + " 合计:" + totalPrice);

			if (sellId > 0 && item != null) {
				Sell obj = sellService.getSellById(sellId);
				if (obj != null) {
					int cnt = sellItemService.countBySellId(sellId);
					if (obj.getCustomerIM().isEmpty()) {
						obj.setCustomerIM(item.getByerId());
						obj.setCustomerIMType(0);
						// IMType的具体定义在SellService里
					}
					obj.setFee(cnt > 0 ? totalExFee + obj.getFee() : totalExFee);
					sellService.updateSell(obj);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return SUCCESS;
	}

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);

		char[] cbuf = new char[1024];
		StringBuffer buf = new StringBuffer();
		InputStreamReader is = new InputStreamReader(new FileInputStream(
				"F:/content2.txt"));
		int size;
		while ((size = is.read(cbuf)) != -1) {
			buf.append(cbuf, 0, size);
		}

		SellItemImportAction action = new SellItemImportAction();
		action.sellContent = buf.toString();
		action.execute();

		/*
		 * Matcher matcher; Pattern pattern =
		 * Pattern.compile("([\\S\\s]+)[\\s]{2}([\\S]+)");
		 * 
		 * String info; 
		 * String[] infos = buf.toString().split("\n"); 
		 * for (int i = 0; i < infos.length; i++) { 
		 *   info = infos[i].trim(); 
		 *   matcher = pattern.matcher(info); 
		 *   if (matcher.find()) {
		 *     logger.debug(matcher.group(0));
		 *     //logger.debug(matcher.group(1));
		 *     //logger.debug(matcher.group(2));
		 *   }
		 * }
		 */

	}

	public void setSellService(SellService sellService) {
		this.sellService = sellService;
	}

	public String getSellContent() {
		return sellContent;
	}

	public void setSellContent(String sellContent) {
		this.sellContent = sellContent;
	}

	public List<InvoiceItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<InvoiceItem> itemList) {
		this.itemList = itemList;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public void setSellItemService(SellItemService sellItemService) {
		this.sellItemService = sellItemService;
	}

	public void setWareService(WareService wareService) {
		this.wareService = wareService;
	}

	public Sell getSell() {
		return sell;
	}

	public List<SellItem> getSellItemList() {
		return sellItemList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalExFee() {
		return totalExFee;
	}

	public void setTotalExFee(double totalExFee) {
		this.totalExFee = totalExFee;
	}

}
