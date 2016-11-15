package ru.com.rh.sp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<Group> menu = new ArrayList<>();

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

   class Group {
        String groupTitle;
        ArrayList<Children> children = new ArrayList<Children>();

        Group (String groupTitle, ArrayList <Children> children) {
            this.groupTitle = groupTitle;
            this.children.addAll(children);
        }
    }

    class Children {
          String childrenTitle;
          Drawable childrenIcon;

        Children (String childrenTitile, Drawable childrenIcon) {
            this.childrenTitle = childrenTitile;
            this.childrenIcon = childrenIcon;
        }
    }


    class MenuExpListAdapter extends BaseExpandableListAdapter{
        private ArrayList<Group> groups;
        private Context context;

        public MenuExpListAdapter(Context context, ArrayList<Group> groups) {
            this.context = context;
            this.groups = groups;
        }

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return groups.get(groupPosition).children.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return groups.get(groupPosition).children.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.group_layout, null);
            }
//
//        if (isExpanded){
//            //Изменяем что-нибудь, если текущая Group раскрыта
//        }
//        else{
//            //Изменяем что-нибудь, если текущая Group скрыта
//        }

            TextView textGroup = (TextView) convertView.findViewById(R.id.group_menu_text);
            textGroup.setText(((Group)getGroup(groupPosition)).groupTitle);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_layout, null);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.item_menu_image);
            imageView.setImageDrawable();
            //ПРОДОЛЖИТЬ: Прикрепить к каждому итему картинку!

            TextView textChild = (TextView) convertView.findViewById(R.id.item_menu_text);
            textChild.setText(mGroups.get(groupPosition).get(childPosition));

            Button button = (Button)convertView.findViewById(R.id.buttonChild);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext,"button is pressed",5000).show();
                }
            });

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
