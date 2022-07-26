package edu.multicampus.daoimpl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.multicampus.dao.EmployeeDAO;
import edu.multicampus.model.Employee;
import edu.multicampus.utils.DBConnection;


public class EmployeeDaoImpl implements EmployeeDAO {

	@Override
	public Employee getEmployeeById(int id) {

		// Quy trình 4 bước:
		// B1. Kết nối được vào Database Server
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// B2. Định nghĩa Câu truy vấn và thực hiện truy vấn
			String sql = "SELECT * FROM employee WHERE id = ?";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			// B3. Xử lý kết quả trả về
			Employee emp = new Employee();
			while (rs.next()) {
				// Tạo đối tượng, lưu thông tin Bản ghi

				emp.setId(rs.getInt("id"));
				emp.setFullName(rs.getString("fullname"));
				emp.setEmail(rs.getString("email"));
				emp.setHourWorkPerDay(rs.getInt("hour_work_per_day"));
				emp.setLongWork(rs.getInt("long_work"));
				emp.setFixedSalary(rs.getDouble("fixed_salary"));
				emp.setTotalSalary(rs.getDouble("total_salary"));
				emp.setOutsideServiceNumber(rs.getInt("outside_service_number"));

			}
			// B4. Đóng kết nối
			dbc.disconnectDB();

			return emp;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Employee getEmployeeByName(String name) {

		return null;
	}

	@Override
	public void getEmployeeByEmail(String email) {

	}

	@Override
	public String getNameById(int id) {

		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();

			// b2. dinh nghia cau truy van
			String sql = "SELECT fullname FROM employee WHERE id = ?";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			Employee emp = new Employee();
			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setFullName(rs.getString("fullname"));
				emp.setEmail(rs.getString("email"));
				emp.setHourWorkPerDay(rs.getInt("hour_work_per_day"));
				emp.setLongWork(rs.getInt("long_work"));
				emp.setFixedSalary(rs.getDouble("fixed_salary"));
				emp.setOutsideServiceNumber(rs.getInt("outside_service_number"));
				emp.setTotalSalary(rs.getDouble("total_number"));

			}

			return emp.getFullName();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {

		List<Employee> listOfEmps = new ArrayList<Employee>();
		// Quy trình 4 bước:
		// B1. Kết nối được vào Database Server

		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// B2. Định nghĩa Câu truy vấn và thực hiện truy vấn
			String sql = "SELECT * FROM employee";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// B3. Xử lý kết quả trả về
			while (rs.next()) {
				// Tạo đối tượng, lưu thông tin Bản ghi
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setFullName(rs.getString("fullname"));
				emp.setEmail(rs.getString("email"));
				emp.setHourWorkPerDay(rs.getInt("hour_work_per_day"));
				emp.setLongWork(rs.getInt("long_work"));
				emp.setFixedSalary(rs.getDouble("fixed_salary"));
				emp.setTotalSalary(rs.getDouble("total_salary"));
				emp.setOutsideServiceNumber(rs.getInt("outside_service_number"));

				// Đưa đối tượng vào DANH SÁCH lưu
				listOfEmps.add(emp);
			}
			// B4. Đóng kết nối
			dbc.disconnectDB();
			return listOfEmps;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void saveEmployee(Employee emp) {

		// Quy trình 4 bước:
		// B1. Kết nối được vào Database Server
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// B2. Định nghĩa Câu truy vấn và thực hiện truy vấn

			String sql = "INSERT INTO employee (fullname, email) VALUES (?, ?)";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ps.setString(1, emp.getFullName());
			ps.setString(2, emp.getEmail());
			int numberOfRecords = ps.executeUpdate();

			// B3. Xử lý kết quả trả về
			if (numberOfRecords > 0) {
				System.out.println("Ban ghi dc luu thanh cong");
			} else {
				System.out.println("Ban ghi k0 dc luu");
			}

			// B4. Đóng kết nối
			dbc.disconnectDB();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateEmployee(Employee emp) {
		boolean checked = false;

		// Quy trình 4 bước:
		// B1. Kết nối được vào Database Server
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// B2. Định nghĩa Câu truy vấn và thực hiện truy vấn

			String sql = "UPDATE employee  SET fullname = ?, email = ?, total_salary = ? WHERE ID = ?";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			System.out.println("Plesea input ID you need change information");
			int id = new Scanner(System.in).nextInt();

			if (getEmployeeById(id) != null) {
				ps.setString(1, new Scanner(System.in).nextLine());
				ps.setString(2, new Scanner(System.in).nextLine());
				ps.setDouble(3, new Scanner(System.in).nextDouble());
				ps.setInt(4, id);
			} else System.out.println("No have employee");

			int numberOfRecords = ps.executeUpdate();

			// B3. Xử lý kết quả trả về

			// B4. Đóng kết nối
			dbc.disconnectDB();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteEmployee(Employee emp) {

	}

}
