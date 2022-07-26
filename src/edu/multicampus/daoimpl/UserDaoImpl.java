package daoimpl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDAO;
import model.User;
import utils.DBConnection;


public class UserDAOImpl implements UserDAO {

	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		// Quy trình 4 bước:
		// B1: Kết nối được vào Database Server
		// String dbURL =
		// "jdbc:mysql://localhost:3306/employeemanagement";
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
			// Connection conn = DriverManager.getConnection(dbURL,
			// "root", "kiTs@2022");
			// B2: Định nghĩa câu truy vấn và thực hiện truy vấn
			String sql = "SELECT * FROM user WHERE email=? AND password=?";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			// B3: Xử lý kết quả trả về
			if (rs.next()) {
				// Tạo đối tượng, lưu thông tin Bản ghi
				User u = new User();
				u.setEmail(rs.getString("email"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				// B4: Đóng kết nối
				// conn.close();
				dbc.disconnectDB();
				return u;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
