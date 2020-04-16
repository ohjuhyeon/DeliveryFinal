package delivery.model.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import delivery.common.ConnectionFactory;
import delivery.model.dao.OrderDao;
import delivery.model.vo.OrderMenu;

public class OrderService {

	private ConnectionFactory factory;
	
	public OrderService() {
		factory = ConnectionFactory.getInstance();
	}
	
	public ArrayList<OrderMenu> orderPrintAll(String userID){
	      
	      Connection conn = null;
	      ArrayList<OrderMenu> list = null;
	      try {
	         conn = factory.createConnection();
	         list = new OrderDao().orderPrintAll(conn, userID);
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         factory.close(conn);
	      }
	      return list;
	   }

	   public ArrayList<OrderMenu> orderListPrintAll(String userID) {
		    Connection conn = null;
		      ArrayList<OrderMenu> list = null;
		      try {
		         conn = factory.createConnection();
		         list = new OrderDao().orderListPrintAll(conn, userID);
		         
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }finally {
		         factory.close(conn);
		      }
		      return list;
		   }
	
	public int insertOrder(OrderMenu order) {
		Connection conn = null;
		int result = 0;
		 try {
			conn = factory.createConnection();
			result = new OrderDao().insertOrder(order, conn);
			
			if( result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			factory.close(conn);
		}
		 return result;
	}
	
	public int deleteOrder(int orderNumber) {
		Connection conn = null;
		int result = 0;
		 try {
			conn = factory.createConnection();
			result = new OrderDao().deleteOrder(orderNumber, conn);
			
			if( result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			factory.close(conn);
		}
		 return result;
	}
	
	public int lastOrder() {
		Connection conn = null;
		int result = 0;
		 try {
			conn = factory.createConnection();
			result = new OrderDao().lastOrder(conn);
			
			if( result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			factory.close(conn);
		}
		 return result;
	}

	
}
