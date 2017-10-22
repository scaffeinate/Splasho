package io.phoenix.app.splasho.curated;

import android.support.annotation.NonNull;

import java.util.List;

import io.phoenix.app.splasho.data.Cancellable;
import io.phoenix.app.splasho.data.photos.PhotosRepository;
import io.phoenix.app.splasho.models.Photo;

import static io.phoenix.app.splasho.curated.CuratedContract.Presenter;
import static io.phoenix.app.splasho.curated.CuratedContract.View;
import static io.phoenix.app.splasho.data.photos.PhotosDataSource.LoadPhotosCallback;

/**
 * Created by sudharti on 10/22/17.
 */

public class CuratedPresenter implements Presenter, Cancellable {

    @NonNull
    private View mView;

    @NonNull
    private final PhotosRepository mRepository;

    public CuratedPresenter(View view, PhotosRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadCuratedPhotos(int page, String orderBy) {
        mRepository.loadCuratedPhotos(page, orderBy, new LoadPhotosCallback() {
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

    @Override
    public void cancel() {
        mRepository.cancel();
    }
}
