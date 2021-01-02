package com.example.conversion;

import android.view.View;
import android.widget.AdapterView;

public class OutputSpinnerClass implements AdapterView.OnItemSelectedListener {
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
