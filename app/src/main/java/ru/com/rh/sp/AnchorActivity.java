package ru.com.rh.sp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class AnchorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_anchor, null, false);
        mDrawerLayout.addView(contentView, 0);

        //Do the rest as you want for each activity

        mToolbar.setTitle("Нахлестка и анкеровка");
    }
}
