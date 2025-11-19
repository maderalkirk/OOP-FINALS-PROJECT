package com.mycompany.finalsmain;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class FinalMain {

    private static final String HISTORY_FILE = "order_history.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodMenu menu = new FoodMenu();
        ArrayList<Food> order = new ArrayList<>();
        ArrayList<Food> orderHistory = loadOrderHistory();

        while (true) {
            System.out.println("=== MENU ===");
            System.out.println("1. View Food Menu");
            System.out.println("2. View Your Orders");
            System.out.println("3. Proceed to Payment");
            System.out.println("4. View Order History");
            System.out.println("5. Clear Order History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int userChoice = getIntInput(sc);

            switch (userChoice) {
                case 1 ->
                    handleMenuSelection(sc, menu, order);
                case 2 ->
                    viewCurrentOrder(order);
                case 3 ->
                    processPayment(sc, order, orderHistory);
                case 4 ->
                    viewOrderHistory(orderHistory);
                case 5 ->
                    clearOrderHistory(orderHistory);
                case 6 -> {
                    saveOrderHistory(orderHistory);
                    System.out.println("\nOrder history saved. Exiting program. Goodbye!");
                    sc.close();
                    return;
                }
                default ->
                    System.out.println("\nInvalid option. Please select from 1 to 6.\n");
            }
        }

    }

    public static int getIntInput(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    private static void clearOrderHistory(ArrayList<Food> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("\nNo history to clear.\n");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nAre you sure you want to clear all order history? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            orderHistory.clear();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
                bw.write("");
            } catch (IOException e) {
                System.out.println("Error clearing history file: " + e.getMessage());
            }

            System.out.println("Order history cleared successfully!\n");
        } else {
            System.out.println("Action canceled.\n");
        }
    }

    private static ArrayList<Food> loadOrderHistory() {
        ArrayList<Food> history = new ArrayList<>();
        File file = new File(HISTORY_FILE);
        if (!file.exists()) {
            return history;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        String name = parts[0];
                        double price = Double.parseDouble(parts[1]);
                        history.add(new Food(name, price));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping malformed line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading order history: " + e.getMessage());
        }
        return history;
    }

    private static void saveOrderHistory(ArrayList<Food> orderHistory) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
            for (Food item : orderHistory) {
                bw.write(item.getName() + "," + item.getPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving order history: " + e.getMessage());
        }
    }

    private static void handleMenuSelection(Scanner sc, FoodMenu menu, ArrayList<Food> order) {
        while (true) {
            menu.displayMenu();
            System.out.print("\nEnter food number to order (0 to go back): ");
            int menuChoice = getIntInput(sc);
            if (menuChoice == 0) {
                return;
            }
            if (menuChoice >= 1 && menuChoice <= menu.getMenuSize()) {
                Food selected = new Food(menu.getFood(menuChoice - 1));
                order.add(selected);
                System.out.println(selected.getName() + " added to your order!\n");
            } else {
                System.out.println("Invalid choice. Try again.\n");
            }
        }
    }

    private static void viewCurrentOrder(ArrayList<Food> order) {
        if (order.isEmpty()) {
            System.out.println("\nYour order is empty.\n");
            return; // ⬅️ Good to add a return here
        }

        System.out.println("\n=== Your Current Order ===");
        double total = 0; // ⬅️ 1. Initialize total

        for (Food item : order) {
            // Using printf for better alignment
            System.out.printf("- %s (PHP%.2f)\n", item.getName(), item.getPrice());
            total += item.getPrice(); // ⬅️ 2. Add item price to total
        }

        System.out.println("-------------------------");
        System.out.printf("TOTAL: PHP%.2f\n", total); // ⬅️ 3. Print the formatted total
        System.out.println(); // Add a blank line for spacing
    }

    private static void processPayment(Scanner sc, ArrayList<Food> order, ArrayList<Food> orderHistory) {
        if (order.isEmpty()) {
            System.out.println("\nNo items to pay for.\n");
            return;
        }

        double total = 0;
        System.out.println("\n=== CHECKOUT ===");
        for (Food item : order) {
            // Using printf for better currency formatting
            System.out.printf("- %s (PHP%.2f)\n", item.getName(), item.getPrice());
            total += item.getPrice();
        }
        System.out.println("-------------------------");
        System.out.printf("TOTAL: PHP%.2f\n", total);

        System.out.print("\nProceed to checkout? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (!confirm.equals("y")) {
            System.out.println("Checkout canceled.\n");
            return;
        }

        Payment payment = new Payment(total);

        boolean paymentSuccessful = payment.process(sc);

        if (paymentSuccessful) {
            System.out.println("\nPayment successful! Thank you for your order.\n");
            orderHistory.addAll(order);
            order.clear();
        }
    }

    private static void viewOrderHistory(ArrayList<Food> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("\nNo order history.\n");
        } else {
            System.out.println("\n=== Your Order History ===");
            for (Food item : orderHistory) {
                System.out.println("- " + item.getName());
            }
            System.out.println();
        }
    }
}
