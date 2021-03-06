package people;

public class Employee{
	
	private String Name;
	private String Surname;
	private String Username;
	private String Password;
	private int CodI;
	private boolean Admin;
		
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
	public int getCodI() {
		return CodI;
	}
	public void setCodI(int codI) {
		CodI = codI;
	}
	public boolean isAdmin() {
		return Admin;
	}
	public void setAdmin(boolean admin) {
		this.Admin = admin;
	}
	
	//constructor
	public Employee(String name, String surname, String username, String password,int codi, boolean admin) {
		Name = name;
		Surname = surname;
		Username = username;
		Password = password;
		CodI = codi;
		Admin = admin;
	}
}
