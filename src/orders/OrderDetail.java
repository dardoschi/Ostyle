package orders;

public class OrderDetail {
	
	private int CodO;
	private int CodA;
	private int Amount;
	
	//getters and setters
	public int getCodO() {
		return CodO;
	}
	public void setCodO(int codO) {
		CodO = codO;
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
	
	//constructor
	public OrderDetail(int codO, int codA, int amount) {
		super();
		CodO = codO;
		CodA = codA;
		Amount = amount;
	}
	
}
