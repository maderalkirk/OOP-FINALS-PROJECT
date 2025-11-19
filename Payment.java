package com.mycompany.finalsmain;

import java.util.Scanner;

public class Payment {
    private final double total;

    public Payment(double total) {
        this.total = total;
    }

    public boolean process(Scanner sc) {
        System.out.println("\nSelect Payment Method:");
        System.out.println("[1] Cash");
        System.out.println("[2] GCash / E-Wallet");
        System.out.println("[3] Card Payment");
        System.out.print("Enter choice: ");
        
        int method = FinalMain.getIntInput(sc); 
        
        switch (method) {
            case 1 -> {
                return processCash(sc);
            }
            case 2 -> {
                return processGcash(sc);
            }
            case 3 -> {
                return processCard(sc);
            }
            default -> {
                System.out.println("Invalid option. Payment canceled.\n");
                return false;
            }
        }
    }

    private boolean processCash(Scanner sc) {
        System.out.print("Enter cash amount: PHP");
        double cash = 0;
        while (true) {
            try {
                cash = Double.parseDouble(sc.nextLine()); 
                if (cash < total) {
                    System.out.print("Insufficient cash. Please enter again: PHP");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Enter again: PHP");
            }
        }
        double change = cash - total;
        System.out.printf("Payment received! Change: PHP%.2f\n", change);
        return true;
    }
    
    private boolean processGcash(Scanner sc) {
        System.out.print("Enter GCash number: ");
        String gcash = sc.nextLine();
        System.out.println("Processing payment via GCash...");
        System.out.println("Payment successful from " + gcash + "!");
        return true;
    }

    private boolean processCard(Scanner sc) {
        System.out.print("Enter last 4 digits of your card: ");
        String card = sc.nextLine();
        System.out.println("Processing card payment...");
        System.out.println("Payment successful (Card ****" + card + ")");
        return true;
    }
}
