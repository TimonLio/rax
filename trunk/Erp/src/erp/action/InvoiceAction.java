package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Sell;
import erp.model.SellItem;
import erp.service.SellItemService;
import erp.service.SellService;
import erp.service.WareService;

public class InvoiceAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(InvoiceAction.class);

    private SellService sellService = null;
    private SellItemService sellItemService = null;
    private WareService wareService = null;

    private Sell sell;
    private List<SellItem> sellItemList;
    private int sellId;

    public String input() throws Exception {
        if (sellId > 0) {
            sell = sellService.getSellById(sellId);
            if (sell.getSender().isEmpty()) {
                sell.setSender("冰心抹茶");
            }
            return SUCCESS;
        }
        return ERROR;
    }

    @Override
    public String execute() throws Exception {
        if (sellId > 0) {
            Sell s = sellService.getSellById(sellId);
            if (false == sell.getCommentInvoice().equals(s.getCommentInvoice())
                    || false == sell.getSender().equals(s.getSender())) {
                if (sell.getSender().equals("冰心抹茶")) {
                    s.setSender("");
                } else {
                    s.setSender(sell.getSender());
                }
                s.setCommentInvoice(sell.getCommentInvoice());
                sellService.updateSell(s);
            }
            sell = s;
            sellItemList = sellItemService.listBySell(sell);
            for (SellItem item : sellItemList) {
                item.setWare(wareService.getWareById(item.getWareId()));
            }
            return SUCCESS;
        }
        return ERROR;
    }

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public SellService getSellService() {
        return sellService;
    }

    public void setSellService(SellService sellService) {
        this.sellService = sellService;
    }

    public WareService getWareService() {
        return wareService;
    }

    public void setWareService(WareService wareService) {
        this.wareService = wareService;
    }

    public SellItemService getSellItemService() {
        return sellItemService;
    }

    public void setSellItemService(SellItemService sellItemService) {
        this.sellItemService = sellItemService;
    }

    public List<SellItem> getSellItemList() {
        return sellItemList;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public void setSellItemList(List<SellItem> sellItemList) {
        this.sellItemList = sellItemList;
    }

}
