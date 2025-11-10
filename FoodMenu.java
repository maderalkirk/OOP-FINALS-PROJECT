package com.mycompany.finalsmain;

import java.util.ArrayList;

public class FoodMenu {

    private final ArrayList<Food> menu;

    public FoodMenu() {
        menu = new ArrayList<>();
        menu.add(new MainDish("Chicken Katsu", 150, true));
        menu.add(new MainDish("Pork Katsu", 150, true));
        menu.add(new MainDish("Chicken Katsu Curry", 179, true));
        menu.add(new MainDish("Chicken Teriyaki Bowl", 130, true));
        menu.add(new Drinks("Red Iced Tea", 70, "Large"));
        menu.add(new Drinks("Bottled Water", 30, "16oz"));
        menu.add(new Drinks("Soda in Can", 45, "12oz"));
    }

    public void displayMenu() {
        System.out.println("== Food Menu ==");
        for (int i = 0; i < menu.size(); i++) {
            System.out.print("[" + (i + 1) + "] ");
            menu.get(i).displayInfo();
        }
    }

    public Food getFood(int index) {
        return menu.get(index);
    }

    public int getMenuSize() {
        return menu.size();
    }
}
