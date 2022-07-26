package edu.multicampus.model;


import java.sql.Date;


public class User {
	/*
	 * initialize attribute
	 */
	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private Date dob;
	private boolean sex;
	private String phone;
	private boolean isAdmin;
	private boolean isActive;

	public User() {
		super();
	}

	public User(String email, String password, String firstName, String lastName, String address,
							Date dob, boolean sex, String phone, boolean isAdmin,
							boolean isActive) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.sex = sex;
		this.phone = phone;
		this.isAdmin = isAdmin;
		this.isActive = isActive;
	}

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getAddress() { return address; }

	public void setAddress(String address) { this.address = address; }

	public Date getDob() { return dob; }

	public void setDob(Date dob) { this.dob = dob; }

	public boolean isSex() { return sex; }

	public void setSex(boolean sex) { this.sex = sex; }

	public String getPhone() { return phone; }

	public void setPhone(String phone) { this.phone = phone; }

	public boolean isAdmin() { return isAdmin; }

	public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }

	public boolean isActive() { return isActive; }

	public void setActive(boolean isActive) { this.isActive = isActive; }

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName="
								+ firstName + ", lastName=" + lastName + ", address=" + address
								+ ", dob=" + dob + ", sex=" + sex + ", phone=" + phone
								+ ", isAdmin=" + isAdmin + ", isActive=" + isActive + "]";
	}

}
