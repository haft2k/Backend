package edu.multicampus.main;


import java.util.Scanner;

import edu.multicampus.daoimpl.UserDaoImpl;
import edu.multicampus.model.User;


public class Main {
	static boolean isEnding = false;

	public static void main(String[] args) {

		// Sửa chỗ này > Hiển thị Giao diện LOGIN
		showLogin();

	}

	private static void showLogin() {
		boolean isLogin = false;
		do {
			System.out.println("---- HE THONG QUAN LY NHAN VIEN----");
			System.out.println("Nhập thông tin để Đăng nhập");
			System.out.println("Tai khoan:");
			String userName = new Scanner(System.in).nextLine();
			System.out.println("Mat khau:");
			String passWord = new Scanner(System.in).nextLine();

			UserDaoImpl udi = new UserDaoImpl();
			User u = udi.getUserByEmailAndPassword(userName, passWord);
			if (u != null) {
				isLogin = true;
				System.out.println("Xin chào Bạn " + u.getLastName() + " " + u.getFirstName());
				// Hien thi Menu lua chon Chuc nang cua he thong > 8 method
				// cua Employee
				showMenu();
			} else {
				System.out.println("Dang nhap that bai. Kiem tra Tai khoan va Mat khau");
			}
		} while (!isLogin);
	}

	private static void showMenu() {

		do {
			System.out.println("---Moi ban chon Chuc nang can xu ly:---");
			System.out.println("1. Hien thi thong tin NV theo Ma");
			System.out.println("2. Hien thi thong tin NV theo Ten");
			System.out.println("3. Hien thi thong tin NV theo Email");
			System.out.println("4. Hien thi Ten NV theo Ma");
			System.out.println("5. Hien thi Danh sach NV");
			System.out.println("6. Them mot nhan vien moi");
			System.out.println("7. Sua thong tin Nhan vien");
			System.out.println("8. Xoa mot Nhan vien");
			System.out.println("Other. Thoat");
			System.out.println("Lua chon cua Ban la:");
			int choice = new Scanner(System.in).nextInt();
			switch (choice) {
				case 1:
					System.out.println("Ma nhan vien muon lay thong tin:");
					int id = new Scanner(System.in).nextInt();
					displayEmployeeById(id);
					break;
				case 2:
					System.out.println("Ten nhan vien muon lay thong tin:");
					String name = new Scanner(System.in).nextLine();
					displayEmployeeByName(name);
					break;
				case 3:
					System.out.println("Email nhan vien muon lay thong tin:");
					String email = new Scanner(System.in).nextLine();
					displayEmployeeByEmail(email);
					break;
				default:
					isEnding = true;
					System.out.println("Cam on Ban. Hen gap lai");
					System.exit(0);
			}
		} while (!isEnding);

	}

	public static void displayEmployeeById(int id) {
		// EmployeeDaoImpl edi = new EmployeeDaoImpl();
		// Employee emp = edi.getEmployeeById(id);
		// System.out.println("Thong tin Nhan vien co Ma: " + id);
		// System.out.println(emp.toString());
		System.out.println("Nhan vien la " + id);
		// Ket thuc noi dung PT
		System.out.println("Ban co muon tiep tuc khong? (0: Dung - 1: Tiep tuc)");
		int answer = new Scanner(System.in).nextInt();
		if (answer == 0) {
			isEnding = true;
		}
	}

	public static void displayEmployeeByName(String name) { System.out.println("Nhan vien la " + name); }

	public static void displayEmployeeByEmail(String email) { System.out.println("Nhan vien la " + email); }

}
