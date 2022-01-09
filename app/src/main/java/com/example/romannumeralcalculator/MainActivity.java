package com.example.romannumeralcalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

/**
 * Main class for this Roman Numeral Calculator App
 *
 * UI inspired by Apple's Default Calculator:
 * https://apps.apple.com/us/app/calculator/id1069511488
 */
public class MainActivity extends Activity implements Observer, OnClickListener {
    /*
     * Used the observer pattern as made it easy to make updates to the display
     *
     * References:
     * https://medium.com/@meekg33k/the-observer-design-pattern-an-android-implementation-f2e3d589eda8
     * https://code.tutsplus.com/tutorials/android-design-patterns-the-observer-pattern--cms-28963
     * */

    /**
     * RomanNumeralCalculator class contains all the main functionality of the App
     */
    private RomanNumeralCalculator romanNumeralCalculator;

    /**
     * Initializes the App
     *
     * @param savedInstanceState the saved instance state of this App
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        romanNumeralCalculator = new RomanNumeralCalculator();
        romanNumeralCalculator.addObserver(this);

        this.findViewById(R.id.button0).setOnClickListener(this);
        this.findViewById(R.id.button1).setOnClickListener(this);
        this.findViewById(R.id.button2).setOnClickListener(this);
        this.findViewById(R.id.button3).setOnClickListener(this);
        this.findViewById(R.id.button4).setOnClickListener(this);
        this.findViewById(R.id.button5).setOnClickListener(this);
        this.findViewById(R.id.button6).setOnClickListener(this);
        this.findViewById(R.id.button7).setOnClickListener(this);
        this.findViewById(R.id.button8).setOnClickListener(this);
        this.findViewById(R.id.button9).setOnClickListener(this);
        this.findViewById(R.id.buttonI).setOnClickListener(this);
        this.findViewById(R.id.buttonV).setOnClickListener(this);
        this.findViewById(R.id.buttonX).setOnClickListener(this);
        this.findViewById(R.id.buttonL).setOnClickListener(this);
        this.findViewById(R.id.buttonC).setOnClickListener(this);
        this.findViewById(R.id.buttonD).setOnClickListener(this);
        this.findViewById(R.id.buttonM).setOnClickListener(this);

        this.findViewById(R.id.plusButton).setOnClickListener(this);
        this.findViewById(R.id.minusButton).setOnClickListener(this);
        this.findViewById(R.id.multiplyButton).setOnClickListener(this);
        this.findViewById(R.id.divideButton).setOnClickListener(this);
        this.findViewById(R.id.powerButton).setOnClickListener(this);
        this.findViewById(R.id.equalsButton).setOnClickListener(this);
        this.findViewById(R.id.clearButton).setOnClickListener(this);
    }

    /**
     * Updates the App when there is a change
     *
     * @param observable Observable class
     * @param data       Object
     */
    @Override
    public void update(Observable observable, Object data) {
        TextView mainDisplay = this.findViewById(R.id.mainDisplay);
        TextView secondaryDisplay = this.findViewById(R.id.secondaryDisplay);
        RomanNumeralCalculator romanNumeralCalculator = (RomanNumeralCalculator) observable;

        mainDisplay.setText(romanNumeralCalculator.getMainTextDisplay());
        secondaryDisplay.setText(romanNumeralCalculator.getSecondaryTextDisplay());

        if (romanNumeralCalculator.getErrorMessage() != null) {
            /* Used the Toast class to show error messages
             * Reference: https://developer.android.com/guide/topics/ui/notifiers/toasts
             * */
            Toast toast = Toast.makeText(getApplicationContext(),
                    romanNumeralCalculator.getErrorMessage(),
                    Toast.LENGTH_SHORT);
            toast.show();
            romanNumeralCalculator.setErrorMessage(null);
        }
    }

    /**
     * Performs the button's actions when clicked
     *
     * @param v View the button
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (button.getId() == R.id.clearButton) {
            romanNumeralCalculator.clear();
        } else if (button.getId() == R.id.plusButton) {
            romanNumeralCalculator.add();
        } else if (button.getId() == R.id.minusButton) {
            romanNumeralCalculator.subtract();
        } else if (button.getId() == R.id.multiplyButton) {
            romanNumeralCalculator.multiple();
        } else if (button.getId() == R.id.divideButton) {
            romanNumeralCalculator.divide();
        } else if (button.getId() == R.id.powerButton) {
            romanNumeralCalculator.power();
        } else if (button.getId() == R.id.equalsButton) {
            romanNumeralCalculator.calculate();
        } else {
            romanNumeralCalculator.setMainTextDisplay(button.getText().toString(),
                    false);
        }
    }

}
