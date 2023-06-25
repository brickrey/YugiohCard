package com.brickrey.yugiohcard.helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

public class FileHelper {

    private static final String TAG = FileHelper.class.getName();

    public static void appendLineToFile(String text, String filename){
        try {
            String dirPath = Environment.getExternalStorageDirectory() + File.separator + "yugioh-card";
            File dir = new File(dirPath);
            if(!dir.isDirectory()){
                if(!dir.mkdir()) return;
            }
            if(dir.isDirectory()){
                File file = new File(dirPath, filename);
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();

                FileOutputStream fOutStream = new FileOutputStream(file, true);
                fOutStream.write(text.getBytes());
                String separator = System.getProperty("line.separator");
                if(separator != null) fOutStream.write(separator.getBytes());
                fOutStream.close();
            }
        } catch (java.io.IOException e) {
            Log.e(TAG, e.getLocalizedMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        } finally {
            Log.d(TAG, text);
        }
    }
}
