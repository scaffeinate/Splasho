package io.phoenix.app.splasho.collections;

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

public class CollectionsContainerFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private static String[] mTabs = new String[]{"All", "Featured", "Curated"};
    private CollectionsViewPagerAdapter mAdapter;

    public static Fragment newIntance() {
        CollectionsContainerFragment collectionsContainerFragment = new CollectionsContainerFragment();
        return collectionsContainerFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);

        mViewPager = view.findViewById(R.id.view_pager);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        mAdapter = new CollectionsViewPagerAdapter(getChildFragmentManager(), mTabs);
        mViewPager.setAdapter(mAdapter);

        return view;
    }
}
