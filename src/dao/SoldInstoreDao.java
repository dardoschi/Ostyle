package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import orders.Soldinstore;

public class SoldInstoreDao {

	private Connection conn = DBConnection.getDBConnection();
	
	public void getSoldInStore(ArrayList<Soldinstore> SoldInStoreList) {
		PreparedStatement st;
		try {
				st = conn.prepareStatement("select * from soldinstore;");
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					Soldinstore sis = new Soldinstore(rs.getInt("codi"),rs.getInt("coda"),rs.getInt("amount"),rs.getDate("date"));
					SoldInStoreList.add(sis);
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
