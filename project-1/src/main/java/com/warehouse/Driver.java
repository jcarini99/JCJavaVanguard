package com.warehouse;

public class Driver {
		
		//Initializer
		static {
			try {
				//Load into memory
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	public static void main(String[] args) {
		
	}

}
