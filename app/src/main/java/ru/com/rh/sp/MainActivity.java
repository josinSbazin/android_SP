package ru.com.rh.sp;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ru.com.rh.sp.ExpandedMenuCreator.ExpMenu;

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
        initDrawer(mDrawerLayout);

        //initialize menu
        ExpMenu mainMenu = new ExpMenu();
        ExpMenu.Group arm = mainMenu.addGroup("Арматура");
//        arm.addItem("")

    }

    //Навигационные кнопки внутри и снаружи drawer'a
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

    //Инициализация drawer
    private void initDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout != null) {
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_drawer_main, menu);
//        return true;
//    }
}
