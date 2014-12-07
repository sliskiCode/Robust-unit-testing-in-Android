package com.code.sliski.ui;

public class TestAppForDataLoaded extends App {
    @Override
    protected Object[] getModules() {
        return TestModulesForDataLoaded.get(this);
    }
}
