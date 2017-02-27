package lancepogi.mobiledevelopmentproject;

import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Lance on 12/24/2016.
 */

public class PagerAdapterClass extends FragmentStatePagerAdapter {


    public PagerAdapterClass(FragmentManager fm) {

        super(fm);

    }

    @Override
    public void notifyDataSetChanged() {

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentSummary summary = new FragmentSummary();
                return summary;
            case 1:
                FragmentSchedule schedule = new FragmentSchedule();
                return schedule;
            case 2:
                FragmentAdd add = new FragmentAdd();

                return add;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SUMMARY";
            case 1:
                return "SCHEDULE";
            case 2:
                return "ADD";
            default:
                return null;
        }
    }
}
