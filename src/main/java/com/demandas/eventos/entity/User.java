package com.demandas.eventos.entity;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotEmpty
	@Column(name="name")
	private String name;
	
	@NotEmpty
	@Column(name="email", unique = true)
	private String email;
	
	@NotEmpty
	@Column(name="password")
	private String password;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="created_at")
	private Date createdAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_types_id", referencedColumnName = "id")
	private UserType userType;

	public User() {
	}
	
	public User(int id, @NotEmpty String name, @NotEmpty String email, @NotEmpty String password, boolean isActive,
			Date createdAt, UserType userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.userType = userType;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", isActive="
				+ isActive + ", createdAt=" + createdAt + ", userType=" + userType + "]";
	}
	
	
}
