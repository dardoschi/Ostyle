package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Item.Item;
//import dao.ConnectionFactory;
import frames.AddNewItemFrame;
import frames.CartFrame;
import frames.EditSelectedItemFrame;
import frames.LoginFrame;
import frames.MainFrameEmployee;
import frames.RegisterNewUserFrame;
import people.Employee;
import frames.MainFrameAdmin;
import dao.ItemDao;
import dao.EmployeeDao;

public class Controller {
	
//	private static ConnectionFactory conn;
	private ItemDao IDao;
	private EmployeeDao EDao;
	private LoginFrame LoginFrame;
	private MainFrameAdmin MAdminFrame;
	private MainFrameEmployee MEmployeeFrame;
	private RegisterNewUserFrame RegisterFrame;
	private EditSelectedItemFrame EditFrame;
	private Item selecteditem;
	private CartFrame CFrame;
	private AddNewItemFrame AddFrame;
	public ArrayList<Item> Warehouse;
	public ArrayList<Item> Cart;
	
	//Constructor
	public Controller(){
		Warehouse = new ArrayList<Item>();
		Cart = new ArrayList<Item>();
		IDao = new ItemDao();
		EDao = new EmployeeDao(this);
		LoginFrame = new LoginFrame(this);
		LoadWarehouseArray(Warehouse);
		MAdminFrame = new MainFrameAdmin(this);
		MEmployeeFrame = new MainFrameEmployee(this);
		RegisterFrame = new RegisterNewUserFrame(this);
		AddFrame = new AddNewItemFrame(this);
		CFrame = new CartFrame(this);
		LoginFrame.setVisible(true);
	}
	
	//FUNCTIONS FOR ARRAYLIST
	public void add(Item i) {
		Warehouse.add(i);
	}
	public ArrayList<Item> getWarehouse(){
		return Warehouse;
	}
	public ArrayList<Item> deleteWarehouseArray(){
		Warehouse.clear();
		return Warehouse;
	}
	//retrieves data from database (data will be loaded into table in mainframe)
	public void LoadWarehouseArray(ArrayList<Item> warehouse) {
		IDao.getWarehousefromDB(warehouse);
	}
	//reloads the Warehouse arrayList (updates it with current DB values)(use it after insert/delete/update querys)
	public ArrayList<Item> reloadWarehouseArray(ArrayList<Item> warehouse){
		deleteWarehouseArray();
		LoadWarehouseArray(warehouse);
		return Warehouse;
	}
	
	//open registernewuser frame from login frame
	public void RegisterFrameOpen() {
		RegisterFrame.setVisible(true);	
		LoginFrame.setVisible(false);
	}
	
	//open login frame from register frame
	public void LoginFrameOpen() {
		RegisterFrame.setVisible(false);	
		LoginFrame.setVisible(true);
	}
	
    // open AddNewItemFrame
	public void AddNewItemFrameOpen() {
		AddFrame.setVisible(true);
	}
	
	//open EditSelectedItemFrame
	public void EditSelectedFrameOpen() {
		EditFrame = new EditSelectedItemFrame(this);
		EditFrame.setVisible(true);
	}
	
	//open CartFrame
	public void CartFrameOpen() {
		if(CFrame != null) {
			CFrame.setVisible(true);
		}else
			CFrame = new CartFrame(this);
			CFrame.setVisible(true);
	}
	
	public void Login(String Username, String Password) {
		Employee user = EDao.Login(Username, Password);
		if(user == null) {
			LoginFrame.UnregisteredUser();
		}else {
			if(user.isAdmin()==true) {
				MAdminFrame.setVisible(true);
				LoginFrame.setVisible(false);
				}else 
					MEmployeeFrame.setVisible(true);
					LoginFrame.setVisible(false);
			}
	}
		
	public void LogOut() {
		MAdminFrame.setVisible(false);
		MEmployeeFrame.setVisible(false);
		LoginFrame.setVisible(true);
	}
		
