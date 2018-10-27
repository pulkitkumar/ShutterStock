package com.pulkit.shutterstock.domain;

import com.pulkit.shutterstock.domain.model.ShutterPage;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface ShutterRepository {

  @GET("/images/search")
  Single<ShutterPage> search(String query, int page);

}
