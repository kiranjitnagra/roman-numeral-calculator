package com.example.romannumeralcalculator;

/**
 * RomanNumeralConverter Class
 */
public class RomanNumeralConverter {

    /**
     * String array of Roman Numeral Letters from largest value to smallest
     */
    private final String[] numerals = {"M", "CM", "D", "CD", "C",
            "XC", "L", "XL", "X",
            "IX", "V", "IV", "I"};
    /**
     * Integer array of Decimal Integers values of the
     * Roman Numeral Letters from largest value to smallest
     */
    private final Integer[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    /**
     * Empty Constructor for this RomanNumeralConverter Class
     */
    public RomanNumeralConverter() {
    }

    /**
     * Return String of the Roman Numeral
     * representation given Short Integer value
     *
     * @param value Short Integer number between 1 and 4999
     * @return String Roman Numeral representation of value
     */
    public String convertToString(Integer value) {
        StringBuilder numeral = new StringBuilder();
        if (value > 4999) {
            return "0";
        }

        if (value < 1) {
            return "0";
        }

        int i = 0;
        while (value != 0) {
            int curValue = values[i];
            if (value >= curValue) {
                value = value - curValue;
                numeral.append(numerals[i]);
            } else {
                i++;
            }
        }
        return numeral.toString();
    }

    /**
     * Return Short Integer given
     * String Roman Numeral representation numeral
     *
     * @param numeral String Roman Numeral representation of a number
     * @return Short Integer number
     */
    public Integer convertToInt(String numeral) {
        int value = 0;
        int i = 0;
        int count = 0;

        while (!numeral.equals("") && i < numerals.length) {
            String curNumeral = numerals[i];
            int curNumeralLen = curNumeral.length();
            int numeralLen = numeral.length();

            if (curNumeralLen == 2
                    && numeralLen > 1
                    && curNumeral.equals(numeral.substring(0, 2))) {
                numeral = numeral.substring(2);
                value += values[i];
                count++;
            } else if (curNumeral.equals(numeral.substring(0, 1))) {
                numeral = numeral.substring(1);
                value += values[i];
                count++;
            } else {
                i++;
                count = 0;
            }
            if (count > 3) {
                if (curNumeral.equals("M")) {
                    if (count > 5) {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }
        if (!numeral.equals("")) {
            return 0;
        }
        return value;
    }
}