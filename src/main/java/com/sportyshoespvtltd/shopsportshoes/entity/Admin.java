package com.sportyshoespvtltd.shopsportshoes.entity;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String emailId, String password, String firstName, String lastName, String country, String gender,
			Date dateOfBirth) {
		super(emailId, password, firstName, lastName, country, gender, dateOfBirth);
		// TODO Auto-generated constructor stub
	}

}
