package com.code.sliski;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = TestAppModule.class)
public interface TestAppComponent extends com.code.sliski.Component {
}
