package io.phoenix.app.splasho.collections;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.phoenix.app.splasho.R;

/**
 * Created by sudharti on 10/21/17.
 */

public class CollectionsFragment extends Fragment {
    public static CollectionsFragment newInstance() {
        return new CollectionsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        return view;
    }
}
