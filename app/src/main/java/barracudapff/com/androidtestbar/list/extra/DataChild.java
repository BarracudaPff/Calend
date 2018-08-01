package barracudapff.com.androidtestbar.list.extra;

import java.util.Arrays;

import barracudapff.com.androidtestbar.R;
public class DataChild {
    private String subject;
    private boolean isFav;
    private String timePeriod;
    private String teacher;
    private Integer[] images;

    private boolean isDescription;
    private String description;

    public DataChild(String subject, boolean isFav, String timePeriod, String teacher, int images, String description) {
        this.subject = subject;
        this.isFav = isFav;
        this.timePeriod = timePeriod;
        this.teacher = teacher;
        this.images = new Integer[3];
        for (int i = 0; i < images; i++) {
            this.images[i]= R.color.newC;
        }
        for (int i = images; i < this.images.length; i++) {
            this.images[i]= null;
        }
        this.description = description;
        isDescription = description != null;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isFav() {
        return isFav;
    }

    public String getDate() {
        StringBuilder builder = new StringBuilder();
        builder.append(timePeriod).append(" | ").append(teacher);
        return builder.toString();
    }

    public Integer[] getImages() {
        return images;
    }

    public boolean isDescription() {
        return isDescription;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "DataChild{" +
                "subject='" + subject + '\'' +
                ", isFav=" + isFav +
                ", timePeriod='" + timePeriod + '\'' +
                ", teacher='" + teacher + '\'' +
                ", images=" + Arrays.toString(images) +
                ", isDescription=" + isDescription +
                ", description='" + description + '\'' +
                '}';
    }
}
