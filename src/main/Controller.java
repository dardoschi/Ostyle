package main;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import frames.AddNewItemFrame;
import frames.CartFrame;
import frames.EditSelectedItemFrame;
import frames.EmployeeFrame;
import frames.EmployeeLoginFrame;
import frames.MainFrame;
import frames.RegisterNewEmployeeFrame;
import frames.RegisterNewUserFrame;
import frames.StorePurchasesFrame;
import frames.UserLoginFrame;
import frames.UserOrdersFrame;
import orders.Item;
import orders.Order;
import orders.OrderDetail;
import orders.Soldinstore;
import people.Employee;
import people.User;
import frames.MainFrameAdmin;
import dao.ItemDao;
import dao.OrderDetailDao;
import dao.OrdersDao;
import dao.SoldInstoreDao;
import dao.UserDao;
import dao.EmployeeDao;

public class Controller {
	
	private ItemDao IDao;
	private EmployeeDao EDao;
	private UserDao UDao;
	private OrdersDao ODao;
	private OrderDetailDao ODDao;
	private SoldInstoreDao SDao;
	private EmployeeLoginFrame EmployeeLoginFrame;
	private UserLoginFrame UserLogFrame;
	private MainFrameAdmin MAdminFrame;
	private MainFrame MFrame;
	private EmployeeFrame EFrame;
	private RegisterNewEmployeeFrame RegisterEmployeeFrame;
	private RegisterNewUserFrame RegisterUserFrame;
	private EditSelectedItemFrame EditFrame;
	private Item selecteditem;
	private CartFrame CFrame;
	private AddNewItemFrame AddFrame;
	private StorePurchasesFrame SPFrame;
	public ArrayList<Item> Warehouse;
	public ArrayList<Item> Cart;
	public ArrayList<Order> OrderList;
	public ArrayList<OrderDetail> OrderDetailList;
	public ArrayList<Item> OrderItems;
	public ArrayList<Soldinstore> StorePurchases;
	private UserOrdersFrame OrdersFrame;
	private Employee Cashier;
	private User Client;
	
