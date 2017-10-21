package ru.com.rh.sp.WeightCalc;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.util.Log;

import ru.com.rh.sp.BR;
import ru.com.rh.sp.BuildConfig;
import ru.com.rh.sp.databinding.ActivityWeightCalcBinding;

public class WeightCalcPresenter extends BaseObservable {
    private static final String TAG = WeightCalcPresenter.class.getSimpleName();

    private boolean isMultiplySwitchOn;
    private boolean isPriceSwitchOn;
    private ActivityWeightCalcBinding binding;

    WeightCalcPresenter(ActivityWeightCalcBinding binding) {
        this.binding = binding;
        this.isMultiplySwitchOn = binding.swMultiply.isChecked();
        this.isPriceSwitchOn = binding.swPrice.isChecked();
    }

    public void changeMultiplySwitch() {
        isMultiplySwitchOn = binding.swMultiply.isChecked();
        notifyPropertyChanged(BR.multiplySwitchOn);
    }

    public void changePriceSwitch() {
        isPriceSwitchOn = binding.swPrice.isChecked();
        notifyPropertyChanged(BR.priceSwitchOn);
    }

    public void incrementOrDecrementCount(boolean isIncrement) {
        try {
            int count = Integer.parseInt(binding.etCount.getText().toString());
            int newCount = count + (isIncrement ? 1 : -1);
            if (newCount < 0) newCount = count;
            binding.etCount.setText(String.valueOf(newCount));
        } catch (NumberFormatException e) {
            if (BuildConfig.DEBUG)
                Log.e(TAG, "Не удалось преобразовать ввод в поле count");
        }
    }

    @Bindable
    public boolean getMultiplySwitchOn() {
        return isMultiplySwitchOn;
    }

    @Bindable
    public boolean getPriceSwitchOn() {
        return isPriceSwitchOn;
    }
}


