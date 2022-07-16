package com.warehouse.models;

public class Item {
	
	private int id;
	private String name;
	
	
	
	public Item() {
		super();
	}



	public Item(int id) {
		super();
		this.id = id;
	}



	public Item(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + "]";
	}

	
	
	
	
	
}
