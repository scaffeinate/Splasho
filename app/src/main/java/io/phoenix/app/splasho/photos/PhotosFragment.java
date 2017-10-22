package io.phoenix.app.splasho.photos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import io.phoenix.app.splasho.R;
import io.phoenix.app.splasho.data.photos.PhotosRepository;
import io.phoenix.app.splasho.models.Photo;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosFragment extends Fragment implements PhotosContract.View {

    private Context mContext;
    private PhotosRepository mRepository;
    private PhotosPresenter mPresenter;

    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        mRepository = PhotosRepository.getInstance();
        mPresenter = new PhotosPresenter(this, mRepository);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadPhotos(1, PhotosContract.OrderBy.LATEST);
    }

    @Override
    public void onPhotosLoaded(List<Photo> photos) {
        Toast.makeText(mContext, "" + photos.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataNotAvailable(String errorMessage) {
        Toast.makeText(mContext, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
