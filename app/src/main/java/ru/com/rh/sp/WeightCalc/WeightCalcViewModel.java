package ru.com.rh.sp.WeightCalc;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ru.com.rh.sp.BR;
import ru.com.rh.sp.databinding.ActivityWeightCalcBinding;

public class WeightCalcViewModel extends BaseObservable {
    private boolean isMultiplySwitchOn;
    private ActivityWeightCalcBinding binding;

    WeightCalcViewModel(ActivityWeightCalcBinding binding) {
        this.binding = binding;
        this.isMultiplySwitchOn = binding.swMultiply.isChecked();
    }

    public void changeSwitch() {
        isMultiplySwitchOn = binding.swMultiply.isChecked();
        notifyPropertyChanged(BR.multiplySwitchOn);
    }


    @Bindable
    public boolean getMultiplySwitchOn() {
        return isMultiplySwitchOn;
    }
}
