package ru.com.rh.sp.WeightCalc;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.com.rh.sp.BR;
import ru.com.rh.sp.BuildConfig;
import ru.com.rh.sp.Data.LinearUnits;
import ru.com.rh.sp.Data.PriceUnits;
import ru.com.rh.sp.Data.RebarDiameters;
import ru.com.rh.sp.R;
import ru.com.rh.sp.databinding.ActivityWeightCalcBinding;

import static ru.com.rh.sp.Data.LinearUnits.M;
import static ru.com.rh.sp.Data.WeightUnits.KG;
import static ru.com.rh.sp.Data.WeightUnits.T;

public class WeightCalcPresenter extends BaseObservable {
    private static final String TAG = WeightCalcPresenter.class.getSimpleName();

    private Resources res;
    private boolean isMultiplySwitchOn;
    private boolean isPriceSwitchOn;
    private ActivityWeightCalcBinding binding;

    WeightCalcPresenter(ActivityWeightCalcBinding binding, Context context) {
        this.binding = binding;
        this.isMultiplySwitchOn = binding.swMultiply.isChecked();
        this.isPriceSwitchOn = binding.swPrice.isChecked();
        res = context.getResources();
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

    void calculateAndShowResult() {
        binding.tvError.setVisibility(View.GONE);
        try {
            RebarDiameters selectedRebar = (RebarDiameters) binding.spnDiameter.getSelectedItem();
            LinearUnits selectedUnits = (LinearUnits) binding.spnUnit.getSelectedItem();
            double weightPerMeter = selectedRebar.getWeightPerMeter();
            int length = Integer.parseInt(binding.etLength.getText().toString());
            int count = Integer.parseInt(binding.etCount.getText().toString());

            //Создаем вспомогательный инструмент, т.к. долбанный биндинг с инклюдами - НЕ РАБОТАЕТ!!
            ViewFinder finder = new ViewFinder(binding.resultRows);

            //Название арматуры
            String rebarName = String.format(res.getString(R.string.rebar_name), selectedRebar);
//            ((TextView) binding.tvRebarName).setText(rebarName);
            ((TextView)finder.getViewById(R.id.tv_rebar_name)).setText(rebarName);

            //Масса одного метра
            String oneItemWeight = String.format(res.getString(R.string.one_item_weight_name),
                    M, weightPerMeter, KG);
//            ((TextView) binding.tvOneItemWeight).setText(oneItemWeight);
            ((TextView)finder.getViewById(R.id.tv_one_item_weight)).setText(oneItemWeight);

            double resultWeight = getWeigthByLength(length, selectedRebar, selectedUnits);

            //Стоимость
            TextView tvOneMeterPrice = (TextView) finder.getViewById(R.id.tv_one_meter_price);
            TextView tvOneTonPrice = (TextView) finder.getViewById(R.id.tv_one_ton_price);
            TextView tvOneStickPrice = (TextView) finder.getViewById(R.id.tv_one_stick_price);
            TextView tvResultPrice = (TextView) finder.getViewById(R.id.tv_result_price);

            if (isPriceSwitchOn) {
                PriceUnits selectedPriceUnits = (PriceUnits) binding.spnPriceUnit.getSelectedItem();
                double price = Double.parseDouble(binding.etPrice.getText().toString());

                //Стоимость одного метра
                String oneItemPriceOfLength = String.format(res.getString(R.string.one_item_price_name),
                        M, getPricePerMeter(price, selectedRebar, selectedPriceUnits));
                tvOneMeterPrice.setText(oneItemPriceOfLength);
                tvOneMeterPrice.setVisibility(View.VISIBLE);

                //Стоимость одной тонны
                String oneItemPriceOfWeight = String.format(res.getString(R.string.one_item_price_name),
                        T, getPricePerTon(price, selectedRebar, selectedPriceUnits));
                tvOneTonPrice.setText(oneItemPriceOfWeight);
                tvOneTonPrice.setVisibility(View.VISIBLE);

                //Общая стоимость
                double resultPrice = getPriceByLength(
                        price,
                        length,
                        selectedRebar,
                        selectedPriceUnits,
                        selectedUnits) * length;

                //Стоимость одного стержня
                if (isMultiplySwitchOn) {
                    String sOneStickPrice = String.format(res.getString(R.string.one_stick_price_name),
                            length,
                            selectedUnits,
                            resultPrice);
                    tvOneStickPrice.setText(sOneStickPrice);
                    tvOneStickPrice.setVisibility(View.VISIBLE);

                    //Общая стоимость с учетом количества штук
                    resultPrice *= count;
                }

                String sResultPrice = String.format(res.getString(R.string.result_price_name),
                        resultPrice);
                tvResultPrice.setText(sResultPrice);
                tvResultPrice.setVisibility(View.VISIBLE);
            } else {
                tvOneMeterPrice.setVisibility(View.GONE);
                tvOneTonPrice.setVisibility(View.GONE);
                tvOneStickPrice.setVisibility(View.GONE);
                tvResultPrice.setVisibility(View.GONE);
            }

            TextView tvOneStickWeight = (TextView) finder.getViewById(R.id.tv_one_stick_weight);

            if(isMultiplySwitchOn) {
                String oneStickWeight = String.format(res.getString(R.string.one_stick_weight_name),
                        length,
                        binding.spnUnit.getSelectedItem(),
                        getWeigthByLength(length, selectedRebar, selectedUnits));
                tvOneStickWeight.setText(oneStickWeight);
                tvOneStickWeight.setVisibility(View.VISIBLE);

                resultWeight *= count;

            } else {
                tvOneStickWeight.setVisibility(View.GONE);
            }

            //Общая масса
            String sResultWeight = String.format(res.getString(R.string.result_weight_name),
                    resultWeight);
            ((TextView)finder.getViewById(R.id.tv_all_items_weight)).setText(sResultWeight);

            //Делаем видимыми результаты
            binding.resultBlock.setVisibility(View.VISIBLE);

        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            if (BuildConfig.DEBUG)
                Log.e(TAG, "Не удалось преобразовать ввод в поле price", e);
            binding.tvError.setVisibility(View.VISIBLE);
        }
    }

    private double getWeigthByLength(double length, RebarDiameters rebar, LinearUnits units) {
        return length * units.getPartOfMeter() * rebar.getWeightPerMeter();
    }

    //Расчет параметров
    private double getPriceByLength(
            double price,
            double length,
            RebarDiameters rebar,
            PriceUnits pUnits,
            LinearUnits units) throws IndexOutOfBoundsException {
        double lengthInMeters = length * units.getPartOfMeter();

        switch ((PriceUnits) binding.spnPriceUnit.getSelectedItem()) {
            case OF_METERS:
                return lengthInMeters * price;
            case OF_TONNAS:
                return lengthInMeters * getPricePerMeter(price, rebar, pUnits);
            default:
                throw new IndexOutOfBoundsException("Нет элемента в enum'e");
        }
    }

    private double getPricePerMeter(double price, RebarDiameters rebar, PriceUnits pUnits)
            throws IndexOutOfBoundsException {
        switch (pUnits) {
            case OF_METERS:
                return price;

            case OF_TONNAS:
                double meters = 1000d / rebar.getWeightPerMeter();
                return price / meters;

            default:
                throw new IndexOutOfBoundsException("Нет элемента в enum'e");
        }
    }

    private double getPricePerTon(double price, RebarDiameters rebar, PriceUnits pUnits)
            throws IndexOutOfBoundsException {
        switch (pUnits) {
            case OF_TONNAS:
                return price;

            case OF_METERS:
                double meters = 1000d / rebar.getWeightPerMeter();
                return price * meters;

            default:
                throw new IndexOutOfBoundsException("Нет элемента в enum'e");
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

    private class ViewFinder {
        private ViewGroup parent;

        public ViewFinder(ViewGroup parent) {
            this.parent = parent;
        }

        public View getViewById(int id) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                if (child.getId() == id) return child;
            }

            throw new IllegalArgumentException("View by id not found in parent id-" + parent.getId());
        }
    }
}


