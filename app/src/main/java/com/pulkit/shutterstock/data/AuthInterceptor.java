package com.pulkit.shutterstock.data;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

  private static final String AUTHORIZATION = "Authorization";
  private final String clientId;
  private final String clientSecret;

  @Inject
  public AuthInterceptor(@Named("clientId") String clientId, @Named("clientSecret") String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();
    Request.Builder builder = original.newBuilder().method(original.method(), original.body());
    String credentials = Credentials.basic(clientId, clientSecret);
    builder.header(AUTHORIZATION,  credentials);
    return chain.proceed(builder.build());
  }
}
