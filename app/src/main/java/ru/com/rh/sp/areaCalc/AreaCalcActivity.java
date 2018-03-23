package ru.com.rh.sp.areaCalc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.com.rh.sp.R;
import ru.com.rh.sp.SecondaryActivity;

public class AreaCalcActivity extends SecondaryActivity {

    @BindView(R.id.table)
    TableLayout table;
    @BindView(R.id.expandable)
    ExpandableLayout expandable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_calc);
        ButterKnife.bind(this);

        setUpToolbar(getString(R.string.menu_area_title));
        initTable();
    }

    private void initTable() {
        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);


    }
}
