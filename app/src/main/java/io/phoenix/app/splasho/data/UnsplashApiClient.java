package io.phoenix.app.splasho.data;

import java.util.List;

import io.phoenix.app.splasho.BuildConfig;
import io.phoenix.app.splasho.UnsplashAPI;
import io.phoenix.app.splasho.models.Photo;
import io.phoenix.app.splasho.photos.PhotosContract;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sudharti on 10/21/17.
 */

public class UnsplashApiClient {
    private final static String TAG = UnsplashApiClient.class.getSimpleName();
    private final static String UNSPLASH_APP_ID = BuildConfig.UNSPLASH_APP_ID;
    private final static String SCHEME = "https";
    private final static String BASE_PATH = "/api.unsplash.com";

    private static Retrofit mRetrofit;
    private static UnsplashApiClient mApiClient;
    private static UnsplashAPI mApi;

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

    public void loadPhotos(int page, @PhotosContract.OrderBy String orderBy) {
        mApi.loadPhotos(UNSPLASH_APP_ID, page, orderBy).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }

    private HttpUrl buildBaseURL() {
        return new HttpUrl.Builder()
                .scheme(SCHEME)
                .addEncodedPathSegment(BASE_PATH)
                .build();
    }
}
