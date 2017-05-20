package com.yuplus.cloudsdk.cst;

/**
 * @user longzhen
 * @date 5/12/2017
 * @desc
 */

public class HttpCst {
    public static final String TAG = "HTTP";
    //Http请求默认连接超时时间, 15s
    public static final int CONNECTION_TIMEOUT = 15000;
    //Http请求默认数据读取超时时间, 10s
    public static final int READ_TIMEOUT       = 10000;
    //Http请求默认数据写入超时时间, 10s
    public static final int WRITE_TIMEOUT      = 10000;

    //Http请求头Content-Type
    public static final String CONTENT_TYPE    = "Content-Type";
    //Http请求头Accept-Encoding
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    //Http请求头Accept-Language
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    //Http请求头User-Agent
    public static final String USER_AGNET      = "User-Agent";

    //Http请求头COOKIE
    public static final String COOKIE     = "Cookie";
    //Http请求头SET_COOKIE
    public static final String SET_COOKIE = "Set-Cookie";

    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String ETAG          = "ETag";

    public static final String TERMINAL = "Android";


    public static final String URLENCODED_FORM = "application/x-www-form-urlencoded; charset=utf-8";

    public static final String URLENCODED_JSON = "application/json; charset=utf-8";

    public static final String URLENCODED_TEXTPLAIN = "text/plain; charset=utf-8";

    public static final String URLENCODED_OCTET_STREAM = "application/octet-stream";

    public static class Method {
        //GET请求
        public static final String GET     = "GET";
        //POST请求
        public static final String POST    = "POST";
        //PUT请求
        public static final String PUT     = "PUT";
        //DELETE请求
        public static final String DELETE  = "DELETE";
        //PATCH请求
        public static final String PATCH   = "PATCH";
        //HEAD请求
        public static final String HEAD    = "HEAD";
        //TRACE跟踪请求
        public static final String TRACE   = "TRACE";
        //OPTIONS查询能力请求
        public static final String OPTIONS = "OPTIONS";
    }

    public static class ExceptionMsg {
        public static final String LOGIN_AGAIN  = "登录超时，请重新登录!";
        public static final String FAILURE_CODE_MSG = "ErrCode:%1$d,Message:%2$s";
    }
}
