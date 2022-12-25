package com.comtrade.domain;


import java.io.Serializable;
import java.util.Objects;

public class Ingredients implements Serializable/*, CommonDomain*/ {

    private int id_ingredient;
    private String ingredient_name;
    private double price;
    private String quantity_measure;
    private double quantity;
    private int idRestaurant;
    private ExistingIngredients existingIngredients = new ExistingIngredients();

    @Override
	public String toString() {
		return  ingredient_name + "[" + " Цена товара: "
				+ price + ";" + " В наличии: " + quantity + " ]";
	}

	public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Ingredients(int id_ingredient, String ingredient_name, double price, String quantity_measure, double quantity) {
        this.id_ingredient = id_ingredient;
        this.ingredient_name = ingredient_name;
        this.price = price;
        this.quantity_measure = quantity_measure;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return Objects.equals(ingredient_name, that.ingredient_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient_name);
    }

    public Ingredients() {
    }

    public Ingredients(String name, String measurement, double quantity2, double price2) {
    	 this.ingredient_name = name;
         this.price = price2;
         this.quantity_measure = measurement;
         this.quantity = quantity2;
	}

	public Ingredients(int id_ingredient2, double getprice, int i, double quantity2, String quantity_measure2,
			String ingredient_name2) {
		this.id_ingredient = id_ingredient2;
		this.price = getprice;
		this.idRestaurant = i;
		this.quantity = quantity2;
		this.quantity_measure = quantity_measure2;
		this.ingredient_name = ingredient_name2;
	}

	public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public double getprice() {
        return price;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public String getQuantity_measure() {
        return quantity_measure;
    }

    public void setQuantity_measure(String quantity_measure) {
        this.quantity_measure = quantity_measure;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}