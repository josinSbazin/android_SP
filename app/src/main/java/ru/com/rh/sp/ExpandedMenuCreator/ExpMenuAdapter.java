package ru.com.rh.sp.ExpandedMenuCreator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ru.com.rh.sp.R;

/**
 * Адаптер для реализации созданного с помощью {@link ExpMenu} меню
 */

public class ExpMenuAdapter extends BaseExpandableListAdapter{
    private ExpMenu menu;
    private Context context;

    public ExpMenuAdapter(Context context, ExpMenu menu) {
        this.context = context;
        this.menu = menu;
    }

    @Override
    public int getGroupCount() {
        return menu.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return menu.getGroupById(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return menu.getGroupById(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return menu.getGroupById(groupPosition).getMenuItemById(childPosition);
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
            convertView = inflater.inflate(R.layout.group_layout, parent, false);
        }

        ExpMenu.Group group = (ExpMenu.Group)getGroup(groupPosition);

        ImageView indicatorImageView = group.getIndicatorImage();
        if (isExpanded){
                indicatorImageView.setImageResource(R.drawable.ic_group_open);
        }
        else{
                indicatorImageView.setImageResource(R.drawable.ic_group_close);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.group_menu_image);
        imageView.setImageDrawable(group.getIcon());

        TextView textGroup = (TextView) convertView.findViewById(R.id.group_menu_text);
        textGroup.setText(group.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
        }

        ExpMenu.Group.MenuItem child = ((ExpMenu.Group.MenuItem)getChild(groupPosition, childPosition));

        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_menu_image);
        imageView.setImageDrawable(child.getIcon());

        TextView textChild = (TextView) convertView.findViewById(R.id.item_menu_text);
        textChild.setText(child.getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
