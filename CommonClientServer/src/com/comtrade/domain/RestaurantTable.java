package com.comtrade.domain;

import java.io.Serializable;
import java.util.Objects;

public class RestaurantTable implements Serializable {
    private int id_restaurantTable;
    private int table_number;
    private int id_restaurant;
    private int status;

    public RestaurantTable(int table_number) {
        this.table_number = table_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTable that = (RestaurantTable) o;
        return id_restaurantTable == that.id_restaurantTable &&
                table_number == that.table_number &&
                id_restaurant == that.id_restaurant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_restaurantTable, table_number, id_restaurant);
    }

    public RestaurantTable() {
		// TODO Auto-generated constructor stub
	}

	public int getTable_number() {
        return table_number;
    }

    public void setTable_number(int table_number) {
        this.table_number = table_number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}