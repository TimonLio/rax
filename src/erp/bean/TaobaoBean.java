package erp.bean;

import org.apache.log4j.Logger;

import erp.action.TradeImportAction;

public class TaobaoBean {

	private final static Logger logger = Logger
			.getLogger(TradeImportAction.class);

	private String appKey;
	private String appSecret;
	private String restUrl;
	private String sessionUrl;

	public TaobaoBean() {
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public String getSessionUrl() {
		return sessionUrl;
	}

	public void setSessionUrl(String sessionUrl) {
		this.sessionUrl = sessionUrl;
	}

	public void dump() {
		logger.info("appkey:" + this.appKey);
		logger.info("appSecret:" + this.appSecret);
		logger.info("restUrl:" + this.restUrl);
		logger.info("sessionUrl:" + this.sessionUrl);
	}
}
