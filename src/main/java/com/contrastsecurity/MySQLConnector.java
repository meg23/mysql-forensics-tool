package com.contrastsecurity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MySQLConnector {

	private static Connection connection = null;

	public static Connection getConnection() throws IOException {

		try {
			
			String installDir = System.getProperty("contrast.home");
	
			EsapiFileDecryptor prop = new EsapiFileDecryptor();
			prop.setEsapiProperties(FileFinder.fileFind(installDir, "ESAPI.properties"));
			prop.setPropertyFile(FileFinder.fileFind(installDir, "database.properties"));
	
			dbPass = prop.getProperty("jdbc.pass");
			dbUser = prop.getProperty("jdbc.user");
			dbPort = prop.getProperty("jdbc.port");
			dbHost = prop.getProperty("jdbc.host");
			dbSchema = prop.getProperty("jdbc.schema");
		
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			String dbUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbSchema;
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			return connection;

		} catch (SQLException e) {

			System.out.println(e.toString());
			return connection;

		}
	}

	private static String dbPass;
	private static String dbUser;
	private static String dbHost;
	private static String dbPort;
	private static String dbSchema;

}
