package com.yuplus.publiccloud.cst;

import com.yuplus.cloudsdk.future.ApiCst;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public class AppCst {
    public static final String APP_NAME = "PublicCloud";

    //SP名字
    public static final String SHAREDPREFERENCES_NAME = APP_NAME;

    //服务器地址
    public static final String SERVER_HOST = "http://36.110.36.118";//"http://demo.proudsmart.com";//"http://36.110.36.118";

    //服务器端口
    public static final int SERVER_PORT = 8095;//8095;

    //忘记密码Web url
    public static final String FORGET_PASSWORD_URL = ApiCst.SERVER_ADDRESS + "/app-uc/forgetPassword.html";

    //WebSocket地址
    public static final String WEBSOCKET_ADDRESS = "wss://yzt.raonecloud.com/websocket/message";

    //Api基本path
    public static final String SERVER_BASE_PATH = "/api/rest/post";

    //SplashActivity页面过度的时间
    public static final int SPLASH_TRANSITION_TIME = 3000;

    //分页数量
    public static final int PAGE_SIZE = 10;

    //common key one  -->key one
    public static final String COMMON_KEY = "common_key";

    //common key status  -->key two
    public static final String COMMON_STATUS = "common_status";

    //common key flag  -->key three
    public static final String COMMON_FLAG = "common_flag";

    //common key data  -->key four
    public static final String COMMON_DATA = "common_data";

    //common key url  -->key five
    public static final String COMMON_URL = "common_url";

    //common key type  -->key six
    public static final String COMMON_TYPE = "common_type";

    //common key title -->key seven
    public static final String COMMON_TITLE = "common_title";

    //common key title -->key eight
    public static final String COMMON_CONTENT = "common_title";

    //common key id -->key id
    public static final String COMMON_ID = "common_id";
}
