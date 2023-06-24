package com.brickrey.yugiohcard;

import android.content.Context;
import android.content.SharedPreferences;

import com.brickrey.yugiohcard.helper.LogHelper;

public class SharedPreferenceManager {

    private final static String TAG = SharedPreferenceManager.class.getCanonicalName();

    private static final String APP_SETTINGS = "APP_SETTINGS";
    private static final String VOLLEY_CONCURRENT_CONNECTIONS    = "VOLLEY_CONCURRENT_CONNECTIONS";
    private static final String LOG_TO_FILE                      = "LOG_TO_FILE";

    private SharedPreferenceManager() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        try {
            return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        }catch (Exception e){
            LogHelper.logException(TAG, e, context);
        }
        return null;
    }

    public static int getVolleyConcurrentConnections(Context context) {
        SharedPreferences spm = getSharedPreferences(context);
        if(spm != null) return Math.round(spm.getFloat(VOLLEY_CONCURRENT_CONNECTIONS, 15));
        else return 15;
    }

    public static boolean isLogToFileActivated(Context context) {
        SharedPreferences spm = getSharedPreferences(context);
        return spm != null && Math.round(spm.getFloat(LOG_TO_FILE, 0)) == 1;
    }
}
