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

import delivery.common.ConnectionFactory;
import delivery.model.vo.Menu;
import delivery.model.vo.OrderMenu;
import delivery.model.vo.Owner;
import myException.DeliveryException;

public class OwnerDao {

	private Properties prop;
	public ArrayList<Owner> OwnerList;

	public OwnerDao() {
		prop = new Properties();

		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertOwner(Owner signUpOwner, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("insertOwner");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, signUpOwner.getStrId());
			pstmt.setString(2, signUpOwner.getCategory());
			pstmt.setString(3, signUpOwner.getStrPwd());
			pstmt.setString(4, signUpOwner.getStrAddr());
			pstmt.setString(5, signUpOwner.getStrPhone());
			pstmt.setString(6, signUpOwner.getStrName());
			pstmt.setString(7, signUpOwner.getCeoName());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Owner loginOwner(Owner loginOwner, Connection conn) {
		Owner owner = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginOwner");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginOwner.getStrId());
			pstmt.setString(2, loginOwner.getStrPwd());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				owner = new Owner();
				owner.setStrId(rset.getString("str_id"));
				owner.setCategory(rset.getString("category"));
				owner.setStrPwd(rset.getString("str_pwd"));
				owner.setStrAddr(rset.getString("str_Addr"));
				owner.setStrPhone(rset.getString("str_phone"));
				owner.setStrName(rset.getString("str_name"));
				owner.setCeoName(rset.getString("ceo_name"));
				owner.setStrNo(rset.getInt("str_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return owner;
	}
	public ArrayList<Menu> selectAllStoreMenu(Connection conn, String a) {
		ArrayList<Menu> menuList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllStoreMenu");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, a);
			rset = pstmt.executeQuery();
			menuList = new ArrayList<Menu>();

			while (rset.next()) {
				Menu menu = new Menu();
				menu.setMenuNo(rset.getInt("MENU_NO"));
				menu.setStrId(rset.getString("str_id"));
				menu.setMenuName(rset.getString("Menu_Name"));
				menu.setPrice(rset.getInt("price"));
				menu.setCategory(rset.getString("category"));
				menuList.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menuList;
	}

	public int insertMenu(Menu insertMenu, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMenu");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, insertMenu.getStrId());
			pstmt.setString(2, insertMenu.getMenuName());
			pstmt.setInt(3, insertMenu.getPrice());
			pstmt.setString(4, insertMenu.getCategory());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<OrderMenu> SelectOrderAllList(Connection conn, String strId) throws DeliveryException {
		ArrayList<OrderMenu> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("OrderAllList");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, strId);
			rset = pstmt.executeQuery();
			list = new ArrayList<OrderMenu>();

			while (rset.next()) {
				OrderMenu orderMenu = new OrderMenu();
				orderMenu.setOrderNo(rset.getInt("ORDER_NO"));
				orderMenu.setOrderDate(rset.getDate("ORDER_DATE"));
				orderMenu.setOrderAmount(rset.getInt("ORDER_AMOUNT"));
				orderMenu.setCusId(rset.getString("CUS_ID"));
				orderMenu.setDelivery(rset.getString("DELIVERY"));
				orderMenu.setPriceSum(rset.getInt("PRICE_SUM"));
		
				list.add(orderMenu);
			}
		} catch (SQLException e) {
			throw new DeliveryException("OrderAllList 메소드 처리 불가 : " + e.getMessage());
		} finally {
			ConnectionFactory.close(rset);
			ConnectionFactory.close(pstmt);
		}
		return list;
	}

	public int updateMenu(Menu modifyMenu, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("updateMenu");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, modifyMenu.getMenuName());
			pstmt.setInt(2, modifyMenu.getPrice());
			pstmt.setInt(3, modifyMenu.getMenuNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteMenu(int deleteMenuNo, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMenu");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteMenuNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int DeliveryUpdate(Connection conn, OrderMenu inputDelivery) throws DeliveryException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("DeliveryUpdate");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputDelivery.getDelivery());
			pstmt.setString(2, inputDelivery.getStrId());
			pstmt.setInt(3, inputDelivery.getOrderNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DeliveryException("DeliverUpdate 메소드 처리 불가 : " + e.getMessage());
		} finally {
			ConnectionFactory.close(pstmt);
		}
		return result;
	}

	public ArrayList<OrderMenu> SelectDateMoney(Connection conn, String a) throws DeliveryException {
		ArrayList<OrderMenu> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String query = prop.getProperty("SelectDateMoney");

		try {
			pstmt = conn.prepareStatement(query);
			// pstmt.setString(1, b);
			pstmt.setString(1, a);
			// pstmt.setString(3, b);

			rset = pstmt.executeQuery();
			list = new ArrayList<OrderMenu>();

			while (rset.next()) {

				OrderMenu orderMenu = new OrderMenu();
				// orderMenu.setOrderNo(rset.getInt("ORDER_NO"));
				orderMenu.setOrderDate(rset.getDate("ORDER_DATE"));
				// orderMenu.setOrderAmount(rset.getInt("ORDER_AMOUNT"));
				// orderMenu.setCusId(rset.getString("CUS_ID"));
				// orderMenu.setDelivery(rset.getString("DELIVERY"));
				orderMenu.setPriceSum(rset.getInt("PRICE_SUM"));
				// orderMenu.setMenuNo(rset.getInt("MENU_NO"));
				// orderMenu.setStrId(rset.getString("STR_ID"));
				list.add(orderMenu);
			}
		} catch (SQLException e) {
			throw new DeliveryException("OrderAllList 메소드 처리 불가 : " + e.getMessage());
		} finally {
			ConnectionFactory.close(rset);
			ConnectionFactory.close(pstmt);
		}
		return list;
	}



}
