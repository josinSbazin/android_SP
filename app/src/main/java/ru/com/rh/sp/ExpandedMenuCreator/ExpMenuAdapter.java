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
    private ExpMenu mMenu;
    private Context mContext;

    public ExpMenuAdapter(Context context, ExpMenu menu) {
        this.mContext = context;
        this.mMenu = menu;
    }

    private static class GroupHolder {
        TextView textView;
        ImageView imageView;
        ImageView imageIndicatorView;
    }

    @Override
    public int getGroupCount() {
        return mMenu.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mMenu.getGroupById(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mMenu.getGroupById(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mMenu.getGroupById(groupPosition).getMenuItemById(childPosition);
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

        GroupHolder groupHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_layout, parent, false);
            groupHolder = new GroupHolder();
            groupHolder.imageView = (ImageView) convertView.findViewById(R.id.group_menu_image);
            groupHolder.textView = (TextView) convertView.findViewById(R.id.group_menu_text);
            groupHolder.imageIndicatorView = (ImageView) convertView.findViewById(R.id.indicator_menu_image);
            convertView.setTag(groupHolder);
        } else groupHolder = (GroupHolder) convertView.getTag();

        ExpMenu.Group group = (ExpMenu.Group)getGroup(groupPosition);

        groupHolder.textView.setText(group.getName());
        groupHolder.imageView.setImageDrawable(group.getIcon());

        if (isExpanded) groupHolder.imageIndicatorView.setImageDrawable(mMenu.getGroupIndicatorOpen());
        else groupHolder.imageIndicatorView.setImageDrawable(mMenu.getGroupIndicatorClose());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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