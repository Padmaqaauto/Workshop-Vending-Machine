package se.lexicon.vendingmachine;

import org.junit.jupiter.api.Test;
import se.lexicon.vendingmachine.payment.Payment;
import se.lexicon.vendingmachine.catalog.ProductCatalog;
import se.lexicon.vendingmachine.product.Beverage;
import se.lexicon.vendingmachine.product.Fruit;
import se.lexicon.vendingmachine.product.Product;
import se.lexicon.vendingmachine.product.Snack;
import se.lexicon.vendingmachine.purchase.PurchaseStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineImplTest {

    @Test
    void acceptsValidCoinAndUpdatesBalance() {
        VendingMachineImpl machine = machine(new Snack(1, "Chips", 15, 5, 130));

        assertTrue(machine.insertCoin(10));
        assertEquals(10, machine.getBalance());
    }

    @Test
    void rejectsInvalidCoinWithoutChangingBalance() {
        VendingMachineImpl machine = machine(new Snack(1, "Chips", 15, 5, 130));

        assertFalse(machine.insertCoin(7));
        assertEquals(0, machine.getBalance());
    }

    @Test
    void paymentReportsSuccessfulAndFailedDeductions() {
        Payment payment = new Payment();
        payment.insertCoin(10);

        assertFalse(payment.deductAmount(20));
        assertEquals(10, payment.getBalance());
        assertTrue(payment.deductAmount(5));
        assertEquals(5, payment.getBalance());
    }

    @Test
    void successfulPurchaseDeductsPriceAndReducesStock() {
        Snack chips = new Snack(1, "Chips", 20, 3, 130);
        VendingMachineImpl machine = machine(chips);
        machine.insertCoin(20);

        var result = machine.purchaseProduct(1);

        assertEquals(PurchaseStatus.SUCCESS, result.getStatus());
        assertSame(chips, result.getProduct().orElseThrow());
        assertEquals(0, result.getChangeReturned());
        assertEquals(0, machine.getBalance());
        assertEquals(2, chips.getQuantity());
    }

    @Test
    void insufficientBalanceReportsMissingAmountAndPreservesState() {
        Snack chips = new Snack(1, "Chips", 20, 3, 130);
        VendingMachineImpl machine = machine(chips);
        machine.insertCoin(10);

        var result = machine.purchaseProduct(1);

        assertEquals(PurchaseStatus.INSUFFICIENT_BALANCE, result.getStatus());
        assertEquals(10, result.getMissingAmount());
        assertEquals(10, machine.getBalance());
        assertEquals(3, chips.getQuantity());
    }

    @Test
    void outOfStockPurchasePreservesBalanceAndStock() {
        Snack chips = new Snack(1, "Chips", 20, 0, 130);
        VendingMachineImpl machine = machine(chips);
        machine.insertCoin(20);

        var result = machine.purchaseProduct(1);

        assertEquals(PurchaseStatus.OUT_OF_STOCK, result.getStatus());
        assertEquals(20, machine.getBalance());
        assertEquals(0, chips.getQuantity());
    }

    @Test
    void unknownProductDoesNotChangeBalance() {
        VendingMachineImpl machine = machine(new Snack(1, "Chips", 20, 3, 130));
        machine.insertCoin(20);

        var result = machine.purchaseProduct(999);

        assertEquals(PurchaseStatus.NOT_FOUND, result.getStatus());
        assertTrue(result.getProduct().isEmpty());
        assertEquals(20, machine.getBalance());
    }

    @Test
    void successfulOverpaymentReturnsChangeAndResetsBalance() {
        Snack chips = new Snack(1, "Chips", 15, 2, 130);
        VendingMachineImpl machine = machine(chips);
        machine.insertCoin(20);

        var result = machine.purchaseProduct(1);

        assertEquals(PurchaseStatus.SUCCESS, result.getStatus());
        assertEquals(5, result.getChangeReturned());
        assertEquals(0, machine.getBalance());
        assertEquals(1, chips.getQuantity());
    }

    @Test
    void returnChangeReturnsFullBalanceAndResetsIt() {
        VendingMachineImpl machine = machine(new Snack(1, "Chips", 15, 2, 130));
        machine.insertCoin(50);

        assertEquals(50, machine.returnChange());
        assertEquals(0, machine.getBalance());
        assertEquals(0, machine.returnChange());
    }

    @Test
    void productListIsReturnedInCatalogOrder() {
        ProductCatalog catalog = new ProductCatalog();
        Product chips = new Snack(1, "Chips", 15, 5, 130);
        Product cola = new Beverage(2, "Cola", 20, 3, 330);
        catalog.addProduct(chips);
        catalog.addProduct(cola);
        VendingMachine machine = new VendingMachineImpl(catalog, new Payment());

        assertEquals(List.of(chips, cola), machine.getProducts());
    }

    @Test
    void productDescriptionsUsePolymorphicCategoryDetails() {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(new Snack(1, "Chips", 15, 5, 130));
        catalog.addProduct(new Beverage(2, "Cola", 20, 3, 330));
        catalog.addProduct(new Fruit(3, "Apple", 10, 8, "Sweden"));
        VendingMachine machine = new VendingMachineImpl(catalog, new Payment());

        assertEquals(List.of("Chips (Snack, 130g)", "Cola (Beverage, 330ml)", "Apple (Fruit, Sweden)"),
                machine.getProducts().stream().map(Product::describe).toList());
    }

    @Test
    void constructorRejectsNullCollaborators() {
        assertThrows(NullPointerException.class, () -> new VendingMachineImpl(null, new Payment()));
        assertThrows(NullPointerException.class, () -> new VendingMachineImpl(new ProductCatalog(), null));
    }

    private VendingMachineImpl machine(Product product) {
        ProductCatalog catalog = new ProductCatalog();
        catalog.addProduct(product);
        return new VendingMachineImpl(catalog, new Payment());
    }
}
