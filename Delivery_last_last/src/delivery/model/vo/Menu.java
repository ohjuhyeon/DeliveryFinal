package delivery.model.vo;

public class Menu {

	private int menuNo;
	private String strId;
	private String menuName;
	private int price;
	private String category;

	public Menu() {
	}

	public Menu(int menuNo, String strId, String menuName, int price, String category) {
		super();
		this.menuNo = menuNo;
		this.strId = strId;
		this.menuName = menuName;
		this.price = price;
		this.category = category;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		this.strId = strId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return this.menuNo + "\t " + this.strId + "\t " + this.menuName + "\t " + this.price + "\t " + this.category;
	}

}
