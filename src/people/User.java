package people;

public class User {
	
	private String Name;
	private String Surname;
	private String Username;
	private String Password;
	private String Email;
	private int CodU;
	private String Address;
	private int CardN;
	
	//getters and setters
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getEmail() {
		return Email;
	}
	public int getCodU() {
		return CodU;
	}
	public void setCodU(int codU) {
		CodU = codU;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getCardN() {
		return CardN;
	}
	public void setCardN(int cardN) {
		CardN = cardN;
	}
	
	//constructor
	public User(String name, String surname, String username, String password,String email, int codU, String address, int cardN) {
		Name = name;
		Surname = surname;
		Username = username;
		Password = password;
		Email = email;
		CodU = codU;
		Address = address;
		CardN = cardN;
	}
}
