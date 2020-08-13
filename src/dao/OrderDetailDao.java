package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import orders.Item;
import orders.Order;
import orders.OrderDetail;

public class OrderDetailDao {

	private Connection conn = DBConnection.getDBConnection();
	
	public void CreateOrderDetail(int CodO, Item Sold) {
		PreparedStatement st;
		try {
				st = conn.prepareStatement("insert into orderdetail (values (?,?,?));");
				st.setInt(1, CodO);
				st.setInt(2, Sold.getId());
				st.setInt(3, Sold.getInCart());
				ResultSet rs = st.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getOrderDetail(int CodO, ArrayList<OrderDetail> OrderDetailList) {
		PreparedStatement st;
		OrderDetail ordd;
		try {
				st = conn.prepareStatement("select * from orderdetail where codo = ?;");
				st.setInt(1, CodO);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					ordd = new OrderDetail(rs.getInt("codo"),rs.getInt("coda"),rs.getInt("amount"));
					OrderDetailList.add(ordd);
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
