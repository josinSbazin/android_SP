package ru.com.rh.sp.ExpandedMenuCreator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ru.com.rh.sp.MainActivity;
import ru.com.rh.sp.R;

/**
 * Адаптер для реализации соpданного с помощью {@link ExpMenu} меню
 */

public class ExpMenuAdapter extends BaseExpandableListAdapter{
    private ArrayList<ExpMenu.Group> groups;
    private Context context;

    public ExpMenuAdapter(Context context, ArrayList<MainActivity.Group> groups) {
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
        textGroup.setText(((MainActivity.Group)getGroup(groupPosition)).groupTitle);
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