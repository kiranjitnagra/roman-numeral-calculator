package com.example.romannumeralcalculator;

import java.util.Observable;

/**
 * TextDisplay Class
 */
public class TextDisplay extends Observable {

    /**
     * Text in the display
     */
    private String text;

    /**
     * Append State: true if new text is allowed to be appended to the current text
     */
    private boolean appendState;

    /**
     * Empty constructor for this TextDisplay class
     */
    public TextDisplay() {
        setAppendState();
        setText("0");
    }

    /**
     * Set appendState to false, new text clears current text in display
     */
    public void setNotAppendState() {
        appendState = false;
    }

    /**
     * Set appendState to true, new text is allowed to be appended to the current text
     */
    public void setAppendState() {
        appendState = true;
    }

    /**
     * Return the appendState for this TextDisplay Class
     *
     * @return Boolean
     */
    public boolean checkAppendText() {
        return appendState;
    }

    /**
     * Return the text for this TextDisplay Class
     *
     * @return String of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set text to be displayed for this TextDisplay Class
     *
     * @param newText String
     */
    public void setText(String newText) {
        this.text = newText;
    }

    /**
     * Append text based on appendState and update this display
     *
     * @param newText String
     */
    public void appendToText(String newText) {
        if (newText == null) {
            return;
        }
        if (checkAppendText()) {
            setText(newText);
            setNotAppendState();
        } else {
            setText(getText() + newText);
        }
        setChanged();
        notifyObservers();
    }


}
