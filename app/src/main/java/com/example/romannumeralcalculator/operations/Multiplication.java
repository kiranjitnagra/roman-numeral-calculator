package com.example.romannumeralcalculator.operations;

/**
 * Multiplication Class
 */
public class Multiplication extends ArithmeticOperation {

    /**
     * Empty Constructor for this Multiplication Class
     */
    public Multiplication() {
    }

    /**
     * Return the multiplication of a and b
     *
     * @param a Integer first number
     * @param b Integer second number
     * @return String of Roman Numeral Representation of result
     * @throws Exception when result > 4999 or result < 1
     */
    @Override
    public String performArithmeticOperation(Integer a, Integer b) throws Exception {
        return outputRomanNumeral(a * b);
    }

}
