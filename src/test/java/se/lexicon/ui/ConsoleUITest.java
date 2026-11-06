package se.lexicon.ui;

import org.junit.jupiter.api.Test;
import se.lexicon.vendingmachine.VendingMachine;
import se.lexicon.vendingmachine.VendingMachineImpl;
import se.lexicon.vendingmachine.catalog.ProductCatalog;
import se.lexicon.vendingmachine.payment.Payment;
import se.lexicon.vendingmachine.product.Beverage;
import se.lexicon.vendingmachine.product.Fruit;
import se.lexicon.vendingmachine.product.Product;
import se.lexicon.vendingmachine.product.Snack;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleUITest {
    @Test
    void displaysProductsAndCompletesPurchaseWithAutomaticChange() {
        ProductCatalog catalog = catalogWithProducts();
        VendingMachine machine = new VendingMachineImpl(catalog, new Payment());

        String output = runUi(machine, "2\n50\n3\n1\n0\n");

        Product purchased = catalog.getProductById(1).orElseThrow();
        assertAll(
                () -> assertTrue(output.contains("Welcome to the Lexicon Vending Machine!")),
                () -> assertTrue(output.contains("ID 1 | Chocolate Bar | 20 kr | Chocolate Bar (Snack, 50g) | Stock: 3")),
                () -> assertTrue(output.contains("Accepted 50 kr.")),
                () -> assertTrue(output.contains("Purchase successful: Chocolate Bar.")),
                () -> assertTrue(output.contains("Your change is 30 kr.")),
                () -> assertEquals(2, purchased.getQuantity()),
                () -> assertEquals(0, machine.getBalance())
        );
    }

    @Test
    void displaysEveryProductTypeAndStockInProductList() {
        VendingMachine machine = new VendingMachineImpl(catalogWithProducts(), new Payment());

        String output = runUi(machine, "0\n");

        assertAll(
                () -> assertTrue(output.contains("Chocolate Bar (Snack, 50g) | Stock: 3")),
                () -> assertTrue(output.contains("Mineral Water (Beverage, 500ml) | Stock: 5")),
                () -> assertTrue(output.contains("Apple (Fruit, Sweden) | Stock: 4"))
        );
    }

    @Test
    void reportsInvalidCoinAndInsufficientBalanceWithoutChangingState() {
        ProductCatalog catalog = catalogWithProducts();
        VendingMachine machine = new VendingMachineImpl(catalog, new Payment());

        String output = runUi(machine, "2\n7\n2\n10\n3\n1\n0\n");

        assertAll(
                () -> assertTrue(output.contains("Invalid coin.")),
                () -> assertTrue(output.contains("Insufficient balance. Please insert 10 kr more.")),
                () -> assertTrue(output.contains("Current balance: 10 kr.")),
                () -> assertEquals(3, catalog.getProductById(1).orElseThrow().getQuantity()),
                () -> assertEquals(0, machine.getBalance())
        );
    }

    @Test
    void handlesInvalidMenuAndNumberInputAndZeroBalanceChangeReturn() {
        VendingMachine machine = new VendingMachineImpl(catalogWithProducts(), new Payment());

        String output = runUi(machine, "x\n2\nnot-a-number\n4\n0\n");

        assertAll(
                () -> assertTrue(output.contains("Invalid menu option.")),
                () -> assertTrue(output.contains("Please enter a whole number.")),
                () -> assertTrue(output.contains("There is no change to return.")),
                () -> assertTrue(output.contains("Thank you for using the vending machine.")),
                () -> assertTrue(output.contains("Current balance: 0 kr."))
        );
    }

    @Test
    void reportsUnknownProductAndOutOfStockSelection() {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Snack(1, "Sold Out Chips", 20, 0, 50));
        VendingMachine machine = new VendingMachineImpl(catalog, new Payment());

        String output = runUi(machine, "3\n999\n3\n1\n0\n");

        assertAll(
                () -> assertTrue(output.contains("No product found with ID 999.")),
                () -> assertTrue(output.contains("That product is out of stock.")),
                () -> assertEquals(0, catalog.getProductById(1).orElseThrow().getQuantity()),
                () -> assertTrue(output.contains("Current balance: 0 kr."))
        );
    }

    @Test
    void returnsInsertedBalanceWhenUserRequestsChange() {
        VendingMachine machine = new VendingMachineImpl(catalogWithProducts(), new Payment());

        String output = runUi(machine, "2\n20\n4\n0\n");

        assertAll(
                () -> assertTrue(output.contains("Accepted 20 kr.")),
                () -> assertTrue(output.contains("Returning 20 kr.")),
                () -> assertTrue(output.contains("Current balance: 0 kr.")),
                () -> assertEquals(0, machine.getBalance())
        );
    }

    @Test
    void returnsRemainingBalanceWhenInputEnds() {
        VendingMachine machine = new VendingMachineImpl(catalogWithProducts(), new Payment());

        String output = runUi(machine, "2\n10\n");

        assertAll(
                () -> assertTrue(output.contains("Accepted 10 kr.")),
                () -> assertTrue(output.contains("Input closed. Goodbye!")),
                () -> assertTrue(output.contains("Returning 10 kr.")),
                () -> assertEquals(0, machine.getBalance())
        );
    }

    private static ProductCatalog catalogWithProducts() {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Snack(1, "Chocolate Bar", 20, 3, 50));
        catalog.addProduct(new Beverage(2, "Mineral Water", 15, 5, 500));
        catalog.addProduct(new Fruit(3, "Apple", 10, 4, "Sweden"));
        return catalog;
    }

    private static String runUi(VendingMachine machine, String input) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream capturedOut = new PrintStream(output, true, StandardCharsets.UTF_8);
             Scanner scanner = new Scanner(input)) {
            System.setOut(capturedOut);
            new ConsoleUI(machine, scanner).run();
        } finally {
            System.setOut(originalOut);
        }
        return output.toString(StandardCharsets.UTF_8);
    }
}
