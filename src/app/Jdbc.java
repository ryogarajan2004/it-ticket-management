package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		try {
			Connection connection=DriverManager.getConnection("jdbc:sqlite:data.db");
			Statement stmt=connection.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS ticket("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+"name TEXT NOT NULL)");
			stmt.execute("INSERT INTO ticket values(1,\"Hello world\")");

			
			
			
			ResultSet set=stmt.executeQuery("SELECT * FROM Ticket");
			System.out.print(set.getString(2));
			connection.close();
		}catch(Exception e) {
			System.out.println("SQL Exception");
			System.out.print(e);
		}

	}

}
