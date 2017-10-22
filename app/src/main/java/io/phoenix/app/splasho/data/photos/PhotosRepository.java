package io.phoenix.app.splasho.data.photos;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import io.phoenix.app.splasho.data.Cancellable;
import io.phoenix.app.splasho.data.UnsplashApiClient;
import io.phoenix.app.splasho.models.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sudharti on 10/21/17.
 */

public class PhotosRepository implements PhotosDataSource, Cancellable {

    private static PhotosRepository mRepository;
    private UnsplashApiClient mApiClient;

    private PhotosRepository() {
        mApiClient = UnsplashApiClient.getInstance();
    }

    public static PhotosRepository getInstance() {
        if (mRepository == null) {
            mRepository = new PhotosRepository();
        }

        return mRepository;
    }

    @Override
    public void loadPhotos(int page, String orderBy, final LoadPhotosCallback callback) {
        mApiClient.loadPhotos(page, orderBy, new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK && response.body() != null) {
                    callback.onPhotosLoaded(response.body());
                } else {
                    callback.onDataNotAvailable(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void loadCuratedPhotos(int page, String orderBy, final LoadPhotosCallback callback) {
        mApiClient.loadCuratedPhotos(page, orderBy, new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.code() == HttpsURLConnection.HTTP_OK && response.body() != null) {
                    callback.onPhotosLoaded(response.body());
                } else {
                    callback.onDataNotAvailable(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    @Override
    public void cancel() {
        mApiClient.cancel();
    }
}
