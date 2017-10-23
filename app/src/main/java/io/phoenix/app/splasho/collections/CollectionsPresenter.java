package io.phoenix.app.splasho.collections;

import android.support.annotation.NonNull;

import java.util.List;

import io.phoenix.app.splasho.collections.CollectionsContract.Presenter;
import io.phoenix.app.splasho.data.Cancellable;
import io.phoenix.app.splasho.data.repositories.collections.CollectionsDataSource;
import io.phoenix.app.splasho.data.repositories.collections.CollectionsRepository;
import io.phoenix.app.splasho.data.models.Collection;

/**
 * Created by sudharti on 10/22/17.
 */

public class CollectionsPresenter implements Presenter, Cancellable {

    @NonNull
    private CollectionsContract.View mView;

    @NonNull
    private CollectionsRepository mRepository;

    public CollectionsPresenter(CollectionsContract.View view, CollectionsRepository repository) {
        this.mView = view;
        this.mRepository = repository;
    }

    @Override
    public void loadCollections(int page, String type) {
        mRepository.loadCollections(page, type, new CollectionsDataSource.LoadCollectionsCallback() {
            @Override
            public void onCollectionsLoaded(List<Collection> collections) {
                mView.onCollectionsLoaded(collections);
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
