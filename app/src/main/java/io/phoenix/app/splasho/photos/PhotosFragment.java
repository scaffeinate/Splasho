package io.phoenix.app.splasho.photos;

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

public class PhotosFragment extends Fragment {
    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        return view;
    }
}
