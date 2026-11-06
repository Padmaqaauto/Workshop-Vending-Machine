package se.lexicon.vendingmachine.product;

public abstract class Product {

    private final int id;
    private final String name;
    private final int price;
    private int quantity;
    protected Product(int id, String name, int price, int quantity) {
        if (id <= 0) throw new IllegalArgumentException("ID must be positive");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required");
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        this.id = id;
        this.name = name.strip();
        this.price = price;
        this.quantity = quantity;
    }
    public final int getId() {
        return id;
    }
    public final String getName() {
        return name;
    }
    public final int getPrice() {
        return price;
    }
    public final synchronized int getQuantity() {
        return quantity;

    }
    public abstract String describe();
    public final synchronized void restock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Restock amount must be positive");
        }
        quantity += amount;
    }
    public final synchronized boolean dispenseOne() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }
}