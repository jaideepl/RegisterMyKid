package com.registermykid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.registermykid.RegisterMyKidApp;

import java.lang.reflect.Field;

/**
 * Created by Jaideep.Lakshminaray on 31-07-2017.
 */

public class Util {
    public static void hideSoftinput(Activity activity) {
        InputMethodManager imm = (InputMethodManager)
                RegisterMyKidApp.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

    public static void showInputMethod(View view) {
        InputMethodManager imm = (InputMethodManager) RegisterMyKidApp.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    public static String getVersionName() {
        PackageInfo pInfo = null;
        String versionName = "";
        try {
            pInfo = RegisterMyKidApp.getContext().getPackageManager().getPackageInfo(RegisterMyKidApp.getContext().getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return versionName;
    }

    public static String getAppName() {
        PackageManager packageManager = RegisterMyKidApp.getContext().getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(RegisterMyKidApp.getContext().getApplicationInfo().packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "Unknown");
    }

    public static void displayMessage(String message) {
        Toast.makeText(RegisterMyKidApp.getContext(), message, Toast.LENGTH_LONG).show();
    }

    private static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(RegisterMyKidApp.getContext());
    }

    public static void setStringToSharedPreferences(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStringFromSharedPreferences(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }


    public static void setToolbarProperties(Toolbar toolbar) {
        Typeface font = Typeface.createFromAsset(RegisterMyKidApp
                .getContext().getAssets(), "dancingscript_regular.ttf");

        try {
            Field f = toolbar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            TextView mToolbarTitle = (TextView) f.get(toolbar);
            mToolbarTitle.setTypeface(font);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static int getMatColor(String typeColor) {
        int returnColor = Color.BLACK;
        int arrayId = RegisterMyKidApp.getContext().getResources().getIdentifier("mdcolor_" + typeColor, "array", RegisterMyKidApp.getContext().getPackageName());

        if (arrayId != 0) {
            TypedArray colors = RegisterMyKidApp.getContext().getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }
}
