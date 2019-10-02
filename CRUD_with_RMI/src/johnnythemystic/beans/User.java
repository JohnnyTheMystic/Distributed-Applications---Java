package johnnythemystic.beans;

public class User {
	
	private String name;
	private String password;
	private Boolean admin;
	
	
	public User(String name, String password, Boolean admin) {
		super();
		this.setName(name);
		this.password = password;
		this.admin = admin;
	}


	public Object getPassword() {
		return this.password;
	}
	
	public Boolean getAdmin() {
		return this.admin;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
