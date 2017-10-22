package io.phoenix.app.splasho.photos;

import android.support.annotation.NonNull;

import java.util.List;

import io.phoenix.app.splasho.data.photos.PhotosDataSource;
import io.phoenix.app.splasho.data.photos.PhotosRepository;
import io.phoenix.app.splasho.models.Photo;

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
        mRepository.loadPhotos(page, orderBy, new PhotosDataSource.LoadPhotosCallback() {
            @Override
            public void onPhotosLoaded(List<Photo> photos) {
                mView.onPhotosLoaded(photos);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                mView.onDataNotAvailable(errorMessage);
            }
        });
    }

    @Override
    public void loadCuratedPhotos(int page, String orderBy) {

    }
}
