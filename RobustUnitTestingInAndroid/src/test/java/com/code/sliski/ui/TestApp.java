package com.code.sliski.ui;

public class TestApp extends App {
    @Override
    protected Object[] getModules() {
        return TestModules.get(this);
    }
}
