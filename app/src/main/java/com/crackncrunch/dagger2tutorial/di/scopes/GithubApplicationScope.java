package com.crackncrunch.dagger2tutorial.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Lilian on 10-Feb-17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface GithubApplicationScope {

}
