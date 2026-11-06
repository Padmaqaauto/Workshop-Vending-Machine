
package se.lexicon.vendingmachine.purchase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.vendingmachine.product.Product;
import se.lexicon.vendingmachine.product.Snack;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseResultTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Snack(1, "Chips", 20, 5, 100);
    }

    @Test
    void successShouldHaveCorrectStatus() {
        PurchaseResult result = PurchaseResult.success(product, 0);

        assertEquals(PurchaseStatus.SUCCESS, result.getStatus());
        assertTrue(result.isSuccessful());
    }

    @Test
    void successShouldContainPurchasedProduct() {
        PurchaseResult result = PurchaseResult.success(product, 0);

        assertTrue(result.getProduct().isPresent());
        assertSame(product, result.getProduct().orElseThrow());
    }

    @Test
    void successShouldReturnChange() {
        PurchaseResult result = PurchaseResult.success(product, 30);

        assertEquals(30, result.getChangeReturned());
        assertEquals(0, result.getMissingAmount());
    }

    @Test
    void notFoundShouldHaveCorrectStatus() {
        PurchaseResult result = PurchaseResult.notFound();

        assertEquals(PurchaseStatus.NOT_FOUND, result.getStatus());
        assertFalse(result.isSuccessful());
    }

    @Test
    void notFoundShouldHaveNoProduct() {
        PurchaseResult result = PurchaseResult.notFound();

        assertTrue(result.getProduct().isEmpty());
        assertEquals(0, result.getMissingAmount());
        assertEquals(0, result.getChangeReturned());
    }

    @Test
    void insufficientBalanceShouldReportMissingAmount() {
        PurchaseResult result = PurchaseResult.insufficientBalance(product, 10);

        assertEquals(
                PurchaseStatus.INSUFFICIENT_BALANCE,
                result.getStatus());
        assertEquals(10, result.getMissingAmount());
        assertFalse(result.isSuccessful());
    }

    @Test
    void insufficientBalanceShouldContainProduct() {
        PurchaseResult result = PurchaseResult.insufficientBalance(product, 10);

        assertSame(product, result.getProduct().orElseThrow());
        assertEquals(0, result.getChangeReturned());
    }

    @Test
    void outOfStockShouldHaveCorrectStatus() {
        PurchaseResult result = PurchaseResult.outOfStock(product);

        assertEquals(PurchaseStatus.OUT_OF_STOCK, result.getStatus());
        assertFalse(result.isSuccessful());
    }

    @Test
    void outOfStockShouldContainProduct() {
        PurchaseResult result = PurchaseResult.outOfStock(product);

        assertSame(product, result.getProduct().orElseThrow());
        assertEquals(0, result.getMissingAmount());
        assertEquals(0, result.getChangeReturned());
    }

    @Test
    void shouldRejectNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(null, product, 0, 0));
    }

    @Test
    void shouldRejectNegativeMissingAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.INSUFFICIENT_BALANCE,
                        product, -10, 0));
    }

    @Test
    void shouldRejectNegativeChange() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.SUCCESS,
                        product, 0, -10));
    }

    @Test
    void shouldRejectProductForNotFoundStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.NOT_FOUND,
                        product, 0, 0));
    }

    @Test
    void shouldRejectNullProductForSuccess() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.SUCCESS,
                        null, 0, 0));
    }

    @Test
    void shouldRejectNullProductForInsufficientBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.INSUFFICIENT_BALANCE,
                        null, 10, 0));
    }

    @Test
    void shouldRejectNullProductForOutOfStock() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.OUT_OF_STOCK,
                        null, 0, 0));
    }
// Code Review - Finding 2:
// A successful purchase must not have an outstanding payment amount.
// This test verifies an invariant not yet enforced by PurchaseResult.
// Expected to fail until the constructor validation is corrected.

    @Test
    void successShouldRejectPositiveMissingAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.SUCCESS,
                        product, 50, 0));
    }

    @Test
    void insufficientBalanceShouldRejectZeroMissingAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchaseResult(
                        PurchaseStatus.INSUFFICIENT_BALANCE,
                        product, 0, 0));
    }

}
