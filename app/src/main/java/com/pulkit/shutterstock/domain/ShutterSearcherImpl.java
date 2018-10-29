package com.pulkit.shutterstock.domain;

import com.pulkit.shutterstock.domain.entity.ShutterPage;
import io.reactivex.Single;
import javax.inject.Inject;

public class ShutterSearcherImpl implements ShutterSearcher {

  private final ShutterRepository repository;

  @Inject
  public ShutterSearcherImpl(ShutterRepository shutterRepository) {
    this.repository = shutterRepository;
  }

  public Single<ShutterPage> search(String query, int page, int perPage) {
    return repository.search(query, page, perPage);
  }
}
