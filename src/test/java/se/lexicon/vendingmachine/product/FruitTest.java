
package se.lexicon.vendingmachine.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    void shouldStoreOrigin() {
        Fruit fruit = new Fruit(3, "Apple", 10, 5, "Sweden");

        assertEquals("Sweden", fruit.getOrigin());
    }

    @Test
    void shouldDescribeFruit() {
        Fruit fruit = new Fruit(3, "Apple", 10, 5, "Sweden");

        assertEquals(
            "Apple (Fruit, Sweden)",
            fruit.describe()
        );
    }

    @Test
    void shouldRejectNullOrigin() {
        assertThrows(IllegalArgumentException.class,
            () -> new Fruit(3, "Apple", 10, 5, null));
    }

    @Test
    void shouldRejectBlankOrigin() {
        assertThrows(IllegalArgumentException.class,
            () -> new Fruit(3, "Apple", 10, 5, "   "));
    }
}
