package delivery.model.vo;

import java.sql.Date;

public class OrderMenu {

   private Date orderDate;
   private int orderNo;
   private int orderAmount;
   private int menuNo;
   private String cusId;
   private String strId;
   private String delivery;
   private int priceSum;
   private String strName;
   private String menuName;
   private int price;
   private int rank;
   private int sale;

   public int getRank() {
	return rank;
}

public void setRank(int rank) {
	this.rank = rank;
}

public int getSale() {
	return sale;
}

public void setSale(int sale) {
	this.sale = sale;
}

public OrderMenu() {
   }

   public OrderMenu(Date orderDate, int orderNo, int orderAmount, int menuNo, String cusId, String strId,
         String delivery, int priceSum) {
      super();
      this.orderDate = orderDate;
      this.orderNo = orderNo;
      this.orderAmount = orderAmount;
      this.menuNo = menuNo;
      this.cusId = cusId;
      this.strId = strId;
      this.delivery = delivery;
      this.priceSum = priceSum;
   }

   public Date getOrderDate() {
      return orderDate;
   }

   public void setOrderDate(Date orderDate) {
      this.orderDate = orderDate;
   }

   public int getOrderNo() {
      return orderNo;
   }

   public void setOrderNo(int orderNo) {
      this.orderNo = orderNo;
   }

   public int getOrderAmount() {
      return orderAmount;
   }

   public void setOrderAmount(int orderAmount) {
      this.orderAmount = orderAmount;
   }

   public int getMenuNo() {
      return menuNo;
   }

   public void setMenuNo(int menuNo) {
      this.menuNo = menuNo;
   }

   public String getCusId() {
      return cusId;
   }

   public void setCusId(String cusId) {
      this.cusId = cusId;
   }

   public String getStrId() {
      return strId;
   }

   public void setStrId(String strId) {
      this.strId = strId;
   }

   public String getDelivery() {
      return delivery;
   }

   public void setDelivery(String delivery) {
      this.delivery = delivery;
   }

   public int getPriceSum() {
      return priceSum;
   }

   public void setPriceSum(int priceSum) {
      this.priceSum = priceSum;
   }

   public String getStrName() {
      return strName;
   }

   public void setStrName(String strName) {
      this.strName = strName;
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

   @Override
   public String toString() {
      return this.orderNo + "\t " + this.strName + "\t " + this.menuName + "\t" + this.price + "\t " + this.orderAmount
            + "\t" + this.orderDate;
   }
   
   public String toToString() {
	   return this.rank+"\t"+this.strId+"\t"+this.sale;
   }
	public String printString() {

		return this.orderDate + "\t " + this.priceSum;

	}
	
	public String toString2() {
		return this.orderNo +"\t " + this.orderDate + "\t " + this.cusId + "\t " + this.priceSum + "\t " + this.orderAmount
				+ "\t " + this.delivery + "\t ";
	}

}