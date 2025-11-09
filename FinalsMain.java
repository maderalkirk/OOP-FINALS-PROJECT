package com.mycompany.finalsmain;

import java.util.Scanner;
import java.util.ArrayList;

public class FinalsMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        FoodMenu menu = new FoodMenu();
        ArrayList<Food> order = new ArrayList<>();
        ArrayList<Food> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("=== MENU ===");
            System.out.println("1. View Food Menu");
            System.out.println("2. View Your Orders");
            System.out.println("3. Proceed to Payment");
            System.out.println("4. View Order History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int userChoice = getIntInput(sc);

            switch (userChoice) {
                case 1 -> handleMenuSelection(sc, menu, order);
                case 2 -> viewCurrentOrder(order);
                case 3 -> processPayment(order, orderHistory);
                case 4 -> viewOrderHistory(orderHistory);
                case 5 -> {
                    System.out.println("\nExiting program. Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("\nInvalid option. Please select from 1 to 5.\n");
            }
        }
    }

    private static int getIntInput(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    private static void handleMenuSelection(Scanner sc, FoodMenu menu, ArrayList<Food> order) {
        while (true) {
            menu.displayMenu();
            System.out.print("\nEnter food number to order (0 to go back): ");
            int menuChoice = getIntInput(sc);

            if (menuChoice == 0) {
                System.out.println("Returning to main menu...\n");
                return;
            }

            if (menuChoice >= 1 && menuChoice <= menu.getMenuSize()) {
                Food selected = menu.getFood(menuChoice - 1);
                order.add(selected);
                System.out.println(selected.getName() + " added to your order!\n");
            } else {
                System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    private static void viewCurrentOrder(ArrayList<Food> order) {
        if (order.isEmpty()) {
            System.out.println("\nYour order is currently empty.\n");
        } else {
            System.out.println("\n=== Your Current Order ===");
            for (Food item : order) {
                System.out.println("- " + item.getName() + " (₱" + item.getPrice() + ")");
            }
            System.out.println();
        }
    }

    private static void processPayment(ArrayList<Food> order, ArrayList<Food> orderHistory) {
        if (order.isEmpty()) {
            System.out.println("\nYou have no items to pay for.\n");
        } else {
            double total = 0;
            for (Food f : order) {
                total += f.getPrice();
            }
            System.out.println("\nYour total is ₱" + total);
            System.out.println("Processing payment...");
            System.out.println("Payment successful! Thank you.\n");

            orderHistory.addAll(order);
            order.clear();
        }
    }

    private static void viewOrderHistory(ArrayList<Food> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("\nYou have no order history.\n");
        } else {
            System.out.println("\n=== Your Order History ===");
            for (Food item : orderHistory) {
                System.out.println("- " + item.getName());
            }
            System.out.println();
        }
    }
}
