package com.pulkit.shutterstock.data;

import com.pulkit.shutterstock.data.qualifiers.Username;
import com.pulkit.shutterstock.data.qualifiers.Password;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This class takes care of adding basic authorization header for the provided username and password in the constructor.
 */
public class BasicAuthInterceptor implements Interceptor {

  private static final String AUTHORIZATION = "Authorization";
  private final String username;
  private final String password;

  @Inject
  public BasicAuthInterceptor(@Username String username, @Password String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();
    Request.Builder builder = original.newBuilder().method(original.method(), original.body());
    String credentials = Credentials.basic(username, password);
    builder.header(AUTHORIZATION,  credentials);
    return chain.proceed(builder.build());
  }
}
