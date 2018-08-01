package barracudapff.com.androidtestbar.list.extra;

import android.widget.ImageView;
import android.widget.TextView;

import barracudapff.com.androidtestbar.R;

public class ChildItem {

    public ChildItem(String subject, boolean isFav, String date, String teacher, int imagesCount, String description) {
        this.subject = subject;
        this.isFav = isFav;
        this.star = R.drawable.ic_star_fill_24dp;
        this.date = date + " | " + teacher;
        this.images = new Integer[3];
        for (int i = 0; i < imagesCount; i++) {
            this.images[i] = R.color.newC;
        }
        for (int i = imagesCount; i < this.images.length; i++) {
            this.images[i] = null;
        }
        this.isDescr = isDescr;
        this.description = description;
        isDescr = description != null;
    }

    public String subject;
    public boolean isFav;
    public Integer star;
    public String date;
    public Integer[] images;
    public boolean isDescr;
    public String description;
}
