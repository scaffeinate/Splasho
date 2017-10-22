package io.phoenix.app.splasho.collections;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.util.List;

import io.phoenix.app.splasho.models.Collection;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by sudharti on 10/22/17.
 */

public interface CollectionsContract {

    @Retention(SOURCE)
    @StringDef({Type.ALL, Type.FEATURED, Type.CURATED})
    @interface Type {
        String ALL = "all";
        String FEATURED = "featured";
        String CURATED = "curated";
    }

    interface Presenter {
        void loadCollections(int page, @Type String type);
    }

    interface View {
        void onCollectionsLoaded(List<Collection> collections);

        void onDataNotAvailable(String errorMessage);
    }
}
