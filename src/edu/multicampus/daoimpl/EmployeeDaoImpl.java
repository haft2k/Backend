package daoimpl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.EmployeeDAO;
import model.Employee;
import utils.DBConnection;


public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		// B1: Kết nối được vào Database Server
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// B2: Định nghĩa câu truy vấn và thực hiện truy vấn
			String sql = "SELECT * FROM employee";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Employee emp = new Employee();

			// B3: Xử lý kết quả trả về
			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setFullName(rs.getString("fullname"));
				emp.setEmail(rs.getString("email"));
				emp.setHourWorkPerDay(rs.getInt("hour_work_per_day"));
				emp.setLongWork(rs.getInt("long_work"));
				emp.setFixedSalary(rs.getDouble("fixed_salary"));
				emp.setTotalSalary(rs.getDouble("total_salary"));
				emp.setOutsideServiceNumber(rs.getInt("outside_service_number"));
			}
			// B4: Đóng kết nối
			dbc.disconnectDB();
			return emp;
		} catch (SQLException e) {
			// TODO Auto-generated catch stub
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeebyName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getEmployeeByEmail(String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNameById(int id) { return null; }

	@Override
	public List<Employee> getAllEmployee() {

		List<Employee> listOfEmps = new ArrayList<Employee>();
		// Quy trình 4 bước:
		// B1: Kết nối được vào Database Server
		// "jdbc:mysql://localhost:3306/employeemanagement";
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();

			// B2: Định nghĩa câu truy vấn và thực hiện truy vấn
			String sql = "SELECT * FROM employee";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			// B3: Xử lý kết quả trả về
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setFullName(rs.getString("fullname"));
				emp.setEmail(rs.getString("email"));
				emp.setHourWorkPerDay(rs.getInt("hour_work_per_day"));
				emp.setLongWork(rs.getInt("long_work"));
				emp.setFixedSalary(rs.getDouble("fixed_salary"));
				emp.setTotalSalary(rs.getDouble("total_salary"));
				emp.setOutsideServiceNumber(rs.getInt("outside_service_number"));

				// Đưa đối tượng vào Danh sách lưu
				listOfEmps.add(emp);

			}
			// B4: Đóng kết nối
			// conn.close();
			dbc.disconnectDB();
			return listOfEmps;

		} catch (SQLException e) {
			// TODO Auto-generated catch stub
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		// Quy trình 4 bước:
		// B1: Kết nối được vào Database Server
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// B2: Định nghĩa câu truy vấn và thực hiện truy vấn
			String sql = "INSERT INTO employee (fullname, email) VALUES (?,?)";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			int numberOfRecords = ps.executeUpdate();
			// B3: Xử lý kết quả trả về
			if (numberOfRecords != 0) {
				System.out.println("Ban ghi duoc luu thanh cong");
			}
			// B4: Đóng kết nối
			dbc.disconnectDB();

		} catch (SQLException e) {
			// TODO Auto-generated catch stub
			e.printStackTrace();
		}
		System.out.println("Ban ghi khong the luu");
	}

	@Override
	public void updateEmployee(Employee employee) {
		// // TODO Auto-generated method stub
		// // Quy trình 4 bước:
		// // B1: Kết nối được vào Database Server
		// // Coi như nhân viên muốn xóa có tồn tại
		// try {
		// DBConnection dbc = new DBConnection();
		// dbc.connectDB();
		// // B2: Định nghĩa câu truy vấn và thực hiện truy vấn
		// String sql = "UPDATE FOR employee (fullname, email)
		// VALUES (?,?)";
		// PreparedStatement ps =
		// dbc.getConn().prepareStatement(sql);
		// int numberOfRecords = ps.executeUpdate();
		// // B3: Xử lý kết quả trả về
		// if (numberOfRecords != 0) {
		// System.out.println("Ban ghi da duoc cap nhat");
		// }
		// // B4: Đóng kết nối
		// dbc.disconnectDB();
		//
		// } catch (SQLException e) {
		// // TODO Auto-generated catch stub
		// e.printStackTrace();
		// }
		// System.out.println("Ban ghi khong the cap nhat");
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		// Quy trình 4 bước:
		// B1: Kết nối được vào Database Server
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// B2: Định nghĩa câu truy vấn và thực hiện truy vấn
			String sql = "DELETE FROM employee WHERE id = ?";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ps.setInt(1, id);
			// Xác nhận từ người dùng
			int confirm = JOptionPane.showConfirmDialog(null, "Ban co chac chan xoa NV co ma: " + id);
			if (confirm == 0) {
				int numberOfRecords = ps.executeUpdate();
				// B3: Xử lý kết quả trả về
				if (numberOfRecords != 0) {
					System.out.println("Ban ghi da duoc xoa");
				} else {
					System.out.println("Ban ghi khong the xoa bo");
				}
			}
			// B4: Đóng kết nối
			dbc.disconnectDB();

		} catch (SQLException e) {
			// TODO Auto-generated catch stub
			e.printStackTrace();
		}

	}

	@Override
	public void deleteEmployee(Employee employee) {}

}
