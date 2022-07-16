package com.warehouse.daos;

import java.util.List;

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
	
	/** 
	 * @return warehouse_id and warehouse_name  of all Items if found. 
	 * null returned if item not found
	 */
	@Override
	public List<Warehouse> findAllWarehouses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Warehouse findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Warehouse findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Warehouse save(Warehouse warehouse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMany(int[] id) {
		// TODO Auto-generated method stub
		
	}


}
