package test;

import main.Math.PrimeFactorization;

import static org.junit.jupiter.api.Assertions.*;

class PrimeFactorizationTest {
    private PrimeFactorization test;

    @org.junit.jupiter.api.Test
    void testFactor() {
        test = new PrimeFactorization();
        assertTrue("3 * 7 *".equals("3 * 7 *"));
        assertTrue(test.factor(23).equals("23 *"));
        assertEquals(test.factor(1234),"2 * 617 *");
    }

}