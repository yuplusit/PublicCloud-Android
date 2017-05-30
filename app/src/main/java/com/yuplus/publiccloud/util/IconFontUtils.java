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

    public static Typeface getTypeface(Context context, EAppIconFont iconFont) {
        Typeface typeface = null;
        switch (iconFont) {
            case SYSTEM_ICON_CAR:
            case SYSTEM_ICON_O2:
            case SYSTEM_ICON_WATER:
            case SYSTEM_ICON_HEATING_01:
            case SYSTEM_ICON_ELECTRIC:
            case SYSTEM_ICON_CONDITIONER:
            case SYSTEM_ICON_WATER_LOOP:
            case SYSTEM_ICON_LIFT:
            case APP_ICON_HOME_STROKE:
            case APP_ICON_CUSTOMER_STROKE:
            case APP_ICON_DEVICE_STROKE:
            case APP_ICON_ALERT_STROKE:
            case APP_ICON_ORDER_STROKE:
            case APP_ICON_HOME_SOLID:
            case APP_ICON_CUSTOMER_SOLID:
            case APP_ICON_DEVICE_SOLID:
            case APP_ICON_ALERT_SOLID:
            case APP_ICON_ORDER_SOLID:
            case APP_ICON_ORDER_STROKE_SEC:
            case APP_ITEM_ICON_DEVICE:
            case APP_ITEM_ICON_ALERT:
            case APP_ALERT_DEALING_ICON:
            case APP_ALERT_FLAG_ICON:
            case APP_ALERT_ALL_ICON:
            case APP_ALERT_NEW_ICON:
            case APP_ALERT_FINISH_ICON:
            case APP_TEST_CALENDAR_ICON:
            case APP_TEST_HISTORY_ICON:
            case APP_TEST_NAME_ICON:
            case APP_TEST_VALUE_ICON:
                typeface = getAppIconTypeface(context);
                break;
        }
        return typeface;
    }

    static class IconWrapper {
        private Typeface mTypeface;
        private String mValue;

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
