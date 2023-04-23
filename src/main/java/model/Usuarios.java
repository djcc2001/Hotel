package model;

public class Usuarios {

	private int id;
	private String user;
	private String pass;

	public Usuarios() {
	}

	public Usuarios(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
