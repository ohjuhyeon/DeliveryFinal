package delivery.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import delivery.common.ConnectionFactory;
import delivery.model.dao.AdminDao;
import delivery.model.dao.OwnerDao;
import delivery.model.vo.Menu;
import delivery.model.vo.OrderMenu;
import delivery.model.vo.Owner;
import myException.DeliveryException;

public class OwnerService {

	private ConnectionFactory factory;

	public OwnerService() {

		factory = ConnectionFactory.getInstance();

	}

	public int insertOwner(Owner signUpOwner) {

		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new OwnerDao().insertOwner(signUpOwner, conn);

			if (result > 0)
				factory.commit(conn);
			else
				factory.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Owner loginOwner(Owner loginOwner) {
		Connection conn = null;
		Owner owner = null;
		try {
			conn = factory.createConnection();
			owner = new OwnerDao().loginOwner(loginOwner, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return owner;

	}
	public ArrayList<Menu> selectAllStoreMenu(String a) {
		Connection conn = null;
		ArrayList<Menu> menuList = null;

		try {
			conn = factory.createConnection();
			menuList = new OwnerDao().selectAllStoreMenu(conn, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}

	public int insertMenu(Menu insertMenu) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new OwnerDao().insertMenu(insertMenu, conn);

			if (result > 0)
				factory.commit(conn);
			else
				factory.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<OrderMenu> SelectOrderAllList(String strId) throws DeliveryException {
		ArrayList<OrderMenu> list = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			list = new OwnerDao().SelectOrderAllList(conn, strId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}

	public int updateMenu(Menu modifyMenu) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new OwnerDao().updateMenu(modifyMenu, conn);

			if (result > 0)
				factory.commit(conn);
			else
				factory.rollback(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public int deleteMenu(int deleteMenuNo) {
		Connection conn = null;
		int result = 0;
		if (result > 0) {
			factory.commit(conn);
		} else {
			factory.rollback(conn);
		}
		try {
			conn = factory.createConnection();
			result = new OwnerDao().deleteMenu(deleteMenuNo, conn);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int DeliveryUpdate(OrderMenu inputDelivery) throws DeliveryException {
		int result = 0;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			result = new OwnerDao().DeliveryUpdate(conn, inputDelivery);

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

	public ArrayList<OrderMenu> SelectDateMoney(String a) throws DeliveryException {
		ArrayList<OrderMenu> list = null;
		Connection conn = null;

		try {
			conn = factory.createConnection();
			list = new OwnerDao().SelectDateMoney(conn, a);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			factory.close(conn);
		}
		return list;
	}

}
