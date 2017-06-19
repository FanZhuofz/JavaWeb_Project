package com.dawn.model;

public class Student {

	private int id;
	private String userName;
	private String password;
	private String name;
	private int age;
	private String sex;
	private String dept;
	private String tel;
	private String email;
	
	public Student(){
		super();
	}
	
	public Student(String userName,String password,String name,int age,String sex,String dept,String tel,String email){
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.dept = dept;
		this.tel = tel;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
