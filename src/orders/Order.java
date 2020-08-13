package orders;

import java.util.Date;

public class Order {
	
	private int CodO;
	private int CodU;
	private double TotalPrice;
	private Date OrderDate;
	
	//getters and setters
	public int getCodO() {
		return CodO;
	}
	public void setCodO(int codO) {
		CodO = codO;
	}
	public int getCodU() {
		return CodU;
	}
	public void setCodU(int codU) {
		CodU = codU;
	}
	public double getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	
	//constructor
	public Order(int codO, int codU, double totalPrice, Date orderDate) {
		CodO = codO;
		CodU = codU;
		TotalPrice = totalPrice;
		OrderDate = orderDate;
	}
	
	
}
