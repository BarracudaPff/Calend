package barracudapff.com.androidtestbar.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import barracudapff.com.androidtestbar.R;
import barracudapff.com.androidtestbar.list.extra.ChildItem;

public class ExpListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private LayoutInflater inflater;

    private ArrayList<ArrayList<ChildItem>> mGroups;
    private ArrayList<String> mGroupsNames;


    public ExpListAdapter(Context context, ArrayList<ArrayList<ChildItem>> groups, ArrayList<String> names) {
        inflater = LayoutInflater.from(context);
        mGroups = groups;
        mGroupsNames = names;
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;

        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = inflater.inflate(R.layout.child_view, null);
            childHolder.subject = (TextView) convertView.findViewById(R.id.textChild);
            childHolder.star = (ImageView) convertView.findViewById(R.id.imageView5);
            childHolder.date = (TextView) convertView.findViewById(R.id.textView8);
            childHolder.description = (TextView) convertView.findViewById(R.id.textView4);
            childHolder.images = new ImageView[3];
            View view = convertView.findViewById(R.id.incl1);
            childHolder.images[0] = view.findViewById(R.id.photo_image);
            view = convertView.findViewById(R.id.incl2);
            childHolder.images[1] = view.findViewById(R.id.photo_image);
            view = convertView.findViewById(R.id.incl3);
            childHolder.images[2] = view.findViewById(R.id.photo_image);

            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        ChildItem cur = mGroups.get(groupPosition).get(childPosition);
        childHolder.subject.setText(cur.subject);
        if (cur.isFav)
            childHolder.star.setImageResource(R.drawable.ic_star_fill_24dp);
        childHolder.date.setText(cur.date);

        for (int i = 0; i < childHolder.images.length; i++) {
            if (cur.images[i] != null)
                childHolder.images[i].setImageResource(cur.images[i]);
        }
        if (cur.isDescr)
            childHolder.description.setText(cur.description);
        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public GroupItem getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;

        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = inflater.inflate(R.layout.group_view, null);
            groupHolder.title = (TextView) convertView.findViewById(R.id.textGroup);
            groupHolder.arrow = (ImageView) convertView.findViewById(R.id.imageView2);

            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        if (isExpanded)// ture is Expanded or false is not isExpanded
            groupHolder.arrow.setImageResource(R.drawable.ic_arrow_up_black_24dp);
        else
            groupHolder.arrow.setImageResource(R.drawable.ic_arrow_down_black_24dp);

        //TextView textGroup = (TextView) convertView.findViewById(R.id.textGroup);
        groupHolder.title.setText(mGroupsNames.get(groupPosition));

        return convertView;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    private static class GroupItem {
        String title;
        Integer id;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildHolder {
        TextView subject;
        ImageView star;
        TextView date;
        ImageView[] images;
        TextView description;
    }

    private static class GroupHolder {
        TextView title;
        ImageView arrow;
    }

}