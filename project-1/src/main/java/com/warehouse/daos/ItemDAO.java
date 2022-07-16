package com.warehouse.daos;

import java.util.List;

import com.warehouse.models.Item;

//Queries specific to Item
public interface ItemDAO {
	//CRUD operations
	
	public List<Item> findAll();
	public Item findById(int id);
	public Item findByName(String name);
	public Item save(Item item);
	
	public void update(Item item);
	public void delete(Item item);
	public void delete(int id);
	public void deleteMany(int[] id);
}
