package com.biraj.springmysql.todo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Todo {
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long Id;
  private Date date;
  private String title;
  private String details;
  
  
  public Todo(){
   
  }
  
  public Todo(Date date, String title, String details) {
    super();
    this.date = date;
    this.title = title;
    this.details = details;
  }
  
  
  
  

}
