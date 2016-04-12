package erp.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.Action;

import erp.model.Express;
import erp.model.Sell;
import erp.service.ExpressService;
import erp.service.SellService;
import erp.service.UtilService;

public class ExpressAction implements Action {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(ExpressAction.class);
	private SellService sellService = null;
	private ExpressService expressService = null;
	private UtilService utilService = null;

	private Sell sell;
	private int sellId;
	private String date;
	private String sender;
	private String senderPhone;
	private String senderAddress;
	private String senderPostCode;

	private List<Express> expressList;
	private Express express;
	private int id;

	private String startDate;
	private String endDate;
	private List<Sell> sellList;

	private List<String> dateSel;
	private Map<Integer, String> expressSel;
	private Map<Integer, String> imTypeSel;

	private long mm2pt(int mm) {
		return Math.round(mm * 2.834645669291);
	}

	public InputStream getInputStream() throws Exception {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		try {
			Express ex = expressService.getExpressById(sell.getExpressId());
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			BaseFont bfEnglish = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

			Document doc = new Document(new Rectangle(mm2pt(ex.getTplSizeX()),
					mm2pt(ex.getTplSizeY())));
			PdfWriter writer = PdfWriter.getInstance(doc, buf);
			doc.open();

			PdfContentByte cb = writer.getDirectContent();
			cb.beginText();

			// 英文字体
			cb.setFontAndSize(bfEnglish, 11);

			// 发件日期
			if (ex.getTplDateX() != 0 || ex.getTplDateY() != 0) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date d = df.parse(date);
				df = new SimpleDateFormat(ex.getTplDateFmt());
				cb.setTextMatrix(mm2pt(ex.getTplDateX()), mm2pt(ex
						.getTplDateY()));
				cb.showText(df.format(d));
			}

			// 发件人电话
			cb.setTextMatrix(mm2pt(ex.getTplSrcPhoneX()), mm2pt(ex
					.getTplSrcPhoneY()));
			cb.showText(senderPhone);

			// 收件人电话1
			cb.setTextMatrix(mm2pt(ex.getTplDstPhone1X()), mm2pt(ex
					.getTplDstPhone1Y()));
			cb.showText(sell.getCustomerPhone1());

			// 收件人电话2
			cb.setTextMatrix(mm2pt(ex.getTplDstPhone2X()), mm2pt(ex
					.getTplDstPhone2Y()));
			cb.showText(sell.getCustomerPhone2());

			cb.setFontAndSize(bfEnglish, 12);
			float charSpace = cb.getCharacterSpacing();
			cb.setCharacterSpacing(6);
			
			// 发件人邮编
			if (ex.getTplSrcZipX() != 0 || ex.getTplSrcZipY() != 0) {
				cb.setTextMatrix(mm2pt(ex.getTplSrcZipX()), mm2pt(ex
						.getTplSrcZipY()));
				cb.showText(senderPostCode);
			}
			
			// 收件人邮编
			if (ex.getTplDstZipX() != 0 || ex.getTplDstZipY() != 0) {
				cb.setTextMatrix(mm2pt(ex.getTplDstZipX()), mm2pt(ex
						.getTplDstZipY()));
				cb.showText(sell.getCustomerPostCode());
			}
			cb.setCharacterSpacing(charSpace);

			// 发件人姓名
			cb.setFontAndSize(bfChinese, 11);
			cb.setTextMatrix(mm2pt(ex.getTplSrcNameX()), mm2pt(ex
					.getTplSrcNameY()));
			cb.showText(sender);

			// 收件人姓名
			cb.setFontAndSize(bfChinese, 12);
			cb.setTextMatrix(mm2pt(ex.getTplDstNameX()), mm2pt(ex
					.getTplDstNameY()));
			cb.showText(sell.getCustomerName());

			cb.endText();

			// 收发件人地址
			StringBuffer recvAddress = new StringBuffer();
			for (int i = 0; i < ex.getTplDstAddrIndent(); i++) {
				recvAddress.append("　");
			}
			recvAddress.append(sell.getCustomerAddress());
			if (false == sell.getCommentExpress().isEmpty()) {
				recvAddress.append("\n");
				recvAddress.append(sell.getCommentExpress());
			}

			StringBuffer sendAddress = new StringBuffer();
			for (int i = 0; i < ex.getTplSrcAddrIndent(); i++) {
				sendAddress.append("　");
			}
			sendAddress.append(senderAddress);

			Font cf = new Font(bfChinese, 11, Font.NORMAL);
			Phrase srcAddress = new Phrase(sendAddress.toString(), cf);
			Phrase dstAddress = new Phrase(recvAddress.toString(), cf);

			// 发件人地址
			ColumnText ct = new ColumnText(cb);
			logger.info("SrcAddr:" + senderAddress);
			ct.setSimpleColumn(srcAddress, mm2pt(ex.getTplSrcAddrLBX()),
					mm2pt(ex.getTplSrcAddrLBY()), mm2pt(ex.getTplSrcAddrRTX()),
					mm2pt(ex.getTplSrcAddrRTY()), 15, Element.ALIGN_LEFT
							| Element.ALIGN_TOP);
			ct.go();

			// 收件人地址
			ct = new ColumnText(cb);
			logger.info("DstAddr:" + recvAddress.toString());
			ct.setSimpleColumn(dstAddress, mm2pt(ex.getTplDstAddrLBX()),
					mm2pt(ex.getTplDstAddrLBY()), mm2pt(ex.getTplDstAddrRTX()),
					mm2pt(ex.getTplDstAddrRTY()), 15, Element.ALIGN_LEFT
							| Element.ALIGN_TOP);
			ct.go();

			doc.close();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return new ByteArrayInputStream(buf.toByteArray());
	}

