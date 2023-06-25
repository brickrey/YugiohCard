package com.brickrey.yugiohcard.helper;

import android.content.Context;
import android.util.Log;

import com.brickrey.yugiohcard.SharedPreferenceManager;

import java.util.Arrays;
import java.util.Date;

public class LogHelper {

    private static final String LogFileName = "yugioh-card.log";

    /**
     * Print logs
     * @param tag       class name where log is triggered
     * @param message   message to be logged
     * @param logLevel  <ol><li>Info</li><li>Warn</li><li>Error</li></ol>
     * @param context   aplication context
     * @param logToFile whenever this parameter has a true value the log is appended to taxinetDriverApp.log file.
     */
    public static void log(String tag, String message, int logLevel, Context context, boolean logToFile){
        if(logToFile && (SharedPreferenceManager.isLogToFileActivated(context))){
            FileHelper.appendLineToFile((new Date()).toString() + (logLevel == 3 ? "[ERROR]: " : logLevel == 2 ? "[WARN]: " : "[INFO]: ") + tag + " - " + message, LogFileName);
        }
        if(logLevel == 3)  Log.e(tag, message);
        else Log.i(tag, message);
    }

    public static void log(String tag, String message, int logLevel, Context context){
        log(tag, message, logLevel, context, false);
    }

    public static void logException(String tag, Exception ex, Context context){
        log(tag, ex.getLocalizedMessage() + "\n" + Arrays.toString(ex.getStackTrace()), 3, context, false);
    }
}
