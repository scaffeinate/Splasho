package io.phoenix.app.splasho.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.phoenix.app.splasho.R;

/**
 * Created by sudharti on 10/23/17.
 */

// TODO (9): Finish this fragment functionality
public class PhotoDetailsFragment extends Fragment implements PhotoDetailsContract.View {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_details, container, false);
        return view;
    }
}
