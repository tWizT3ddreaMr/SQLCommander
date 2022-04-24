package me.tWizT3d_dreamr.SQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLExe{
	  private Connection connection;
	  private String host, database, username, password;
	  private int port;
	  private static Statement statement;
public SQLExe(String host,int port, String database,String username,String password) throws ClassNotFoundException, SQLException {
	  	this.host=host;
	  	this.port=port;
	  	this.database=database;
	  	this.username=username;
	  	this.password=password;
	  	openConnection();
	  	statement = connection.createStatement(); 
	  }

	public void openConnection() throws ClassNotFoundException
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException classNotFoundException)
		{
			try{
			Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException classNotFoundException2)
			{
				System.out.println("DoublFail");
			}
		}
		try
		{
		this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?" + 
		        "&autoReconnect=true&wait_timeout=31536000&interactive_timeout=31536000&useUnicode=true&characterEncoding=utf8&useSSL=" + 
		        "false", this.username, this.password);
		}
		catch (Exception e)
		{
			System.out.println("Failed Database Connection: " + e);
		}
		
	}

	public void run(String SQL) throws SQLException {
		statement.execute(SQL);
	}
	}