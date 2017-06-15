package com.biraj.springmysql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;
  
  private String password;
  
  private String passwordConform;

  public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getPasswordConform() {
	return passwordConform;
}

public void setPasswordConform(String passwordConform) {
	this.passwordConform = passwordConform;
}

public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String toString(){
	  return "User: " + name + " Email: " + email +  " Password: " + password + " Confom Password " + passwordConform;
	  
  }

}
