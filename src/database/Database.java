package database;

import java.sql.*;

/**
 * Project: Health-Tracker
 * File Name: Database.java
 * <p>
 * Created by David on 5/4/2017.
 */
public class Database
{
	public static void main(String args[]) throws SQLException
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");

			//here sonoo is database name, root is username and password
			stmt = con.createStatement();
			rs = stmt.executeQuery("SHOW DATABASES;");

			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}

			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from emp");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			stmt.close();
			con.close();
			rs.close();
		}
	}
}