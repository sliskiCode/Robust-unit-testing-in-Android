package com.code.sliski.ui;

import android.content.Context;

public class TestModulesForDataLoaded {
    public static Object[] get(Context context) {
        return new Object[]{
                new TestAppModuleForDataLoaded(context)
        };
    }
}
