package ru.com.rh.sp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //disable shadow on DrawerMenu
        ((DrawerLayout)findViewById(R.id.drawer_layout)).setDrawerElevation(0);
    }
}
