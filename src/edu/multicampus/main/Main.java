package main;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import daoimpl.EmployeeDAOImpl;
import daoimpl.UserDAOImpl;
import model.Employee;
import model.User;


public class Main {
	static boolean isEnding = false;

	public static void main(String[] args) {

		showLogin();
	}

	// Sửa chỗ này -> Hiển thị Giao diện Login
	public static void showLogin() {
		boolean isLogin = false;
		do {

			System.out.println("---HE THONG QUAN LY NHAN VIEN");
			System.out.println("Nhap thong tin de dang nhap");
			System.out.println("Tai khoan:");
			String userName = new Scanner(System.in).nextLine();
			System.out.println("Mat khau:");
			String password = new Scanner(System.in).nextLine();

			UserDAOImpl udi = new UserDAOImpl();
			User u = udi.getUserByEmailAndPassword(userName, password);
			if (u != null) {
				isLogin = true;
				System.out.println("Xin chao" + u.getLastName() + " " + u.getFirstName());

				showMenu();
			} else {
				System.out.println("Dang nhap that bai. Check lai hang ho");
			}

		} while (isLogin);
	}

	private static void showMenu() {
		do {
			System.out.println("Moi ban chon chuc nang can xu ly:");
			System.out.println("1. Lay thong tin NV theo ma");
			System.out.println("2. Lay thong tin NV theo ten");
			System.out.println("3. Hien thi NV theo email");
			System.out.println("4. Hien thi NV theo ma");
			System.out.println("5. Hien thi danh sach NV");
			System.out.println("6. Them 1 nhan vien moi");
			System.out.println("7. Sua thong tin NV");
			System.out.println("8. Xoa 1 NV");
			System.out.println("Other. Thoat");
			System.out.println("Lua chon cua ban la: ");
			int choice = new Scanner(System.in).nextInt();
			switch (choice) {
				case 1:
					System.out.println("Ma nhan vien muon lay thong tin");
					int id = new Scanner(System.in).nextInt();
					displayEmployeeById(id);
					break;
				case 2:
					System.out.println("Ten nhan vien muon lay thong tin");
					String name = new Scanner(System.in).nextLine();
					displayEmployeeByName(name);
					break;
				case 3:
					System.out.println("Email nhan vien muon lay thong tin");
					String email = new Scanner(System.in).nextLine();
					displayEmployeeByEmail(email);
					break;
				case 6:
					addNewEmployee();
					break;
				case 8:
					deleteEmployee();
					break;
				default:
					isEnding = true;
					System.out.println("Cam on ban, hen gap lai!");
					System.exit(0);
			}
		} while (isEnding);
	}

	public static void displayEmployeeById(int id) {

		System.out.println("Nhan vien la" + id);

		System.out.println("Ban co muon tiep tuc khong? (0: dung / 1: tiep tuc)");
		int answer = new Scanner(System.in).nextInt();
		if (answer == 0) {
			isEnding = true;

		}

	}

	public static void displayEmployeeByName(String name) { System.out.println("Nhan vien la" + name); }

	public static void displayEmployeeByEmail(String email) { System.out.println("Nhan vien la" + email); }

	public static void addNewEmployee() {
		System.out.println("Them nhan vien moi");
		System.out.println("Ho va ten NV: ");
		String fullname = new Scanner(System.in).nextLine();
		System.out.println("Email NV: ");
		String email = new Scanner(System.in).nextLine();

		Employee emp = new Employee(fullname, email);
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		edi.saveEmployee(emp);

	}

	public static void deleteEmployee() {
		System.out.println("Xoa du lieu NV");
		System.out.println("Ma NV can xoa: ");
		int empID = new Scanner(System.in).nextInt();

		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		if (edi.getEmployeeById(empID) != null) {
			edi.deleteEmployee(empID);
		} else {
			JOptionPane.showConfirmDialog(null, "Ma NV" + empID + "khong ton tai trong CSDL");
			System.out.println("Ma NV khong ton tai");
		}
	}

	public static void exportEmployeeToXLSX() {
		// 1. Tạo 1 số tính mới (trống) - blank workbook -> tệp mới
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 2. Tạo ra 1 trang tính mới (trống)
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// 3. Tạo đối tượng lưu trữ dữ liệu chuẩn bị lưu vào Excel
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "ID", "FULLNAME", "EMAIL", "HOUR WORK PER DAY", "LONG WORK", "FIXED SALARY",
				"OUTSIDE SERVICE NUMBER", "TOTAL SALARY" });//
		List<Employee> listOfEmployee = new ArrayList<Employee>();
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		listOfEmployee = edi.getAllEmployee();
		for (Employee e : listOfEmployee) {
			data.put(String.valueOf(e.getId()),
					new Object[] { e.getId(), e.getFullName(), e.getEmail(), e.getHourWorkPerDay(), e.getLongWork(),
							e.getFixedSalary(), e.getOutsideServiceNumber(), e.getTotalSalary() });

		}
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) { // Duyệt từng bản ghi thông qua key
			Row row = sheet.createRow(rownum++); // chỉ số hàng chạy trong excel
			Object[] objArr = data.get(key); // thông qua key -> lấy đât tương ứng từng hàng
			int cellnum = 0; // Trong 1 hàng, có nhiều cột -> chạy chỉ số cột
			for (Object obj : objArr) { // Duyệt cột để hiển thị ra từng ô (cột)
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String) // Kiểm tra nếu dữ liệu là kiểu chuỗi
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer) cell.setCellValue((Integer) obj); // Kiểm tra nếu dữ liệu là kiểu số
			}
		}

		// Tiến hành ghi file ra đĩa
		try {
			FileOutputStream out = new FileOutputStream(new File("D:\\Employee Data_1.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Employee Data.xlsx created successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
