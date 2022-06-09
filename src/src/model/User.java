package model;

import java.io.Serializable;

public class User implements Serializable {
	private String id;	// ログイン時のID

	public User() {
		this(null);
	}

	public User(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setUserId(String id) {
		this.id = id;
	}
}
