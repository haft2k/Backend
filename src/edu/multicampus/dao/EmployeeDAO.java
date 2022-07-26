package edu.multicampus.dao;


import java.util.List;

import edu.multicampus.model.Employee;


public interface EmployeeDAO {
	public Employee getEmployeeById(int id);// Dùng cho TRUY VẤN (SELECT)

	public Employee getEmployeebyName(String name);// Dùng cho TRUY VẤN (SELECT)

	public void getEmployeeByEmail(String email);// Dùng cho TRUY VẤN (SELECT)

	public String getNameById(int id); // Dùng cho TRUY VẤN (SELECT)

	public List<Employee> getAllEmployee(); // Dùng cho TRUY VẤN (SELECT)

	public void saveEmployee(Employee employee);// Dùng cho THÊM dữ liệu (INSERT INTO)

	public void updateEmployee(Employee employee);// Dùng cho SỬA dữ liệu (UPDATE)

	public void deleteEmployee(Employee employee);// Dùng cho XÓA dữ liệu (DELETE FROM)

	public void deleteEmployee(int id);
}