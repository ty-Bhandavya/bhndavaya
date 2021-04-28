package com.jspiders.marriage.frist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class InsertPersonDetails {
	public static void main(String[] args) throws FileNotFoundException {

		Connection CON = null;
		PreparedStatement PSTMT = null;
		try {
			Driver driverref = new Driver();
			DriverManager.registerDriver(driverref);

			String dburl = "jdbc:mysql://localhost:3306/marriage?user=root&password=root";
			CON = DriverManager.getConnection(dburl);

			String query = " insert into person " + " values(?,?,?,?,?) ";

			File file = new File("C:\\FILEHANDLING\\bhandu.jpg");
			FileInputStream inputStream = new FileInputStream(file);

			PSTMT = CON.prepareStatement(query);
			PSTMT.setString(1, args[0]);
			PSTMT.setInt(2, Integer.parseInt(args[1]));
			PSTMT.setDouble(3, Double.parseDouble(args[2]));
			PSTMT.setBinaryStream(4, inputStream);
			PSTMT.setLong(5, Long.parseLong(args[3]));

			int res = PSTMT.executeUpdate();

			if (res != 0) {
				System.out.println("Person Details Inserted");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (CON != null) {
					CON.close();
				}
				if (PSTMT != null) {
					PSTMT.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
}
