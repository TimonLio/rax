package erp.model;

public class Evaluation {

	private String content;
	private String pubDate;
	private String name;
	private String explain;
	private String rank;
	private boolean haveRank;

	public Evaluation() {
		content = "";
		pubDate = "";
		name = "";
		explain = "";
		rank = "";
		haveRank = false;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public boolean isHaveRank() {
		return haveRank;
	}

	public void setHaveRank(boolean haveRank) {
		this.haveRank = haveRank;
	}
}
