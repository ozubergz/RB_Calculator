package view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
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
    private MaterialButton myBtn;

    private boolean lastNumber;
    private boolean lastDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tv_display);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(
                getApplication()).create(MainViewModel.class);

        tvDisplay.setText(viewModel.getStringData());

        viewModel.getStringOfValues().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvDisplay.setText(s);
            }
        });

        viewModel.getResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvDisplay.setText(s);
            }
        });
    }

    public void btnNumber(View v) {
        myBtn = (MaterialButton) v;
        viewModel.setStringOfValues(myBtn.getText().toString());
        lastNumber = true;
    }

    public void btnClear(View v) {
        viewModel.setEmptyString();
        lastNumber = false;
        lastDecimal = false;
    }

    public void btnDecimal(View v) {
        if(lastNumber && !lastDecimal) {
            viewModel.setStringOfValues(".");
            lastDecimal = true;
        }
    }

    public void btnOperator(View v) {
        myBtn = (MaterialButton) v;
        if(lastNumber && !isOperatorAdded(tvDisplay.getText().toString())) {
            viewModel.setStringOfValues(myBtn.getText().toString());
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