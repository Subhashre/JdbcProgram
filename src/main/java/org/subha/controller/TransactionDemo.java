package org.subha.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/subhadb"; // jdbc:mysql://hostname/ databaseName

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			// Class.forName("com.mysql.jdbc.Driver");

			// or

			Driver myDriver;
			myDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(myDriver);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, "root", "");
			conn.setAutoCommit(false);
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			 String sql = "SELECT * FROM `employee`";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("ENo");
				String first = rs.getString("EName");
				int salary = rs.getInt("ESalary");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", First: " + first);
				System.out.println(", Salary: " + salary);
			}
			
			sql = "INSERT INTO `employee` (`ENo`, `EName`, `ESalary`, `Designation`) VALUES ('7', 'Dada', '10000', 'Lawyer') ";
			stmt = conn.createStatement();
			
//			System.out.println("Nos of row affescted :" + stmt.execute(sql));
			System.out.println("Nos of row affescted :" + stmt.executeUpdate(sql));
			
			
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}

