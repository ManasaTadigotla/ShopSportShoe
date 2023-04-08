package com.sportyshoespvtltd.shopsportshoes.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	String houseNum;
	String street;
	String townOrCity;
	String pincode;
	public String getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTownOrCity() {
		return townOrCity;
	}
	public void setTownOrCoty(String townOrCity) {
		this.townOrCity = townOrCity;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
