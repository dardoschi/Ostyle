package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orders.Order;

public class OrdersDao {

	private Connection conn = DBConnection.getDBConnection();
	
	//insert into orders values(null,1,23.5,current_date)
	public int CreateOrder(int CodU, double TotalPrice) {
		PreparedStatement st;
		int CodO = -1;
		try {
				st = conn.prepareStatement("insert into orders (codu, totalprice, date) values (?,?,current_date) returning codo;");
				st.setInt(1, CodU);
				st.setDouble(2, TotalPrice);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					CodO = (rs.getInt("codo"));
					return CodO;
				}
				
		}catch(SQLException e3) {
			e3.printStackTrace();
			return -1;
		}
		return CodO;
	}
	
	public void GetUserOrders(int CodU, ArrayList<Order> OrdList ) {
		PreparedStatement st;
		Order ord;
		try {
				st = conn.prepareStatement("select * from orders where codu = ?");
				st.setInt(1, CodU);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					ord = new Order(rs.getInt("codo"),rs.getInt("codu"),rs.getDouble("totalprice"), rs.getDate("date"));
					OrdList.add(ord);
				}
		}catch(SQLException e3) {
			e3.printStackTrace();
		}	
	}
	
	public void OrderReturn(int CodO) {
		PreparedStatement st;
		try {
			st = conn.prepareStatement("delete from orders where codo = ?");
			st.setInt(1, CodO);
			ResultSet rs = st.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void GetAllOrders(ArrayList<Order> OrdList ) {
		PreparedStatement st;
		Order ord;
		try {
				st = conn.prepareStatement("select * from orders");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					ord = new Order(rs.getInt("codo"),rs.getInt("codu"),rs.getDouble("totalprice"), rs.getDate("date"));
					OrdList.add(ord);
				}
		}catch(SQLException e3) {
			e3.printStackTrace();
		}	
	}
}
