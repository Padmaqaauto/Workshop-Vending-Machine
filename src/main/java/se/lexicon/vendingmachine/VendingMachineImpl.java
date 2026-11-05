package se.lexicon.vendingmachine;

import se.lexicon.payment.Payment;
import se.lexicon.vendingmachine.catalog.ProductCatalog;
import se.lexicon.vendingmachine.product.Product;
import se.lexicon.vendingmachine.purchase.PurchaseResult;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Coordinates product inventory and payment without handling console I/O. */
public final class VendingMachineImpl implements VendingMachine {
    private final ProductCatalog productCatalog;
    private final Payment payment;

    public VendingMachineImpl(ProductCatalog productCatalog, Payment payment) {
        this.productCatalog = Objects.requireNonNull(productCatalog, "Product catalog must not be null");
        this.payment = Objects.requireNonNull(payment, "Payment must not be null");
    }

    @Override
    public boolean insertCoin(int value) {
        if (!payment.isValidCoin(value)) {
            return false;
        }
        payment.insertCoin(value);
        return true;
    }

    @Override
    public int getBalance() {
        return payment.getBalance();
    }

    @Override
    public List<Product> getProducts() {
        return productCatalog.getAllProducts();
    }

    @Override
    public int returnChange() {
        return payment.returnChange();
    }

    @Override
    public PurchaseResult purchaseProduct(int productId) {
        Optional<Product> match = productCatalog.getProductById(productId);
        if (match.isEmpty()) {
            return PurchaseResult.notFound();
        }

        Product product = match.get();
        if (product.getQuantity() == 0) {
            return PurchaseResult.outOfStock(product);
        }

        int missingAmount = product.getPrice() - payment.getBalance();
        if (missingAmount > 0) {
            return PurchaseResult.insufficientBalance(product, missingAmount);
        }

        // Both conditions were checked above; these operations now complete the transaction.
        if (!product.dispenseOne()) {
            return PurchaseResult.outOfStock(product);
        }
        payment.deductAmount(product.getPrice());
        int changeReturned = payment.returnChange();
        return PurchaseResult.success(product, changeReturned);
    }
}
