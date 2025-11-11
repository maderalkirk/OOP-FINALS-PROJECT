package com.mycompany.finalsmain;

public class FoodMenu {

    private final Food[] menu;

    public FoodMenu() {
        menu = new Food[7];
        menu[0] = new MainDish("Chicken Katsu", 150, true);
        menu[1] = new MainDish("Pork Katsu", 150, true);
        menu[2] = new MainDish("Chicken Katsu Curry", 179, true);
        menu[3] = new MainDish("Chicken Teriyaki Bowl", 130, true);
        menu[4] = new Drinks("Red Iced Tea", 70, "Large");
        menu[5] = new Drinks("Bottled Water", 30, "16oz");
        menu[6] = new Drinks("Soda in Can", 45, "12oz");
    }

    public void displayMenu() {
        System.out.println("== Food Menu ==");
        for (int i = 0; i < menu.length; i++) {
            System.out.print("[" + (i + 1) + "] ");
            menu[i].displayInfo();
        }
    }

    public Food getFood(int index) {
        return menu[index];
    }

    public int getMenuSize() {
        return menu.length;
    }
}
