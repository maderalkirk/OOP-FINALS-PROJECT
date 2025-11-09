package com.mycompany.finalsmain;

import java.util.ArrayList;

public class FoodMenu {

    // ✅ Marked as final since the reference never changes
    private final ArrayList<Food> menu;

    public FoodMenu() {
        menu = new ArrayList<>();

        // add items
        menu.add(new MainDish("Chicken Katsu", 150, true));
        menu.add(new Drinks("Red Iced Tea", 70, "Large"));
    }

    // display menu
    public void displayMenu() {
        System.out.println("== Food Menu ==");
        for (int i = 0; i < menu.size(); i++) {
            System.out.print("[" + (i + 1) + "] ");
            menu.get(i).displayInfo();
        }
    }

    // return food from the array
    public Food getFood(int index) {
        return menu.get(index);
    }

    // return the size of the array
    public int getMenuSize() {
        return menu.size();
    }
}
