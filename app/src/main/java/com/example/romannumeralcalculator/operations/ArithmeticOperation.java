package com.example.romannumeralcalculator.operations;

import com.example.romannumeralcalculator.RomanNumeralConverter;

/**
 * ArithmeticOperation Abstract Class
 */
public abstract class ArithmeticOperation {

    /**
     * Return the Roman Numeral Representation of Integer n
     *
     * @param n Integer number between 1 and 4999
     * @return String of Roman Numeral Representation of n
     * @throws Exception when result > 4999 or result < 1
     */
    protected static String outputRomanNumeral(Integer n) throws Exception {
        /* Exception messages will be displayed using Toast */
        if (n > 4999) {
            throw new Exception("Result greater than 4999");
        } else if (n < 0) {
            throw new Exception("Result less than 0");
        } else {
            RomanNumeralConverter romanNumeralConverterNumeral = new RomanNumeralConverter();
            return romanNumeralConverterNumeral.convertToString(
                    Integer.parseInt(String.valueOf(n)));
        }
    }

    /**
     * Override this function
     * Perform an arithmetic operation on a and b
     *
     * @param a Integer first number
     * @param b Integer second number
     * @return String of Roman Numeral Representation of result
     * @throws Exception when result > 4999 or result < 1
     */
    public abstract String performArithmeticOperation(Integer a, Integer b) throws Exception;
}
