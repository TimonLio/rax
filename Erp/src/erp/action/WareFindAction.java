package erp.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Ware;
import erp.service.KeywordService;
import erp.service.WareService;

public class WareFindAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(WareFindAction.class);

    private WareService wareService = null;
    private KeywordService keywordService = null;

    private String keyword;
    private String barcode;

    private List<Ware> wareList;

    @Override
    public String execute() throws Exception {
        if ((barcode != null) && (barcode.isEmpty() == false)) {
            logger.info("findByBarcode:" + barcode);
            wareList = wareService.findByBarcode(barcode);
        } else if ((keyword != null) && keyword.isEmpty() == false) {
            logger.info("findByKeywords:" + keyword);
            wareList = wareService.findByKeywords(keyword);
        }
        return SUCCESS;
    }

    public String fullTextSearch() throws Exception {
        logger.info("fullTextSearch:" + keyword);
        List<String> tokenList = keywordService.parseToken(keyword);
        StringBuffer tokenBuf = new StringBuffer();
        boolean skipFirstToken = true;
        for (String token : tokenList) {
            if (skipFirstToken == false) {
                tokenBuf.append("|");
            }
            tokenBuf.append(token);
            skipFirstToken = false;
        }
        wareList = wareService.fullTextSearch(tokenBuf.toString().trim());
        return SUCCESS;
    }

    public void setWareService(WareService service) {
        wareService = service;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<Ware> getWareList() {
        return wareList;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setKeywordService(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

}
