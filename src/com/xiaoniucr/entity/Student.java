package com.xiaoniucr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
public class Student implements Serializable{
	
	/**
	 * ID
	 */
	private Integer id;
	
	
	/**
	 * 账号
	 */
	private String username;
	
	
	
	/**
	 * 姓名
	 */
	private String nickname;
	
		
	/**
	 * 性别: 0男，1女
	 */
	private Integer sex;
	
	
	/**
	 * 生日
	 */
	private String birthday;

	
	/**
	 * 电话
	 */
	private String telephone;
	
	
	/**
	 * 邮箱
	 */
	private String email;
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Integer getSex() {
		return sex;
	}


	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	

}
