package io.phoenix.app.splasho.curated;

import java.util.List;

import io.phoenix.app.splasho.models.Photo;
import io.phoenix.app.splasho.photos.PhotosContract.OrderBy;

/**
 * Created by sudharti on 10/22/17.
 */

public interface CuratedContract {

    interface Presenter {
        void loadCuratedPhotos(int page, @OrderBy String orderBy);
    }

    interface View {
        void onCuratedPhotosLoaded(List<Photo> photos);

        void onDataNotAvailable(String errorMessage);
    }
}
