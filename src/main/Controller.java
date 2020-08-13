package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import frames.AddNewItemFrame;
import frames.CartFrame;
import frames.EditSelectedItemFrame;
import frames.EmployeeLoginFrame;
import frames.MainFrame;
import frames.RegisterNewEmployeeFrame;
import frames.RegisterNewUserFrame;
import frames.UserLoginFrame;
import frames.UserOrdersFrame;
import orders.Item;
import orders.Order;
import orders.OrderDetail;
import people.Employee;
import people.User;
import frames.MainFrameAdmin;
import dao.ItemDao;
import dao.OrderDetailDao;
import dao.OrdersDao;
import dao.UserDao;
import dao.EmployeeDao;

public class Controller {
	
	private ItemDao IDao;
	private EmployeeDao EDao;
	private UserDao UDao;
	private OrdersDao ODao;
	private OrderDetailDao ODDao;
	private EmployeeLoginFrame EmployeeLoginFrame;
	private UserLoginFrame UserLogFrame;
	private MainFrameAdmin MAdminFrame;
	private MainFrame MFrame;
	private RegisterNewEmployeeFrame RegisterEmployeeFrame;
	private RegisterNewUserFrame RegisterUserFrame;
	private EditSelectedItemFrame EditFrame;
	private Item selecteditem;
	private CartFrame CFrame;
	private AddNewItemFrame AddFrame;
	public ArrayList<Item> Warehouse;
	public ArrayList<Item> Cart;
	public ArrayList<Order> OrderList;
	public ArrayList<OrderDetail> OrderDetailList;
	public ArrayList<Item> OrderItems;
	private UserOrdersFrame OrdersFrame;
	private Employee Cashier;
	private User Client;
	
	//Constructor
	public Controller(){
		Warehouse = new ArrayList<Item>();
		Cart = new ArrayList<Item>();
		OrderItems = new ArrayList<Item>();
		IDao = new ItemDao();
		EDao = new EmployeeDao();
		UDao = new UserDao();
		ODao = new OrdersDao();
		ODDao = new OrderDetailDao();
		EmployeeLoginFrame = new EmployeeLoginFrame(this);
		UserLogFrame = new UserLoginFrame(this);
		LoadWarehouseArray(Warehouse);
		MAdminFrame = new MainFrameAdmin(this);
		//MFrame = new MainFrame(this);
		RegisterEmployeeFrame = new RegisterNewEmployeeFrame(this);
		RegisterUserFrame = new RegisterNewUserFrame(this);
		AddFrame = new AddNewItemFrame(this);
		CFrame = new CartFrame(this);
		//EmployeeLoginFrame.setVisible(true);
		UserLogFrame.setVisible(true);
	}
	
	public User getClient() {
		return Client;
	}

	public void setClient(User client) {
		Client = client;
	}

	public Employee getCashier() {
		return Cashier;
	}

