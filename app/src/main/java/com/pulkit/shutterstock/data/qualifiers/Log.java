package com.pulkit.shutterstock.data.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Qualifier to provide Logging {@link okhttp3.Interceptor} for {@link okhttp3.OkHttpClient}
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

}
