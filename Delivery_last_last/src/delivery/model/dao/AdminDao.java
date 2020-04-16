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
import delivery.model.vo.BlackList;
import delivery.model.vo.Customer;
import delivery.model.vo.Menu;
import delivery.model.vo.OrderMenu;
import delivery.model.vo.Owner;
import myException.DeliveryException;

public class AdminDao {

	private Properties prop;
	public ArrayList<Owner> ownerList;

	public AdminDao() {
		prop = new Properties();

		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Owner> selectAllStore(Connection conn) {

		ArrayList<Owner> ownerList = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllStore");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			ownerList = new ArrayList<Owner>();

			while (rset.next()) {
				Owner owner = new Owner();
				owner.setStrId(rset.getString("str_id"));
				owner.setCategory(rset.getString("category"));
				owner.setStrPwd(rset.getString("str_pwd"));
				owner.setStrAddr(rset.getString("str_Addr"));
				owner.setStrPhone(rset.getString("str_phone"));
				owner.setStrName(rset.getString("str_name"));
				owner.setCeoName(rset.getString("ceo_name"));
				owner.setStrNo(rset.getInt("str_no"));
				ownerList.add(owner);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ownerList;
	}

	public Owner searchStore(int inputStoreNo, Connection conn) {
		Owner owner = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchStore");

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inputStoreNo);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return owner;
	}

	public int deleteStore(int deleteStoreNo, Connection conn) {

		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("deleteStore");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteStoreNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public ArrayList<Menu> selectAllMenu(Connection conn) {

		ArrayList<Menu> menuList = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllMenu");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			menuList = new ArrayList<Menu>();

			while (rset.next()) {
				Menu menu = new Menu();
				menu.setStrId(rset.getString("str_id"));
				menu.setMenuName(rset.getString("Menu_Name"));
				menu.setPrice(rset.getInt("price"));
				menu.setCategory(rset.getString("category"));
				menu.setMenuNo(rset.getInt("menu_no"));
				menuList.add(menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menuList;
	}

	public int insertBlackList(BlackList insertBlackList, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("insertBlackList");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, insertBlackList.getStrId());
			pstmt.setString(2, insertBlackList.getKeyword());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int deleteBlackList(int deleteBlackListNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("deleteBlackList");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, deleteBlackListNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Customer> selectAllCus(Connection conn) {
		ArrayList<Customer> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllCus");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<Customer>();

			while (rset.next()) {
				Customer customer = new Customer();
				customer.setCusId(rset.getString("CUS_ID"));
				customer.setCusName(rset.getString("CUS_NAME"));
				customer.setCusPwd(rset.getString("CUS_PWD"));
				customer.setCusPhone(rset.getString("CUS_PHONE"));
				customer.setCusAddr(rset.getString("CUS_ADDR"));
				customer.setCusNo(rset.getInt("CUS_NO"));
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public Customer searchByNo(Connection conn, int inputByNo) throws DeliveryException {

		Customer customer = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("searchByNo");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, inputByNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				customer = new Customer();
				customer.setCusId(rset.getString("CUS_ID"));
				customer.setCusName(rset.getString("CUS_NAME"));
				customer.setCusPwd(rset.getString("CUS_PWD"));
				customer.setCusPhone(rset.getString("CUS_PHONE"));
				customer.setCusAddr(rset.getString("CUS_ADDR"));
				customer.setCusNo(rset.getInt("CUS_NO"));
			}
		} catch (SQLException e) {
			throw new DeliveryException("searchByNo() 메소드 처리 불가 : " + e.getMessage());
		}
		return customer;
	}

	public ArrayList<OrderMenu> selectAllOrder(Connection conn) throws DeliveryException {
		ArrayList<OrderMenu> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllOrder");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<OrderMenu>();

			while (rset.next()) {
				OrderMenu order = new OrderMenu();
				order.setOrderDate(rset.getDate("ORDER_DATE"));
				order.setOrderNo(rset.getInt("ORDER_NO"));
				order.setOrderAmount(rset.getInt("ORDER_AMOUNT"));
				order.setMenuNo(rset.getInt("MENU_NO"));
				order.setCusId(rset.getString("CUS_ID"));
				order.setStrId(rset.getString("STR_ID"));
				order.setDelivery(rset.getString("DELIVERY"));
				order.setPriceSum(rset.getInt("PRICE_SUM"));
				list.add(order);
			}

		} catch (SQLException e) {
			throw new DeliveryException("selectAllOrder메소드 처리불가 : " + e.getMessage());
		} finally {
			ConnectionFactory.close(rset);
			ConnectionFactory.close(stmt);
		}
		return list;
	}

	// 빛나==========================================
	public ArrayList<BlackList> complainSelectAll(Connection conn) throws DeliveryException {
		ArrayList<BlackList> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("complainList");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<BlackList>();

			while (rset.next()) {
				BlackList bL = new BlackList();
				bL.setStrId(rset.getString("STR_ID"));
				bL.setKeyword(rset.getString("KEYWORD"));
				bL.setBlNo(rset.getInt("BL_NO"));
				bL.setAdmin(rset.getString("admin"));
				list.add(bL);
			}

		} catch (SQLException e) {
			throw new DeliveryException("complainList 메소드 실패" + e.getMessage());
		}

		return list;
	}

	public int complainUpdate(int checkNo, String decision, Connection conn) throws DeliveryException {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("complainUpdate");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, decision);
			pstmt.setInt(2, checkNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DeliveryException("complainUpdate 메소드 실패" + e.getMessage());
		}

		return result;
	}

	public ArrayList<BlackList> blackListSelectApplyAll(Connection conn) throws DeliveryException {
		ArrayList<BlackList> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("blackListSelectApplyAll");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<BlackList>();
			while (rset.next()) {
				BlackList bL = new BlackList();

				bL.setStrId(rset.getString("STR_ID"));
				bL.setKeyword(rset.getString("KEYWORD"));
				bL.setBlNo(rset.getInt("BL_NO"));
				bL.setAdmin(rset.getString("admin"));
				list.add(bL);

			}

		} catch (SQLException e) {
			throw new DeliveryException("blackListSelectApplyAll 메소드 실패 ->" + e.getMessage());
		}

		return list;
	}

	public ArrayList<BlackList> blackListSelectAll(Connection conn) throws DeliveryException {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<BlackList> list = null;
		String query = prop.getProperty("blackListSelectAll");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			list = new ArrayList<BlackList>();
			while (rset.next()) {
				BlackList bL = new BlackList();

				bL.setStrId(rset.getString("STR_ID"));
				bL.setKeyword(rset.getString("KEYWORD"));
				bL.setBlNo(rset.getInt("BL_NO"));
				bL.setAdmin(rset.getString("admin"));
				list.add(bL);

			}

		} catch (SQLException e) {
			throw new DeliveryException("blackListSelectAll 메소드 실패 ->" + e.getMessage());
		}

		return list;

	}

	public ArrayList<OrderMenu> rankCheck(Connection conn) {
		ArrayList<OrderMenu> list = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("rankCheck");

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			list = new ArrayList<OrderMenu>();
			while (rset.next()) {
				OrderMenu oM = new OrderMenu();
				oM.setRank(rset.getInt("RANKING"));
				oM.setStrId(rset.getString("STR_ID"));
				oM.setSale(rset.getInt("ALLSUM"));
				list.add(oM);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
