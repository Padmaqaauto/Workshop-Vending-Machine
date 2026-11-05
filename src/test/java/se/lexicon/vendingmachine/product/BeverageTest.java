
package se.lexicon.vendingmachine.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeverageTest {

    @Test
    void shouldStoreVolume() {
        Beverage beverage = new Beverage(2, "Juice", 15, 5, 250);

        assertEquals(250, beverage.getVolumeMl());
    }

    @Test
    void shouldDescribeBeverage() {
        Beverage beverage = new Beverage(2, "Juice", 15, 5, 250);

        assertEquals("Juice - 250ml", beverage.describe());
    }

    @Test
    void shouldRejectZeroVolume() {
        assertThrows(IllegalArgumentException.class,
                () -> new Beverage(2, "Juice", 15, 5, 0));
    }

    @Test
    void shouldRejectNegativeVolume() {
        assertThrows(IllegalArgumentException.class,
                () -> new Beverage(2, "Juice", 15, 5, -250));
    }
}
