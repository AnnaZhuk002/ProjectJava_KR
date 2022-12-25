package com.comtrade.domain;

import java.io.Serializable;

public class Orders implements Serializable {

    private int table_number;
    private double price;
	private int id_ingredient;
	private int id_order;

	public Orders(int id_order,int table_number, double price, int id_ingredient) {
		this.id_order = id_order;
		this.table_number = table_number;
		this.price = price;
		this.id_ingredient = id_ingredient;
	}

	public int getId_order() {
		return id_order;
	}

	public void setId_order(int id_order) {
		this.id_order = id_order;
	}

	public int getTable_number() {
		return table_number;
	}

	public void setTable_number(int table_number) {
		this.table_number = table_number;
	}

	public int getId_ingredient() {
		return id_ingredient;
	}

	public void setId_ingredient(int id_ingredient) {
		this.id_ingredient = id_ingredient;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}