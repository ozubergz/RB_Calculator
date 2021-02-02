package viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rbcalculator.R;

public class MainViewModel extends AndroidViewModel {

    private static final String STRING_DATA_KEY = "SAVED_DATA_KEY";
    private String characters = "";

    // Mutable is a subclass of LiveData.
    // It can be change hence the name Mutable; READ & WRITE
    private MutableLiveData<String> result = new MutableLiveData<>();
    private MutableLiveData<String> stringOfValues = new MutableLiveData<>();

    // Shared Preferences
    private SharedPreferences sharedPref = getApplication().getSharedPreferences(
            getApplication().getString(R.string.preference_file_key), Context.MODE_PRIVATE);

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveStringData(String data) {
        sharedPref.edit().putString(STRING_DATA_KEY, data).apply();
    }

    public String getStringData() {
        return sharedPref.getString(STRING_DATA_KEY, "");
    }

    // LiveData cannot be change; Immutable
    // READ ONLY
    public LiveData<String> getResult() {
        return result;
    }

    public LiveData<String> getStringOfValues() {
        return stringOfValues;
    }

    public void setEmptyString() {
        characters = "";
        saveStringData("");
        stringOfValues.setValue(characters);
    }

    public void setStringOfValues(String v) {
        characters += v;
        saveStringData(characters);
        stringOfValues.setValue(characters);
    }

    public void calculate(String tvValue) {
        if(tvValue.contains("+")) {
            String[] parts = tvValue.split("\\+");
            double one = Double.parseDouble(parts[0]);
            double two = Double.parseDouble(parts[1]);
            String value = Double.toString(one + two);
            result.setValue(removeDotZero(value));
        } else if(tvValue.contains("-")) {
            String[] parts = tvValue.split("-");
            double one = Double.parseDouble(parts[0]);
            double two = Double.parseDouble(parts[1]);
            String value = Double.toString(one - two);
            result.setValue(removeDotZero(value));
        } else if(tvValue.contains("*")) {
            String[] parts = tvValue.split("\\*");
            double one = Double.parseDouble(parts[0]);
            double two = Double.parseDouble(parts[1]);
            String value = Double.toString(one * two);
            result.setValue(removeDotZero(value));
        } else if(tvValue.contains("/")) {
            String[] parts = tvValue.split("/");
            double one = Double.parseDouble(parts[0]);
            double two = Double.parseDouble(parts[1]);
            String value = Double.toString(one / two);
            result.setValue(removeDotZero(value));
        }
    }


    private String removeDotZero(String value) {
        String result = value;
        if(value.endsWith(".0")) {
            result = value.substring(0, value.length() - 2);
        }
        return result;
    }

}