	//Constructor
	public Controller(){
		Warehouse = new ArrayList<Item>();
		Cart = new ArrayList<Item>();
		OrderItems = new ArrayList<Item>();
		OrderList = new ArrayList<Order>();
		OrderDetailList = new ArrayList<OrderDetail>();
		IDao = new ItemDao();
		EDao = new EmployeeDao();
		UDao = new UserDao();
		ODao = new OrdersDao();
		ODDao = new OrderDetailDao();
		SDao = new SoldInstoreDao();
		EmployeeLoginFrame = new EmployeeLoginFrame(this);
		UserLogFrame = new UserLoginFrame(this);
		LoadWarehouseArray(Warehouse);
		MAdminFrame = new MainFrameAdmin(this);
		RegisterEmployeeFrame = new RegisterNewEmployeeFrame(this);
		RegisterUserFrame = new RegisterNewUserFrame(this);
		AddFrame = new AddNewItemFrame(this);
		CFrame = new CartFrame(this);
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
	
	public void EmployeeLogin(String Username, String Password) {
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
					EFrame = new EmployeeFrame(this);
					EFrame.setVisible(true);
				}
			//UpdateUserOrderList(OrderList);

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
			MFrame = new MainFrame(this);
			MFrame.setVisible(true);
			OrdersFrame = new UserOrdersFrame(this);
		}
	}
	
	
	public void EmployeeLogOut() {
		if(Cashier.isAdmin()==true) {
			MAdminFrame.setVisible(false);
		}else if(Cashier.isAdmin()==false) {
			EFrame.setVisible(false);
		}
		//EmployeeLoginFrame.setVisible(true);
		UserLogFrame.setVisible(true);
		System.out.println(Cashier.getUsername());
		setCashier(null);
		OrdersFrame.dispose();
		OrdersFrame = null;	
		UpdateUserOrderList(OrderList);
		
	}
		
	public void MFrameLogOut() {
		if(Cashier==null) {
			//user logout
			MFrame.setVisible(false);
			UserLogFrame.setVisible(true);
			setClient(null);
			MFrame.dispose();
			OrdersFrame.dispose();
			OrdersFrame = null;	
			UpdateUserOrderList(OrderList);
		}else {
			EmployeeLogOut();
		}
	}
	
	//register a new user
	public void RegisterEmployee(String Username, String Password,boolean Admin, String Name, String Surname) { 
		if(EDao.RegisterNewEmployee(Username, Password, Admin, Name, Surname)==true){ 
			RegisterEmployeeFrame.UserHasBeenRegistered();
			RegisterEmployeeFrame.setVisible(false);
			EmployeeLoginFrame.setVisible(true);
		}else {
			//if (EDao.Login(Username, Password)!=null)
				RegisterEmployeeFrame.UserAlreadyRegistered();
		}						
	}
	
	public void RegisterUser(String Name, String Surname,String Username, String Password, String Email, String Address, int CardN) {
		if( (UDao.RegisterNewUser(Name, Surname, Username, Password, Email, Address, CardN) == 0) ){
			RegisterUserFrame.UserHasBeenRegistered();
			RegisterUserFrame.setVisible(false);
			UserLogFrame.setVisible(true);
		}else if (UDao.RegisterNewUser(Name, Surname, Username, Password, Email, Address, CardN) == 1) {
			RegisterUserFrame.UserNameAlreadyRegistered();
		}else if (UDao.RegisterNewUser(Name, Surname, Username, Password, Email, Address, CardN) == 2) {
			RegisterUserFrame.EmailAlreadyUsed();
		}
	}
	
	//reloads the JTable in MainFrameAdmin (use after every change to the Database)
	public void ReloadDBTable() {
		reloadWarehouseArray(Warehouse);
		if(MAdminFrame != null) {
			MAdminFrame.TModel.fireTableDataChanged();
		}
		if(MFrame != null) {
			MFrame.TModel.fireTableDataChanged();
		}
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
			if(EFrame != null) {
				EFrame.TModel.fireTableDataChanged();
			}
			if(MFrame != null) {
				MFrame.TModel.fireTableDataChanged();
			}
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
		if(Cashier != null) {
			for(Item i : Cart) {
				EDao.CreateSoldInStore(Cashier.getCodI(), i);
			}
		}else {
			CreateOrder();
		}
		if(EFrame != null) {
			EFrame.TModel.fireTableDataChanged();
		}
		if(MFrame != null) {
			MFrame.TModel.fireTableDataChanged();
		}
		Cart.clear();
		CFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();	
	}
	
	public void CreateOrder(){
		//per ora solo clienti
		int NewCodO = ODao.CreateOrder(Client.getCodU(), getTotal());
		for(Item i : Cart) {
			ODDao.CreateOrderDetail(NewCodO, i);
		}
		UpdateUserOrderList(OrderList);
		OrdersFrame.ODTModel.fireTableDataChanged();
		OrdersFrame.OLTModel.fireTableDataChanged();
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
		//MFrame.TModel.fireTableDataChanged();
		if(EFrame != null) {
			EFrame.TModel.fireTableDataChanged();
		}
		if(MFrame != null) {
			MFrame.TModel.fireTableDataChanged();
		}
		CFrame.updateTotal();
	}
	
	//empties the cart and reloads the table
	public void emptyCart() {
		Cart.clear();
		ReloadDBTable();
		CFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();
		if(EFrame != null) {
			EFrame.TModel.fireTableDataChanged();
		}
		if(MFrame != null) {
			MFrame.TModel.fireTableDataChanged();
		}
	}
	
	//removes all of one item in cart
	public void removeAllFromCart(Item selected) {
		int index = selected.getSelectedItemIndex(Cart);
		Cart.get(index).inStockAdd(Cart.get(index).getInCart());
		Cart.get(index).zeroInCart();
		Cart.remove(index);
		CFrame.TModel.fireTableDataChanged();
		//MFrame.TModel.fireTableDataChanged();
		CFrame.updateTotal();
		if(EFrame != null) {
			EFrame.TModel.fireTableDataChanged();
		}
		if(MFrame != null) {
			MFrame.TModel.fireTableDataChanged();
		}
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
		if(Client == null && Cashier.isAdmin()== true) {
			ODao.GetAllOrders(OrderList);
		}else {
			ODao.GetUserOrders(Client.getCodU(), OrderList);
		}
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
			OrderItems.clear();
			if(Cashier != null) {
				GetUserOrderList(0);
			}
		}else {
			OrderList.clear();
			OrderDetailList.clear();
			OrderItems.clear();
			GetUserOrderList(Client.getCodU());			
		}
	}
	
	public void getOrderDetail(int CodO) {
		OrderDetailList.clear();
		ODDao.getOrderDetail(CodO, OrderDetailList);
		getItemsFromOD(OrderDetailList);
	}
	
	public void getItemsFromOD(ArrayList<OrderDetail> OD){
		OrderItems.clear();
		for (OrderDetail od : OD) {
			Item i = IDao.getSelectedItemFromDB(od.getCodA());
			i.setInCart(od.getAmount());
			OrderItems.add(i);
		}
	}
	
	public void ReturnOrder(int CodO) {
		ODao.OrderReturn(CodO);
		OrdersFrame.OLTModel.fireTableDataChanged();
		OrdersFrame.ODTModel.fireTableDataChanged();
		UpdateUserOrderList(OrderList);
		ReloadDBTable();
	}
	
	public void getStorePurchases(){
		if(StorePurchases != null) {
			StorePurchases.clear();
		}else {
			StorePurchases = new ArrayList<Soldinstore>();
		}
		SDao.getSoldInStore(StorePurchases);
		for(Soldinstore s : StorePurchases) {
			s.setItemName(getItemName(s.getCodA()));
			s.setEmplName(getEmplName(s.getCodI()));
		}
	}
	
	public String getItemName(int id) {
		String name = IDao.getItemName(id);
		return name;
	}
	
	public String getEmplName(int CodI) {
		String name = EDao.getEmplName(CodI);
		return name;
	}
	
	public void StorePurchasesFrameOpen() {
		SPFrame = new StorePurchasesFrame(this);
		SPFrame.setVisible(true);
	}
}	