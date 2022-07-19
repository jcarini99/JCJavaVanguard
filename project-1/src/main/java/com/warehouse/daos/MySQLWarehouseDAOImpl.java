package com.warehouse.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.warehouse.confs.WarehouseDBCreds;
import com.warehouse.models.Warehouse;

public class MySQLWarehouseDAOImpl implements WarehouseDAO{

	/*
	 *JDBC Steps:
	 *	- Load JDBC Driver into memory
	 *	- Establish Connection using Driver
	 *	- Create SQL statement
	 *	- Use connection to execute SQL statement
	 *	- Parse ResultSet
	 *	- Close the connection 
	 */
	
	/** Read
	 * @return warehouse_id, warehouse_name, item_id, and item_quantity of all Items if found. 
	 * null returned if item not found
	 */
	@Override
	public List<Warehouse> findAllWarehouses() {
		String sql = "SELECT warehouse_id Warehouse_ID, warehouse_name Name, item_id Item_ID, item_quantity, Item_Quantity FROM warehouse;";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			//Exectute query to return ResultSet of all values returned
			ResultSet rs = ps.executeQuery();
			LinkedList<Warehouse> warehouses = new LinkedList<>();
			
			//Parse ResultSet
			while(rs.next()) {
				//Loop over rows of ResultSet
				Warehouse warehouse = new Warehouse(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				warehouses.add(warehouse);
			}
			
			return warehouses;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/** Read
	 * @return warehouse_id, warehouse_name, item_id, and item_quantity of Item specified by id if found. 
	 * null returned if item not found
	 */
	@Override
	public Warehouse findById(int warehouse_id) {
		String sql = "SELECT warehouse_id Warehouse_ID, warehouse_name Name, item_id Item_ID, item_quantity, Item_Quantity FROM warehouse WHERE warehouse_id = ?;";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, warehouse_id);
			//Exectute query to return ResultSet of all values returned
			ResultSet rs = ps.executeQuery();			
			//Parse ResultSet
			if(rs.next()) {
				return new Warehouse(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		
		return null;
	}

	/** Read
	 * @return warehouse_id, warehouse_name, item_id, and item_quantity of Item specified by name if found. 
	 * null returned if item not found
	 */
	@Override
	public Warehouse findByName(String name) {
		String sql = "SELECT warehouse_id Warehouse_ID, warehouse_name Name, item_id Item_ID, item_quantity, Item_Quantity FROM warehouse WHERE warehouse_name = ?;";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			//Exectute query to return ResultSet of all values returned
			ResultSet rs = ps.executeQuery();			
			//Parse ResultSet
			if(rs.next()) {
				return new Warehouse(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		
		return null;
	}

	/** Create
	 * @return create warehouse with specified item_id and item_quantity 
	 * OR just add new row (item) to existing warehouse with specified warehouse_id and warehouse_name
	 */
	@Override
	public Warehouse createWarehouseItem(Warehouse warehouse) {
		String sql = "INSERT INTO warehouse(warehouse_id,warehouse_name,item_id,item_quantity) VALUES (?,?,?,?);";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Start transaction
			conn.setAutoCommit(false);			
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, warehouse.getWarehouseId());
			ps.setString(2, warehouse.getName());
			ps.setInt(3, warehouse.getItemId());
			ps.setInt(4, warehouse.getItemQuantity());
			//Exectute update to return int of all rows affected
			int rowsAffected = ps.executeUpdate();		
			if(rowsAffected != 0) {
				ResultSet keys = ps.getGeneratedKeys();
				// List all generated keys
				if(keys.next()) {
					return warehouse;
				}
				conn.commit(); //Execute all queries in transaction if success
			}else {
				conn.rollback(); //Undo all queries in transaction if failure
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/** Update
	 * @return change name of warehouse
	 */
	@Override
	public void updateWarehouseName(Warehouse warehouse) {
		String sql = "UPDATE warehouse SET warehouse_name = ? WHERE warehouse_id = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Start transaction
			conn.setAutoCommit(false);			
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, warehouse.getName());
			ps.setInt(2, warehouse.getItemId());
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

	/** Update
	 * @return change quantity of existing item_id in existing warehouse_id
	 */
	@Override
	public void updateItemQuantity(Warehouse warehouse) {
		String sql = "UPDATE warehouse SET item_quantity = ? WHERE warehouse_id = ? AND item_id = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Start transaction
			conn.setAutoCommit(false);			
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, warehouse.getItemQuantity());
			ps.setInt(2, warehouse.getWarehouseId());
			ps.setInt(3, warehouse.getItemId());
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

	/** Delete
	 * @return delete entire warehouse by warehouse_id
	 */
	@Override
	public void deleteWarehouse(Warehouse warehouse) {
		String sql = "DELETE FROM warehouse WHERE warehouse_id = ?";
		try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
			//Start transaction
			conn.setAutoCommit(false);			
			//Create PreparedStatement using Connection object
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, warehouse.getWarehouseId());
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
	
	/** Delete
	 * @return delete entire warehouse by warehouse_id
	 */
//	@Override
//	public void deleteWarehouse(int id) {
//		String sql = "DELETE FROM warehouse WHERE warehouse_id = ?";
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
	
	/** Delete
	 * @return delete multiple warehouses by array of warehouse_id
	 */
	@Override
	public void deleteManyWarehouses(int[] id) {
		for(int i : id) {
			String sql = "DELETE FROM warehouse WHERE warehouse_id = ?";
			try (Connection conn = WarehouseDBCreds.getInstance().getConnection();) {
				//Start transaction
				conn.setAutoCommit(false);			
				//Create PreparedStatement using Connection object
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id[i]);
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
		
	}
	


}
