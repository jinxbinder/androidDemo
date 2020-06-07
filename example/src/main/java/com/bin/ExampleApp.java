package com.bin;

import android.app.Application;

import com.bin.latte_core.app.Latte;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)

                .configure();
    }
}