	public String input() throws Exception {
		if (sellId > 0) {
			sell = sellService.getSellById(sellId);

			logger.debug("sell.expressId:" + sell.getExpressId());
			logger.debug("sell.sendDate:" + sell.getSendDate());

			Date d = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(d);

			logger.debug("Calendar.YEAR:" + c.get(Calendar.YEAR));
			logger.debug("Calendar.MONTH:" + c.get(Calendar.MONTH));
			logger.debug("Calendar.DATE:" + c.get(Calendar.DATE));
			logger.debug("Calendar.HOUR_OF_DAY:" + c.get(Calendar.HOUR_OF_DAY));
			logger.debug("Calendar.MINUTE:" + c.get(Calendar.MINUTE));

			if (sell.getSendDate() != null) {
				d = sell.getSendDate();
			} else if (c.get(Calendar.HOUR_OF_DAY) > 18) {
				c.setTimeInMillis(d.getTime() + 86400000);
				d = c.getTime();
			}
			date = df.format(d);
			logger.debug("d3:" + date);

			Date d1 = new Date();
			c.setTimeInMillis(d1.getTime() + 86400000);
			Date d2 = c.getTime();
			c.setTimeInMillis(d2.getTime() + 86400000);
			Date d3 = c.getTime();

			dateSel = new ArrayList<String>();
			dateSel.add(df.format(d1));
			dateSel.add(df.format(d2));
			dateSel.add(df.format(d3));

			sender = utilService.getSenderName();
			senderAddress = utilService.getSenderAddress();
			senderPhone = utilService.getSenderPhone();
			senderPostCode = utilService.getSenderPostCode();

			return SUCCESS;
		}
		return ERROR;
	}

	@Override
	public String execute() throws Exception {
		if (sellId > 0) {
			Sell s = sellService.getSellById(sellId);

			s.setCommentExpress(sell.getCommentExpress());
			s.setCustomerName(sell.getCustomerName());
			s.setCustomerAddress(sell.getCustomerAddress());
			s.setCustomerPhone1(sell.getCustomerPhone1());
			s.setCustomerPhone2(sell.getCustomerPhone2());
			s.setCustomerPostCode(sell.getCustomerPostCode());
			s.setExpressId(sell.getExpressId());

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			s.setSendDate(df.parse(date));

			sellService.updateSell(s);
			sell = s;
			return SUCCESS;
		}
		return ERROR;
	}

	public String list() throws Exception {
		expressList = expressService.list();
		return SUCCESS;
	}

	public String get() throws Exception {
		express = expressService.getExpressById(id);
		return SUCCESS;
	}

	public String save() throws Exception {
		if (id > -1) {
			expressService.updateExpress(express);
		} else {
			id = expressService.createExpress(express);
		}
		return SUCCESS;
	}

	public String summary() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (startDate == null || endDate == null) {
			express = expressService.getExpressById(id);
			startDate = df.format(express.getSettleDate());
			endDate = df.format(new Date());
		}
		try {
			Date from = df.parse(startDate);
			Date to = df.parse(endDate);
			sellList = sellService.listByExpress(id, from, to);
			sell = new Sell();
			for (Sell s : sellList) {
				sell.setFeeReal(sell.getFeeReal() + s.getFeeReal());
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return SUCCESS;
	}

	public String updateSettleDate() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date settleDate = df.parse(endDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(settleDate);
			calendar.add(Calendar.DATE, 1);
			express = expressService.getExpressById(id);
			express.setSettleDate(calendar.getTime());
			expressService.updateExpress(express);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return SUCCESS;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Sell> getSellList() {
		return sellList;
	}

	public void setSellService(SellService sellService) {
		this.sellService = sellService;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	public List<String> getDateSel() {
		return dateSel;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Sell getSell() {
		return sell;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSenderPostCode() {
		return senderPostCode;
	}

	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
		expressSel = expressService.getExpressSel();
	}

	public void setSenderPostCode(String senderPostCode) {
		this.senderPostCode = senderPostCode;
	}

	public Map<Integer, String> getExpressSel() {
		return expressSel;
	}

	public List<Express> getExpressList() {
		return expressList;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<Integer, String> getImTypeSel() {
		return imTypeSel;
	}

	public void setUtilService(UtilService utilService) {
		this.imTypeSel = utilService.getIMTypeSel();
		this.utilService = utilService;
	}

}
