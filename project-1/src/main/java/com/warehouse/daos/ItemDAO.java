package com.warehouse.daos;

import java.util.List;

import com.warehouse.models.Item;

//Queries specific to Item
public interface ItemDAO {
	//CRUD operations
	//C
	public Item createItem(Item item);
	//R
	public List<Item> findAllItems();
	public Item findById(int id);
	public Item findByName(String name);
	//U
	public void updateItemName(Item item);
	//D
	public void deleteItem(Item item);
//	public void deleteItem(int id);
//	public void deleteManyItems(int[] id);
}
