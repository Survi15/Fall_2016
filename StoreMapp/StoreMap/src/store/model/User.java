package store.model;

import store.model.Products.Category;

public class User {
protected String userid;
protected String firstname;
protected String lastname;
protected String password;
protected UserType usertype;
public UserType getUsertype() {
	return usertype;
}
public void setUsertype(UserType usertype) {
	this.usertype = usertype;
}
public enum UserType {
	CUSTOMER, ADMIN
}

public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public User(String userid, String firstname, String lastname, String password,UserType userType) {
	super();
	this.userid = userid;
	this.firstname = firstname;
	this.lastname = lastname;
	this.password = password;
	this.usertype=userType;
}

}
