package com.warehouse.daos;

import java.util.List;

import com.warehouse.models.Warehouse;

public interface WarehouseDAO {

	//C
	public Warehouse createWarehouseItem(Warehouse warehouse);
	//R
	public List<Warehouse> findAllWarehouses();
	public Warehouse findById(int warehouse_id);
	public Warehouse findByName(String name);
	//U
	public void updateItemQuantity(Warehouse warehouse);
	public void updateWarehouseName(Warehouse warehouse);
	//D
	public void deleteWarehouse(Warehouse warehouse);
//	public void deleteWarehouse(int id);
	public void deleteManyWarehouses(int[] id);
}
