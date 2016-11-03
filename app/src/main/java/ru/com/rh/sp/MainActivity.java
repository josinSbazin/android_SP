package ru.com.rh.sp;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
    }

    public void onClickNavigationButton(View view) {
        if (mDrawerLayout != null)
            switch (view.getId()) {
                case R.id.iButtonOpenDrawer:
                    mDrawerLayout.openDrawer(mDrawerView);
                    break;
                case R.id.iButtonCloseDrawer:
                    mDrawerLayout.closeDrawer(mDrawerView);
                    break;
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawer_main, menu);
        return true;
    }
}
