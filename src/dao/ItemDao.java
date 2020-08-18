package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.Controller;
import orders.Item;

public class ItemDao {
	
	private Connection connection = DBConnection.getDBConnection();
		
		//Loads the ArrayList used for the jtable in mainframe
		public void getWarehousefromDB(ArrayList<Item> warehouse) {
			try {
				Statement st = connection.createStatement();
				String query ="select * from item";
				ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					Item s = new Item();
					s.setId(rs.getInt("id"));
					s.setSize(rs.getString("size"));
					s.setPrice(rs.getDouble("price"));
					s.setType(rs.getString("type"));
					s.setInStock(rs.getInt("instock"));
					s.setColour(rs.getString("colour"));
					s.setName(rs.getString("name"));
					warehouse.add(s);
				}
			}
			catch (SQLException e ) {
				e.printStackTrace();
			}		
			
		}

	//Add new item to Database, after this reload the arrayList
	public void AddNewItemToDB(int Id, String Size, double Price, String Type, int InStock, String Colour, String Name) {
		PreparedStatement st;
		try {
				st = connection.prepareStatement("insert into item (values (?,?,?,?,?,?,?));");
				st.setInt(1, Id);
				st.setString(2, Size);
				st.setDouble(3, Price);
				st.setString(4, Type);
				st.setInt(5, InStock);
				st.setString(6, Colour);
				st.setString(7, Name);
				ResultSet rs = st.executeQuery();
		}catch(SQLException e3) {
			e3.printStackTrace();
		}

	}
	
	//check id, false if there is already an item with that id, true otherwise
	public boolean CheckItemId(int Id){
		PreparedStatement st;
			try {
				st = connection.prepareStatement("SELECT * from item WHERE id = ?");
					 st.setInt(1, Id);
				     ResultSet rs = st.executeQuery(); 
				     // if this id exist returns true else returns false 
				     if ( rs.next() ) {    	
				    	 return false;
				    	}
				     else 
				    	 return true;
				}
				catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
	}
	
	
	
	//get selected item from JTable (fetch its data from DB)
	public Item getSelectedItemFromDB(int Id) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("SELECT * from item WHERE id = ? ");
			st.setInt(1,  Id);
			ResultSet rs = st.executeQuery();
			Item selected = new Item();
			while (rs.next()) {
				selected.setId(rs.getInt("id"));
				selected.setSize(rs.getString("size"));
				selected.setPrice(rs.getDouble("price"));
				selected.setType(rs.getString("type"));
				selected.setInStock(rs.getInt("instock"));
				selected.setColour(rs.getString("colour"));
				selected.setName(rs.getString("name"));
			}
			return selected;
		}	
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateItem(int Id, String Size, double Price, String Type, int InStock, String Colour, String Name, int OldId) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("UPDATE item SET id = ?, size = ?, price = ?, type = ?, instock = ?, colour = ?, name = ? WHERE id = ?");
			st.setInt(1, Id);
			st.setString(2, Size);
			st.setDouble(3, Price);
			st.setString(4, Type);
			st.setInt(5, InStock);
			st.setString(6, Colour);
			st.setString(7, Name);
			st.setInt(8,OldId);
			ResultSet rs = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateInStockDBValue(Item item) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("UPDATE item SET instock = ? WHERE id = ?");
			st.setInt(1, item.getInStock());
			st.setInt(2, item.getId());
			ResultSet rs = st.executeQuery();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//updates the InStock value for the sold items
	public void updateOnSaleInDB(int sold, int id) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("select update_on_sale(?,?)");
			st.setInt(1, sold);
			st.setInt(2, id);
			ResultSet rs = st.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//remove an item from database
	public void removeFromWarehouse(int id) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("delete from item where id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}