package orders;

import java.util.ArrayList;

public class Item {
	private int Id;
	private String Size;
	private double Price;
	private String Type;
	private String Colour;
	private int InStock;
	private int InCart;
	private String Name;
	
	//constructor
	public Item() {
	}
	
	public Item(Item selected) {
		this.Id = selected.getId();
		this.Size = selected.getSize();
		this.Price = selected.getPrice();
		this.Type = selected.getType();
		this.Colour = selected.getColour();
		this.InStock = selected.getInStock();
		this.InCart = 1;
		this.Name = selected.getName();
		
	}

	//getters and setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	public String getColour() {
		return Colour;
	}
	public void setColour(String colour) {
		Colour = colour;
	}
	public int getInStock() {
		return InStock;
	}
	public void setInStock(int inStock) {
		InStock = inStock;
	}
	public int getInCart() {
		return InCart;
	}
	public void setInCart(int inCart) {
		InCart = inCart;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	//true if the item is in cart, false otherwise
	public boolean checkInCart(ArrayList<Item> cart) {
		int id = this.getId();
		for(Item i : cart) {
			if(id==i.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public int getSelectedItemIndex(ArrayList<Item> array) {
		int id = this.getId();
		for(Item i : array) {
			if(id==i.getId()) {
				int index = array.indexOf(i);
				return index;
			}
		}
		return -1;
	}
	
	public void inCartPlusOne() {
		this.InCart++;
	}
	
	public void inStockMinusOne() {
		this.InStock--;
	}
	
	public void inCartMinusOne() {
		this.InCart--;
	}
	
	public void inStockPlusOne() {
		this.InStock++;
	}
	
	public void zeroInCart() {
		this.setInCart(0);
	}
	
	public void inStockAdd(int amount) {
		this.InStock = InStock + amount;
	}
}
