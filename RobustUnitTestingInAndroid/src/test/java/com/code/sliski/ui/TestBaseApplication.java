package com.code.sliski.ui;

public class TestBaseApplication extends BaseApplication {
    @Override
    protected Object[] getModules() {
        return TestModules.get(this);
    }
}
