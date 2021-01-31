package com.example.rbcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView tvDisplay;
    boolean lastNumber;
    boolean lastDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tv_display);
    }

    public void btnNumber(View v) {
        MaterialButton myBtn = (MaterialButton) v;
        tvDisplay.append(myBtn.getText());
        lastNumber = true;
    }

    public void btnClear(View v) {
        tvDisplay.setText("");
        lastNumber = false;
        lastDecimal = false;
    }

    public void btnDecimal(View v) {
        if(lastNumber && !lastDecimal) {
            tvDisplay.append(".");
            lastDecimal = true;
        }
    }

    public void btnOperator(View v) {
        MaterialButton operator = (MaterialButton) v;
        if(lastNumber && !isOperatorAdded(tvDisplay.getText().toString())) {
            tvDisplay.append(operator.getText());
            lastNumber = false;
            lastDecimal = false;
        }
    }

    public void btnEqual(View v) {
        if(lastNumber) {
            String tvString = tvDisplay.getText().toString();
            if(tvString.contains("+")) {
                String[] parts = tvString.split("\\+");
                double one = Double.parseDouble(parts[0]);
                double two = Double.parseDouble(parts[1]);
                String value = Double.toString(one + two);
                tvDisplay.setText(removeDotZero(value));
            } else if(tvString.contains("-")) {
                String[] parts = tvString.split("-");
                double one = Double.parseDouble(parts[0]);
                double two = Double.parseDouble(parts[1]);
                String value = Double.toString(one - two);
                tvDisplay.setText(removeDotZero(value));
            } else if(tvString.contains("*")) {
                String[] parts = tvString.split("\\*");
                double one = Double.parseDouble(parts[0]);
                double two = Double.parseDouble(parts[1]);
                String value = Double.toString(one * two);
                tvDisplay.setText(removeDotZero(value));
            } else if(tvString.contains("/")) {
                String[] parts = tvString.split("/");
                double one = Double.parseDouble(parts[0]);
                double two = Double.parseDouble(parts[1]);
                String value = Double.toString(one / two);
                tvDisplay.setText(removeDotZero(value));
            }
        }
    }

    private String removeDotZero(String value) {
        String result = value;
        if(value.endsWith(".0")) {
            result = value.substring(0, value.length() - 2);
        }
        return result;
    }

    private Boolean isOperatorAdded(String value) {
        return value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-");
    }


}