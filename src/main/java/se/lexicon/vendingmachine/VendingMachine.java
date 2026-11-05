package se.lexicon.vendingmachine;

import se.lexicon.vendingmachine.product.Product;
import se.lexicon.vendingmachine.purchase.PurchaseResult;

import java.util.List;

/** Business operations exposed to the console UI. */
public interface VendingMachine {
    /** @return true when the coin was accepted; false when it was rejected. */
    boolean insertCoin(int value);

    int getBalance();

    List<Product> getProducts();

    PurchaseResult purchaseProduct(int productId);

    int returnChange();
}
