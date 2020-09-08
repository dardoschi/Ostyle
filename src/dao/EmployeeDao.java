package dao;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Controller;
import orders.Item;
import people.Employee;

public class EmployeeDao {
	
	private Connection conn = DBConnection.getDBConnection();
	
	public Employee Login(String Username, String Password) {
		PreparedStatement st;
		try {
			st = conn.prepareStatement("SELECT * from employees WHERE username = ? and password = ?");
				 st.setString(1, Username);
				 st.setString(2, Password);
			     ResultSet rs = st.executeQuery(); 
			     // if this account exist returns true else returns false 
			     if ( rs.next() ) {    	
			    	 Employee user = new Employee(rs.getString("name"),rs.getString("surname"),rs.getString("username"),rs.getString("password"),rs.getInt("codi"), rs.getBoolean("admin"));
			    	 return user;
			    	}
			     else 
			    	 return null;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	//register user
	public boolean RegisterNewEmployee(String Username, String Password, boolean Admin, String Name, String Surname) {
		PreparedStatement st;
		try {
				st = conn.prepareStatement("insert into employees(username, password, admin, name, surname) values(?,?,?,?,?);");
				st.setString(1, Username);
				st.setString(2, Password);
				st.setBoolean(3, Admin);
				st.setString(4, Name);
				st.setString(5, Surname);
				ResultSet rs = st.executeQuery();
				return true;
		}
		catch (SQLException e) {
			String Error = e.getMessage();
			if(Error.contains("valore chiave duplicato") || Error.contains("duplicate")) {
				return false;
			}else {
					return true;
			}
		}
	}
	
	public void CreateSoldInStore(int CodI, Item i) {
		PreparedStatement st;
		try {
			st = conn.prepareStatement("insert into soldinstore(values(?, ?, ?, current_date))");
			st.setInt(1, CodI);
			st.setInt(2, i.getId());
			st.setInt(3, i.getInCart());
			ResultSet rs = st.executeQuery();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	 
	public String getEmplName(int CodI) {
		PreparedStatement st;
		String name = new String();
		try {
			st = conn.prepareStatement("select username from employees where codi = ?");
			st.setInt(1, CodI);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				name = rs.getString("username");
			}
			return name;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
