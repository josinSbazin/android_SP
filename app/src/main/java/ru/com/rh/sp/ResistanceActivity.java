package ru.com.rh.sp;

import android.os.Bundle;
import butterknife.ButterKnife;

public class ResistanceActivity extends SecondaryActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistance);
        ButterKnife.bind(this);

        setUpToolbar(getString(R.string.menu_r_title));
    }
}
