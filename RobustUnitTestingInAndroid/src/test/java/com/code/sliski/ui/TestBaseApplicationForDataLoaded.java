package com.code.sliski.ui;

import com.code.sliski.App;

public class TestBaseApplicationForDataLoaded extends App {
    @Override
    protected Object[] getModules() {
        return TestModulesForDataLoaded.get(this);
    }
}
