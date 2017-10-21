package io.phoenix.app.splasho.photos;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import io.phoenix.app.splasho.SmartFragmentStatePagerAdapter;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosViewPagerAdapter extends SmartFragmentStatePagerAdapter {

    private String[] tabs;

    public PhotosViewPagerAdapter(FragmentManager fragmentManager, String[] tabs) {
        super(fragmentManager);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotosFragment.newInstance();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
