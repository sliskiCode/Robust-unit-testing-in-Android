package com.code.sliski.ui;

import android.content.Context;

public class TestModules {
    public static Object[] get(Context context) {
        return new Object[]{
                new TestAppModule(context)
        };
    }
}
