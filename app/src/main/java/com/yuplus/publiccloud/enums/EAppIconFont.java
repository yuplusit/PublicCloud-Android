package com.yuplus.publiccloud.enums;

/**
 * @user longzhen
 * @date 5/17/2017
 * @desc
 */

public enum EAppIconFont {
    //此App常用图标
    APP_ICON_HOME("\ua001"),
    APP_ICON_USER("\ua002"),
    APP_ICON_DEVICE_STROKE("\ua003"),
    APP_ICON_WARNING_01("\ua004"),
    APP_ICON_ORDER("\ua005"),
    APP_ICON_EYE("\ua006"),
    APP_ICON_PERSONAL_STROKE("\ua007"),
    APP_ICON_DEVICE_SOLID("\ua008"),
    APP_ICON_DEAWER("\ua009"),
    APP_ICON_PERSONAL_SOLID("\ua010"),
    APP_ICON_WARNING_02("\ua011"),
    APP_ICON_SCRREN("\ua012"),
    APP_ICON_DATA("\ua013"),
    APP_ICON_MENU("\ua014"),
    APP_ICON_REFRESH_01("\ua015"),
    APP_ICON_REFRESH_02("\ua016"),
    APP_ICON_REFRESH_03("\ua017"),
    APP_ICON_REFRESH_04("\ua018"),
    APP_ICON_REFRESH_05("\ua019"),
    APP_ICON_REFRESH_06("\ua020"),
    APP_ICON_REFRESH_07("\ua021"),
    APP_ICON_REFRESH_08("\ua022"),
    APP_ICON_REFRESH_09("\ua023"),
    APP_ICON_REFRESH_10("\ua024"),
    APP_ICON_LOGINOFF_01("\ua025"),
    APP_ICON_LOGINOFF_02("\ua026"),

    //此App八大系统图标
    SYSTEM_ICON_CAR("\ua040"),
    SYSTEM_ICON_O2("\ua041"),
    SYSTEM_ICON_WATER("\ua042"),
    SYSTEM_ICON_HEATING_01("\ua043"),
    SYSTEM_ICON_ELECTRIC("\ua045"),
    SYSTEM_ICON_CONDITIONER("\ua046"),
    SYSTEM_ICON_WATER_LOOP("\ua047"),
    SYSTEM_ICON_LIFT("\ua048"),;

    private String mValue;

    EAppIconFont(String value) {
        this.mValue = value;
    }

    public String getValue() {
        return this.mValue;
    }
}
