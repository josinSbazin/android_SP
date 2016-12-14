package ru.com.rh.sp;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ExpandableListView;


import ru.com.rh.sp.ExpandedMenuCreator.ExpMenu;
import ru.com.rh.sp.ExpandedMenuCreator.ExpMenuAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private View mDrawerView;
    private ExpandableListView mExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerView = findViewById(R.id.left_drawer);
        mExpandableListView = (ExpandableListView)findViewById(R.id.exListMenu);

        final ExpMenuAdapter mainMenuAdapter = initMainMenu(mExpandableListView);
        initDrawer(mDrawerLayout);

        SearchView mSearchView = (SearchView) findViewById(R.id.searchView);

        //назначаем создание нового меню с при поиске
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            ExpMenu currentMainMenu = mainMenuAdapter.getMenu();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return search(newText);
            }

            private boolean search(String query) {
                if (query.length() == 0) {
                    //вообще, это метод пересоздания меню, но куда его деть?
                    mExpandableListView.setAdapter(mainMenuAdapter);
                    mExpandableListView.expandGroup(0);
                }

                ExpMenuAdapter newExpMenuAdapter = new ExpMenuAdapter(getApplicationContext(),
                        currentMainMenu.getMenuBySearchString(query));
                mExpandableListView.setAdapter(newExpMenuAdapter);

                for (int i = 0, len = newExpMenuAdapter.getGroupCount(); i < len; i++) {
                    mExpandableListView.expandGroup(i);
                }
                return false;
            }

        });

        //Назначаем воссоздание основного меню на функцию закрытия
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mExpandableListView.setAdapter(mainMenuAdapter);
                mExpandableListView.expandGroup(0);
                return false;
            }
        });
    }

    //Навигационные кнопки внутри и снаружи drawer'a
    public void onClickNavigationButton(View view) {
        if (mDrawerLayout != null)
                    mDrawerLayout.openDrawer(mDrawerView);
            }

    //Инициализация drawer
    private void initDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout != null) {
            drawerLayout.setScrimColor(Color.TRANSPARENT);
            drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        }
    }

    private ExpMenuAdapter initMainMenu(ExpandableListView listView) {
        //initialize menu
        ExpMenu mainMenu = new ExpMenu(ContextCompat.getDrawable(this, R.drawable.ic_group_close),
                ContextCompat.getDrawable(this, R.drawable.ic_group_open));
        ExpMenu.Group arm = mainMenu.addGroup("Арматура",
                ContextCompat.getDrawable(this, R.drawable.ic_arm));
        arm.addMenuItem("Нахлестка и анкеровка",
                ContextCompat.getDrawable(this, R.drawable.ic_menu_item));
        arm.addMenuItem("Сортамент",
                ContextCompat.getDrawable(this, R.drawable.ic_menu_item));
        arm.addMenuItem("Расчетные сопротивления",
                ContextCompat.getDrawable(this, R.drawable.ic_menu_item));
        arm.addMenuItem("Калькулятор массы",
                ContextCompat.getDrawable(this, R.drawable.ic_menu_item));
        arm.addMenuItem("Расчет площади",
                ContextCompat.getDrawable(this, R.drawable.ic_menu_item));

        ExpMenuAdapter adapter = new ExpMenuAdapter(getApplicationContext(), mainMenu);
        listView.setAdapter(adapter);
        listView.expandGroup(0);

        return adapter;
    }
}
