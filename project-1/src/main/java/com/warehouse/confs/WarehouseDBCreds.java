package com.warehouse.confs;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class WarehouseDBCreds {
	
	private static WarehouseDBCreds instance;
	private String url;
	private String username;
	private String password;
	
	private WarehouseDBCreds() {
		try {
			// Load it into memory immediately so that I have it
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Read the properties (key/value pairs) from the application.properties
			try (InputStream input = WarehouseDBCreds.class.getClassLoader()
					.getResourceAsStream("application.properties")){
				// Properties object
				Properties props = new Properties();
				props.load(input);
				
				// Grab out the keys that I want
				this.url = props.getProperty("db.url");
				this.username = props.getProperty("db.username");
				this.password = props.getProperty("db.password");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
