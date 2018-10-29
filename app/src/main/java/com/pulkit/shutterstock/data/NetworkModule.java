package com.pulkit.shutterstock.data;

import android.app.Application;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pulkit.shutterstock.data.qualifiers.Auth;
import com.pulkit.shutterstock.data.qualifiers.BaseUrl;
import com.pulkit.shutterstock.data.qualifiers.Log;
import com.pulkit.shutterstock.domain.ShutterRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class has all the network related dependencies.
 */
@Module
public abstract class NetworkModule {

  // considering a max size of 5MB disk irrespective of device as the size is not too much.
  private static final long CACHE_SIZE = 5 * 1024 * 1024;

  @Provides
  @Singleton
  static Gson gson() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
  }

  @Provides
  @Singleton
  static Cache cache(Application application) {
    return new Cache(application.getCacheDir(), CACHE_SIZE);
  }

  @Binds
  @Singleton
  @Auth
  abstract Interceptor authInterceptor(BasicAuthInterceptor authInterceptor);

  @Provides
  @Singleton
  @Log
  static Interceptor loggingInterceptor() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(Level.BODY);
    return loggingInterceptor;
  }

  @Provides
  @Singleton
  static OkHttpClient okHttpClient(boolean isDebug, @Auth Interceptor authInterceptor,
      @Log Interceptor loggingInterceptor) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS);
    if (isDebug) {
      builder.addInterceptor(loggingInterceptor);
    }
    return builder.build();
  }

  @Provides
  @Singleton
  static Retrofit retrofit(OkHttpClient httpClient, Gson gson, @BaseUrl String baseUrl) {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient)
        .build();
  }

  @Provides
  @Singleton
  static ShutterRepository repository(Retrofit retrofit) {
    return retrofit.create(ShutterRepository.class);
  }
}
