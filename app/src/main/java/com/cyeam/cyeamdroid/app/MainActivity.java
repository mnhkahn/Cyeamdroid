package com.cyeam.cyeamdroid.app;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.cyeam.cyeamdroid.doodle.Doodle;
import com.cyeam.cyeamdroid.push.Utils;
import com.cyeam.cyeamdroid.wallpaper.Bing;

import java.util.Locale;


public class MainActivity extends FragmentActivity {

    FragmentPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(MainActivity.this, "api_key"));
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                this, R.layout.notification_custom_builder, R.id.notification_icon,
                R.id.notification_title, R.id.notification_text);
        cBuilder.setNotificationFlags(Notification.DEFAULT_ALL);
//		cBuilder.setNotificationDefaults(Notification.DEFAULT_SOUND
//				| Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(R.drawable.ic_launcher);
        cBuilder.setLayoutDrawable(R.drawable.ic_launcher);
        PushManager.setNotificationBuilder(this, 1, cBuilder);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_bing) {
            Bing.SetWallpaper();
            return true;
        } else if (id == R.id.action_doodle) {
            Doodle.SetDoodle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Default fragment.
            Fragment fragment = new DummySectionFragment();

            switch (position) {
                case 0:
                    fragment = new BlogFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.latest_post).toUpperCase(l);
            }
            return null;
        }
    }

    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_dummy,
                    container, false);
            TextView dummyTextView = (TextView) rootView
                    .findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(
                    ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
