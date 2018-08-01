package barracudapff.com.androidtestbar.list;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import barracudapff.com.androidtestbar.R;

public class ImageListAdapter extends BaseAdapter {

    Context context;
    List<Integer> items;

    public ImageListAdapter(Context context, List<Integer> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.photo, null);

            view = (ImageView) convertView.findViewById(R.id.photo_image);
            convertView.setTag(view);
        } else {
            convertView.getTag();
        }

        return convertView;
    }
}
