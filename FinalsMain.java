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
            System.out.println("5. Clear Order History");   // ⬅️ add this line
            System.out.println("6. Exit");                  // ⬅️ update numbering
            System.out.print("Enter your choice: ");

            int userChoice = getIntInput(sc);

            switch (userChoice) {
                case 1 ->
                    handleMenuSelection(sc, menu, order);
                case 2 ->
                    viewCurrentOrder(order);
                case 3 ->
                    processPayment(order, orderHistory);
                case 4 ->
                    viewOrderHistory(orderHistory);
                case 5 ->
                    clearOrderHistory(orderHistory);   // ⬅️ call the new method
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

    private static void clearOrderHistory(ArrayList<Food> orderHistory) {
        if (orderHistory.isEmpty()) {
            System.out.println("\nNo history to clear.\n");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nAre you sure you want to clear all order history? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            orderHistory.clear(); // Clear in-memory list

            // Also clear the file itself
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(HISTORY_FILE))) {
                // Just overwrite with an empty file
            } catch (IOException e) {
                System.out.println("Error clearing history file: " + e.getMessage());
            }

            System.out.println("Order history cleared successfully!\n");
        } else {
            System.out.println("Action canceled.\n");
        }
    }

    // --- Order History Methods ---
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

    // --- Input & Menu Handling ---
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
            System.out.println("\nNo items to pay for.\n");
            return;
        }

        Scanner sc = new Scanner(System.in);
        double total = 0;

        System.out.println("\n=== CHECKOUT ===");
        for (Food item : order) {
            System.out.println("- " + item.getName() + " (₱" + item.getPrice() + ")");
            total += item.getPrice();
        }
        System.out.println("-------------------------");
        System.out.println("TOTAL: ₱" + total);

        System.out.print("\nProceed to checkout? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();
        if (!confirm.equals("y")) {
            System.out.println("Checkout canceled.\n");
            return;
        }

        System.out.println("\nSelect Payment Method:");
        System.out.println("[1] Cash");
        System.out.println("[2] GCash / E-Wallet");
        System.out.println("[3] Card Payment");
        System.out.print("Enter choice: ");

        int method = getIntInput(sc);
        switch (method) {
            case 1 -> {
                System.out.print("Enter cash amount: ₱");
                double cash = 0;
                while (true) {
                    try {
                        cash = Double.parseDouble(sc.nextLine());
                        if (cash < total) {
                            System.out.print("Insufficient cash. Please enter again: ₱");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid amount. Enter again: ₱");
                    }
                }
                double change = cash - total;
                System.out.println("Payment received! Change: ₱" + change);
            }

            case 2 -> {
                System.out.print("Enter GCash number: ");
                String gcash = sc.nextLine();
                System.out.println("Processing payment via GCash...");
                System.out.println("Payment successful from " + gcash + "!");
            }

            case 3 -> {
                System.out.print("Enter last 4 digits of your card: ");
                String card = sc.nextLine();
                System.out.println("Processing card payment...");
                System.out.println("Payment successful (Card ****" + card + ")");
            }

            default -> {
                System.out.println("Invalid option. Payment canceled.\n");
                return;
            }
        }

        System.out.println("\n✅ Payment successful! Thank you for your order.\n");

        // Save to order history
        orderHistory.addAll(order);
        order.clear();
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
