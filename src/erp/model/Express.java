package erp.model;

import java.util.Date;

public class Express {
	
	/*
	 * 坐标系X向右, Y向上, 测量单位为毫米
	 * 可以用index.html上的输入框换算为pt 
	 * 如设置ExpressPos 对象为null, 可不打印该项 
	 * 英文/数字内容使用10号字体 
	 * 普通汉字内容使用11号字体 
	 * 收件人姓名使用12号字体
	 * 
	 * Size 标准快递单尺寸 232 127 
	 * Date 发件日期 
	 * SrcPhone 发件人电话
	 * SrcPostCode 发件人邮编
	 * DstPhone1 收件人电话1, 移动电话 
	 * DstPhone2 收件人电话2, 固定电话, 两个电话上下排开行高取13pt/5mm
	 * DstPostCode 收件人邮编 
	 * SrcName 发件人姓名 
	 * DstName 收件人姓名 
	 * SrcAddressLB 发件人地址框左下角
	 * SrcAddressRT 发件人地址框右上角 
	 * SrcAddressIndent 发件人地址缩进 
	 * DstAddressLB 收件人地址框左下角, 备注会换行打印在收件人地址后面 
	 * DstAddressRT 收件人地址框右上角 
	 * DstAddressIndent 收件人地址缩进
	 */
	
	private int id;
	private String exName;
	private String phone;
	private Date settleDate;
	private int tplSizeX;
	private int tplSizeY;
	private int tplDateX;
	private int tplDateY;
	private String tplDateFmt;
	private int tplSrcNameX;
	private int tplSrcNameY;
	private int tplSrcPhoneX;
	private int tplSrcPhoneY;
	private int tplSrcZipX;
	private int tplSrcZipY;
	private int tplSrcAddrLBX;
	private int tplSrcAddrLBY;
	private int tplSrcAddrRTX;
	private int tplSrcAddrRTY;
	private int tplSrcAddrIndent;
	private int tplDstNameX;
	private int tplDstNameY;
	private int tplDstPhone1X;
	private int tplDstPhone1Y;
	private int tplDstPhone2X;
	private int tplDstPhone2Y;
	private int tplDstZipX;
	private int tplDstZipY;
	private int tplDstAddrLBX;
	private int tplDstAddrLBY;
	private int tplDstAddrRTX;
	private int tplDstAddrRTY;
	private int tplDstAddrIndent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public int getTplSizeX() {
		return tplSizeX;
	}

	public void setTplSizeX(int tplSizeX) {
		this.tplSizeX = tplSizeX;
	}

	public int getTplSizeY() {
		return tplSizeY;
	}

	public void setTplSizeY(int tplSizeY) {
		this.tplSizeY = tplSizeY;
	}

	public int getTplDateX() {
		return tplDateX;
	}

	public void setTplDateX(int tplDateX) {
		this.tplDateX = tplDateX;
	}

	public int getTplDateY() {
		return tplDateY;
	}

	public void setTplDateY(int tplDateY) {
		this.tplDateY = tplDateY;
	}

	public String getTplDateFmt() {
		return tplDateFmt;
	}

	public void setTplDateFmt(String tplDateFmt) {
		this.tplDateFmt = tplDateFmt;
	}

	public int getTplSrcNameX() {
		return tplSrcNameX;
	}

	public void setTplSrcNameX(int tplSrcNameX) {
		this.tplSrcNameX = tplSrcNameX;
	}

	public int getTplSrcNameY() {
		return tplSrcNameY;
	}

	public void setTplSrcNameY(int tplSrcNameY) {
		this.tplSrcNameY = tplSrcNameY;
	}

	public int getTplSrcPhoneX() {
		return tplSrcPhoneX;
	}

	public void setTplSrcPhoneX(int tplSrcPhoneX) {
		this.tplSrcPhoneX = tplSrcPhoneX;
	}

	public int getTplSrcPhoneY() {
		return tplSrcPhoneY;
	}

	public void setTplSrcPhoneY(int tplSrcPhoneY) {
		this.tplSrcPhoneY = tplSrcPhoneY;
	}

	public int getTplSrcZipX() {
		return tplSrcZipX;
	}

	public void setTplSrcZipX(int tplSrcZipX) {
		this.tplSrcZipX = tplSrcZipX;
	}

