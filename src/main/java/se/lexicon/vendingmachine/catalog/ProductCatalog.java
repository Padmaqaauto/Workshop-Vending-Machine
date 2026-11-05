package se.lexicon.vendingmachine.catalog;

import se.lexicon.vendingmachine.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductCatalog {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        for (Product existing : products) {
            if (existing.getId() == product.getId()) {
                throw new IllegalArgumentException("Product ID already exists");
            }
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