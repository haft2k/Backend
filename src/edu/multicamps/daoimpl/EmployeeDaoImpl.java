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
package edu.multicamps.daoimpl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.multicamps.dao.EmployeeDAO;
import edu.multicamps.model.Employee;


/**
 *
 * @author falcon
 */
public class EmployeeDaoImpl implements EmployeeDAO {
	String dbURL = "jdbc:mysql://localhost:3306/employeemanagement";

	@Override
	public Employee getEmployeeByID(int id) {

		try {
			Connection conn = DriverManager.getConnection(dbURL, "root", "root");
			String sql = "SELECT * FROM employee WHERE id = " + id;

			PreparedStatement preSta = conn.prepareStatement(sql);
			ResultSet rS = preSta.executeQuery();

			Employee emp = new Employee();
			while (rS.next()) {
				emp.setID(rS.getInt("id"));
				emp.setFullName(rS.getString("fullname"));
				emp.setEmail(rS.getString("email"));
				emp.setHourWorkPerDay(rS.getInt("hour_work_per_day"));
				emp.setLongWork(rS.getInt("long_work"));
				emp.setFixSalary(rS.getDouble("fixed_salary"));
				emp.setOutsideServiceNumber(rS.getInt("outside_service_number"));
				emp.setTotalSalary(rS.getDouble("total_salary"));
			}
			return emp;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeebyName(String name) {
		return null;
	}

	@Override
	public void getEmployeeByEmail(String email) {}

	@Override
	public String getNameByID(int id) {
		return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> listOfEmployees = new ArrayList<Employee>();
		/* Quy trinh 4 buoc */
		// connect to database
		try {
			Connection conn = DriverManager.getConnection(dbURL, "root", "root");
			// b2: dinh nghi cau truy van
			String sql = "SELECT * FROM employee";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// b3: xy ly ket qua tra ve
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setID(rs.getInt("id"));
				emp.setFullName(rs.getString("fullname"));
				emp.setEmail(rs.getString("email"));
				emp.setHourWorkPerDay(rs.getInt("hour_work_per_day"));
				emp.setLongWork(rs.getInt("long_work"));
				emp.setFixSalary(rs.getDouble("fixed_salary"));
				emp.setTotalSalary(rs.getDouble("total_salary"));
				emp.setOutsideServiceNumber(rs.getInt("outside_service_number"));
				listOfEmployees.add(emp);
			}

			// b4: close connection
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfEmployees;
	}

	@Override
	public void saveEmployee(Employee employee) {}

	@Override
	public void updateEmployee(Employee employee) {}

	@Override
	public void deleteEmployee(Employee employee) {}

}
