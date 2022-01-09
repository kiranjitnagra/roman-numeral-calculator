package com.example.romannumeralcalculator.operations;

/**
 * Exponentiation Class
 */
public class Exponentiation extends ArithmeticOperation {

    /**
     * Empty Constructor for this Exponentiation Class
     */
    public Exponentiation() {
    }

    /**
     * Return a to the power of b
     *
     * @param a Integer base number
     * @param b Integer exponent number
     * @return String of Roman Numeral Representation of result
     * @throws Exception when result > 4999 or result < 1
     */
    @Override
    public String performArithmeticOperation(Integer a, Integer b) throws Exception {
        return outputRomanNumeral((int) Math.pow(a, b));
    }
}
