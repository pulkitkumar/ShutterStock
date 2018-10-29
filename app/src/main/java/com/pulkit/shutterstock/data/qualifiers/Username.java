package com.pulkit.shutterstock.data.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Qualifier to provide username for basic authentication.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {

}
