package delivery.model.vo;

public class Owner {

	private String strId;
	private String category;
	private String strPwd;
	private String strAddr;
	private String strPhone;
	private String strName;
	private String ceoName;
	private int strNo;

	public Owner() {
	}

	public Owner(String strId, String category, String strPwd, String strAddr, String strPhone, String strName,
			String ceoName, int strNo) {
		super();
		this.strId = strId;
		this.category = category;
		this.strPwd = strPwd;
		this.strAddr = strAddr;
		this.strPhone = strPhone;
		this.strName = strName;
		this.ceoName = ceoName;
		this.strNo = strNo;
	}

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		this.strId = strId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStrPwd() {
		return strPwd;
	}

	public void setStrPwd(String strPwd) {
		this.strPwd = strPwd;
	}

	public String getStrAddr() {
		return strAddr;
	}

	public void setStrAddr(String strAddr) {
		this.strAddr = strAddr;
	}

	public String getStrPhone() {
		return strPhone;
	}

	public void setStrPhone(String strPhone) {
		this.strPhone = strPhone;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getCeoName() {
		return ceoName;
	}

	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}

	public int getStrNo() {
		return strNo;
	}

	public void setStrNo(int strNo) {
		this.strNo = strNo;
	}

	@Override
	public String toString() {
		return this.strId + " \t " + this.category + " \t " + this.strPwd + " \t" + this.strAddr + "\t " + this.strPhone
				+ "\t " + this.strName + "\t" + this.ceoName + " \t" + this.strNo;

	}

}