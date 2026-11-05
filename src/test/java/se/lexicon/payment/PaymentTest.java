
package se.lexicon.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    @Test
    void initialBalanceShouldBeZero() {
        assertEquals(0, payment.getBalance());
    }

    @Test
    void shouldAcceptValidCoins() {
        int[] validCoins = {1, 2, 5, 10, 20, 50};

        for (int coin : validCoins) {
            assertTrue(payment.isValidCoin(coin));
        }
    }

    @Test
    void shouldRejectInvalidCoins() {
        int[] invalidCoins = {-10, 0, 3, 7, 100};

        for (int coin : invalidCoins) {
            assertFalse(payment.isValidCoin(coin));
        }
    }

    @Test
    void validCoinShouldIncreaseBalance() {
        payment.insertCoin(10);

        assertEquals(10, payment.getBalance());
    }

    @Test
    void invalidCoinShouldNotIncreaseBalance() {
        payment.insertCoin(7);

        assertEquals(0, payment.getBalance());
    }

    @Test
    void shouldAccumulateMultipleCoins() {
        payment.insertCoin(10);
        payment.insertCoin(20);
        payment.insertCoin(5);

        assertEquals(35, payment.getBalance());
    }

    @Test
    void shouldDeductAmountWhenBalanceIsSufficient() {
        payment.insertCoin(50);
        payment.deductAmount(20);

        assertEquals(30, payment.getBalance());
    }

    @Test
    void shouldNotDeductMoreThanAvailableBalance() {
        payment.insertCoin(10);
        payment.deductAmount(20);

        assertEquals(10, payment.getBalance());
    }

    @Test
    void shouldRejectZeroOrNegativeDeductions() {
        payment.insertCoin(20);
        payment.deductAmount(0);
        payment.deductAmount(-10);

        assertEquals(20, payment.getBalance());
    }

    @Test
    void shouldReturnChangeAndResetBalance() {
        payment.insertCoin(50);

        assertEquals(50, payment.returnChange());
        assertEquals(0, payment.getBalance());
    }

    @Test
    void returningChangeFromZeroBalanceShouldReturnZero() {
        assertEquals(0, payment.returnChange());
    }
}
