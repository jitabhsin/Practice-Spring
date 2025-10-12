package com.example.excelmysql.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;


@Entity
public class Person {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  
  private String name;
  private String email;
  private Integer age;
  public Long getId() {
	return id;
  }
  public void setId(Long id) {
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
  public Integer getAge() {
	return age;
  }
  public void setAge(Integer age) {
	this.age = age;
  }
  public Person(Long id, String name, String email, Integer age) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.age = age;
  }
  public Person() {
	super();
	// TODO Auto-generated constructor stub
  }
  
}