	public int getTplSrcZipY() {
		return tplSrcZipY;
	}

	public void setTplSrcZipY(int tplSrcZipY) {
		this.tplSrcZipY = tplSrcZipY;
	}

	public int getTplSrcAddrLBX() {
		return tplSrcAddrLBX;
	}

	public void setTplSrcAddrLBX(int tplSrcAddrLBX) {
		this.tplSrcAddrLBX = tplSrcAddrLBX;
	}

	public int getTplSrcAddrLBY() {
		return tplSrcAddrLBY;
	}

	public void setTplSrcAddrLBY(int tplSrcAddrLBY) {
		this.tplSrcAddrLBY = tplSrcAddrLBY;
	}

	public int getTplSrcAddrRTX() {
		return tplSrcAddrRTX;
	}

	public void setTplSrcAddrRTX(int tplSrcAddrRTX) {
		this.tplSrcAddrRTX = tplSrcAddrRTX;
	}

	public int getTplSrcAddrRTY() {
		return tplSrcAddrRTY;
	}

	public void setTplSrcAddrRTY(int tplSrcAddrRTY) {
		this.tplSrcAddrRTY = tplSrcAddrRTY;
	}

	public int getTplSrcAddrIndent() {
		return tplSrcAddrIndent;
	}

	public void setTplSrcAddrIndent(int tplSrcAddrIndent) {
		this.tplSrcAddrIndent = tplSrcAddrIndent;
	}

	public int getTplDstNameX() {
		return tplDstNameX;
	}

	public void setTplDstNameX(int tplDstNameX) {
		this.tplDstNameX = tplDstNameX;
	}

	public int getTplDstNameY() {
		return tplDstNameY;
	}

	public void setTplDstNameY(int tplDstNameY) {
		this.tplDstNameY = tplDstNameY;
	}

	public int getTplDstPhone1X() {
		return tplDstPhone1X;
	}

	public void setTplDstPhone1X(int tplDstPhone1X) {
		this.tplDstPhone1X = tplDstPhone1X;
	}

	public int getTplDstPhone1Y() {
		return tplDstPhone1Y;
	}

	public void setTplDstPhone1Y(int tplDstPhone1Y) {
		this.tplDstPhone1Y = tplDstPhone1Y;
	}

	public int getTplDstPhone2X() {
		return tplDstPhone2X;
	}

	public void setTplDstPhone2X(int tplDstPhone2X) {
		this.tplDstPhone2X = tplDstPhone2X;
	}

	public int getTplDstPhone2Y() {
		return tplDstPhone2Y;
	}

	public void setTplDstPhone2Y(int tplDstPhone2Y) {
		this.tplDstPhone2Y = tplDstPhone2Y;
	}

	public int getTplDstZipX() {
		return tplDstZipX;
	}

	public void setTplDstZipX(int tplDstZipX) {
		this.tplDstZipX = tplDstZipX;
	}

	public int getTplDstZipY() {
		return tplDstZipY;
	}

	public void setTplDstZipY(int tplDstZipY) {
		this.tplDstZipY = tplDstZipY;
	}

	public int getTplDstAddrLBX() {
		return tplDstAddrLBX;
	}

	public void setTplDstAddrLBX(int tplDstAddrLBX) {
		this.tplDstAddrLBX = tplDstAddrLBX;
	}

	public int getTplDstAddrLBY() {
		return tplDstAddrLBY;
	}

	public void setTplDstAddrLBY(int tplDstAddrLBY) {
		this.tplDstAddrLBY = tplDstAddrLBY;
	}

	public int getTplDstAddrRTX() {
		return tplDstAddrRTX;
	}

	public void setTplDstAddrRTX(int tplDstAddrRTX) {
		this.tplDstAddrRTX = tplDstAddrRTX;
	}

	public int getTplDstAddrRTY() {
		return tplDstAddrRTY;
	}

	public void setTplDstAddrRTY(int tplDstAddrRTY) {
		this.tplDstAddrRTY = tplDstAddrRTY;
	}

	public int getTplDstAddrIndent() {
		return tplDstAddrIndent;
	}

	public void setTplDstAddrIndent(int tplDstAddrIndent) {
		this.tplDstAddrIndent = tplDstAddrIndent;
	}

}
