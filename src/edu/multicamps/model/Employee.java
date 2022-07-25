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

/**
 *
 * @author falcon
 */
public class Employee {
	private int id;
	private String fullName, email;
	private int hourWorkPerDay, longWork, outsideServiceNumber;
	private double fixedSalary, totalSalary;

	public Employee() {}

	public void setID(int id) { this.id = id; }

	public int getID() { return this.id; }

	public String getFullName() { return fullName; }

	public void setFullName(String fullName) { this.fullName = fullName; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public int getHourWorkPerDay() { return hourWorkPerDay; }

	public void setHourWorkPerDay(int hourWorkPerDay) { this.hourWorkPerDay = hourWorkPerDay; }

	public int getLongWork() { return longWork; }

	public void setLongWork(int longWork) { this.longWork = longWork; }

	public int getOutsideServiceNumber() { return outsideServiceNumber; }

	public void setOutsideServiceNumber(int outsideServiceNumber) { this.outsideServiceNumber = outsideServiceNumber; }

	public double getFixSalary() { return fixedSalary; }

	public void setFixSalary(double fixedSalary) { this.fixedSalary = fixedSalary; }

	public double getTotalSalary() { return totalSalary; }

	public void setTotalSalary(double totalSalary) { this.totalSalary = totalSalary; }

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", email=" + email + ", hourWorkPerDay="
				+ hourWorkPerDay + ", longWork=" + longWork + ", outsideServiceNumber=" + outsideServiceNumber
				+ ", fixedSalary=" + fixedSalary + ", totalSalary=" + totalSalary + "]";
	}

}
