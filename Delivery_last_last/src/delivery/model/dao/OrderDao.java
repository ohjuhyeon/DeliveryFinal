package delivery.model.dao;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import delivery.model.vo.OrderMenu;

public class OrderDao {

	private Properties prop;

	public OrderDao() {

		prop = new Properties();

		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public int insertOrder(OrderMenu order, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("insertOrder");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, order.getOrderAmount());
			pstmt.setString(2, order.getCusId());
			pstmt.setString(3, order.getStrId());
			pstmt.setInt(4, order.getMenuNo());
			pstmt.setInt(5, order.getOrderAmount());
			pstmt.setInt(6, order.getMenuNo());
			pstmt.setString(7, order.getStrId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public int deleteOrder(int orderNumber, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("deleteOrder");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNumber);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	public int lastOrder(Connection conn) {
		int result = 0;
		PreparedStatement pstmt= null;

		String query = prop.getProperty("lastOrder");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "Y");
			result =pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	   public ArrayList<OrderMenu> orderPrintAll(Connection conn, String userID) {

		      ArrayList<OrderMenu> list = null;

		      PreparedStatement pstmt = null;
		      ResultSet rset = null;
		      String query = prop.getProperty("selectAllOrder2");

		      try {
		         pstmt = conn.prepareStatement(query);
		         pstmt.setString(1, userID);
		         rset = pstmt.executeQuery();
		         
		         list = new ArrayList<OrderMenu>();

		         while (rset.next()) {
		            OrderMenu order = new OrderMenu();
		            order.setOrderNo(rset.getInt("ORDER_NO"));
		            order.setStrName(rset.getString("STR_NAME"));
		            order.setMenuName(rset.getString("MENU_NAME"));
		            order.setPrice(rset.getInt("PRICE_SUM"));
		            order.setOrderAmount(rset.getInt("ORDER_AMOUNT"));
		            order.setOrderDate(rset.getDate("ORDER_DATE"));
		            
		            list.add(order);
		         }

		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      } finally {
		         try {
		            pstmt.close();
		            rset.close();
		         } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		         }
		      }

		      return list;
		   }

		   public ArrayList<OrderMenu> orderListPrintAll(Connection conn, String userID) {
			   ArrayList<OrderMenu> list = null;

			      PreparedStatement pstmt = null;
			      ResultSet rset = null;
			      String query = prop.getProperty("selectAllOrder1");

			      try {
			         pstmt = conn.prepareStatement(query);
			         pstmt.setString(1, userID);
			         rset = pstmt.executeQuery();
			         
			         list = new ArrayList<OrderMenu>();

			         while (rset.next()) {
			            OrderMenu order = new OrderMenu();
			            order.setOrderNo(rset.getInt("ORDER_NO"));
			            order.setStrName(rset.getString("STR_NAME"));
			            order.setMenuName(rset.getString("MENU_NAME"));
			            order.setPrice(rset.getInt("PRICE_SUM"));
			            order.setOrderAmount(rset.getInt("ORDER_AMOUNT"));
			            order.setOrderDate(rset.getDate("ORDER_DATE"));
			            
			            list.add(order);
			         }

			      } catch (SQLException e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      } finally {
			         try {
			            pstmt.close();
			            rset.close();
			         } catch (SQLException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			         }
			      }

			      return list;
			   }
	

}

