package com.yuplus.publiccloud.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.cst.AppCst;
import com.yuplus.publiccloud.enums.EAppIconFont;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public class ViewUtils {

    public static View getTabItemView(Context context, EAppIconFont eAppIconFont, String desc) {
        Typeface typeface = IconFontUtils.getTypeface(context, eAppIconFont);
        String value = eAppIconFont.getValue();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_tab_view, null, false);
        TextView iconfont = (TextView) view.findViewById(R.id.tab_item_icon_font);
        TextView iconDesc = (TextView) view.findViewById(R.id.tab_item_icon_desc);
        TextView msgTv = (TextView) view.findViewById(R.id.message_id_icon_dig);
        msgTv.setVisibility(View.GONE);
        iconfont.setText(value);
        iconfont.setTypeface(typeface);
        iconDesc.setText(desc);
        return view;
    }

    public static View getTabItemView(Context context, EAppIconFont eAppIconFont, String desc, boolean isRedPoint) {
        Typeface typeface = IconFontUtils.getTypeface(context, eAppIconFont);
        String value = eAppIconFont.getValue();
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_tab_view, null, false);
        TextView iconfont = (TextView) view.findViewById(R.id.tab_item_icon_font);
        TextView iconDesc = (TextView) view.findViewById(R.id.tab_item_icon_desc);
        TextView msgTv = (TextView) view.findViewById(R.id.message_id_icon_dig);
        if (AppCst.ALERT_RED_POINT_SWITCH) {
            if (isRedPoint) {
                msgTv.setVisibility(View.VISIBLE);
            } else {
                msgTv.setVisibility(View.GONE);
            }
        }
        iconfont.setText(value);
        iconfont.setTypeface(typeface);
        iconDesc.setText(desc);
        return view;
    }
}
