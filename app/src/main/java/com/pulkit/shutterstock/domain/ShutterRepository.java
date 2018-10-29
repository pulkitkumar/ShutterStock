package com.pulkit.shutterstock.domain;

import com.pulkit.shutterstock.domain.entity.ShutterPage;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface to search shutterstock data. Implementation should fetch it from API or database.
 */
public interface ShutterRepository {

  @GET("images/search")
  Single<ShutterPage> search(@Query("query") String query,
      @Query("page") int page,
      @Query("per_page") int perPage);
}
