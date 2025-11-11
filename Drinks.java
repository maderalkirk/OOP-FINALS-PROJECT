/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalsmain;

/**
 *
 * @author Owner
 */
public class Drinks extends Food {

    private String size;

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
