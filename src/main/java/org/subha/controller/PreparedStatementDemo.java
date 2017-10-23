package org.subha.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatementDemo {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/subhadb"; // jdbc:mysql://hostname/ databaseName

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,"root","");
			
			String sqlQuery = "SELECT * FROM `employee` WHERE ENo = ?";
			stmt = conn.prepareStatement(sqlQuery);
			stmt.setInt(1,1);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.print("id = "+ rs.getInt("ENo") +" ,");
				System.out.print("Salary = "+rs.getInt("ESalary")+" ,");
				System.out.println("Name = "+rs.getString("EName"));
			}
			sqlQuery ="INSERT INTO `employee` (`ENo`, `EName`, `ESalary`, `Designation`) VALUES (?, ?, ?, ?), (?, ?, ?, ?) ";
			stmt = conn.prepareStatement(sqlQuery);			
			stmt.setInt(1, 3);
			stmt.setString(2, "Pika");
			stmt.setInt(3, 30000);
			stmt.setString(4, "TE");
			
			stmt.setInt(5, 4);
			stmt.setString(6, "Bubu");
			stmt.setInt(7, 55000);
			stmt.setString(8, "TE");
			
			System.out.println("No of lines affescted : "+stmt.executeUpdate());
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	finally {
	
	}
	}

}
