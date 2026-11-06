package se.lexicon.vendingmachine.payment;

public class Payment {
    private int balance;

    /**
     * Adds a valid coin to the balance.
     * @param coin The coin value to add.
     */
    public void insertCoin(int coin) {
        if (isValidCoin(coin)) {
            balance += coin;
        }
    }

    /**
     * Checks if a coin is valid (1, 2, 5, 10, 20, 50 kr).
     * @param coin The coin value to check.
     * @return true if the coin is valid, false otherwise.
     */
    public boolean isValidCoin(int coin) {
        return coin == 1 || coin == 2 || coin == 5 ||
                coin == 10 || coin == 20 || coin == 50;
    }

    /**
     * Returns the current balance.
     * @return The current balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns the remaining balance and resets it to zero.
     * @return The amount of change returned.
     */
    public int returnChange() {
        int change = balance;
        balance = 0;
        return change;
    }

    /**
     * Deducts a specified amount from the balance if valid.
     * @param amount The amount to deduct.
     */
    public void deductAmount(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        }
    }
}