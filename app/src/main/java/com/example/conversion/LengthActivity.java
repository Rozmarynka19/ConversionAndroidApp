package com.example.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LengthActivity extends AppCompatActivity  {

    InputSpinnerClass inputSpinnerClass = new InputSpinnerClass();
    OutputSpinnerClass outputSpinnerClass = new OutputSpinnerClass();
    String[] stringArray;
    double[] unitMultipliers = {1000,1,0.1,0.01,0.001};
    TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        Spinner inputSpinner = (Spinner) findViewById(R.id.fromUnit_spinner);
        Spinner outputSpinner = (Spinner) findViewById(R.id.toUnit_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(adapter);
        outputSpinner.setAdapter(adapter);
        inputSpinner.setOnItemSelectedListener(inputSpinnerClass);
        outputSpinner.setOnItemSelectedListener(outputSpinnerClass);

        stringArray = getResources().getStringArray(R.array.length_units);
        outputTextView = (TextView) findViewById(R.id.output_TextView);
    }

    public void recalculate(View view) {
        double result = Double.valueOf(((EditText) findViewById(R.id.input_TextEdit)).getText().toString());
        for (int i=0;i<stringArray.length;i++)
        {
            if(stringArray[i].equals(inputSpinnerClass.selected))
                result *= unitMultipliers[i];
            if(stringArray[i].equals(outputSpinnerClass.selected))
                result /= unitMultipliers[i];
        }

        outputTextView.setText(String.valueOf(result));
        Log.d("recalculate","here!");
    }

    class InputSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        String selected;
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              selected =parent.getItemAtPosition(position).toString();
//            Log.d("InputSpinnerClass",parent.getItemAtPosition(position).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        public String getSelected()
        {
            return selected;
        }

    }

    class OutputSpinnerClass implements AdapterView.OnItemSelectedListener
    {
        String selected;

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selected =parent.getItemAtPosition(position).toString();
//            Log.d("InputSpinnerClass",parent.getItemAtPosition(position).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        public String getSelected()
        {
            return selected;
        }
    }

}