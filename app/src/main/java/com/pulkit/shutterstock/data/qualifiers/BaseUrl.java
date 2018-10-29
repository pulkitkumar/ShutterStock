package com.pulkit.shutterstock.data.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Qualifier to provide base url for {@link retrofit2.Retrofit}
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseUrl {

}
