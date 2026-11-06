package se.lexicon.vendingmachine.purchase;

import se.lexicon.vendingmachine.product.Product;

import java.util.Optional;

public class PurchaseResult {

    private final PurchaseStatus status;
    private final Product product;
    private final int missingAmount;
    private final int changeReturned;

    // Constructor
    public PurchaseResult(PurchaseStatus status,
                          Product product,
                          int missingAmount,
                          int changeReturned) {

        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }

        if (missingAmount < 0 || changeReturned < 0) {
            throw new IllegalArgumentException("Amounts cannot be negative");
        }

        if (status == PurchaseStatus.NOT_FOUND && product != null) {
            throw new IllegalArgumentException(
                    "PRODUCT_NOT_FOUND cannot have a product");
        }

        if (status != PurchaseStatus.NOT_FOUND && product == null) {
            throw new IllegalArgumentException(
                    status + " requires a product");
        }

        if (status == PurchaseStatus.SUCCESS && missingAmount != 0) {
            throw new IllegalArgumentException(
                    "Successful purchases cannot have a missing amount");
        }

        if (status == PurchaseStatus.INSUFFICIENT_BALANCE && missingAmount == 0) {
            throw new IllegalArgumentException(
                    "Insufficient balance requires a positive missing amount");
        }

        this.status = status;
        this.product = product;
        this.missingAmount = missingAmount;
        this.changeReturned = changeReturned;
    }

    // Static factory methods
    public static PurchaseResult success(Product product, int change) {
        return new PurchaseResult(
                PurchaseStatus.SUCCESS, product, 0, change);
    }

    public static PurchaseResult notFound() {
        return new PurchaseResult(
                PurchaseStatus.NOT_FOUND, null, 0, 0);
    }

    public static PurchaseResult insufficientBalance(
            Product product, int missingAmount) {
        return new PurchaseResult(
                PurchaseStatus.INSUFFICIENT_BALANCE,
                product, missingAmount, 0);
    }

    public static PurchaseResult outOfStock(Product product) {
        return new PurchaseResult(
                PurchaseStatus.OUT_OF_STOCK, product, 0, 0);
    }

    // Getters
    public PurchaseStatus getStatus() {
        return status;
    }

    public Optional<Product> getProduct() {
        return Optional.ofNullable(product);
    }

    public int getMissingAmount() {
        return missingAmount;
    }

    public int getChangeReturned() {
        return changeReturned;
    }

    // Checks whether the purchase was successful
    public boolean isSuccessful() {
        return status == PurchaseStatus.SUCCESS;
    }
}
