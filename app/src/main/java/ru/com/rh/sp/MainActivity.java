package ru.com.rh.sp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private View mDrawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //disable shadow on DrawerMenu
        mDrawerLayout = ((DrawerLayout)findViewById(R.id.drawer_layout));
        mDrawerView = findViewById(R.id.left_drawer);
        mDrawerLayout.setDrawerElevation(0);
    }

    public void onClickNavigateButton(View view) {
        if (mDrawerLayout != null)
        mDrawerLayout.openDrawer(mDrawerView);
    }
}
