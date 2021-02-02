package viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    // Mutable is a subclass of LiveData.
    // It can be change hence the name Mutable; READ & WRITE
    private MutableLiveData<String> result = new MutableLiveData<>();
//    private MutableLiveData<String> stringOfNumbers = new MutableLiveData<>();

    // LiveData cannot be change; Immutable
    // READ ONLY
    public LiveData<String> getResult() {
        return result;
    }

//    public LiveData<String> getStringOfNumbers() {
//        return stringOfNumbers;
//    }

//    public void setStringOfNumbers(String number) {
//        stringOfNumbers.setValue(number);
//    }

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