package com.sportyshoespvtltd.shopsportshoes.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;

//import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity(name="userdetails")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "userrole")
@DiscriminatorValue("customer")
public class User {

	@Id
	@Column(name="emailid")
	private String emailId;
	@Column(length = 20)
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String gender;
	private Date dateOfBirth;
	
	@Embedded
	private Address address;
	
	
	@OneToMany(mappedBy = "user")
	private List<CustomerOrder> orders=new ArrayList<>();
	
	@OneToMany(mappedBy = "purchaseduser")
	private List<PurchaseItem> puchaseItems=new ArrayList<>();
	
	public List<CustomerOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<CustomerOrder> orders) {
		this.orders = orders;
	}
	public List<PurchaseItem> getPuchaseItems() {
		return puchaseItems;
	}
	public void setPuchaseItems(List<PurchaseItem> puchaseItems) {
		this.puchaseItems = puchaseItems;
	}

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String emailId, String password, String firstName, String lastName, String country, String gender,
			Date dateOfBirth) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", country=" + country + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
	}

	@Transient
	public String getDiscriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}

}
