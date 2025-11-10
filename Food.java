package com.mycompany.finalsmain;

public class Food {

    protected String foodName;
    protected double price;

    public Food(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    // Copy constructor
    public Food(Food other) {
        this.foodName = other.foodName;
        this.price = other.price;
    }

    public String getName() {
        return foodName;
    }

    public double getPrice() {
        return price;
    }

    public void displayInfo() {
        System.out.println(getName() + " | price: " + getPrice());
    }
}

class MainDish extends Food {

    boolean addRice;

    public MainDish(String foodName, double price, boolean addRice) {
        super(foodName, price);
        this.addRice = addRice;
    }

    // Copy constructor
    public MainDish(MainDish other) {
        super(other);
        this.addRice = other.addRice;
    }

    @Override
    public void displayInfo() {
        System.out.println(getName() + " | price: " + getPrice() + (addRice ? " (with rice)" : ""));
    }
}

class Drinks extends Food {

    protected String size;

    public Drinks(String foodName, double price, String size) {
        super(foodName, price);
        this.size = size;
    }

    // Copy constructor
    public Drinks(Drinks other) {
        super(other);
        this.size = other.size;
    }

    @Override
    public void displayInfo() {
        System.out.println(getName() + " (" + size + ") | price: " + getPrice());
    }
}
