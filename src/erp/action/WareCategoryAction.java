package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.WareCategory;
import erp.service.WareCategoryService;
import erp.service.WareService;

public class WareCategoryAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger
            .getLogger(WareCategoryAction.class);

    private WareService wareService = null;
    private WareCategoryService wareCategoryService = null;

    private int id;
    private WareCategory category;
    private List<WareCategory> categoryList;

    public String list() throws Exception {
        categoryList = wareCategoryService.list();
        return SUCCESS;
    }

    public String get() throws Exception {
        try {
            category = wareCategoryService.getWareCategoryById(id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public String delete() throws Exception {
        try {
            wareCategoryService.deleteWareCategoryById(id);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        try {
            WareCategory obj = (id > 0) ? wareCategoryService
                    .getWareCategoryById(id) : new WareCategory();
            obj.setName(category.getName().trim());
            if (id > 0) {
                wareCategoryService.updateWareCategory(obj);
            } else {
                id = wareCategoryService.createWareCagegory(obj);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }
        return SUCCESS;
    }

    public WareService getWareService() {
        return wareService;
    }

    public void setWareService(WareService wareService) {
        this.wareService = wareService;
    }

    public WareCategoryService getWareCategoryService() {
        return wareCategoryService;
    }

    public void setWareCategoryService(WareCategoryService wareCategoryService) {
        this.wareCategoryService = wareCategoryService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WareCategory getCategory() {
        return category;
    }

    public void setCategory(WareCategory category) {
        this.category = category;
    }

    public List<WareCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<WareCategory> categoryList) {
        this.categoryList = categoryList;
    }

}
