package com.guestbook.model;

public class MemberDTO {
	private int num;
	private String name;
	private String passwd;
	private String tel;
	private String email;
	private String addr;
	private String age;
	private String id;
	private String gender;
	private String admin;
	
	
	
	
	
	public String getAdmin() {
		return admin == null ? "" : admin.trim();
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getGender() {
		return gender == null ? "" : gender.trim();
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getId() {
		return id == null ? "" : id.trim();
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAge() {
		return age == null ? "" : age.trim();
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name == null ? "" : name.trim();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd == null ? "" : passwd.trim();
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getTel() {
		return tel == null ? "" : tel.trim();
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email == null ? "" : email.trim();
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr == null ? "" : addr.trim();
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	

}
