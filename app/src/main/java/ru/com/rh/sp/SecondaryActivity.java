package ru.com.rh.sp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;

public abstract class SecondaryActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)             protected Toolbar mToolbar;

    /**
     * Устанавливает настройки тулбара для второстепенной активности
     * @param title - заголовок активности
     */
    protected void setUpToolbar(String title) {
        mToolbar.setTitle(title);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackClick();
            }
        });
    }

    private void onBackClick() {
        finish();
    }
}
