package com.comtrade.domain;

import java.io.Serializable;

public class Bill implements Serializable {
    private int idBill;
    private int id_restaurantTable;
    private String dateTime;
    private String waiterName;
    private double finalAmount;
    private int idRestaurant;
    private String orderList;

	public int getId_restaurantTable() {
		return id_restaurantTable;
	}

	public void setId_restaurantTable(int id_restaurantTable) {
		this.id_restaurantTable = id_restaurantTable;
	}

	public String getWaiterName() {
		return waiterName;
	}

	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public int getid_restaurantTable() {
        return id_restaurantTable;
    }

    public void setid_restaurantTable(int id_restaurantTable) {
        this.id_restaurantTable = id_restaurantTable;
    }

    public Bill(String dateTime, String waiterName,double finalAmount,int id_restaurantTable) {
        this.dateTime = dateTime;
        this.waiterName = waiterName;
        this.finalAmount = finalAmount;
        this.id_restaurantTable = id_restaurantTable;
    }

    public Bill() {
		// TODO Auto-generated constructor stub
	}

	public Bill(String waiterName2, String now, int i, Double finalAmount2) {
		// TODO Auto-generated constructor stub
		this.waiterName = waiterName2;
		this.dateTime = now;
		this.id_restaurantTable = i;
		this.finalAmount = finalAmount2;
	}

	public Bill(String waitersFirstName, int idRestaurant2, double price, int tableNumber, String timez) {
		this.waiterName = waitersFirstName;
		this.idRestaurant = idRestaurant2;
		this.finalAmount = price;
		this.id_restaurantTable = tableNumber;
		this.dateTime = timez;
	}

	public Bill(int int1, String string, int int2, String string2, double double1) {
		this.id_restaurantTable = int1;
		this.dateTime = string;
		this.idRestaurant = int2;
		this.waiterName = string2;
		this.finalAmount = double1;
	}

	public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String string) {
        this.orderList = string;
    }
}