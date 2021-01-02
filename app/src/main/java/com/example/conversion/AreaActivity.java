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

public class AreaActivity extends AppCompatActivity {

    com.example.conversion.InputSpinnerClass inputSpinnerClass = new InputSpinnerClass();
    com.example.conversion.OutputSpinnerClass outputSpinnerClass = new OutputSpinnerClass();
    String[] stringArray;
    double[] unitMultipliers = {1000000,10000,100,1,0.01,0.0001,0.000001};
    TextView outputTextView;
    EditText inputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        Spinner inputSpinner = (Spinner) findViewById(R.id.fromUnit_spinner);
        Spinner outputSpinner = (Spinner) findViewById(R.id.toUnit_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.area_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(adapter);
        outputSpinner.setAdapter(adapter);
        inputSpinner.setOnItemSelectedListener(inputSpinnerClass);
        outputSpinner.setOnItemSelectedListener(outputSpinnerClass);

        stringArray = getResources().getStringArray(R.array.area_units);
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

        for (int i=0;i<stringArray.length;i++)
        {
            if(stringArray[i].equals(inputSpinnerClass.selected))
                result *= unitMultipliers[i];
            if(stringArray[i].equals(outputSpinnerClass.selected))
                result /= unitMultipliers[i];
        }

        outputTextView.setText(String.valueOf(result));
    }
}