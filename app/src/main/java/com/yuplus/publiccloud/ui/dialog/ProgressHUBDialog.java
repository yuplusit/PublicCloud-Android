package com.yuplus.publiccloud.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.yuplus.publiccloud.R;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */
public class ProgressHUBDialog extends Dialog {

    public ProgressHUBDialog(Context context) {
        super(context);
    }

    public ProgressHUBDialog(Context context, int theme) {
        super(context, theme);
    }

    public static ProgressHUBDialog createDialog(final Context context){
        return createDialog(context,null);
    }

    public static ProgressHUBDialog createDialog(final Context context, final CharSequence message) {
        return createDialog(context, message, true, null);
    }

    public static ProgressHUBDialog createDialog(final Context context, final CharSequence message, final boolean cancelable,
                                                 final DialogInterface.OnCancelListener cancelListener) {
        final ProgressHUBDialog dialog = new ProgressHUBDialog(context, R.style.app_dialog_theme);
        dialog.setTitle(null);
        dialog.setContentView(R.layout.dialog_progress_hub);
        if (message == null || message.length() == 0) {
            dialog.findViewById(R.id.progress_hub_id_tips).setVisibility(View.GONE);
        } else {
            dialog.findViewById(R.id.progress_hub_id_tips).setVisibility(View.VISIBLE);
            TextView txt = (TextView) dialog.findViewById(R.id.progress_hub_id_tips);
            txt.setText(message);
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.0f;
        dialog.getWindow().setAttributes(lp);
        //dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//        dialog.show();
        return dialog;
    }

    public void setMessage(final CharSequence message) {
        if (message != null && message.length() > 0) {
            findViewById(R.id.progress_hub_id_tips).setVisibility(View.VISIBLE);
            TextView txt = (TextView) findViewById(R.id.progress_hub_id_tips);
            txt.setText(message);
            txt.invalidate();
        }
    }
}

