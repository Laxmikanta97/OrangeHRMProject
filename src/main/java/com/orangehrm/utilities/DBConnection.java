package com.orangehrm.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;

public class DBConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/orangehrm";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";

	public static Connection getDBConnection() {
		System.out.println("Starting DB connection......");
		try {
			Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("DB connection successfull");
			return conn;
		} catch (SQLException e) {
			System.out.println("DB connection failed");
			e.printStackTrace();
			return null;
		}

	}

	public static Map<String, String> getEmployeeDetails(String employee_id) {
		String query = "SELECT emp_firstname,emp_middle_name,emp_lastname FROM hs_hr_employee WHERE employee_id="
				+ employee_id;
		Map<String, String> employeedetails = new HashMap<>();

		try {
			Connection conn = getDBConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("Query executing:" + query);

			if (rs.next()) {
				String firstName = rs.getString("emp_firstname");
				String middleNmae = rs.getString("emp_middle_name");
				String lastName = rs.getString("emp_lastname");
				// store in map
				employeedetails.put("emp_firstname", firstName);
				employeedetails.put("emp_middle_name", middleNmae);
				employeedetails.put("emp_lastname", lastName);
				System.out.println("Employee data fetched");
			}

			else {
				System.out.println("No employee data found");
			}

		} catch (SQLException e) {
			System.out.println("Error while fetching query");
			e.printStackTrace();
		}
		return employeedetails;
	}
}
