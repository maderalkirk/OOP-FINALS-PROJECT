package com.mycompany.finalsmain;

public class Food {

    private String foodName;
    private double price;

    public Food(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    public Food(Food other) {
        this.foodName = other.foodName;
        this.price = other.price;
    }

    public String getName() {
        return foodName;
    }

    public void setName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayInfo() {
        System.out.println(getName() + " | price: " + getPrice());
    }
}
