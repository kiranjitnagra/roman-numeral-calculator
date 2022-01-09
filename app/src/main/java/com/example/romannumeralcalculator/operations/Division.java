package com.example.romannumeralcalculator.operations;

/**
 * Division Class
 */
public class Division extends ArithmeticOperation {

    /**
     * Empty constructor for this Division class
     */
    public Division() {
    }

    /**
     * Return the division of a and b
     *
     * @param a Integer numerator number
     * @param b Integer denominator number
     * @return String of Roman Numeral Representation of result
     * @throws Exception when division by zero
     */
    @Override
    public String performArithmeticOperation(Integer a, Integer b) throws Exception {

        int numerator = a;
        int denominator = b;

        if (denominator == 0) {
            /* Exception messages will be displayed using Toast */
            throw new Exception("Cannot divide by zero");
        } else {
            return outputRomanNumeral(numerator / denominator);
        }
    }

}
