package io.phoenix.app.splasho.curated;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.container.Tab;
import io.phoenix.app.splasho.data.photos.PhotosRepository;
import io.phoenix.app.splasho.models.Photo;
import io.phoenix.app.splasho.photos.PhotosContract.OrderBy;

/**
 * Created by sudharti on 10/22/17.
 */

public class CuratedFragment extends Fragment implements CuratedContract.View {

    private static final String TAB = "tab";

    private CuratedPresenter mPresenter;
    private PhotosRepository mRepository;

    private Tab mTab;

    public static CuratedFragment newInstance(Tab tab) {
        CuratedFragment fragment = new CuratedFragment();

        Bundle args = new Bundle();
        args.putParcelable(TAB, tab);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = PhotosRepository.getInstance();
        mPresenter = new CuratedPresenter(this, mRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);

        mTab = getArguments().getParcelable(TAB);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadCuratedPhotos(1, (mTab != null) ? mTab.getKey() : OrderBy.LATEST);
    }

    @Override
    public void onCuratedPhotosLoaded(List<Photo> photos) {

    }

    @Override
    public void onDataNotAvailable(String errorMessage) {

    }
}
