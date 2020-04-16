package delivery.model.vo;

public class Customer {

	private String cusId;
	private String cusName;
	private String cusPwd;
	private String cusAddr;
	private String cusPhone;
	private int cusNo;

	public Customer() {
	}

	public Customer(String cusId, String cusName, String cusPwd, String cusAddr, String cusPhone, int cusNo) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusPwd = cusPwd;
		this.cusAddr = cusAddr;
		this.cusPhone = cusPhone;
		this.cusNo = cusNo;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPwd() {
		return cusPwd;
	}

	public void setCusPwd(String cusPwd) {
		this.cusPwd = cusPwd;
	}

	public String getCusAddr() {
		return cusAddr;
	}

	public void setCusAddr(String cusAddr) {
		this.cusAddr = cusAddr;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public int getCusNo() {
		return cusNo;
	}

	public void setCusNo(int cusNo) {
		this.cusNo = cusNo;
	}
	@Override
	public String toString() {
		return this.cusId + " \t " + this.cusName + " \t " + this.cusPwd + " \t " + this.cusAddr + " \t " + this.cusPhone
				+ "\t" + this.cusNo;

	}
}
