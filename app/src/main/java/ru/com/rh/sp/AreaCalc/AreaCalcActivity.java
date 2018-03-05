package ru.com.rh.sp.AreaCalc;

import android.os.Bundle;
import butterknife.ButterKnife;
import ru.com.rh.sp.R;
import ru.com.rh.sp.SecondaryActivity;

public class AreaCalcActivity extends SecondaryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_calc);
        ButterKnife.bind(this);

        setUpToolbar(getString(R.string.menu_area_title));
    }
}
