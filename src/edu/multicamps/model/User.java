/*
 * GumBox Inc
 * (c) 2022 GumBox Inc. All rights reserved.
 * address: Viet Nam
 * This software is the confidential and proprietary
 * information of GumBox, Inc
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it
 * only in
 * accordance with the terms of the license agreement you
 * entered into
 * with GumBox
 */
package edu.multicamps.model;


import java.sql.Date;


/**
 *
 * @author falcon
 */
public class User {
	private int id;
	private String email, passWord, firstName, lastName, address, phone;
	private Date dob;
	private boolean sex, isAdmin, isActive;

	public User() {}

	public void setID(int id) { this.id = id; }

	public int getID() { return this.id; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public String getPassWord() { return passWord; }

	public void setPassWord(String passWord) { this.passWord = passWord; }

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getAddress() { return address; }

	public void setAddress(String address) { this.address = address; }

	public String getPhone() { return phone; }

	public void setPhone(String phone) { this.phone = phone; }

	public Date getDob() { return dob; }

	public void setDob(Date dob) { this.dob = dob; }

	public boolean isSex() { return sex; }

	public void setSex(boolean sex) { this.sex = sex; }

	public boolean isAdmin() { return isAdmin; }

	public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

	public boolean isActive() { return isActive; }

	public void setActive(boolean isActive) { this.isActive = isActive; }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", passWord=" + passWord + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", phone=" + phone + ", dob=" + dob + ", sex="
				+ sex + ", isAdmin=" + isAdmin + ", isActive=" + isActive + "]";
	}

}
