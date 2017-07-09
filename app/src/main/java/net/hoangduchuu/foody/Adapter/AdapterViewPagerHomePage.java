package net.hoangduchuu.foody.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.hoangduchuu.foody.View.Fragments.AnGiFragment;
import net.hoangduchuu.foody.View.Fragments.ODauFragment;

/**
 * Created by hoang on 7/8/17.
 */

public class AdapterViewPagerHomePage extends FragmentStatePagerAdapter {
    AnGiFragment anGiFragment;
    ODauFragment oDauFragment;

    public AdapterViewPagerHomePage(FragmentManager fm) {
        super(fm);
        anGiFragment = new AnGiFragment();
        oDauFragment = new ODauFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return oDauFragment;
            case 1:
                return anGiFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
