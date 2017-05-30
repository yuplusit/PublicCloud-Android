package com.yuplus.publiccloud.enums;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public enum EAppIconFont {

    //此App八大系统图标
    SYSTEM_ICON_CAR("\ua040"),
    SYSTEM_ICON_O2("\ua041"),
    SYSTEM_ICON_WATER("\ua042"),
    SYSTEM_ICON_HEATING_01("\ua043"),
    SYSTEM_ICON_ELECTRIC("\ua045"),
    SYSTEM_ICON_CONDITIONER("\ua046"),
    SYSTEM_ICON_WATER_LOOP("\ua047"),
    SYSTEM_ICON_LIFT("\ua048"),
    APP_ICON_HOME_STROKE("\ua049"),
    APP_ICON_CUSTOMER_STROKE("\ua050"),
    APP_ICON_DEVICE_STROKE("\ua051"),
    APP_ICON_ALERT_STROKE("\ua052"),
    APP_ICON_ORDER_STROKE("\ua053"),
    APP_ICON_HOME_SOLID("\ua054"),
    APP_ICON_CUSTOMER_SOLID("\ua055"),
    APP_ICON_DEVICE_SOLID("\ua056"),
    APP_ICON_ALERT_SOLID("\ua057"),
    APP_ICON_ORDER_SOLID("\ua058"),
    APP_ICON_ORDER_STROKE_SEC("\ua059"),
    APP_ITEM_ICON_DEVICE("\ua060"),
    APP_ITEM_ICON_ALERT("\ua061"),
    APP_ALERT_DEALING_ICON("\ua062"),
    APP_ALERT_FLAG_ICON("\ua063"),
    APP_ALERT_ALL_ICON("\ua064"),
    APP_ALERT_NEW_ICON("\ua065"),
    APP_ALERT_FINISH_ICON("\ua066"),
    APP_TEST_CALENDAR_ICON("\ua067"),
    APP_TEST_NAME_ICON("\ua068"),
    APP_TEST_VALUE_ICON("\ua069"),
    APP_TEST_HISTORY_ICON("\ua070");

    private String mValue;

    EAppIconFont(String value) {
        this.mValue = value;
    }

    public String getValue() {
        return this.mValue;
    }
}
