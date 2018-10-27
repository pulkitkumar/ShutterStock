package com.pulkit.shutterstock.domain;

import com.pulkit.shutterstock.domain.model.ShutterPage;
import io.reactivex.Single;

public class ShutterSearcher {

  private final ShutterRepository repository;

  public ShutterSearcher(ShutterRepository shutterRepository) {
    this.repository = shutterRepository;
  }

  public Single<ShutterPage> search(String query, int page) {
    return repository.search(query, page)
        .retry(2);
  }

}
