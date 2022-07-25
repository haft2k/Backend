package edu.multicamps.dao;


import java.util.List;

import edu.multicamps.model.Employee;


public interface EmployeeDAO {
	public Employee getEmployeeByID(int id);

	public Employee getEmployeebyName(String name);

	public void getEmployeeByEmail(String email);

	public String getNameByID(int id);

	public List<Employee> getAllEmployee();

	public void saveEmployee(Employee employee);

	public void updateEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

}