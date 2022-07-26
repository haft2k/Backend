package edu.multicampus.main;


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

import edu.multicampus.daoimpl.EmployeeDAOImpl;
import edu.multicampus.daoimpl.UserDAOImpl;
import edu.multicampus.model.Employee;
import edu.multicampus.model.User;


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
				System.out.println("Welcome, " + u.getFirstName() + " - " + u.getLastName());

				showMenu();
			} else {
				System.out.println("Login fail.");
			}

		} while (isLogin);
	}

	private static void showMenu() {
		do {
			System.out.println("List Function:");
			System.out.println("1. Information employee with ID");
			System.out.println("2. Information employee with name");
			System.out.println("3. Information employee with email");
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
					System.out.println("Information employee with ID");
					int id = new Scanner(System.in).nextInt();
					displayEmployeeById(id);
					break;

				case 2:
					System.out.println("Information employee with name");
					String name = new Scanner(System.in).nextLine();
					displayEmployeeByName(name);
					break;

				case 3:
					System.out.println("Information employee with Email");
					String email = new Scanner(System.in).nextLine();
					displayEmployeeByEmail(email);
					break;

				case 5:
					exportEmployeeToXLSX();
					break;

				case 6:
					addNewEmployee();
					break;

				case 8:
					deleteEmployee();
					break;

				default:
					isEnding = true;
					System.out.println("See yah!");
					System.exit(0);
			}
		} while (isEnding);
	}

	public static void displayEmployeeById(int id) {
		EmployeeDAOImpl edi = new EmployeeDAOImpl();

		System.out.println("ID employee vien la " + id);
		System.out.println(edi.getEmployeeById(id).toString());

		System.out.println("you want continue? (0: Stop - 1: Countinue)");
		int answer = new Scanner(System.in).nextInt();
		if (answer == 0) {
			isEnding = true;
		}
	}

	public static void displayEmployeeByName(String name) {
		System.out.println("Employee is " + name);
	}

	public static void displayEmployeeByEmail(String email) {
		System.out.println("Email Employee is : " + email);
	}

	public static void addNewEmployee() {
		System.out.println("Add new employee");
		System.out.println("Full name: ");
		String fullname = new Scanner(System.in).nextLine();
		System.out.println("Email employee: ");
		String email = new Scanner(System.in).nextLine();

		Employee emp = new Employee(fullname, email);
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		edi.saveEmployee(emp);

	}

	public static void deleteEmployee() {
		System.out.println("Delete data Employee");
		System.out.println("ID Employee want delete: ");
		int empID = new Scanner(System.in).nextInt();

		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		if (edi.getEmployeeById(empID) != null) {
			edi.deleteEmployee(empID);
		} else {
			JOptionPane.showConfirmDialog(null,
									"ID employee" + empID + "no have information in database");
			System.out.println("ID Employee dose not exist");
		}
	}

	public static void exportEmployeeToXLSX() {
		// 1. Create 1 new sheet (trống) - blank workbook -> new file
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 2. Create 1 new sheet (trống)
		XSSFSheet sheet = workbook.createSheet("Employee Data");

		// 3. Tạo đối tượng lưu trữ dữ liệu chuẩn bị lưu vào Excel
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "ID", "FULLNAME", "EMAIL", "HOUR WORK PER DAY", "LONG WORK",
								"FIXED SALARY", "OUTSIDE SERVICE NUMBER", "TOTAL SALARY" });

		List<Employee> listOfEmployee = new ArrayList<Employee>();
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		listOfEmployee = edi.getAllEmployee();

		for (Employee e : listOfEmployee) {
			data.put(String.valueOf(e.getId()), new Object[] { e.getId(), e.getFullName(),
									e.getEmail(), e.getHourWorkPerDay(), e.getLongWork(),
									e.getFixedSalary(), e.getOutsideServiceNumber(),
									e.getTotalSalary() });

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
				else if (obj instanceof Integer) cell.setCellValue((Integer) obj); // Kiểm tra nếu
																					// dữ liệu là
																					// kiểu số
			}
		}

		// starting write data to file
		try {
			FileOutputStream out = new FileOutputStream(new File(
									"C:\\Users\\Thinkpad E14\\Documents\\Employee Data.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("Employee Data.xlsx created successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
