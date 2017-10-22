package io.phoenix.app.splasho.curated;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.container.Tab;

/**
 * Created by sudharti on 10/22/17.
 */

public class CuratedFragment extends Fragment {

    private static final String TAB = "tab";

    private Tab mTab;

    public static CuratedFragment newInstance(Tab tab) {
        CuratedFragment fragment = new CuratedFragment();

        Bundle args = new Bundle();
        args.putParcelable(TAB, tab);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        mTab = getArguments().getParcelable(TAB);

        return view;
    }
}
