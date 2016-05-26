package com.entity;

public class Users {
	private String nickname;
	private String real_name;
	private String account;
	private String password;
	private String age;
	private String gender;
	private String head_portrait;
	private String phone_number;
	private String emai_address;
	private String district;
	private String id_card_number;
	private String password_question;
	private String password_answer;
	
	public Users(){
		
	}

	public Users(String nickname, String account, String password, String gender, String password_question,
			String password_answer) {
		super();
		this.nickname = nickname;
		this.account = account;
		this.password = password;
		this.gender = gender;
		this.password_question = password_question;
		this.password_answer = password_answer;
	}

	public String getId_card_number() {
		return id_card_number;
	}

	public void setId_card_number(String id_card_number) {
		this.id_card_number = id_card_number;
	}

	public String getPassword_question() {
		return password_question;
	}

	public void setPassword_question(String password_question) {
		this.password_question = password_question;
	}

	public String getPassword_answer() {
		return password_answer;
	}

	public void setPassword_answer(String password_answer) {
		this.password_answer = password_answer;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHead_portrait() {
		return head_portrait;
	}
	public void setHead_portrait(String head_portrait) {
		this.head_portrait = head_portrait;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmai_address() {
		return emai_address;
	}
	public void setEmai_address(String emai_address) {
		this.emai_address = emai_address;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	
}
