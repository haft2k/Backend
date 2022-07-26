package edu.multicampus.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.multicampus.dao.UserDAO;
import edu.multicampus.model.Employee;
import edu.multicampus.model.User;
import edu.multicampus.utils.DBConnection;

public class UserDaoImpl implements UserDAO {

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		// Quy trình 4 bước:
		// B1. Kết nối được vào Database Server
		
		try {
			DBConnection dbc = new DBConnection();
			dbc.connectDB();
		// B2. Định nghĩa Câu truy vấn và thực hiện truy vấn
			String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
			PreparedStatement ps = dbc.getConn().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
		// B3. Xử lý kết quả trả về
			if(rs.next()) {
				//Tạo đối tượng, lưu thông tin Bản ghi
				User u = new User();
				u.setEmail(rs.getString("email"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				
				// B4. Đóng kết nối
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
