package com.yuplus.publiccloud.cst;

/**
 * Created by zlzsa on 2017/6/4.
 */

public class BroadcastCst {
    private static final String PROJECT_NAME = AppCst.APP_NAME;

    //消息数量
    public static final String MSG_RED_POINT_COUNT_UPDATE   = PROJECT_NAME + "_msg_red_point_count_update_action";
    //未处理的告警数量
    public static final String UNTREATED_ALERT_COUNT_UPDATE = PROJECT_NAME + "_untreated_alert_count_update_action";
    //更新设备信息
    public static final String DEVICE_INFO_UPDATE           = PROJECT_NAME + "_device_info_update_action";
    //更新告警信息
    public static final String ALERT_INFO_UPDATE            = PROJECT_NAME + "_alert_info_update_action";
    //用户信息的修改
    public static final String USER_INFO_UPDATE             = PROJECT_NAME + "_user_info_update_action";
}
