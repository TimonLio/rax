package erp.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import erp.bean.TaobaoBean;

public class TaobaoSupport {

	private final static Logger logger = Logger
			.getLogger(TaobaoSupport.class);

	protected String appKey;
	protected String appSecret;
	protected String restUrl;

	static {

		BeanDefinitionRegistry reg = new DefaultListableBeanFactory();
		PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(reg);
		reader.loadBeanDefinitions(new ClassPathResource("taobao.properties"));
		BeanFactory factory = (BeanFactory) reg;
		TaobaoBean bean = (TaobaoBean) factory.getBean("taobao");
		System.out.println("appKey:" + bean.getAppKey());	
		//appSecret = bean.getAppSecret();
		//restUrl = bean.getRestUrl();
		
		/*
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("taobao.xml"));
		TaobaoBean bean = (TaobaoBean) factory.getBean("taobao");
		appKey = bean.getAppKey();
		appSecret = bean.getAppSecret();
		restUrl = bean.getRestUrl();
		*/

		/*
		String resource = "taobao.properties";
		try {
			Reader r = Resources.getResourceAsReader(resource);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.toString());
		}
		*/

		/*
		 * Properties prop = new Properties(); InputStream in =
		 * Object.class.getResourceAsStream("taobao.properties"); try {
		 * prop.load(in); if
		 * ("true".equalsIgnoreCase(prop.getProperty("taobao.debug").trim())) {
		 * appKey = prop.getProperty("sandbox.appKey").trim(); appSecret =
		 * prop.getProperty("sandbox.appSecret").trim(); restUrl =
		 * prop.getProperty("sandbox.restUrl").trim(); } else { appKey =
		 * prop.getProperty("appKey").trim(); appSecret =
		 * prop.getProperty("appSecret").trim(); restUrl =
		 * prop.getProperty("restUrl").trim(); } } catch (IOException e) {
		 * e.printStackTrace(); logger.error(e.toString()); }
		 */
		
		//TaobaoBean bean = new TaobaoBean();
		//appKey = bean.getAppKey();
		//appSecret = bean.getAppSecret();
		//restUrl = bean.getRestUrl();
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public static void main(String[] args) throws Exception {
		TaobaoSupport taobao = new TaobaoSupport();
		System.out.println("appKey:" + taobao.getAppKey());
		System.out.println("appSecret:" + taobao.getAppSecret());
		System.out.println("restUrl:" + taobao.getRestUrl());
	}

}
