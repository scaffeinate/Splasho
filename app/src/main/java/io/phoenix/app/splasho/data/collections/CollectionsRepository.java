package io.phoenix.app.splasho.data.collections;

import java.util.List;

import io.phoenix.app.splasho.data.Cancellable;
import io.phoenix.app.splasho.data.UnsplashApiClient;
import io.phoenix.app.splasho.models.Collection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static io.phoenix.app.splasho.collections.CollectionsContract.Type;

/**
 * Created by sudharti on 10/22/17.
 */

public class CollectionsRepository implements CollectionsDatasource, Cancellable {

    private static UnsplashApiClient mApiClient;
    private static CollectionsRepository mRepository;

    private CollectionsRepository() {
        mApiClient = UnsplashApiClient.getInstance();
    }

    public static CollectionsRepository getInstance() {
        if (mRepository == null) {
            mRepository = new CollectionsRepository();
        }
        return mRepository;
    }

    @Override
    public void loadCollections(int page, String type, final LoadCollectionsCallback callback) {
        switch (type) {
            case Type.ALL:
                mApiClient.loadAllCollections(page, new Callback<List<Collection>>() {
                    @Override
                    public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                        if (response.body() != null) {
                            callback.onCollectionsLoaded(response.body());
                        } else {
                            callback.onDataNotAvailable(response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {
                        callback.onDataNotAvailable(t.getMessage());
                    }
                });
                break;
            case Type.FEATURED:
                mApiClient.loadFeaturedCollections(page, new Callback<List<Collection>>() {
                    @Override
                    public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                        callback.onCollectionsLoaded(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {
                        callback.onDataNotAvailable(t.getMessage());
                    }
                });
                break;
            case Type.CURATED:
                mApiClient.loadCuratedCollections(page, new Callback<List<Collection>>() {
                    @Override
                    public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                        callback.onCollectionsLoaded(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {
                        callback.onDataNotAvailable(t.getMessage());
                    }
                });
                break;
        }
    }

    @Override
    public void cancel() {
        mApiClient.cancel();
    }
}
