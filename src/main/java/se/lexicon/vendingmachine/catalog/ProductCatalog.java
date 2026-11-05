package se.lexicon.vendingmachine.catalog;

import se.lexicon.vendingmachine.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Stores products in insertion order and provides lookup by product id. */
public final class ProductCatalog {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Product must not be null");
        if (getProductById(product.getId()).isPresent()) {
            throw new IllegalArgumentException("Duplicate product id: " + product.getId());
        }
        products.add(product);
    }

    public Optional<Product> getProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public List<Product> getAllProducts() {
        return List.copyOf(products);
    }
}
