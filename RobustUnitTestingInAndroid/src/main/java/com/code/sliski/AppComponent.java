package com.code.sliski;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = AppModule.class)
public interface  AppComponent extends com.code.sliski.Component {
}
