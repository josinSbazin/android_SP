package ru.com.rh.sp.weightCalc;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.com.rh.sp.data.LinearUnits;
import ru.com.rh.sp.data.PriceUnits;
import ru.com.rh.sp.data.RebarDiameters;
import ru.com.rh.sp.R;
import ru.com.rh.sp.SecondaryActivity;
import ru.com.rh.sp.databinding.ActivityWeightCalcBinding;

public class WeightCalcActivity extends SecondaryActivity {

    private ActivityWeightCalcBinding binding;
    private WeightCalcPresenter presenter;

    @BindView(R.id.sw_multiply)
    Switch multiplySwitch;

    @BindView(R.id.svMain)
    ScrollView scrollView;

    @BindView(R.id.btn_calculate)
    Button btnCalculate;

    @BindView(R.id.et_count)
    EditText editTextCount;

    @BindView(R.id.et_length)
    EditText editTextLength;

    @BindView(R.id.et_price)
    EditText editTextPrice;

    @BindView(R.id.ibtn_count_plus)
    ImageButton ibtnCountPlus;

    @BindView(R.id.ibtn_count_minus)
    ImageButton ibtnCountMinus;

    @BindView(R.id.spn_diameter)
    Spinner spnDiameters;

    @BindView(R.id.spn_unit)
    Spinner spnUmits;

    @BindView(R.id.spn_price_unit)
    Spinner spnPriceUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_weight_calc, null);
        setContentView(rootView);
        setUpBindings(rootView);
        setUpListeners();
        setUpToolbar(getString(R.string.menu_weight_title));
        fillSpinners();
    }

    private void fillSpinners() {
        spnDiameters.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, RebarDiameters.values()));
        spnUmits.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, LinearUnits.values()));
        spnPriceUnit.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, PriceUnits.values()));
    }

    private void setUpBindings(View view) {
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_weight_calc);
        binding = ActivityWeightCalcBinding.bind(view);
        presenter = new WeightCalcPresenter(binding, this);
        binding.setPresenter(presenter);
        ButterKnife.bind(this);
    }

    private void clearFocusFromEditText(EditText editText, View v, MotionEvent event) {
        Rect outRect = new Rect();
        editText.getGlobalVisibleRect(outRect);
        if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
            editText.clearFocus();
            InputMethodManager imm = (InputMethodManager) v.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private void setUpListeners() {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (editTextCount.isFocused()) {
                        clearFocusFromEditText(editTextCount, v, event);
                    } else if (editTextLength.isFocused()) {
                        clearFocusFromEditText(editTextLength, v, event);
                    } else if (editTextPrice.isFocused()) {
                        clearFocusFromEditText(editTextPrice, v, event);
                    }
                }
                return false;
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.calculateAndShowResult();
            }
        });

    }
}