	//register a new user
	public void RegisterUser(String Username, String Password,boolean Admin, String Name, String Surname, int CodI) { //
		if(EDao.RegisterNewUser(Username, Password, Admin, Name, Surname, CodI)==true){ //
			RegisterFrame.UserHasBeenRegistered();
			RegisterFrame.setVisible(false);
			LoginFrame.setVisible(true);
		}else {
			if (EDao.Login(Username, Password)!=null)
				RegisterFrame.UserAlreadyRegistered();
		}						
	}
	
	//reloads the JTable in MainFrameAdmin (use after every change to the Database)
	public void ReloadDBTable() {
		reloadWarehouseArray(Warehouse);
		MAdminFrame.TModel.fireTableDataChanged();
		MEmployeeFrame.TModel.fireTableDataChanged();
	} 
	
	//add new item
	public void AddNewItem(int Id, String Size, double Price, String Type, int InStock, String Colour) {
		IDao.AddNewItemToDB(Id, Size, Price, Type, InStock, Colour);
		ReloadDBTable();
		}
	
	//updates the SelectedItemFromDB  (from the editselectedframe)
	public void updateItemInDB(int Id, String Size, double Price, String Type, int InStock, String Colour, int OldId) {
		IDao.updateItem(Id, Size, Price, Type, InStock, Colour, OldId);
		ReloadDBTable();
	}

	//check if an item id already exists, true ok, false already exists
	public boolean CheckItemId(int Id){
		if((IDao.CheckItemId(Id))==true) {
			return true;
		}
		else return false;
	}
	
	//get the item fetched from DB with its Id
	public Item getItem(int Id) {
		selecteditem = IDao.getSelectedItemFromDB(Id);
		return selecteditem;
	}
	
	public Item fetchSelectedItem() {
		return selecteditem;
	}

//	transforms the selecteditemfromDb into an ItemInCart and adds it to the cart
	public void addItemToCart(Item selected) {
		if(selected.getInStock()==0) {
			JOptionPane.showMessageDialog(new JFrame(), "Currently not available","ERROR", JOptionPane.ERROR_MESSAGE);
		}else {

			if(selected.checkInCart(Cart)==false) {
				selected.setInCart(1);
				selected.inStockMinusOne();
				Cart.add(selected);   
				}else {
					//get the selected index in cart
					int index = selected.getSelectedItemIndex(Cart);
					Cart.get(index).inCartPlusOne();
					Cart.get(index).inStockMinusOne();
					}
			CFrame.TModel.fireTableDataChanged();
			MEmployeeFrame.TModel.fireTableDataChanged();
			getTotal();
			CFrame.updateTotal();
		}
	}
	
	//payment
	public void BuyandUpdate(){
		for(Item i : Cart) {
			int id = i.getId();
			int sold = i.getInCart();
			IDao.updateOnSaleInDB(sold, id);
		}
		Cart.clear();
		MEmployeeFrame.TModel.fireTableDataChanged();
		CFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();
	}
	
	
	//removes one of the selected item from Cart
	public void removeOneFromCart(Item selected) {
		int index = selected.getSelectedItemIndex(Cart);
		Cart.get(index).inCartMinusOne();
		Cart.get(index).inStockPlusOne();
		if(selected.getInCart()==0) {
			Cart.remove(index);
		}
		CFrame.TModel.fireTableDataChanged();
		MEmployeeFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();
	}
	
	//empties the cart and reloads the table
	public void emptyCart() {
		Cart.clear();
		ReloadDBTable();
		CFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();
	}
	
	//removes all of one item in cart
	public void removeAllFromCart(Item selected) {
		int index = selected.getSelectedItemIndex(Cart);
		Cart.get(index).inStockAdd(Cart.get(index).getInCart());
		Cart.get(index).zeroInCart();
		Cart.remove(index);
		CFrame.TModel.fireTableDataChanged();
		MEmployeeFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();
	}
	
	//calculates the total price
	public double getTotal() {
		double total = 0;
		for(Item i : Cart) {
			int multiplier = i.getInCart();
			total = total + (i.getPrice() * multiplier);
		}
		return total;
	}
	
	
	//removes item from databse
	public void removeFromWarehouse(int id) {
		IDao.removeFromWarehouse(id);
		ReloadDBTable();
	}
	
}	