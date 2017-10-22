package io.phoenix.app.splasho.curated;

import android.support.annotation.NonNull;

import java.util.List;

import io.phoenix.app.splasho.data.photos.PhotosDataSource;
import io.phoenix.app.splasho.data.photos.PhotosRepository;
import io.phoenix.app.splasho.models.Photo;

/**
 * Created by sudharti on 10/22/17.
 */

public class CuratedPresenter implements CuratedContract.Presenter {

    @NonNull
    private CuratedContract.View mView;

    @NonNull
    private final PhotosRepository mRepository;

    public CuratedPresenter(CuratedContract.View view, PhotosRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadCuratedPhotos(int page, String orderBy) {
        mRepository.loadCuratedPhotos(page, orderBy, new PhotosDataSource.LoadPhotosCallback() {
            @Override
            public void onPhotosLoaded(List<Photo> photos) {
                mView.onCuratedPhotosLoaded(photos);
            }

            @Override
            public void onDataNotAvailable(String errorMessage) {
                mView.onDataNotAvailable(errorMessage);
            }
        });
    }
}
