package com.pulkit.shutterstock.domain;

import com.pulkit.shutterstock.domain.entity.ShutterPage;
import io.reactivex.Single;

/**
 * This is the search interface to be used by presentation for any searches.
 */
public interface ShutterSearcher {

  Single<ShutterPage> search(String query, int page, int perPage);

}
