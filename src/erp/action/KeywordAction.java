package erp.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import erp.service.KeywordService;

public class KeywordAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private KeywordService keywordService = null;

	private String tokenize;
	private int status = 0;

	public String parseToken() throws Exception {
		List<String> tokenList = keywordService.parseToken(tokenize);
		StringBuffer tokenBuf = new StringBuffer();
		for (String token : tokenList) {
			tokenBuf.append(token);
			tokenBuf.append(" ");
		}
		tokenize = tokenBuf.toString().trim();
		return SUCCESS;
	}

	public String saveTokens() throws Exception {
		if (tokenize != null && tokenize.isEmpty() == false) {
			status = keywordService.saveTokens(tokenize);
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		status = keywordService.getCount();
		return SUCCESS;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public String getTokenize() {
		return tokenize;
	}

	public void setTokenize(String tokenize) {
		this.tokenize = tokenize;
	}

}
