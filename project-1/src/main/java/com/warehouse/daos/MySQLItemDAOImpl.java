package com.warehouse.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.warehouse.confs.WarehouseDBCreds;
import com.warehouse.models.Item;

public class MySQLItemDAOImpl implements ItemDAO{

	/*
	 *JDBC Steps:
	 *	- Load JDBC Driver into memory
	 *	- Establish Connection using Driver
	 *	- Create SQL statement
	 *	- Use connection to execute SQL statement
	 *	- Parse ResultSet
	 *	- Close the connection 
	 */
	
	/** 
	 * @return item_id and name  of all Items if found. 
	 * null returned if item not found
	 */
	@Override
	public List<Item> findAllItems() {
		String sql = "SELECT item_id ID, item_name Name FROM item;";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			//Exectute query to return ResultSet of all values returned
			ResultSet rs = ps.executeQuery();
			LinkedList<Item> items = new LinkedList<>();
			
			//Parse ResultSet
			while(rs.next()) {
				//Loop over rows of ResultSet
				Item item = new Item(rs.getInt(1), rs.getString(2));
				items.add(item);
			}
			
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/** 
	 * @return item_id and name of Item using specified item_id if found. 
	 * null returned if item not found  
	 */
	@Override
	public Item findById(int id) {
		String sql = "SELECT item_id ID, item_name Name FROM item WHERE item_id = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			//Exectute query to return ResultSet of all values returned
			ResultSet rs = ps.executeQuery();			
			//Parse ResultSet
			if(rs.next()) {
				return new Item(rs.getInt(1), rs.getString(2));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		
		return null;
	}

	/**
	 * @return Reads item_id as ID and item_name as Name from specified item_name if found. 
	 * null returned if item not found 
	 */
	@Override
	public Item findByName(String name) {
		String sql = "SELECT item_id ID, item_name Name FROM item WHERE item_name = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			//Exectute query to return ResultSet of all values returned
			ResultSet rs = ps.executeQuery();			
			//Parse ResultSet
			if(rs.next()) {
				return new Item(rs.getInt(1), rs.getString(2));				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		
		return null;
	}

	//Creates item_name at auto incremented item_id
	@Override
	public Item createItem(Item item) {
		String sql = "INSERT INTO item (item_id,item_name) VALUES (?,?)";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Start transaction
			conn.setAutoCommit(false);			
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, item.getId());
			ps.setString(2, item.getName());
			//Exectute update to return int of all rows affected
			int rowsAffected = ps.executeUpdate();		
			if(rowsAffected != 0) {
				ResultSet keys = ps.getGeneratedKeys();
				// List all generated keys
				if(keys.next()) {
					int key = keys.getInt(1);
					item.setId(key);
				}
				conn.commit(); //Execute all queries in transaction if success
				return item;
			}else {
				conn.rollback(); //Undo all queries in transaction if failure
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//Updates item_name at specified item_id
	@Override
	public void updateItemName(Item item) {
		String sql = "UPDATE item SET item_name = ? WHERE item_id = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Start transaction
			conn.setAutoCommit(false);	
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getId());
			//Exectute update to return int of all rows affected
			int rowsAffected = ps.executeUpdate();		
			if(rowsAffected != 0) {
				conn.commit(); //Execute all queries in transaction if success
			}else {
				conn.rollback(); //Undo all queries in transaction if failure
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//Deletes one row (item) at specified item_id
	@Override
	public void deleteItem(Item item) {
		String sql = "DELETE FROM item WHERE item_id = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Start transaction
			conn.setAutoCommit(false);			
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, item.getId());
			//Exectute update to return int of all rows affected
			int rowsAffected = ps.executeUpdate();		
			if(rowsAffected != 0) {
				conn.commit(); //Execute all queries in transaction if success
			}else {
				conn.rollback(); //Undo all queries in transaction if failure
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}		
	}

	//Deletes one row (item) at specified item_id
//	@Override
//	public void deleteItem(int id) {
//		String sql = "DELETE FROM item WHERE item_id = ?";
//		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
//			//Start transaction
//			conn.setAutoCommit(false);			
//			//Create PreparedStatement using Connection object
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setInt(1, id);
//			//Exectute update to return int of all rows affected
//			int rowsAffected = ps.executeUpdate();		
//			if(rowsAffected != 0) {
//				conn.commit(); //Execute all queries in transaction if success
//			}else {
//				conn.rollback(); //Undo all queries in transaction if failure
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

	//Deletes multiple rows (items) at item_id specified by id array
//	@Override
//	public void deleteManyItems(int[] id) {
//		for(int i : id) {
//			String sql = "DELETE FROM item WHERE item_id = ?";
//			try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
//				//Start transaction
//				conn.setAutoCommit(false);			
//				//Create PreparedStatement using Connection object
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ps.setInt(1, id[i]);
//				//Exectute update to return int of all rows affected
//				int rowsAffected = ps.executeUpdate();		
//				if(rowsAffected != 0) {
//					conn.commit(); //Execute all queries in transaction if success
//				}else {
//					conn.rollback(); //Undo all queries in transaction if failure
//				}
//			} catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}

}
