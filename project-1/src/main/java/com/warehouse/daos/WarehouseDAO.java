package com.warehouse.daos;

import java.util.List;

import com.warehouse.models.Warehouse;

public interface WarehouseDAO {

	public List<Warehouse> findAllWarehouses();
	public Warehouse findById(int id);
	public Warehouse findByName(String name);
	public Warehouse save(Warehouse warehouse);
	
	public void update(Warehouse warehouse);
	public void delete(Warehouse warehouse);
	public void delete(int id);
	public void deleteMany(int[] id);
}
