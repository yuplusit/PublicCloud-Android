package com.yuplus.publiccloud.util;

import android.content.Context;
import android.graphics.Typeface;

import com.yuplus.publiccloud.enums.EAppIconFont;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class IconFontUtils {

    public static Typeface getAppIconTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "font/appicon.otf");
    }

    public static Typeface getSystemIconTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "font/systemicon.otf");
    }

    public static Typeface getTypeface(Context context, EAppIconFont iconFont) {
        Typeface typeface = null;
        switch (iconFont) {
            case APP_ICON_HOME:
            case APP_ICON_USER:
            case APP_ICON_DEVICE_STROKE:
            case APP_ICON_WARNING_01:
            case APP_ICON_ORDER:
            case APP_ICON_EYE:
            case APP_ICON_PERSONAL_STROKE:
            case APP_ICON_DEVICE_SOLID:
            case APP_ICON_DEAWER:
            case APP_ICON_PERSONAL_SOLID:
            case APP_ICON_WARNING_02:
            case APP_ICON_SCRREN:
            case APP_ICON_DATA:
            case APP_ICON_MENU:
            case APP_ICON_REFRESH_01:
            case APP_ICON_REFRESH_02:
            case APP_ICON_REFRESH_03:
            case APP_ICON_REFRESH_04:
            case APP_ICON_REFRESH_05:
            case APP_ICON_REFRESH_06:
            case APP_ICON_REFRESH_07:
            case APP_ICON_REFRESH_08:
            case APP_ICON_REFRESH_09:
            case APP_ICON_REFRESH_10:
            case APP_ICON_LOGINOFF_01:
            case APP_ICON_LOGINOFF_02:
                typeface = getAppIconTypeface(context);
                break;
            case SYSTEM_ICON_CAR:
            case SYSTEM_ICON_O2:
            case SYSTEM_ICON_WATER:
            case SYSTEM_ICON_HEATING_01:
            case SYSTEM_ICON_ELECTRIC:
            case SYSTEM_ICON_CONDITIONER:
            case SYSTEM_ICON_WATER_LOOP:
            case SYSTEM_ICON_LIFT:
                typeface = getSystemIconTypeface(context);
                break;
            default:
                typeface = getAppIconTypeface(context);
        }
        return typeface;
    }

    static class IconWrapper {
        private Typeface mTypeface;
        private String   mValue;

        public Typeface getmTypeface() {
            return mTypeface;
        }

        public IconWrapper setmTypeface(Typeface mTypeface) {
            this.mTypeface = mTypeface;
            return this;
        }

        public String getmValue() {
            return mValue;
        }

        public IconWrapper setmValue(String mValue) {
            this.mValue = mValue;
            return this;
        }
    }
}
