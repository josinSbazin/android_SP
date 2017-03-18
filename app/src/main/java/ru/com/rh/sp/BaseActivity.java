package ru.com.rh.sp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;


import butterknife.BindView;
import butterknife.ButterKnife;
import ru.com.rh.sp.ExpandedMenuCreator.ExpMenu;
import ru.com.rh.sp.ExpandedMenuCreator.ExpMenuAdapter;

public class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)             protected Toolbar mToolbar;
    @BindView(R.id.drawer_layout)       protected DrawerLayout mDrawerLayout;
    @BindView(R.id.exListMenu)          protected ExpandableListView mExpandableListView;
    @BindView(R.id.searchView)          protected SearchView searchView;

    private ExpMenuAdapter mExpMenuAdapter;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Исправит ID чуть позже
        setId(0, -1);

        //Toolbar
        mToolbar.setTitle("Главное меню");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout != null)
                    mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


        if (mDrawerLayout != null) {
            mDrawerLayout.setScrimColor(Color.TRANSPARENT);
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        }


        //Menu in Drawer
        ExpMenu menu = initAndGetMenu();
        mExpMenuAdapter = new ExpMenuAdapter(getApplicationContext(), menu);

        mExpandableListView.setAdapter(mExpMenuAdapter);
        mExpandableListView.expandGroup(0);
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (id == getId()) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }

                //Заменить на SWITCH?
                if (groupPosition == 0 && childPosition == 0) {
                    Intent intent = new Intent(BaseActivity.this, AnchorActivity.class);
                    startActivity(intent);
                }
                //Заменить на SWITCH?
                return false;
            }
        });


        //Search field in Menu in Drawer
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mExpMenuAdapter.filterData(query);
                expandAll();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mExpMenuAdapter.filterData(newText);
                expandAll();
                return false;
            }

            private void expandAll() {
                int count = mExpMenuAdapter.getGroupCount();
                for (int i = 0; i < count; i++)
                    mExpandableListView.expandGroup(i);
            }
        });

        //Назначаем воссоздание основного меню на функцию закрытия поиска
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mExpMenuAdapter.filterData("");
                mExpandableListView.expandGroup(0);
                return false;
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mDrawerLayout.closeDrawer(GravityCompat.START, false);
    }


    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void setId(long groupPosition, long childPosition) {
        id = groupPosition << 32 | childPosition;
    }

    protected long getId() {
        return id;
    }

    private ExpMenu initAndGetMenu() {
        //initialize menu
        ExpMenu mainMenu = new ExpMenu(ContextCompat.getDrawable(this, R.drawable.ic_group_close),
                ContextCompat.getDrawable(this, R.drawable.ic_group_open));
        ExpMenu.Group arm = mainMenu.addGroup("Арматура",
                ContextCompat.getDrawable(this, R.drawable.ic_arm));
        arm.addMenuItem("Нахлестка и анкеровка",
                ContextCompat.getDrawable(this, R.drawable.ic_anchor));
        arm.addMenuItem("Сортамент",
                ContextCompat.getDrawable(this, R.drawable.ic_sortament));
        arm.addMenuItem("Расчетные сопротивления",
                ContextCompat.getDrawable(this, R.drawable.ic_r));
        arm.addMenuItem("Калькулятор массы",
                ContextCompat.getDrawable(this, R.drawable.ic_weight));
        arm.addMenuItem("Расчет площади",
                ContextCompat.getDrawable(this, R.drawable.ic_area));

        return mainMenu;
    }
}
