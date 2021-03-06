package com.warehouse.daos;

import java.util.List;

import com.warehouse.models.Warehouse;

public interface WarehouseDAO {

	//C
	public Warehouse createWarehouseItem(Warehouse warehouse);
	//R
	public List<Warehouse> findAllWarehouses();
	public List<Warehouse> findById(int warehouse_id);
	public List<Warehouse> findByName(String name);
	//U
	public void updateItemQuantity(Warehouse warehouse);
	public void updateWarehouseName(Warehouse warehouse);
	//D
	public void deleteWarehouse(Warehouse warehouse);
	public void deleteWarehouseItem(Warehouse warehouse);
//	public void deleteWarehouse(int id);
//	public void deleteManyWarehouses(int[] id);
	
	// EDGE CASE CHECK
	public int findWarehouseCapacity(int warehouse_id);
}
