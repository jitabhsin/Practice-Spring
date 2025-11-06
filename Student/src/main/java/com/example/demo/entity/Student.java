package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student {
                
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	String name;
	
	String Dept;
	
	String t_marks;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, String name, String dept, String t_marks) {
		super();
		this.id = id;
		this.name = name;
		Dept = dept;
		this.t_marks = t_marks;
	}

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

	public String getDept() {
		return Dept;
	}

	public void setDept(String dept) {
		Dept = dept;
	}

	public String getT_marks() {
		return t_marks;
	}

	public void setT_marks(String t_marks) {
		this.t_marks = t_marks;
	}
	
	
}
