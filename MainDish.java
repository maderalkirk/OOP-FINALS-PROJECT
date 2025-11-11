/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalsmain;

/**
 *
 * @author Owner
 */
public class MainDish extends Food {

    private boolean addRice;

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
