package com.vea.is.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vea.is.validators.UniqueLoginName;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@UniqueLoginName
public abstract class Person extends SchoolEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4130220608092293563L;

	@NotEmpty
	protected String name;
	protected String surname;
	protected String phone;
	@Email
	protected String email;
	@Column(unique = true)
	@NotEmpty

	protected String loginName;
	@JsonIgnore
	protected String password;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(password != null && !password.isEmpty())
			this.password = password;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + ", phone=" + phone + ", email=" + email + "]";
	}

}
