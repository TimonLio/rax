package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Ware;
import erp.service.WareService;

public class IndexAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(IndexAction.class);

    private WareService wareService = null;

    private List<Ware> wareList;

    @Override
    public String execute() throws Exception {
        try {
            wareList = wareService.listLowNumber(0);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public List<Ware> getWareList() {
        return wareList;
    }

    public void setWareService(WareService service) {
        wareService = service;
    }

    public WareService getWareService() {
        return wareService;
    }

    public void setWareList(List<Ware> wareList) {
        this.wareList = wareList;
    }

}
