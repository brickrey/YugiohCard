package com.brickrey.yugiohcard.helper;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

public class FileHelper {

    private static final String TAG = FileHelper.class.getName();

    public static void appendLineToFile(String text, String filename, Context context){
        try {
            String dirPath = Environment.getExternalStorageDirectory() + File.separator + "taxinet";
            File dir = new File(dirPath);
            if(!dir.isDirectory()){
                dir.mkdir();
            }
            if(dir.isDirectory()){
                File file = new File(dirPath, filename);
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();

                FileOutputStream fOutStream = new FileOutputStream(file, true);
                fOutStream.write(text.getBytes());
                String separator = System.getProperty("line.separator");
                fOutStream.write(separator.getBytes());
                fOutStream.close();
            }
        } catch (java.io.IOException e) {
            Log.e(TAG, e.getLocalizedMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
}
