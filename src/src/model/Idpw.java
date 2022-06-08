package model;
import java.io.Serializable;

public class Idpw implements Serializable {
	private String id;	// ID
	private String pw;	// PW

	public Idpw(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public Idpw() {
		this.id = "";
		this.pw = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
}
