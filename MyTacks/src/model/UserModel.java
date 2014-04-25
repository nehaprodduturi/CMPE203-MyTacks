package model;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class UserModel {
private String userName;
private String firstName;
private String lastName;
private String password;
private String email;
private String confirm_password;
public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getConfirm_password() {
	return confirm_password;
}

public void setConfirm_password(String confirm_password) {
	this.confirm_password = confirm_password;
}



public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public void establishConnection(String host,String username) throws UnknownHostException {
	MongoClient mc=new MongoClient(host,27017);
	DB db = mc.getDB("test");
	//DBCollection coll=db.getCollection("user");
	BasicDBObject basicdb=new BasicDBObject("userName",username);
	//coll.insert(basicdb);
	DBConnection dbc=new DBConnection("localhost");
	dbc.collection.insert(basicdb);
	
	
}

}
