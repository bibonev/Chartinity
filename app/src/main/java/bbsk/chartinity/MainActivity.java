package bbsk.chartinity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Boyan on 10/24/2015.
 */
public class MainActivity extends FragmentActivity implements ActionBar.TabListener{

    ViewPager viewPager;
    ActionBar actionBar;

    // initializing variable for SharedPreferences - Saving log in details
    public static final String PREFS_NAME = "LoginPrefs";
    public static final String PREFS_ID = "Verifying Id";
    public static final String DEFAULT = "N/A";
    DatabaseHandler handler = new DatabaseHandler(this);

    private String id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartinity);



        viewPager=(ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                actionBar.setSelectedNavigationItem(arg0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //TODO: FIX THIS ASAP

        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("Capture");
        tab1.setTabListener(this);

        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText("History");
        tab2.setTabListener(this);

        ActionBar.Tab tab3 = actionBar.newTab();
        tab3.setText("Profile");
        tab3.setTabListener(this);

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);

    }

    protected void onStart(){
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_ID, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", DEFAULT);

    }

    // gets all attributes of a user
    public String getCurrentAttribute(String attribute){
        return handler.getUserAttribute(id, attribute);
    }

    public void onLogOutButtonClick(View v){
        /**
         * Removing SharedPreferences when user want to log out
         */
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("logged");
        editor.commit();
        finish();

        // starting log in activity
        Intent i = new Intent(MainActivity.this, LogIn.class);
        startActivity(i);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onBackPressed() {
        finish();
        LogIn.back_press.finish();
    }
}
