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

import delivery.model.vo.Complain;
import delivery.model.vo.Customer;
import delivery.model.vo.Owner;

public class CustomerDao {

	private Properties prop;
	public ArrayList<Customer> CustomerList;

	public CustomerDao() {
		prop = new Properties();

		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertCustomer(Customer signUpCustomer, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertCustomer");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, signUpCustomer.getCusId());
			pstmt.setString(2, signUpCustomer.getCusName());
			pstmt.setString(3, signUpCustomer.getCusPwd());
			pstmt.setString(4, signUpCustomer.getCusAddr());
			pstmt.setString(5, signUpCustomer.getCusPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Customer loginCustomer(Customer loginCustomer, Connection conn) {
		Customer customer = null;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginCustomer");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginCustomer.getCusId());
			pstmt.setString(2, loginCustomer.getCusPwd());
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
			e.printStackTrace();
		}

		return customer;
	}
	
	public int complainInsert(String ownerId, String keyword,Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		String query=prop.getProperty("complainInsert");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, ownerId);
			pstmt.setString(2, keyword);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
		
		
	}public ArrayList<Complain> keywordList(Connection conn){
		ArrayList<Complain> list=null;
		Statement stmt=null;
		ResultSet rset=null;
		String query=prop.getProperty("complainPrint");
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(query);
			
			list=new ArrayList<Complain> ();
			while(rset.next()) {
			Complain complain=new Complain();
			complain.setKeyword(rset.getString("KEYWORD"));
			complain.setComplain(rset.getString("COMPLAIN"));	
			list.add(complain);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}

}
