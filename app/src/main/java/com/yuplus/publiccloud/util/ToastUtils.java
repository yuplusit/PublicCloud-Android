package com.yuplus.publiccloud.util;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yuplus.publiccloud.AppApplication;
import com.yuplus.publiccloud.R;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


/**
 * Created by zlzsam on 2016/7/7.
 */
public class ToastUtils {
    private static Toast mToast;

    public static void make(final int resId) {
        make(AppApplication.getInstance().getString(resId));
    }

    public static void make(final String text) {
        if (null == mToast) {
            mToast = Toast.makeText(AppApplication.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToast(final Context context, final int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(final Activity activity, final String text) {
        //changeBackground
        showToast(activity, text, new Style.Builder()
                .setBackgroundColor(R.color.common_alpha_black)
                .build(), R.id.layout_toast);
    }

    public static void showToast(final Activity activity, final String text, final ViewGroup viewGroup) {
        Crouton.makeText(activity, text, new Style.Builder()
                .setBackgroundColor(R.color.common_alpha_black)
                .build(), viewGroup).show();
    }

    public static void showToast(final Activity activity, final String text, final int resId) {
        showToast(activity, text, new Style.Builder()
                .setBackgroundColor(R.color.common_alpha_black)
                .build(), resId);
    }

    /**
     * common toolbar
     *
     * @param activity
     * @param text
     * @param style
     */
    public static void showToast(final Activity activity, final String text, final Style style, final int resId) {
        Configuration configuration = new Configuration.Builder()
                .setInAnimation(R.anim.toast_slide_in_top)
                .setOutAnimation(R.anim.toast_slide_out_top)
                .build();
        Crouton.makeText(activity, text, style, resId).setConfiguration(configuration).show();
    }

    public static void cancelAllCroutons() {
        Crouton.cancelAllCroutons();
    }

    public static void clearCroutonsForActivity(final Activity activity) {
        Crouton.clearCroutonsForActivity(activity);
    }
}
