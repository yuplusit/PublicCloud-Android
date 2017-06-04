package com.yuplus.publiccloud.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.yuplus.publiccloud.cst.AppCst;

import java.io.File;
import java.io.IOException;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * Created by zlzsa on 2017/6/5.
 */

public class StorageUtils {
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private StorageUtils() {
    }
    /**
     * Returns application cache directory. Cache directory will be created on SD card
     * <i>("/Android/data/[app_package_name]/cache")</i> if card is mounted and app has appropriate permission. Else -
     * Android defines cache directory on device's file system.
     *
     * @param context Application context
     * @return Cache {@link File directory}
     */
    public static File getCacheDirectory(Context context) {
        File appCacheDir = null;
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            Log.w(AppCst.APP_NAME, "Can't define system cache directory! The app should be re-installed.");
        }
        return appCacheDir;
    }
    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                Log.w(AppCst.APP_NAME, "Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                Log.i(AppCst.APP_NAME, "Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }
    /**
     * 文件存储目录
     * @param context
     * @return
     */
    public static File getExternalFileDir(Context context,String fileType){
        File appFileDir = null;
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appFileDir = getExternalCacheDir(context);
        }
        if (appFileDir == null) {
            appFileDir = getCacheDirectory(context);
        }
        if (appFileDir == null) {
            Log.w(AppCst.APP_NAME, "Can't define system cache directory! The app should be re-installed.");
        }
        final File finalAppFileDir = new File(appFileDir,fileType);
        if(!finalAppFileDir.exists()){
            finalAppFileDir.mkdir();
        }
        Log.d("XiuSeKeCaiFileAddress:",finalAppFileDir.getAbsolutePath());
        return finalAppFileDir;
    }
}
