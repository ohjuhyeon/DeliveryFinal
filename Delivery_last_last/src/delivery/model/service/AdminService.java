package delivery.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import delivery.common.ConnectionFactory;
import delivery.model.dao.AdminDao;
import delivery.model.vo.BlackList;
import delivery.model.vo.Customer;
import delivery.model.vo.Menu;
import delivery.model.vo.OrderMenu;
import delivery.model.vo.Owner;
import myException.DeliveryException;

public class AdminService {

	public ConnectionFactory factory;

	public AdminService() {

		factory = ConnectionFactory.getInstance();

	}

	public ArrayList<Owner> selectAllStore() {

		Connection conn = null;
		ArrayList<Owner> ownerList = null;

		try {
			conn = factory.createConnection();
			ownerList = new AdminDao().selectAllStore(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ownerList;
	}

	public Owner searchStore(int inputStoreNo) {
		Connection conn = null;
		Owner owner = null;
		try {
			conn = factory.createConnection();
			owner = new AdminDao().searchStore(inputStoreNo, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return owner;
	}

	public int deleteStore(int deleteStoreNo) {

		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new AdminDao().deleteStore(deleteStoreNo, conn);

			if (result > 0)
				factory.commit(conn);
			else
				factory.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	public ArrayList<Menu> selectAllMenu() {
		Connection conn = null;
		ArrayList<Menu> menuList = null;

		try {
			conn = factory.createConnection();
			menuList = new AdminDao().selectAllMenu(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}

	public ArrayList<Customer> selectAllCus() {

		Connection conn = null;
		ArrayList<Customer> list = null;
		try {
			conn = factory.createConnection();
			list = new AdminDao().selectAllCus(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}

	public Customer searchByNo(int inputByNo) throws DeliveryException {
		Customer customer = new Customer();
		Connection conn = null;

		try {
			conn = factory.createConnection();
			customer = new AdminDao().searchByNo(conn, inputByNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return customer;
	}

	public ArrayList<OrderMenu> selectAllOrder() throws DeliveryException {
		ArrayList<OrderMenu> list = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			list = new AdminDao().selectAllOrder(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}
	
	
	
//=============================================
	public int insertBlackList(BlackList insertBlackList) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new AdminDao().insertBlackList(insertBlackList, conn);

			if (result > 0)
				factory.commit(conn);
			else
				factory.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteBlackList(int deleteBlackListNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new AdminDao().deleteBlackList(deleteBlackListNo, conn);

			if (result > 0)
				factory.commit(conn);
			else
				factory.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return result;
	}

	// 빛나==============================
	public ArrayList<BlackList> complainSelectAll() throws DeliveryException {
		Connection conn = null;
		ArrayList<BlackList> list = null;

		try {
			conn = factory.createConnection();
			list = new AdminDao().complainSelectAll(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;

	}

	public int complainUpdate(int checkNo, String decision) throws DeliveryException {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new AdminDao().complainUpdate(checkNo, decision, conn);

			if (result > 0) {
				factory.commit(conn);
			} else {
				factory.rollback(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return result;

	}

	public ArrayList<BlackList> blackListSelectApplyAll() throws DeliveryException {
		Connection conn = null;
		ArrayList<BlackList> list = null;

		try {
			conn = factory.createConnection();
			list = new AdminDao().blackListSelectApplyAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public ArrayList<BlackList> blackListSelectAll() throws DeliveryException {
		ArrayList<BlackList> list = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			list = new AdminDao().blackListSelectAll(conn);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}

	public ArrayList<OrderMenu> rankCheck() {
		Connection conn = null;
		ArrayList<OrderMenu> list = null;

		try {
			conn = factory.createConnection();
			list = new AdminDao().rankCheck(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}

		return list;
	}

}
