package delivery.controller;



import java.util.ArrayList;

import delivery.model.service.OrderService;
import delivery.model.vo.OrderMenu;
import delivery.view.DeliveryMenu;

public class OrderController {

	public void insertOrder(OrderMenu order) {
		DeliveryMenu menu = new DeliveryMenu();
		int result = new OrderService().insertOrder(order);
		
		if(result > 0) {
			menu.displaySuccess("장바구니에 추가");
		}else {
			menu.displayError("장바구니에 넣기 실패");
		}
	}
	
	public void delectOrder(int orderNumber) {
		DeliveryMenu menu = new DeliveryMenu();
		int result = new OrderService().deleteOrder(orderNumber);
		
		if(result > 0) {
			menu.displaySuccess("장바구니에서 삭제");
		}else {
			menu.displayError("장바구니에서 삭제 실패");
		}
	}
	
	public void lastOrder() {
		DeliveryMenu menu = new DeliveryMenu();
		int result = new OrderService().lastOrder();
		
		if(result > 0) {
			menu.displaySuccess("배달 중");
		}else {
			menu.displayError("배달 실패");
		}
	}
	
	public void orderPrintAll(String userID) {
	      
	      DeliveryMenu menu = new DeliveryMenu();
	      ArrayList<OrderMenu> list = new OrderService().orderPrintAll(userID);
	      
	      if(!list.isEmpty()) { 
	         menu.displayOrderList(list); 
	      }else {
	         menu.displayError("주문 조회 실패");
	      }
	   }

	   public void orderListPrintAll(String userID) {
		   DeliveryMenu menu = new DeliveryMenu();
		      ArrayList<OrderMenu> list = new OrderService().orderListPrintAll(userID);
		      
		      if(!list.isEmpty()) { 
		         menu.displayOrderList(list); 
		      }else {
		         menu.displayError("주문 조회 실패");
		      }
		   }

}

