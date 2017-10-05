package ru.com.rh.sp;

import android.os.Bundle;
import butterknife.ButterKnife;

public class WeightCalcActivity extends SecondaryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calc);
        ButterKnife.bind(this);

        setUpToolbar(getString(R.string.menu_weight_title));
    }
}
