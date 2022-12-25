package com.comtrade.domain;

import java.io.Serializable;

public class ExistingIngredients implements Serializable {

    private int id_ingredientsAvailable;
    private int id_restaurant;
    private int id_ingredients;
    private double quantity;

    public ExistingIngredients(int id_restaurant, int id_ingredients, double quantity) {
        this.id_restaurant = id_restaurant;
        this.id_ingredients = id_ingredients;
        this.quantity = quantity;
    }

    public ExistingIngredients() {
		// TODO Auto-generated constructor stub
	}

	public int getId_ingredientsAvailable() {
        return id_ingredientsAvailable;
    }

    public void setId_ingredientsAvailable(int id_ingredientsAvailable) {
        this.id_ingredientsAvailable = id_ingredientsAvailable;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public int getId_ingredients() {
        return id_ingredients;
    }

    public void setId_ingredients(int id_ingredients) {
        this.id_ingredients = id_ingredients;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}