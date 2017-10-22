package io.phoenix.app.splasho.container;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.util.Arrays;

import io.phoenix.app.splasho.R;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by sudharti on 10/22/17.
 */

public class ViewPagerContainerFragment extends Fragment {

    private static final String SCREEN = "screen";
    private static final String TABS = "tabs";

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private String mScreen;
    private Tab[] mTabs;

    @Retention(SOURCE)
    @StringDef(value = {Screen.PHOTOS, Screen.CURATED_PHOTOS, Screen.COLLECTIONS})
    public @interface Screen {
        String PHOTOS = "photos";
        String CURATED_PHOTOS = "curatedPhotos";
        String COLLECTIONS = "collections";
    }

    private CustomFragmentStatePagerAdapter mAdapter;

    public static Fragment newInstance(@Screen String screen, Tab[] tabs) {
        ViewPagerContainerFragment fragment = new ViewPagerContainerFragment();

        Bundle args = new Bundle();
        args.putString(SCREEN, screen);
        args.putParcelableArray(TABS, tabs);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_container, container, false);

        Parcelable[] arr = getArguments().getParcelableArray(TABS);
        mTabs = Arrays.copyOf(arr, arr.length, Tab[].class);
        mScreen = getArguments().getString(SCREEN);

        mViewPager = view.findViewById(R.id.view_pager);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        if (mScreen != null && mTabs != null) {
            mAdapter = new CustomFragmentStatePagerAdapter(getChildFragmentManager(), mScreen);
            mViewPager.setAdapter(mAdapter);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mTabs != null) {
            mAdapter.setTabs(mTabs);
        }
    }
}
