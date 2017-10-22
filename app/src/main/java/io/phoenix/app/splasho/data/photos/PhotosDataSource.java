package io.phoenix.app.splasho.data.photos;

import java.util.List;

import io.phoenix.app.splasho.models.Photo;
import io.phoenix.app.splasho.photos.PhotosContract;

/**
 * Created by sudharti on 10/21/17.
 */

public interface PhotosDataSource {

    void loadPhotos(int page, @PhotosContract.OrderBy String orderBy, LoadPhotosCallback callback);

    void loadCuratedPhotos(int page, @PhotosContract.OrderBy String orderBy, LoadPhotosCallback callback);

    interface LoadPhotosCallback {
        void onPhotosLoaded(List<Photo> photos);

        void onDataNotAvailable(String errorMessage);
    }
}
