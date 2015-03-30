package com.code.sliski.ui;

public class TestBaseApplicationForDataLoaded extends BaseApplication {
    @Override
    protected Object[] getModules() {
        return TestModulesForDataLoaded.get(this);
    }
}
