package io.phoenix.app.splasho;

import java.util.List;

import io.phoenix.app.splasho.models.Photo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sudharti on 10/21/17.
 */

public interface UnsplashAPI {
    @GET("/photos")
    Call<List<Photo>> loadPhotos(@Query("client_id") String clientId, @Query("page") int page, @Query("order_by") String orderBy);

    @GET("/photos/curated/")
    Call<List<Photo>> loadCuratedPhotos(@Query("client_id") String clientId, @Query("page") int page, @Query("order_by") String orderBy);
}
