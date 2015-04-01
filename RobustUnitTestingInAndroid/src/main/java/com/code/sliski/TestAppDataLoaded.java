package com.code.sliski;

public class TestAppDataLoaded extends App {
    @Override
    protected Component getGraph() {
        return Dagger_TestAppComponentDataLoaded.builder()
                .testAppModuleDataLoaded(new TestAppModuleDataLoaded()).build();
    }
}
