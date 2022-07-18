package com.warehouse.daos;

import java.util.List;

import com.warehouse.models.Warehouse;

public interface WarehouseDAO {

	public List<Warehouse> findAllWarehouses();
	public Warehouse findById(int id);
	public Warehouse findByName(String name);
	public Warehouse addWarehouse(Warehouse warehouse);
	public Warehouse addItemToWarehouse(Warehouse warehouse);
	
	
	public void updateWarehouseName(Warehouse warehouse);
	public void updateItemQuantity(Warehouse warehouse);
	
	public void deleteWarehouse(Warehouse warehouse);
	public void deleteItemInWarehouse(Warehouse warehouse);
	public void delete(int id);
	public void deleteMany(int[] id);
}
