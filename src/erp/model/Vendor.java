package erp.model;

public class Vendor {

	private int id;
	private String title;
	private String name;
	private String phone1;
	private String phone2;
	private String address;
	private String IM;
	private int IMType;
	private String comment;

	public Vendor() {
		id = 0;
		title = "";
		name = "";
		phone1 = "";
		phone2 = "";
		address = "";
		IM = "";
		IMType = 0;
		comment = "";		
	}

	public String getAddress() {
		return address;
	}

	public String getComment() {
		return comment;
	}

	public int getId() {
		return id;
	}

	public String getIM() {
		return IM;
	}

	public int getIMType() {
		return IMType;
	}

	public String getName() {
		return name;
	}

	public String getPhone1() {
		return phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getTitle() {
		return title;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIM(String im) {
		IM = im;
	}

	public void setIMType(int type) {
		IMType = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
