package delivery.model.vo;

public class Complain {

	private String keyword;
	private String complain;

	public Complain() {
	}

	public Complain(String keyword, String complain) {
		super();
		this.keyword = keyword;
		this.complain = complain;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}

	@Override
	public String toString() {
		return this.keyword + " \t " + this.complain;
	}

}
