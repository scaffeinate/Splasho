package io.phoenix.app.splasho.container;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import io.phoenix.app.splasho.collections.CollectionsFragment;
import io.phoenix.app.splasho.curated.CuratedFragment;
import io.phoenix.app.splasho.photos.PhotosFragment;

import static io.phoenix.app.splasho.container.ViewPagerContainerFragment.Screen;

/**
 * Created by sudharti on 10/22/17.
 */

public class CustomFragmentStatePagerAdapter extends SmartFragmentStatePagerAdapter {

    private Tab[] mTabs;
    private String mScreen;

    public CustomFragmentStatePagerAdapter(FragmentManager fragmentManager, @Screen String screen) {
        super(fragmentManager);
        mTabs = new Tab[]{};
        this.mScreen = screen;
    }

    public void setTabs(Tab[] tabs) {
        this.mTabs = tabs;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Tab tab = mTabs[position];
        Fragment fragment = new Fragment();

        if (tab != null && mScreen != null) {
            switch (mScreen) {
                case Screen.PHOTOS:
                    fragment = PhotosFragment.newInstance(tab);
                    break;
                case Screen.CURATED_PHOTOS:
                    fragment = CuratedFragment.newInstance(tab);
                    break;
                case Screen.COLLECTIONS:
                    fragment = CollectionsFragment.newInstance(tab);
                    break;
            }
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position].getTitle();
    }
}
