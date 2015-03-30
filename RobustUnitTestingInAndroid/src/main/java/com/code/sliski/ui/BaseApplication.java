package com.code.sliski.ui;

import android.app.Application;
import android.content.Context;
import dagger.ObjectGraph;

public class BaseApplication extends Application {

    protected ObjectGraph objectGraph;

    protected Object[] getModules() {
        return Modules.get(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules());
    }

    public static void inject(Context context, Object root) {
        BaseApplication baseApplication = (BaseApplication) context.getApplicationContext();
        baseApplication.objectGraph.inject(root);
    }
}
