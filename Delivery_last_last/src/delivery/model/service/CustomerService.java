package delivery.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import delivery.common.ConnectionFactory;
import delivery.model.dao.CustomerDao;
import delivery.model.dao.OwnerDao;
import delivery.model.vo.Complain;
import delivery.model.vo.Customer;
import delivery.model.vo.Owner;

public class CustomerService {
	private ConnectionFactory factory;

	public CustomerService() {

		factory = ConnectionFactory.getInstance();

	}

	public int insertCustomer(Customer signUpCustomer) {
		Connection conn = null;
		int result = 0;

		try {
			conn = factory.createConnection();
			result = new CustomerDao().insertCustomer(signUpCustomer, conn);

			if (result > 0)
				factory.commit(conn);
			else
				factory.rollback(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Customer loginCustomer(Customer loginCustomer) {
		Connection conn = null;
		Customer customer = null;
		try {
			conn = factory.createConnection();
			customer = new CustomerDao().loginCustomer(loginCustomer, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}
	
	public int complainInsert(String ownerId, String keyword) {
		Connection conn = null;
		int result=0;
		
		try {
			conn=factory.createConnection();
			result=new CustomerDao().complainInsert(ownerId,keyword,conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public ArrayList<Complain> keywordList(){
		Connection conn=null;
		ArrayList<Complain>list=null;
		
		try {
			conn=factory.createConnection();
			list=new CustomerDao().keywordList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
