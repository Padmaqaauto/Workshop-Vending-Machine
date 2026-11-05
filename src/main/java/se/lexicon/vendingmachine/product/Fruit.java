package se.lexicon.vendingmachine.product;

public class Fruit extends Product {
    private String origin;
    public Fruit(int id, String name, int price, int quantity, String origin) {
        super(id, name, price, quantity);
        if (origin == null || origin.isBlank()) throw new IllegalArgumentException("Origin is required");
        this.origin = origin;
    }
    public String getOrigin() {
        return origin;
    }
    @Override
    public String describe() {
        return getName() + " - " + getOrigin();
    }
}
