package com.yuplus.cloudsdk.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc 通过反射获取资源工具类
 */

public class ResourceUtils {
    public static String getFileFromAssets(Context context, String fileName) {
        if (context == null || StringUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据资源的类型和名字获取资源id
     *
     * @param context 上下文
     * @param type    资源的类型
     * @param resName 资源的名称
     * @return
     */
    public static int getResId(Context context, String type, String resName) {
        if (StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(resName)) {
            int resId = context.getResources().getIdentifier(resName, type, context.getPackageName());
            return resId > 0 ? resId : 0;
        }

        return 0;
    }

    /**
     * 按原比例获取资产文件夹下的位图
     *
     * @param context 应用上下文
     * @param image   图片文件
     * @return
     */
    public static Bitmap getBitmapFromAssets(Context context, String image) {
        if (StringUtils.isEmpty(image)) return null;

        AssetManager assetManager = context.getResources().getAssets();
        InputStream inputStream = null;

        try {
            inputStream = assetManager.open(image);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return bitmap;
        } catch (IOException ex) {

        }

        return null;
    }


    public static BitmapDrawable getBitmapDrawableFromAssets(Context context, String image) {
        Bitmap bitmap = getBitmapFromAssets(context, image);
        return bitmap == null ? null : new BitmapDrawable(bitmap);
    }


    /**
     * 从资产文件夹中读取正常和按下状态的图片，并将其转化为StateListDrawable，
     * 主要用于视图的点击事件下背景图片效果
     *
     * @param context      应用上下文
     * @param imageNormal  正常状态图片文件
     * @param imagePressed 按下状态图片文件
     * @return
     */
    public static StateListDrawable getStateListDrawableFromAssets(Context context, String imageNormal, String imagePressed) {
        Drawable normalDrawable = getBitmapDrawableFromAssets(context, imageNormal);
        Drawable pressedDrawable = getBitmapDrawableFromAssets(context, imagePressed);

        StateListDrawable drawable = new StateListDrawable();
        //设置按下可用状态下的图片
        drawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        //设置正常状态下的图片
        drawable.addState(new int[]{}, normalDrawable);

        return drawable;
    }


    /**
     * 获取资产文件夹下制定文件的文件描述对象。该函数在应用中被用于设置MediaPlayer的数据源
     *
     * @param context 应用上下文
     * @param name    资源文件下文件名字
     * @param defType Optional default resource type to find, if "type/" is
     *                not included in the name.  Can be null to require an
     *                explicit type.
     * @return
     */
    public static AssetFileDescriptor getAssetFileDescriptor(Context context, String name, String defType) {
        if (context == null
                || StringUtils.isEmpty(name)
                || StringUtils.isEmpty(defType)) return null;

        int resId = context.getResources().getIdentifier(name, defType, context.getPackageName());
        return resId == 0 ? null : context.getResources().openRawResourceFd(resId);
    }
}
