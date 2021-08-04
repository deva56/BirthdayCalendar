package io.birthdaycalendar.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BirthdayRecord {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length=45, nullable=false)
	private String birthdayDate;
	@Column(length=64, nullable=false)
	private String personName;
	@Column(length=64, nullable=false)
	private String userName;
	/*
	 * @ManyToOne private User user;
	 */
	
	public BirthdayRecord() {
		super();
	}
	public BirthdayRecord(String birthdayDate, String personName, String userName) {
		super();
		this.birthdayDate = birthdayDate;
		this.personName = personName;
		this.userName = userName;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/*
	 * public User getUser() { return user; } public void setUser(User user) {
	 * this.user = user; }
	 */
}