	public void setCashier(Employee cashier) {
		Cashier = cashier;
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
	public void RegisterEmployeeFrameOpen() {
		RegisterEmployeeFrame.setVisible(true);	
		EmployeeLoginFrame.setVisible(false);
	}
	
	//open login frame from register frame
	public void EmployeeLoginFrameOpen() {
		RegisterEmployeeFrame.setVisible(false);
		UserLogFrame.setVisible(false);
		EmployeeLoginFrame.setVisible(true);
	}
	
	public void UserLoginFrameOpen() {
		RegisterUserFrame.setVisible(false);
		UserLogFrame.setVisible(true);
	}
	
	public void RegisterUserFrameOpen() {
		RegisterUserFrame.setVisible(true);	
		UserLogFrame.setVisible(false);
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
	
	public void EmpoloyeeLogin(String Username, String Password) {
		Employee user = EDao.Login(Username, Password);
		if(user == null) {
			EmployeeLoginFrame.UnregisteredUser();
		}else {
			if(user.isAdmin()==true) {
				MAdminFrame.setVisible(true);
				EmployeeLoginFrame.setVisible(false);
				setCashier(user);
				}else if(user.isAdmin()==false) {
					EmployeeLoginFrame.setVisible(false);
					setCashier(user);
					MFrame = new MainFrame(this);
					MFrame.setVisible(true);
				}

			}
		System.out.println("Logged as "+user.getUsername());
	}
	
	
	public void UserLogin(String Username, String Password) {
		User user = UDao.UserLogin(Username, Password);
		if(user == null) {
			UserLogFrame.UnregisteredUser();
		}else {
			UserLogFrame.setVisible(false);
			System.out.println("Logged as "+user.getUsername());
			setClient(user);
			GetUserOrderList(user.getCodU());
			UpdateUserOrderList(OrderList);
			MFrame = new MainFrame(this);
			MFrame.setVisible(true);			//apri menu utente
		}
	}
	
	
	public void EmployeeLogOut() {
		if(Cashier.isAdmin()==true) {
			MAdminFrame.setVisible(false);
		}else if(Cashier.isAdmin()==false) {
			MFrame.setVisible(false);
		}
		//EmployeeLoginFrame.setVisible(true);
		UserLogFrame.setVisible(true);
		System.out.println(Cashier.getUsername());
		setCashier(null);
		MFrame.dispose();
		
	}
	
	/*public void UserLogOut() {
		MFrame.setVisible(false);
		UserLogFrame.setVisible(true);
		System.out.println(Client.getUsername());
		setClient(null);
		MFrame.dispose();
	}*/
		
	public void MFrameLogOut() {
		if(Cashier==null) {
			//user logout
			MFrame.setVisible(false);
			UserLogFrame.setVisible(true);
			System.out.println(Client.getUsername());
			setClient(null);
			UpdateUserOrderList(OrderList);
			MFrame.dispose();
		}else {
			EmployeeLogOut();
		}
	}
	//register a new user
	public void RegisterEmployee(String Username, String Password,boolean Admin, String Name, String Surname, int CodI) { //
		if(EDao.RegisterNewEmployee(Username, Password, Admin, Name, Surname, CodI)==true){ //
			RegisterEmployeeFrame.UserHasBeenRegistered();
			RegisterEmployeeFrame.setVisible(false);
			EmployeeLoginFrame.setVisible(true);
		}else {
			//if (EDao.Login(Username, Password)!=null)
				RegisterEmployeeFrame.UserAlreadyRegistered();
		}						
	}
	
	public void RegisterUser(String Name, String Surname,String Username, String Password, String Email, String Address, int CardN) {
		if( (UDao.RegisterNewUser(Name, Surname, Username, Password, Email, Address, CardN) == true) ){
			RegisterUserFrame.UserHasBeenRegistered();
			RegisterUserFrame.setVisible(false);
			UserLogFrame.setVisible(true);
		}
	}
	
	//reloads the JTable in MainFrameAdmin (use after every change to the Database)
	public void ReloadDBTable() {
		reloadWarehouseArray(Warehouse);
		MAdminFrame.TModel.fireTableDataChanged();
		//MFrame.TModel.fireTableDataChanged();
	} 
	//add new item
	public void AddNewItem(int Id, String Size, double Price, String Type, int InStock, String Colour, String Name) {
		IDao.AddNewItemToDB(Id, Size, Price, Type, InStock, Colour, Name);
		ReloadDBTable();
		}
	
	//updates the SelectedItemFromDB  (from the editselectedframe)
	public void updateItemInDB(int Id, String Size, double Price, String Type, int InStock, String Colour, String Name, int OldId) {
		IDao.updateItem(Id, Size, Price, Type, InStock, Colour, Name, OldId);
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
			MFrame.TModel.fireTableDataChanged();
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
		CreateOrder();
		Cart.clear();
		MFrame.TModel.fireTableDataChanged();
		CFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();
	}
	
	public void CreateOrder(){
		//per ora solo clienti
		int NewCodO = ODao.CreateOrder(Client.getCodU(), getTotal());
		System.out.println(NewCodO);
		for(Item i : Cart) {
			ODDao.CreateOrderDetail(NewCodO, i);
		}
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
		MFrame.TModel.fireTableDataChanged();
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
		MFrame.TModel.fireTableDataChanged();
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
	
	public void GetUserOrderList(int CodU) {
		OrderList = new ArrayList<Order>();
		OrderDetailList = new ArrayList<OrderDetail>();
		ODao.GetUserOrders(1, OrderList);
	}
	
	public void OrdersFrameOpen() {
		if(OrdersFrame != null) {
			OrdersFrame.setVisible(true);
		}else
			OrdersFrame = new UserOrdersFrame(this);
			OrdersFrame.setVisible(true);
	}
	
	public void UpdateUserOrderList(ArrayList<Order> OrdList) {
		if(Client == null) {
			OrderList.clear();
			OrderDetailList.clear();
		}else {
			OrderList.clear();
			OrderDetailList.clear();
			GetUserOrderList(Client.getCodU());
		}
	}
	
	public void getOrderDetail(int CodO) {
		ODDao.getOrderDetail(CodO, OrderDetailList);
		getItemsFromOD(OrderDetailList);
	}
	
	public void getItemsFromOD(ArrayList<OrderDetail> OD){
		for (OrderDetail od : OD) {
			Item i = IDao.getSelectedItemFromDB(od.getCodA());
			i.setInCart(od.getAmount());
			OrderItems.add(i);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}	