package com.biraj.springmysql;

public class UserBean {
  
  private Integer id;

  private String name;

  private String email;
  
  private String password;
  
  private String passwordConform;
  
  
  private byte [] image;

  public  byte [] getImage() {
    return image;
  }

  public void setImage( byte [] image) {
    this.image = image;
  }

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
    return "User: " + name + " Email: " + email +  " Password: " + password + " Confom Password " + passwordConform + "filesize" ;
    
  }

}
