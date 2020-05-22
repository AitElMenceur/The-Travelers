package com.gui;

public class User {
protected String username;
protected String password;
protected String email;
protected int ID;

public User(String username, String password, String email, int ID) {
	this.username= username;
	this.password= password;
	this.email=email;
	this.ID=ID;
}

public String getUsername(){
	return this.username;
}

public String getPassword() {
	return this.password;
}

public String getEmail() {
	return this.email;
}

public int getID() {
	return this.ID;
}

public String toString() {
	return this.getUsername()+";"+this.getEmail();
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (ID != other.ID)
		return false;
	if (email == null) {
		if (other.email != null)
			return false;
	} else if (!email.equals(other.email))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (username == null) {
		if (other.username != null)
			return false;
	} else if (!username.equals(other.username))
		return false;
	
	return true;
}

}
