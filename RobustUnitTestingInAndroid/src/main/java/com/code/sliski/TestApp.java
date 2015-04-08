package com.code.sliski;

public class TestApp extends App {
    @Override
    protected Component getGraph() {
        return DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule()).build();
    }
}
