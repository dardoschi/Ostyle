package main;

public class Employee {
	
	private String Username;
	private String Password;
	private boolean admin;
	
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public Employee(String username, boolean admin) {
		Username = username;
		this.admin = admin;
	}

}
