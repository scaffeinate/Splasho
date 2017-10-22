package io.phoenix.app.splasho.data.collections;

import java.util.List;

import io.phoenix.app.splasho.data.Cancellable;
import io.phoenix.app.splasho.models.Collection;

import static io.phoenix.app.splasho.collections.CollectionsContract.Type;

/**
 * Created by sudharti on 10/22/17.
 */

public interface CollectionsDataSource {
    void loadCollections(int page, @Type String type, LoadCollectionsCallback callback);

    interface LoadCollectionsCallback {
        void onCollectionsLoaded(List<Collection> collections);

        void onDataNotAvailable(String errorMessage);
    }
}
