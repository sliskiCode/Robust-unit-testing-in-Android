package com.code.sliski;

public class TestApp extends App {
    @Override
    protected Component getGraph() {
        return Dagger_TestAppComponent.builder()
                .testAppModule(new TestAppModule()).build();
    }
}
