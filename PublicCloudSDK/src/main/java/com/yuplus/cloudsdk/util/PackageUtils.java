package com.yuplus.cloudsdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc 安装包工具类
 */

public class PackageUtils {
    /**
     * 判断指定包名的应用是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isApkInstalled(Context context, String packageName) {
        if (context == null || StringUtils.isBlank(packageName)) return false;

        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 安装应用
     *
     * @param context
     * @param filePath
     * @return
     */
    public static boolean installApk(Context context, String filePath) {
        File file = new File(filePath);

        if (context == null
                || file == null
                || !file.exists()
                || !file.isFile()
                || file.length() <= 0) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }

    /**
     * 卸载指定包名的应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean uninstallApk(Context context, String packageName) {
        if (context == null
                || StringUtils.isBlank(packageName)
                || !isApkInstalled(context, packageName)) return false;

        Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);
        return true;
    }

    /**
     * 打开指定包名的应用
     *
     * @param context
     * @param packageName
     */
    public static void launchApk(Context context, String packageName) {
        if (context == null || StringUtils.isBlank(packageName)) return;

        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 判断指定包名的应用是否是第三方应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isUserApk(Context context, String packageName) {
        return isSystemApk(context, packageName);
    }

    /**
     * 判断当前应用是否是系统应用
     *
     * @param context
     * @return
     */
    public static boolean isSystemApk(Context context) {
        if (context == null) return false;
        return isSystemApk(context, context.getPackageName());
    }

    /**
     * 判断指定包名的应用是否是系统应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isSystemApk(Context context, String packageName) {
        if (context == null || StringUtils.isBlank(packageName)) return false;

        try {
            ApplicationInfo app = context.getPackageManager().getApplicationInfo(packageName, 0);
            return (app != null && (app.flags & ApplicationInfo.FLAG_SYSTEM) > 0);
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 获取已安装的应用
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getInstalledApks(Context context) {
        return context.getPackageManager().getInstalledPackages(0);
    }

    /**
     * 获取已安装的第三方应用
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getInstalledUserApks(Context context) {
        List<PackageInfo> infos = context.getPackageManager().getInstalledPackages(0);
        List<PackageInfo> userInfos = new ArrayList<PackageInfo>();

        for (PackageInfo info : infos) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                userInfos.add(info);
            }
        }

        return userInfos;
    }

    /**
     * 获取已安装的系统应用
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getInstalledSystemApks(Context context) {
        List<PackageInfo> infos = context.getPackageManager().getInstalledPackages(0);
        List<PackageInfo> systemInfos = new ArrayList<PackageInfo>();

        for (PackageInfo info : infos) {
            if ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                systemInfos.add(info);
            }
        }

        return systemInfos;
    }
}
