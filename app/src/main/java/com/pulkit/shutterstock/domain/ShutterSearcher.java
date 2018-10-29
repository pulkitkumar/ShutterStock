package com.pulkit.shutterstock.domain;

import com.pulkit.shutterstock.domain.entity.ShutterPage;
import io.reactivex.Single;

public interface ShutterSearcher {

  Single<ShutterPage> search(String query, int page, int perPage);

}
