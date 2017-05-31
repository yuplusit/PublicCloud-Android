package com.yuplus.publiccloud.sp;

import android.content.Context;
import android.content.SharedPreferences;

import com.yuplus.publiccloud.cst.AppCst;

import java.util.Set;

/**
 * Created by geminiwen on 14/11/29.
 */
public class PublicCloudPreferences {
    private static final String                SHARED_PREFERENCES_NAME = AppCst.SHAREDPREFERENCES_NAME;
    private static       PublicCloudPreferences mInstance               = null;
    private SharedPreferences mSharedPreferences;


    private PublicCloudPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PublicCloudPreferences getInstance(final Context context) {
        if (null == mInstance){
            mInstance = new PublicCloudPreferences(context);
        }
        return mInstance;
    }

    public void set(String key, Object value) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        if (value instanceof String) {
            edit.putString(key, (String)value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key ,(Boolean)value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer)value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Set) {
            edit.putStringSet(key, (Set<String>) value);
        } else {
            throw new RuntimeException("Not support type");
        }
        edit.apply();
    }


    public String getString(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public Boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public Integer getInteger(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public Float getFloat(String key) {
        return mSharedPreferences.getFloat(key, 0.0f);
    }

    public Long getLong(String key) {
        return mSharedPreferences.getLong(key, 0l);
    }

    public Set<String> getStringSet(String key) {
        return mSharedPreferences.getStringSet(key, null);
    }

    public void remove(String key){
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.remove(key);
    }
}
