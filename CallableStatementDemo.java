package org.subha.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CallableStatementDemo {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/subhadb"; // jdbc:mysql://hostname/ databaseName

	public static void main(String[] args) {

		Connection conn = null;
		CallableStatement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,"root","");
			String sqlQuery =" call GetAllEmployee()";
			stmt = conn.prepareCall(sqlQuery);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.print("id = "+ rs.getInt("ENo") +" ,");
				System.out.print("Salary = "+rs.getInt("ESalary")+" ,");
				System.out.println("Name = "+rs.getString("EName"));
			}
			System.out.println("Callable with input parameter");
			sqlQuery = "call getEmployeeById(?)";
			stmt = conn.prepareCall(sqlQuery);
			stmt.setInt(1, 2);
			rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.print("id = "+ rs.getInt("ENo") +" ,");
				System.out.print("Salary = "+rs.getInt("ESalary")+" ,");
				System.out.println("Name = "+rs.getString("EName"));
			}
			
			stmt.close();
			conn.close();
			
		}catch (SQLException e) {
			 e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
