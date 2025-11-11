package com.mycompany.finalsmain;

public class Food {

    private String foodName;
    private double price;

    public Food(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    // Copy constructor
    public Food(Food other) {
        this.foodName = other.foodName;
        this.price = other.price;
    }

    // Getters and Setters (Encapsulation)
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

    // Polymorphic method
    public void displayInfo() {
        System.out.println(getName() + " | price: " + getPrice());
    }
}
