package com.code.sliski;

import android.app.Application;

public class App extends Application {

    public static Component mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mGraph = getGraph();
    }

    protected Component getGraph() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }
}
