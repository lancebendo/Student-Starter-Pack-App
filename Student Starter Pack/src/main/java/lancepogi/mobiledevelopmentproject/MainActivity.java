package lancepogi.mobiledevelopmentproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    android.app.FragmentManager fm = getFragmentManager();

    private ViewPager mViewPager;
    private PagerAdapterClass pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Student Starter Pack");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        pagerAdapter = new PagerAdapterClass(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        if(AlarmReceiver.ringtone != null) {
            if(AlarmReceiver.ringtone.isPlaying() == true) {
                AlarmReceiver.ringtone.stop();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FragmentSettings newSettings = new FragmentSettings();
            newSettings.show(fm, "Settings");
            return true;
        }

        if (id == R.id.action_about) {
            FragmentAbout newAbout = new FragmentAbout();
            newAbout.show(fm, "About");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
