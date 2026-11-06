package se.lexicon;

import se.lexicon.vendingmachine.VendingMachine;
import se.lexicon.vendingmachine.VendingMachineImpl;
import se.lexicon.vendingmachine.catalog.ProductCatalog;
import se.lexicon.vendingmachine.payment.Payment;
import se.lexicon.vendingmachine.product.Beverage;
import se.lexicon.vendingmachine.product.Fruit;
import se.lexicon.vendingmachine.product.Snack;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Snack(1, "Chocolate Bar", 20, 8, 50));
        catalog.addProduct(new Beverage(2, "Mineral Water", 15, 10, 500));
        catalog.addProduct(new Beverage(3, "Orange Juice", 25, 6, 330));
        catalog.addProduct(new Fruit(4, "Apple", 10, 12, "Sweden"));

        VendingMachine vendingMachine = new VendingMachineImpl(catalog, new Payment());
        new ConsoleUI(vendingMachine).run();
    }
}
