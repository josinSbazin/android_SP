package ru.com.rh.sp;

import android.os.Bundle;
import butterknife.ButterKnife;

public class SortamentActivity extends SecondaryActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortament);
        ButterKnife.bind(this);

        setUpToolbar(getString(R.string.menu_sortament_title));
    }
}
