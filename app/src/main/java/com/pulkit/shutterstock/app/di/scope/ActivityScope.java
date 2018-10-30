package com.pulkit.shutterstock.app.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * To scope dependencies that live within the scope of an activity.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME) public @interface ActivityScope {

}
