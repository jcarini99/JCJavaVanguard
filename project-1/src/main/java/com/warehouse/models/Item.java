package com.warehouse.models;

public class Item {
	private int id;
	private String name;
	private String date;
	
	
	
	public Item() {
		super();
	}

	public Item(int id, String name, String date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public Item(String name, String date) {
		super();
		this.name = name;
		this.date = date;
	}

	public Item(String name) {
		super();
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", date=" + date + "]";
	}
	
	
	
	
}
