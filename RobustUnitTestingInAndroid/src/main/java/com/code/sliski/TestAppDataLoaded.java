package com.code.sliski;

public class TestAppDataLoaded extends App {
    @Override
    protected Component getGraph() {
        return DaggerTestAppComponentDataLoaded.builder()
                .testAppModuleDataLoaded(new TestAppModuleDataLoaded()).build();
    }
}
