
package se.lexicon.vendingmachine.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnackTest {

    @Test
    void shouldStoreWeight() {
        Snack snack = new Snack(1, "Chips", 20, 5, 150);

        assertEquals(150, snack.getWeightGrams());
    }

    @Test
    void shouldDescribeSnack() {
        Snack snack = new Snack(1, "Chips", 20, 5, 150);

        assertEquals("Chips (Snack, 150g)", snack.describe());
    }

    @Test
    void shouldRejectZeroWeight() {
        assertThrows(IllegalArgumentException.class,
            () -> new Snack(1, "Chips", 20, 5, 0));
    }

    @Test
    void shouldRejectNegativeWeight() {
        assertThrows(IllegalArgumentException.class,
            () -> new Snack(1, "Chips", 20, 5, -100));
    }
}
