package com.gdky.restful.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** ID_. */
	private Integer id;

	/** 登陆名称. */
	private String loginName;

	/** 用户名称. */
	private String userName;

	/** 密码. */
	private String password;

	/** 手机号码. */
	private String phone;

	/** 入职时间. */
	private Date joinedDate;

	/** 职位. */
	private String job;

	/** 头像. */
	private String photo;

	/** 生日. */
	private Date birthday;

	private String dlpz;

	private Integer accountEnabled;
	private Integer accountExpired;
	private Integer accountLocked;
	private Integer credentialsExpired;

	/**
	 * Constructor.
	 */
	public User() {
	}

	public String getUsername() {
		return this.loginName;
	}

	public User(User u) {

		this.id = u.id;
		this.loginName = u.loginName;
		this.userName = u.userName;
		this.phone = u.phone;
		this.joinedDate = u.joinedDate;
		this.job = u.job;
		this.password = u.password;
		this.photo = u.photo;
		this.birthday = u.birthday;
		this.dlpz = u.dlpz;
		this.accountEnabled = u.accountEnabled;
		this.accountExpired = u.accountExpired;
		this.accountLocked = u.accountLocked;
		this.credentialsExpired = u.credentialsExpired;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDlpz() {
		return dlpz;
	}

	public void setDlpz(String dlpz) {
		this.dlpz = dlpz;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
