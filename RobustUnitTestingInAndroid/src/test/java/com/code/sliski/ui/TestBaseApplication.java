package com.code.sliski.ui;

import com.code.sliski.App;

public class TestBaseApplication extends App {
    @Override
    protected Object[] getModules() {
        return TestModules.get(this);
    }
}
