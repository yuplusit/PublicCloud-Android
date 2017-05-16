package com.yuplus.cloudsdk.enums;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc 网络类型
 */

public enum ENetworkType {
    //未知网络类型
    NETWORK_UNKNOWN("UNKNOWN"),
    //WIFI
    NETWORK_WIFI("WIFI"),
    //2G网络
    NETWORK_2G("2G"),
    //3G网络
    NETWORK_3G("3G"),
    //4G网络
    NETWORK_4G("4G");

    private String mValue;

    ENetworkType(String value) {
        this.mValue = value;
    }

    public String getValue() {
        return this.mValue;
    }
}
