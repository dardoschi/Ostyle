package orders;

import java.util.Date;

public class Soldinstore {
	
	private int CodI;
	private int CodA;
	private int Amount;
	private Date PurDate;
	
	private String EmplName;
	private String ItemName;
	
	//getters and setters
	public int getCodI() {
		return CodI;
	}
	public void setCodI(int codI) {
		CodI = codI;
	}
	public int getCodA() {
		return CodA;
	}
	public void setCodA(int codA) {
		CodA = codA;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public Date getPurDate() {
		return PurDate;
	}
	public void setPurDate(Date purDate) {
		PurDate = purDate;
	}

	public String getEmplName() {
		return EmplName;
	}
	public void setEmplName(String emplName) {
		EmplName = emplName;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	//constructor
	public Soldinstore(int codI, int codA, int amount, Date purDate) {
		CodI = codI;
		CodA = codA;
		Amount = amount;
		PurDate = purDate;
	}

}
