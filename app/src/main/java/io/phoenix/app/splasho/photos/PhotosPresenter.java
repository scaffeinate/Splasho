package io.phoenix.app.splasho.photos;

import android.support.annotation.NonNull;

import io.phoenix.app.splasho.data.photos.PhotosRepository;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosPresenter implements PhotosContract.Presenter {

    @NonNull
    private PhotosContract.View mView;

    @NonNull
    private final PhotosRepository mRepository;

    public PhotosPresenter(PhotosContract.View view, PhotosRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadPhotos(int page, String orderBy) {

    }

    @Override
    public void loadCuratedPhotos(int page, String orderBy) {

    }
}
