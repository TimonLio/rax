package erp.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class Pager {

	static private String pagePre = "[";
	static private String pagePost = "]";
	static private String pageSeparator = " ";
	static private String prevImg = "<";
	static private String nextImg = ">";
	static private String prevSlide = "<<";
	static private String nextSlide = ">>";
	static private String firstImg = "|<";
	static private String lastImg = ">|";

	private String action;
	private int ignoreItems;
	private int offsetItems;
	private int totalItems;
	private int perPage;
	private int currentPage;
	private int totalPage;
	private int delta;
	private boolean expanded;

	private final static Logger logger = Logger.getLogger(Pager.class);

	public static void main(String[] args) throws Exception {
		Pager p = new Pager();
		p.setAction("disp.action");
		p.setCurrentPage(8);
		p.setTotalItems(30);
		p.setPerPage(2);
		p.setDelta(2);
		p.generatePageData();
		System.out.println("PreviousSlide:" + p.getPreviousSlide());
		System.out.println("BackLink:" + p.getBackLink());
		System.out.println("CurrentPage:" + p.getCurrentPage());
		System.out.println("TotalPage:" + p.getTotalPage());
		System.out.println("NextLink:" + p.getNextLink());
		System.out.println("NextSlide:" + p.getNextSlide());
		System.out.println("PageLinks:" + p.getPageLinks());
		System.out.println("PageInputBox:" + p.getPageInputBox());
	}

	public Pager() {
		action = "";
		ignoreItems = 0;
		offsetItems = 0;
		totalItems = 0;
		perPage = 10;
		currentPage = 1;
		totalPage = 0;
		delta = 5;
		expanded = false;
	}

	public void generatePageData() {
		logger.debug("totalItems:" + totalItems);
		logger.debug("ignoreItems:" + ignoreItems);
		logger.debug("perPage:" + perPage);
		totalPage = (totalItems - ignoreItems + perPage - 1) / perPage;
		logger.debug("totalPage:" + totalPage);
		if (totalPage < 1) {
			totalPage = 1;
		}
		logger.debug("totalPage:" + totalPage);
		if (currentPage < 1 || currentPage > totalPage) {
			currentPage = 1;
		}
		logger.debug("currentPage:" + currentPage);
		offsetItems = (currentPage - 1) * perPage + ignoreItems;
		logger.debug("offsetItems:" + offsetItems);
	}

	public int getNextPage() {
		return currentPage == totalPage ? 0 : currentPage + 1;
	}

	public int getPreviousPage() {
		return currentPage == 1 ? 0 : currentPage - 1;
	}

	public boolean isFirstPage() {
		return currentPage < 2;
	}

	public boolean isLastPage() {
		return currentPage == totalPage;
	}

	public Map<String, String> getLinks() {
		HashMap<String, String> m = new HashMap<String, String>();

		return m;
	}

	public String getPageLinks() {
		int rangeLeft = getPageRangeLeft();
		int rangeRight = getPageRangeRight();

		StringBuffer content = new StringBuffer();
		for (int i = rangeLeft; i <= rangeRight; i++) {
			if (i != rangeLeft) {
				content.append(pageSeparator);
			}
			if (i != currentPage) {
				content.append("<a href='" + getAction(i) + "'>");
			}
			content.append(pagePre + String.valueOf(i) + pagePost);
			if (i != currentPage) {
				content.append("</a>");
			}
		}
		return content.toString();
	}

	private int getPageRangeLeft() {
		int surplus = 0;
		if (expanded) {
			surplus = currentPage - totalPage + delta;
			if (surplus < 0) {
				surplus = 0;
			}
		}
		return Math.max(currentPage - delta - surplus, 1);
	}

	private int getPageRangeRight() {
		int surplus = 0;
		if (expanded) {
			surplus = (currentPage <= delta) ? delta - currentPage + 1 : 0;
		}
		return Math.min(currentPage + delta + surplus, totalPage);
	}

	public String getBackLink() {
		int pageId = getPreviousPage();
		return (pageId > 0) ? "<a href='" + getAction(pageId) + "'>" + prevImg
				+ "</a>" : "";
	}

	public String getNextLink() {
		int pageId = getNextPage();
		return (pageId > 0 && pageId < totalPage) ? "<a href='"
				+ getAction(pageId) + "'>" + nextImg + "</a>" : "";
	}

	public String getFirstLink() {
		return isFirstPage() ? "" : "<a href='" + getAction(1) + "'>"
				+ firstImg + "</a>";
	}

	public String getLastLink() {
		return isLastPage() ? "" : "<a href='" + getAction(totalPage) + "'>"
				+ lastImg + "</a>";
	}

	public String getPreviousSlide() {
		int pageId = currentPage - delta * 2 - 1;
		return (pageId > 0) ? "<a href='" + getAction(pageId) + "'>"
				+ prevSlide + "</a>" : "";
	}

	public String getNextSlide() {
		int pageId = currentPage + delta * 2 + 1;
		return (pageId > totalPage) ? "" : "<a href='" + getAction(pageId)
				+ "'>" + nextSlide + "</a>";
	}

	public String getPageInputBox() {
		StringBuffer content = new StringBuffer();
		content.append("<form action='" + action
				+ "' method='GET' name='__pageForm' style='margin:0'>");
		content
				.append("<input name='currentPage' type='text' size='2' onkeydown='if (event.keyCode==13) {this.form.submit()}'/>");
		content.append("</form>");
		return content.toString();
	}

	public String getAction() {
		return action;
	}

	public String getAction(int currentPage) {
		return action
				+ ((action.indexOf("?") > -1) ? "&currentPage="
						: "?currentPage=") + String.valueOf(currentPage);
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getIgnoreItems() {
		return ignoreItems;
	}

	public void setIgnoreItems(int ignoreItems) {
		this.ignoreItems = ignoreItems;
	}

	public int getOffsetItems() {
		return offsetItems;
	}

	public void setOffsetItems(int offsetItems) {
		this.offsetItems = offsetItems;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
}
