package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:sqlite:data.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void defaultSchema() {

		try {
			Connection connect = getConnection();
			if (connect == null)
				System.out.print("Connect is a null");
			String queryString = "CREATE TABLE IF NOT EXISTS User(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,"
					+ "name TEXT NOT NULL," + "username TEXT NOT NULL UNIQUE," + "email TEXT NOT NULL,"
					+ "password TEXT NOT NULL," + "role TEXT NOT NULL," + "category TEXT)";
			if (connect.createStatement() == null)
				System.out.print("Statement is null");
			Statement stmt = connect.createStatement();
			stmt.execute(queryString);
			
			
			String string = "INSERT INTO User(name, username, email, password, role, category)" + "VALUES(?,?,?,?,?,?)";
			PreparedStatement statement = connect.prepareStatement(string);
			statement.setString(1, "admin");
			statement.setString(2, "admin");
			statement.setString(3, "admin@example.com");
			statement.setString(4, "1234");
			statement.setString(5, "ADMIN");
			statement.setString(6, "null");

			statement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
