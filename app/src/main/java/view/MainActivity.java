package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.rbcalculator.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView tvDisplay;
    private MainViewModel viewModel;

    private boolean lastNumber;
    private boolean lastDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

//        viewModel.getStringOfNumbers().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                tvDisplay.setText(s);
//            }
//        });

        viewModel.getResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvDisplay.setText(s);
            }
        });

        tvDisplay = findViewById(R.id.tv_display);
    }

    public void btnNumber(View v) {
        MaterialButton myBtn = (MaterialButton) v;
//        String number = myBtn.getText().toString();
//        viewModel.setStringOfNumbers(number);
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
            String tvValue = tvDisplay.getText().toString();
            viewModel.calculate(tvValue);
        }
    }

    private Boolean isOperatorAdded(String value) {
        return value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-");
    }


}