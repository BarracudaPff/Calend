package barracudapff.com.androidtestbar;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import barracudapff.com.androidtestbar.fragments.CalendarFragment;
import barracudapff.com.androidtestbar.fragments.TasksFragment;
import barracudapff.com.androidtestbar.fragments.ScreenSheduleFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;

    private TasksFragment tasksFragment;
    private ScreenSheduleFragment screenSheduleFragment;
    private CalendarFragment calendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        tasksFragment = new TasksFragment();
        screenSheduleFragment = new ScreenSheduleFragment();
        calendFragment = new CalendarFragment();

        setMainFragment(tasksFragment);
        setTheme(R.style.ActionSheetStyleiOS7);

        setDropDownMenu();

        setButtonListeners();

        setNavigationMenu();

        setFloatButton();
    }

    private void setDropDownMenu() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setNavigationMenu() {
        mainNav.setSelectedItemId(R.id.nav_account);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home: {
                        mainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setMainFragment(screenSheduleFragment);
                        return true;
                    }
                    case R.id.nav_account: {
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setMainFragment(tasksFragment);
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }

    private void setFloatButton() {
        ImageView button = new ImageView(this);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setTheme(FloatingActionButton.THEME_DARK)
                .setContentView(button)
                .setBackgroundDrawable(R.drawable.add)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this)
                .setTheme(SubActionButton.THEME_DARK);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        ImageView rlIcon5 = new ImageView(this);

        rlIcon1.setImageResource(R.drawable.ic_action_chat);
        rlIcon2.setImageResource(R.drawable.ic_action_camera);
        rlIcon3.setImageResource(R.drawable.ic_action_video);
        rlIcon4.setImageResource(R.drawable.ic_action_place);
        rlIcon5.setImageResource(R.drawable.ic_action_headphones);
        FloatingActionMenu centerBottomMenu = new FloatingActionMenu.Builder(this)
                .setStartAngle(225)
                .setEndAngle(315)
                //.setAnimationHandler(new SlideInAnimationHandler())
                .addSubActionView(itemBuilder.setContentView(rlIcon1).build())
                .addSubActionView(itemBuilder.setContentView(rlIcon5).build())
                .attachTo(actionButton)
                .build();
    }

    private void setButtonListeners(){
        final TextView calendar = findViewById(R.id.textCalend);
        final TextView tasks = findViewById(R.id.textTasks);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainFragment(calendFragment);
                tasks.setBackgroundResource(0);
                tasks.setTextColor(getColor(R.color.newC));
                calendar.setBackground(getDrawable(R.drawable.layout_bg));
                calendar.setTextColor(getColor(R.color.selected));
            }
        });
        tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainFragment(tasksFragment);
                calendar.setBackgroundResource(0);
                calendar.setTextColor(getColor(R.color.newC));
                tasks.setBackground(getDrawable(R.drawable.layout_bg));
                tasks.setTextColor(getColor(R.color.selected));
            }
        });
    }

    private void setMainFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    private void setScreenFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.replace(R.id.sceen_frame, fragment);
        fragmentTransaction.commit();
    }
}
