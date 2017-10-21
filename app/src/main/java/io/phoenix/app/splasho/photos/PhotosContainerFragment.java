package io.phoenix.app.splasho.photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.phoenix.app.splasho.R;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosContainerFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private static String[] mTabs = new String[]{"Latest", "Popular", "Curated"};

    private PhotosViewPagerAdapter mAdapter;

    public static PhotosContainerFragment newInstance() {
        PhotosContainerFragment photosContainerFragment = new PhotosContainerFragment();
        return photosContainerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);

        mViewPager = view.findViewById(R.id.view_pager);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new PhotosViewPagerAdapter(getChildFragmentManager(), mTabs);
        mViewPager.setAdapter(mAdapter);
    }
}
