
package se.lexicon.vendingmachine.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.vendingmachine.product.Beverage;
import se.lexicon.vendingmachine.product.Fruit;
import se.lexicon.vendingmachine.product.Product;
import se.lexicon.vendingmachine.product.Snack;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCatalogTest {

    private ProductCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new ProductCatalog();
    }

    @Test
    void newCatalogShouldBeEmpty() {
        assertTrue(catalog.getAllProducts().isEmpty());
    }

    @Test
    void shouldAddProduct() {
        Product snack = new Snack(1, "Chips", 20, 5, 100);

        catalog.addProduct(snack);

        assertEquals(1, catalog.getAllProducts().size());
        assertSame(snack, catalog.getProductById(1).orElseThrow());
    }

    @Test
    void shouldStoreDifferentProductTypes() {
        catalog.addProduct(new Snack(1, "Chips", 20, 5, 100));
        catalog.addProduct(new Beverage(2, "Juice", 15, 5, 250));
        catalog.addProduct(new Fruit(3, "Apple", 10, 5, "Sweden"));

        assertEquals(3, catalog.getAllProducts().size());
    }

    @Test
    void shouldFindProductById() {
        Product snack = new Snack(1, "Chips", 20, 5, 100);
        catalog.addProduct(snack);

        assertSame(snack, catalog.getProductById(1).orElseThrow());
    }

    @Test
    void unknownProductIdShouldReturnEmpty() {
        catalog.addProduct(new Snack(1, "Chips", 20, 5, 100));

        assertTrue(catalog.getProductById(999).isEmpty());
    }

    @Test
    void shouldRejectDuplicateProductId() {
        catalog.addProduct(new Snack(1, "Chips", 20, 5, 100));

        assertThrows(IllegalArgumentException.class,
            () -> catalog.addProduct(
                new Beverage(1, "Juice", 15, 5, 250)
            ));
    }

    @Test
    void duplicateIdShouldNotChangeCatalog() {
        catalog.addProduct(new Snack(1, "Chips", 20, 5, 100));

        assertThrows(IllegalArgumentException.class,
            () -> catalog.addProduct(
                new Fruit(1, "Apple", 10, 5, "Sweden")
            ));

        assertEquals(1, catalog.getAllProducts().size());
    }

    @Test
    void shouldRejectNullProduct() {
        assertThrows(NullPointerException.class,
            () -> catalog.addProduct(null));
    }

    @Test
    void shouldPreserveInsertionOrder() {
        Product snack = new Snack(3, "Chips", 20, 5, 100);
        Product fruit = new Fruit(1, "Apple", 10, 5, "Sweden");
        Product beverage = new Beverage(2, "Juice", 15, 5, 250);

        catalog.addProduct(snack);
        catalog.addProduct(fruit);
        catalog.addProduct(beverage);

        assertEquals(
            List.of(snack, fruit, beverage),
            catalog.getAllProducts()
        );
    }

    @Test
    void returnedListShouldBeUnmodifiable() {
        catalog.addProduct(new Snack(1, "Chips", 20, 5, 100));

        List<Product> products = catalog.getAllProducts();

        assertThrows(UnsupportedOperationException.class,
            () -> products.add(
                new Fruit(2, "Apple", 10, 5, "Sweden")
            ));
    }

    @Test
    void returnedListShouldNotReflectLaterAdditions() {
        catalog.addProduct(new Snack(1, "Chips", 20, 5, 100));

        List<Product> snapshot = catalog.getAllProducts();

        catalog.addProduct(new Fruit(2, "Apple", 10, 5, "Sweden"));

        assertEquals(1, snapshot.size());
        assertEquals(2, catalog.getAllProducts().size());
    }

    @Test
    void emptyCatalogLookupShouldReturnEmpty() {
        assertTrue(catalog.getProductById(1).isEmpty());
    }
}
