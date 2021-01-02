package com.example.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TempActivity extends AppCompatActivity {

    com.example.conversion.InputSpinnerClass inputSpinnerClass = new InputSpinnerClass();
    com.example.conversion.OutputSpinnerClass outputSpinnerClass = new OutputSpinnerClass();
    String[] stringArray;
    double[] unitMultipliers = {-272.15,1,-457.87};
    TextView outputTextView;
    EditText inputEditText;

    public static class FromKelvin
    {
        static double toCelsius = -273.15;
        static double toFahrenheitMultiplier = 9/5d;
        static double toFahrenheit = -459.67;

        public static double getToCelsius() {
            return toCelsius;
        }

        public static double getToFahrenheitMultiplier() {
            return toFahrenheitMultiplier;
        }

        public static double getToFahrenheit() {
            return toFahrenheit;
        }
    }

    public static class FromCelsius
    {
        static double toKelvin = 273.15;
        static double toFahrenheitMultiplier = 9/5d;
        static double toFahrenheit = 32;

        public static double getToKelvin() {
            return toKelvin;
        }

        public static double getToFahrenheitMultiplier() {
            return toFahrenheitMultiplier;
        }

        public static double getToFahrenheit() {
            return toFahrenheit;
        }
    }

    public static class  FromFahrenheit {
        static double toKelvinMultiplier = 5/9d;
        static double toKelvin = 255.37;
        static double toCelsiusMultiplier = 5/9d;
        static double toCelsius = -17.78;

        public static double getToKelvinMultiplier() {
            return toKelvinMultiplier;
        }

        public static double getToKelvin() {
            return toKelvin;
        }

        public static double getToCelsiusMultiplier() {
            return toCelsiusMultiplier;
        }

        public static double getToCelsius() {
            return toCelsius;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        Spinner inputSpinner = (Spinner) findViewById(R.id.fromUnit_spinner);
        Spinner outputSpinner = (Spinner) findViewById(R.id.toUnit_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.temp_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(adapter);
        outputSpinner.setAdapter(adapter);
        inputSpinner.setOnItemSelectedListener(inputSpinnerClass);
        outputSpinner.setOnItemSelectedListener(outputSpinnerClass);

        stringArray = getResources().getStringArray(R.array.temp_units);
        outputTextView = (TextView) findViewById(R.id.output_TextView);

        inputEditText = findViewById(R.id.input_TextEdit);
        inputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    inputEditText.setHintTextColor(Color.BLACK);
                }
            }
        });
    }

    public void recalculate(View view) {
        inputEditText.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        double result = 0.d;
        try
        {
            result = Double.parseDouble(inputEditText.getText().toString());
        }
        catch (NumberFormatException e) {
            inputEditText.setHintTextColor(Color.RED);

            Toast toast = Toast.makeText(this, R.string.inputTextEdit_toast_message,Toast.LENGTH_SHORT);
            toast.show();
        }


        //Kelvin -> Celsius
        if (inputSpinnerClass.selected.equals(stringArray[0]) &&
                outputSpinnerClass.selected.equals(stringArray[1]))
            result += FromKelvin.getToCelsius();
        //Kelvin -> Fahrenheit
        else if (inputSpinnerClass.selected.equals(stringArray[0]) &&
                outputSpinnerClass.selected.equals(stringArray[2]))
            result = FromKelvin.getToFahrenheitMultiplier()*result + FromKelvin.getToFahrenheit();
        //Celsius -> Kelvin
        else if (inputSpinnerClass.selected.equals(stringArray[1]) &&
                outputSpinnerClass.selected.equals(stringArray[0]))
            result += FromCelsius.getToKelvin();
        //Celsius -> Fahrenheit
        else if (inputSpinnerClass.selected.equals(stringArray[1]) &&
                outputSpinnerClass.selected.equals(stringArray[2]))
            result = FromCelsius.getToFahrenheitMultiplier()*result + FromCelsius.getToFahrenheit();
        //Fahrenheit -> Celsius
        else if (inputSpinnerClass.selected.equals(stringArray[2]) &&
                outputSpinnerClass.selected.equals(stringArray[1]))
            result = FromFahrenheit.getToCelsiusMultiplier()*result + FromFahrenheit.getToCelsius();
        //Fahreinheit -> Kelvin
        else if (inputSpinnerClass.selected.equals(stringArray[2]) &&
                outputSpinnerClass.selected.equals(stringArray[0]))
            result = FromFahrenheit.getToKelvinMultiplier()*result + FromFahrenheit.getToKelvin();


        outputTextView.setText(String.valueOf(result));
    }
}