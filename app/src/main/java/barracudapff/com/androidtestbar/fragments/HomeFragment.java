package barracudapff.com.androidtestbar.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import barracudapff.com.androidtestbar.R;
import barracudapff.com.androidtestbar.list.AnimatedExpandableListView;
import barracudapff.com.androidtestbar.list.ExpListAdapter;
import barracudapff.com.androidtestbar.list.extra.ChildItem;
import barracudapff.com.androidtestbar.list.extra.DataChild;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ActionSheet.ActionSheetListener {

    private AnimatedExpandableListView listView;
    private ExpandableListAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return setList(inflater.inflate(R.layout.fragment_home, container, false));
    }

    private View setList(View view) {
        // Находим наш list
        ArrayList<ArrayList<ChildItem>> groups = setData();
        ArrayList<String> names = new ArrayList<>();
        names.add("23 сентября, понедельник");
        names.add("24 сентября, вторник");
        names.add("26 сентября, четверг");
        names.add("27 сентября, четверг");
        adapter = new ExpListAdapter(getContext(), groups, names);

        listView = view.findViewById(R.id.exListView);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });
        System.out.println("Only");
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    showActionSheet();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    private ArrayList<ArrayList<ChildItem>> setData() {
        ArrayList<ArrayList<ChildItem>> groups = new ArrayList<>();
        ArrayList<ChildItem> children1 = new ArrayList<>();
        ArrayList<ChildItem> children2 = new ArrayList<>();
        ArrayList<ChildItem> children3 = new ArrayList<>();
        children1.add(new ChildItem("Матан", true, "9:30-11:05", "Бочкарев", 3,
                "текст задания текст задания текст задания текст задания текст задания текст задания текст задания ..."));
        children1.add(new ChildItem("Геометрия", false, "11:15-12:50", "Коровкин", 2, null));
        groups.add(children1);
        children2.add(new ChildItem("Практика ЭВМ", false, "11:15-12:50", "Коровкин", 1,
                "текст задания текст задания текст задания текст задания текст задания текст задания текст задания ..."));
        children2.add(new ChildItem("Практика ЭВМ", false, "11:15-12:50", "Коровкин", 1,
                "текст задания текст задания текст задания текст задания текст задания текст задания текст задания ..."));
        children2.add(new ChildItem("Практика ЭВМ", false, "11:15-12:50", "Коровкин", 1,
                "текст задания текст задания текст задания текст задания текст задания текст задания текст задания ..."));
        children2.add(new ChildItem("Практика ЭВМ", false, "11:15-12:50", "Коровкин", 1,
                "текст задания текст задания текст задания текст задания текст задания текст задания текст задания ..."));
        groups.add(children2);
        children3.add(new ChildItem("БДСТ", true, "9:30-11:15", "Малинин", 2,
                null));
        children3.add(new ChildItem("БДСТ", true, "9:30-11:15", "Малинин", 2,
                null));
        children3.add(new ChildItem("БДСТ", true, "9:30-11:15", "Малинин", 2,
                null));
        groups.add(children3);

        return groups;
    }

    public void showActionSheet() {
        ActionSheet.createBuilder(getContext(), getFragmentManager())
                .setCancelButtonTitle("Cancel")
                .setOtherButtonTitles("Item1", "Item2", "Item3", "Item4")
                .setCancelableOnTouchOutside(true).setListener(this).show();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
        Toast.makeText(getContext().getApplicationContext(), "dismissed isCancle = " + isCancel, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        Toast.makeText(getContext().getApplicationContext(), "click item index = " + index,
                Toast.LENGTH_SHORT).show();
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
