package se.lexicon.vendingmachine;

public abstract class Product {

    private final int id;
    private final String name;
    private final int price;
    private int quantity;
    protected Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
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
    public final int getQuantity() {
        return quantity;

    }
    public abstract String describe();
    public final boolean dispenseOne() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }
}