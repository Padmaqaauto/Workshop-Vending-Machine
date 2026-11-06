
package se.lexicon.vendingmachine.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldStoreProductFields() {
        Product product = new Snack(1, "Chips", 20, 5, 100);

        assertAll(
                () -> assertEquals(1, product.getId()),
                () -> assertEquals("Chips", product.getName()),
                () -> assertEquals(20, product.getPrice()),
                () -> assertEquals(5, product.getQuantity()));
    }

    @Test
    void shouldTrimProductName() {
        Product product = new Snack(1, "  Chips  ", 20, 5, 100);

        assertEquals("Chips", product.getName());
    }

    @Test
    void shouldRejectZeroId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Snack(0, "Chips", 20, 5, 100));
    }

    @Test
    void shouldRejectNegativeId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Snack(-1, "Chips", 20, 5, 100));
    }

    @Test
    void shouldRejectNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Snack(1, null, 20, 5, 100));
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Snack(1, "   ", 20, 5, 100));
    }

    @Test
    void shouldRejectZeroPrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Snack(1, "Chips", 0, 5, 100));
    }

    @Test
    void shouldRejectNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Snack(1, "Chips", -20, 5, 100));
    }

    @Test
    void shouldRejectNegativeQuantity() {
        assertThrows(IllegalArgumentException.class,
                () -> new Snack(1, "Chips", 20, -1, 100));
    }

    @Test
    void shouldAllowZeroQuantity() {
        Product product = new Snack(1, "Chips", 20, 0, 100);

        assertEquals(0, product.getQuantity());
    }

    @Test
    void shouldDispenseOneProduct() {
        Product product = new Snack(1, "Chips", 20, 3, 100);

        assertTrue(product.dispenseOne());
        assertEquals(2, product.getQuantity());
    }

    @Test
    void shouldNotDispenseWhenOutOfStock() {
        Product product = new Snack(1, "Chips", 20, 0, 100);

        assertFalse(product.dispenseOne());
        assertEquals(0, product.getQuantity());
    }

    @Test
    void shouldSupportPolymorphicDescriptions() {
        Product snack = new Snack(1, "Chips", 20, 5, 100);
        Product beverage = new Beverage(2, "Juice", 15, 5, 250);
        Product fruit = new Fruit(3, "Apple", 10, 5, "Sweden");

        assertAll(
                () -> assertEquals("Chips (Snack, 100g)", snack.describe()),
                () -> assertEquals("Juice (Beverage, 250ml)", beverage.describe()),
                () -> assertEquals("Apple (Fruit, Sweden)", fruit.describe()));
    }
}
