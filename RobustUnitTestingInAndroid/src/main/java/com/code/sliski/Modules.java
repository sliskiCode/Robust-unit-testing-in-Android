package com.code.sliski;

import android.content.Context;

public final class Modules {
    public static Object[] get(Context context) {
        return new Object[]{
                new AppModule(context)
        };
    }
}
