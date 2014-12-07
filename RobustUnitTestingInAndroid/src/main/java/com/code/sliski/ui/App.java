package com.code.sliski.ui;

import android.app.Application;
import android.content.Context;
import dagger.ObjectGraph;

public class App extends Application {

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
        App app = (App) context.getApplicationContext();
        app.objectGraph.inject(root);
    }
}
