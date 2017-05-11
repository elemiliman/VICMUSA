package org.vicmusa.church.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_address")
public class UserAddress {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = true)
	private String gender;
	
	@Column(nullable = true)
	private String dob;
	
	@Column(nullable = true)
	private String addressLineOne;
	
	@Column(nullable = true)
	private String addressLineTwo;
	
	@Column(nullable = true)
	private String city;
	
	@Column(nullable = true, length = User.ZIP_CODE_MAX)
	private String zipCode;
	
	@Column(nullable = true)
	private String state;
	
	@Column(nullable = true)
	private String country;
	
	@Column(nullable = true)
	private String profilePicture;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddressLineOne() {
		return addressLineOne;
	}
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}
	public String getAddressLineTwo() {
		return addressLineTwo;
	}
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
}
