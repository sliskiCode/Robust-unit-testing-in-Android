package com.code.sliski;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = TestAppModuleDataLoaded.class)
public interface TestAppComponentDataLoaded extends com.code.sliski.Component {
}
