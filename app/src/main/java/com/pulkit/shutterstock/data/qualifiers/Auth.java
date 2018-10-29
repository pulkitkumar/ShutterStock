package com.pulkit.shutterstock.data.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Qualifier for providing {@link okhttp3.Interceptor} for authentication.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

}
