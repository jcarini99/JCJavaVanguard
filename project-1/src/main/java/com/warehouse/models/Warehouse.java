package com.warehouse.models;

public class Warehouse {

	private int warehouseId;
	private String name;
	private int itemId;
	private int itemQuantity;
	
	public Warehouse() {
		super();
	}

	public Warehouse(int warehouseId, String name, int itemId, int itemQuantity) {
		super();
		this.warehouseId = warehouseId;
		this.name = name;
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}

	public Warehouse(String name, int itemId, int itemQuantity) {
		super();
		this.name = name;
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
	}
	
	public Warehouse(int warehouseId, String name) {
		super();
		this.warehouseId = warehouseId;
		this.name = name;
	}

	public Warehouse(String name) {
		super();
		this.name = name;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Override
	public String toString() {
		return "Warehouse [warehouseId=" + warehouseId + ", name=" + name + ", itemId=" + itemId + ", itemQuantity="
				+ itemQuantity + "]";
	}
	
	
	
}
