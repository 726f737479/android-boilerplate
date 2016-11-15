package com.brainbeanapps.core.di.context;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention (RetentionPolicy.RUNTIME)
public @interface UIContext {
}
