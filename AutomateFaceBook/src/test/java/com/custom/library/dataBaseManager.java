package com.custom.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dataBaseManager {
	private String dataBaseServerName = "localhost";
	private String dataBasePort = "";
	private String dataBaseName = "";
	private String userName = "";
	private String userPassword = "";
	private String connectionURL = "jdbc:oracle:hr@//" + dataBaseServerName + ":" + dataBasePort + "/" + dataBaseName;

	private Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;

	private void connectToOracleDB() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		connection = DriverManager.getConnection(connectionURL, userName, userPassword);
		statement = connection.createStatement();

	}
	
	public static ResultSet runSQLQuery(String sqlQuery) throws SQLException
	{
		resultSet = statement.executeQuery(sqlQuery);
		return resultSet ;
	}
	
	public static void main (String[] args) throws SQLException
	{	
		dataBaseManager dbManager = new dataBaseManager();
		ResultSet data = dbManager.runSQLQuery("select * from employees");
		System.out.println("result : " + data);
	}

}
