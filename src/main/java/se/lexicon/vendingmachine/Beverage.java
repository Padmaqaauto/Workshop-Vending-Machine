package se.lexicon.vendingmachine;

public class Beverage extends Product {
    private int volumeMl;
    public Beverage(int id, String name, int price, int quantity, int volumeMl) {
        super(id, name, price, quantity);
        this.volumeMl = volumeMl;
    }
    public int getVolumeMl() {
        return volumeMl;
    }
    @Override
    public String describe() {
        return getName() + " - " + getVolumeMl() + "ml";
    }
}