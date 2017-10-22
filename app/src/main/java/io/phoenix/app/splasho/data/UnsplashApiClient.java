package io.phoenix.app.splasho.data;

import java.util.List;

import io.phoenix.app.splasho.models.Collection;
import io.phoenix.app.splasho.models.Photo;
import io.phoenix.app.splasho.photos.PhotosContract;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static io.phoenix.app.splasho.Splasho.PER_PAGE;
import static io.phoenix.app.splasho.Splasho.UNSPLASH_APP_ID;

/**
 * Created by sudharti on 10/21/17.
 */

public class UnsplashApiClient {

    private final static String SCHEME = "https";
    private final static String BASE_PATH = "api.unsplash.com";

    private static Retrofit mRetrofit;
    private static UnsplashApiClient mApiClient;
    private static UnsplashAPI mApi;

    private Call call;

    private UnsplashApiClient() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(buildBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = mRetrofit.create(UnsplashAPI.class);
    }

    public static UnsplashApiClient getInstance() {
        if (mApiClient == null) {
            mApiClient = new UnsplashApiClient();
        }

        return mApiClient;
    }

    public void loadPhotos(int page, @PhotosContract.OrderBy String orderBy, Callback<List<Photo>> apiCallback) {
        call = mApi.loadPhotos(UNSPLASH_APP_ID, page, PER_PAGE, orderBy);
        call.enqueue(apiCallback);
    }

    public void loadCuratedPhotos(int page, @PhotosContract.OrderBy String orderBy, Callback<List<Photo>> apiCallback) {
        call = mApi.loadCuratedPhotos(UNSPLASH_APP_ID, page, PER_PAGE, orderBy);
        call.enqueue(apiCallback);
    }

    public void loadAllCollections(int page, Callback<List<Collection>> apiCallback) {
        call = mApi.loadAllCollections(UNSPLASH_APP_ID, page, PER_PAGE);
        call.enqueue(apiCallback);
    }

    public void loadFeaturedCollections(int page, Callback<List<Collection>> apiCallback) {
        call = mApi.loadFeaturedCollections(UNSPLASH_APP_ID, page, PER_PAGE);
        call.enqueue(apiCallback);
    }

    public void loadCuratedCollections(int page, Callback<List<Collection>> apiCallback) {
        call = mApi.loadCuratedCollections(UNSPLASH_APP_ID, page, PER_PAGE);
        call.enqueue(apiCallback);
    }

    public void cancel() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }

    private HttpUrl buildBaseURL() {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .host(BASE_PATH)
                .build();
    }
}
