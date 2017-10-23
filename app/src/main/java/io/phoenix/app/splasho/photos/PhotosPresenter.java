package io.phoenix.app.splasho.photos;

import android.support.annotation.NonNull;

import java.util.List;

import io.phoenix.app.splasho.data.Cancellable;
import io.phoenix.app.splasho.data.repositories.photos.PhotosRepository;
import io.phoenix.app.splasho.data.models.Photo;

import static io.phoenix.app.splasho.data.repositories.photos.PhotosDataSource.*;
import static io.phoenix.app.splasho.photos.PhotosContract.*;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosPresenter implements Presenter, Cancellable {

    @NonNull
    private View mView;

    @NonNull
    private final PhotosRepository mRepository;

    public PhotosPresenter(View view, PhotosRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadPhotos(int page, String orderBy) {
        mRepository.loadPhotos(page, orderBy, new LoadPhotosCallback() {
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
    public void cancel() {
        mRepository.cancel();
    }
}
