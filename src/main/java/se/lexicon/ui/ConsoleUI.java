package se.lexicon.ui;

import se.lexicon.vendingmachine.VendingMachine;
import se.lexicon.vendingmachine.product.Product;
import se.lexicon.vendingmachine.purchase.PurchaseResult;
import se.lexicon.vendingmachine.purchase.PurchaseStatus;

import java.util.Objects;
import java.util.Scanner;

/** Text-based interface; vending and payment rules remain in VendingMachine. */
public final class ConsoleUI {
    private final VendingMachine vendingMachine;
    private final Scanner scanner;

    public ConsoleUI(VendingMachine vendingMachine) {
        this(vendingMachine, new Scanner(System.in));
    }

    public ConsoleUI(VendingMachine vendingMachine, Scanner scanner) {
        this.vendingMachine = Objects.requireNonNull(vendingMachine, "Vending machine must not be null");
        this.scanner = Objects.requireNonNull(scanner, "Scanner must not be null");
    }

    public void run() {
        System.out.println("Welcome to the Lexicon Vending Machine!");
        boolean running = true;
        while (running) {
            displayBalance();
            displayMenu();
            String input = readInput("Choose an option: ");
            if (input == null) {
                break;
            }

            switch (input.strip()) {
                case "1" -> displayProducts();
                case "2" -> insertCoin();
                case "3" -> purchaseProduct();
                case "4" -> returnChange();
                case "0" -> {
                    int change = vendingMachine.returnChange();
                    if (change > 0) {
                        System.out.println("Returning " + change + " kr.");
                    }
                    System.out.println("Thank you for using the vending machine. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid menu option. Please choose 0–4.");
            }
            displayBalance();
        }

        if (running) {
            int change = vendingMachine.returnChange();
            if (change > 0) {
                System.out.println("Returning " + change + " kr.");
            }
            System.out.println("Input closed. Goodbye!");
        }
    }

    private void displayProducts() {
        System.out.println("\nAvailable products:");
        if (vendingMachine.getProducts().isEmpty()) {
            System.out.println("  No products available.");
            return;
        }
        for (Product product : vendingMachine.getProducts()) {
            System.out.printf("  ID %d | %s | %d kr | %s | Stock: %d%n",
                    product.getId(), product.getName(), product.getPrice(),
                    product.describe(), product.getQuantity());
        }
    }

    private void displayMenu() {
        System.out.println("\n1. Show products");
        System.out.println("2. Insert coin");
        System.out.println("3. Purchase product");
        System.out.println("4. Return change");
        System.out.println("0. Exit");
    }

    private void insertCoin() {
        Integer coin = readNumber("Insert coin (1, 2, 5, 10, 20, or 50 kr): ");
        if (coin == null) {
            return;
        }
        if (vendingMachine.insertCoin(coin)) {
            System.out.println("Accepted " + coin + " kr.");
        } else {
            System.out.println("Invalid coin. Accepted coins are 1, 2, 5, 10, 20, and 50 kr.");
        }
    }

    private void purchaseProduct() {
        Integer productId = readNumber("Enter product ID: ");
        if (productId == null) {
            return;
        }
        PurchaseResult result = vendingMachine.purchaseProduct(productId);
        if (result.isSuccessful()) {
            Product product = result.getProduct().orElseThrow();
            System.out.println("Purchase successful: " + product.getName() + ".");
            if (result.getChangeReturned() > 0) {
                System.out.println("Your change is " + result.getChangeReturned() + " kr.");
            }
            return;
        }

        PurchaseStatus status = result.getStatus();
        switch (status) {
            case NOT_FOUND -> System.out.println("No product found with ID " + productId + ".");
            case OUT_OF_STOCK -> System.out.println("That product is out of stock.");
            case INSUFFICIENT_BALANCE -> System.out.println("Insufficient balance. Please insert "
                    + result.getMissingAmount() + " kr more.");
            case SUCCESS -> throw new IllegalStateException("Unexpected successful result");
        }
    }

    private void returnChange() {
        int change = vendingMachine.returnChange();
        if (change == 0) {
            System.out.println("There is no change to return.");
        } else {
            System.out.println("Returning " + change + " kr.");
        }
    }

    private void displayBalance() {
        System.out.println("Current balance: " + vendingMachine.getBalance() + " kr.");
    }

    private Integer readNumber(String prompt) {
        String input = readInput(prompt);
        if (input == null) {
            return null;
        }
        try {
            return Integer.parseInt(input.strip());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a whole number.");
            return null;
        }
    }

    private String readInput(String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }
}
