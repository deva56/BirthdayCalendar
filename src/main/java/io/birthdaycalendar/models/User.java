package io.birthdaycalendar.models;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true, length = 45, nullable = false)
	private String username;
	@Column(length = 64, nullable = false)
	private String password;
	@Column(unique=true, length = 45, nullable = false)
	private String email;
	@Column(length = 64, nullable = false)
	private Instant createdAt;
	
	public User() {
		super();
	}
	
	public User(String userName, String email, String password, Instant createdAt) {
		super();
		this.username = userName;
		this.password = password;
		this.email = email;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
}
