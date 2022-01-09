package com.example.romannumeralcalculator;

import com.example.romannumeralcalculator.operations.Addition;
import com.example.romannumeralcalculator.operations.ArithmeticOperation;
import com.example.romannumeralcalculator.operations.Division;
import com.example.romannumeralcalculator.operations.Exponentiation;
import com.example.romannumeralcalculator.operations.Multiplication;
import com.example.romannumeralcalculator.operations.Subtraction;

import java.util.Observable;
import java.util.Observer;

/**
 * RomanNumeralCalculator Class
 */
public class RomanNumeralCalculator extends Observable implements Observer {

    /**
     * Secondary Text Display: shows the decimal results
     */
    private final TextDisplay decimalTextDisplay;

    /**
     * Main Text Display: shows input text and output Roman Numeral representation
     */
    private final TextDisplay mainTextDisplay;

    /**
     * Abstract Class for arithmetic operations
     */
    private ArithmeticOperation arithmeticOperation;

    /**
     * Error Message based on exceptions
     */
    private String errorMessage;

    /**
     * Refresh the displays when true
     */
    private boolean refresh;

    /**
     * Integer of the input text from the main text display
     */
    private Integer input;

    /**
     * Empty constructor for this RomanNumeralCalculator Class
     */
    public RomanNumeralCalculator() {
        decimalTextDisplay = new TextDisplay();
        mainTextDisplay = new TextDisplay();

        decimalTextDisplay.addObserver(this);
        mainTextDisplay.addObserver(this);
        refresh = false;

        setArithmeticOperation(null);
        setErrorMessage(null);
        setInput(null);
    }

    /**
     * Calculate the result from using input and arithmeticOperation
     * then display it to both of the displays
     */
    public void calculate() {
        if (getArithmeticOperation() == null) {
            return;
        }

        if (getInput() == null) {
            setInput(getMainTextDisplay());
        }

        if (refresh) {
            try {
                int a = getInput();
                if (!getMainTextDisplay().equals("")) {
                    setInput(getMainTextDisplay());
                    if (getInput() != null) {
                        int b = getInput();
                        String result = getArithmeticOperation().performArithmeticOperation(a, b);
                        setArithmeticOperation(null);
                        setInput(null);
                        setMainTextDisplay(result, true);
                        RomanNumeralConverter romanNumeral = new RomanNumeralConverter();
                        int decimalValue = romanNumeral.convertToInt(result);
                        setSecondaryTextDisplay(String.valueOf(decimalValue));
                        refresh = false;
                    }
                }
            } catch (Exception e) {
                setErrorMessage(e.getMessage());
                setChanged();
                notifyObservers();
            }
        }
    }

    /**
     * Return the text from the mainTextDisplay
     *
     * @return String text
     */
    public String getMainTextDisplay() {
        return mainTextDisplay.getText();
    }

    /**
     * Return the text from the secondary decimalTextDisplay
     *
     * @return String text
     */
    public String getSecondaryTextDisplay() {
        return decimalTextDisplay.getText();
    }

    /**
     * Set input text to the secondary decimalTextDisplay and update the display
     *
     * @param input String text
     */
    public void setSecondaryTextDisplay(String input) {
        decimalTextDisplay.setText(input);
        setChanged();
        notifyObservers();
    }

    /**
     * Set or append input text to the mainTextDisplay
     * based on refreshed, and update the display
     *
     * @param input     String text
     * @param refreshed Boolean true if the input replaces current text in the display
     */
    public void setMainTextDisplay(String input, boolean refreshed) {
        if (refreshed) {
            mainTextDisplay.setText(input);
            mainTextDisplay.setAppendState();
        } else {
            mainTextDisplay.appendToText(input);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Perform the Addition Operation
     */
    public void add() {
        setArithmeticOperation(new Addition());
    }

    /**
     * Perform the Subtraction Operation
     */
    public void subtract() {
        setArithmeticOperation(new Subtraction());
    }

    /**
     * Perform the Multiplication Operation
     */
    public void multiple() {
        setArithmeticOperation(new Multiplication());
    }

    /**
     * Perform the Division Operation
     */
    public void divide() {
        setArithmeticOperation(new Division());
    }

    /**
     * Perform the Exponentiation Operation
     */
    public void power() {
        setArithmeticOperation(new Exponentiation());
    }

    /**
     * Clear the MainTextDisplay, arithmeticOperation, and input text
     */
    public void clear() {
        setInput(null);
        setArithmeticOperation(null);
        setMainTextDisplay("0", true);
    }

    /**
     * Return the input for this RomanNumeralCalculator Class
     *
     * @return Integer of input
     */
    public Integer getInput() {
        return input;
    }

    /**
     * Set the input for this RomanNumeralCalculator Class
     *
     * @param input String text
     */
    private void setInput(String input) {
        if (input == null || input.equals("")) {
            this.input = null;
            return;
        }
        RomanNumeralConverter romanNumeral = new RomanNumeralConverter();
        Integer decimalValue = romanNumeral.convertToInt(input);
        if (decimalValue == 0) {
            try {
                this.input = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                setErrorMessage("Invalid Value");
                setMainTextDisplay("0", true);
                this.input = 0;
            }
        } else {
            this.input = decimalValue;
        }
    }

    /**
     * Return the arithmeticOperation for this RomanNumeralCalculator Class
     *
     * @return ArithmeticOperation
     */
    private ArithmeticOperation getArithmeticOperation() {
        return arithmeticOperation;
    }

    /**
     * Set the arithmeticOperation for this RomanNumeralCalculator Class
     *
     * @param arithmeticOperation ArithmeticOperation operator to use
     */
    private void setArithmeticOperation(ArithmeticOperation arithmeticOperation) {
        if (arithmeticOperation == null) {
            this.arithmeticOperation = null;
            return;
        }
        calculate();
        setInput(getMainTextDisplay());
        this.arithmeticOperation = arithmeticOperation;
        mainTextDisplay.setAppendState();
    }

    /**
     * Return the errorMessage for this RomanNumeralCalculator Class
     *
     * @return String of the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set the errorMessage for this RomanNumeralCalculator Class
     *
     * @param message String of the errorMessage
     */
    public void setErrorMessage(String message) {
        errorMessage = message;
    }

    /**
     * Set the mainTextDisplays to be refereshed for this RomanNumeralCalculator Class
     *
     * @param observable Observable class
     * @param data       Object
     */
    @Override
    public void update(Observable observable, Object data) {
        refresh = true;
    }
}
