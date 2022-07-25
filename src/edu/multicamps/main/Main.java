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
package edu.multicamps.main;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.multicamps.dao.EmployeeDAO;
import edu.multicamps.daoimpl.EmployeeDaoImpl;
import edu.multicamps.model.Employee;


/**
 *
 * @author falcon
 */
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EmployeeDAO empDAO = new EmployeeDaoImpl();
		List<Employee> listOfEmps = new ArrayList<Employee>();

		int choice;
		do {
			menu();
			System.out.print("Enter choice: ");
			choice = scanner.nextInt();
			System.out.println();

			switch (choice) {
				case 1:
					listOfEmps = empDAO.getAllEmployee();

					for (Employee emp : listOfEmps) {
						System.out.println(emp.toString());
					}

					break;

				case 2:
					int id;
					do {
						System.out.print("ID want search ");
						id = scanner.nextInt();
						if (id > 0) System.out.println(empDAO.getEmployeeByID(id).toString());
						System.out.println();
					} while (id < 0);

					break;

				case 3:

				default:
					System.out.println("No have function you choice. Please try again!");
					break;
			}

		} while (choice != 0);
	}

	static void menu() {
		System.out.println("Choose one of this options:");
		System.out.println("1. Print all employee");
		System.out.println("2. Print employee by id");

	}

}
