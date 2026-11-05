package se.lexicon.vendingmachine;

public class Snack extends Product {

    private int weightGrams;
    public Snack(int id, String name, int price, int quantity, int weightGrams) {
        super(id, name, price, quantity);
        this.weightGrams = weightGrams;
    }
    public int getWeightGrams() {
        return weightGrams;
    }
    @Override
    public String describe() {
        return getName() + " - " + getWeightGrams() + "g";
    }
}