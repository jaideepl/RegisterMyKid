package com.registermykid;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jaideep.Lakshminaray on 18-11-2017.
 */

public class RegisterMyKidApp extends Application {
    private static Context appContext = null;

    public static Context getContext() {
        return appContext;
    }

    public static void setAppContext(final Context context) {
        RegisterMyKidApp.appContext = context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RegisterMyKidApp.appContext = this;
    }
}
