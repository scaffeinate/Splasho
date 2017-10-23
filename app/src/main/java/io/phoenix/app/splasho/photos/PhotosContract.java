package io.phoenix.app.splasho.photos;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.util.List;

import io.phoenix.app.splasho.data.models.Photo;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by sudharti on 10/21/17.
 */

public interface PhotosContract {

    @Retention(SOURCE)
    @StringDef({OrderBy.LATEST, OrderBy.POPULAR, OrderBy.OLDEST})
    @interface OrderBy {
        String LATEST = "latest";
        String POPULAR = "popular";
        String OLDEST = "oldest";
    }

    interface Presenter {
        void loadPhotos(int page, @OrderBy String orderBy);
    }

    interface View {
        void onPhotosLoaded(List<Photo> photos);

        void onDataNotAvailable(String errorMessage);
    }
}
