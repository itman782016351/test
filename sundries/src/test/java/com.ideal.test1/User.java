package com.ideal.test1;

public class User {
	private int id;
	private String name;
 
	public User() {
		this(0, "0");
	}
 
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
 
}
