package com.yuplus.cloudsdk.future.http;

import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class ApiCst {
    private static SDKConfiguration mSdkConfiguration;

    static {
        mSdkConfiguration = OkHttpUtils.getInstance().getSdkConfiguration();
    }

    private static final String HOST_NAME   = mSdkConfiguration.getHttpHost();
    private static final int    PORT        = mSdkConfiguration.getHttpPort();
    private static final String ROUTE       = mSdkConfiguration.getBasePath();
    private static final String REQUEST_API = HOST_NAME + (PORT != 80 && PORT > 0 ? (":" + PORT) : "") + ROUTE;


    //登录接口
    public static final String LOGIN_API = REQUEST_API + "/userLoginUIService/login";

    //获取警告信息接口
    public static final String GET_ALERT_BY_PAGE_API = REQUEST_API + "/alertQueryFlexService/getAlertByPage";

    //获取设备信息接口
    public static final String GET_DEVICES_BY_CONDITION_WITHPAGE_API = REQUEST_API + "/resourceUIService/getDevicesByConditionWithPage";

    //获取客户信息接口
    public static final String GET_CUSTOMER_INFO_API = REQUEST_API + "/customerUIService/findCustomersByCondition";

    //根据id获取客户信息接口
    public static final String GET_CUSTOMER_INFO_BY_ID_API = REQUEST_API + "/customerUIService/findCustomersById";

    //关闭警告接口
    public static final String ALERT_SEND_RECOVER_ACTION_API = REQUEST_API + "/alertManageFlexService/sendRecoverAction";

    //警告确认接口
    public static final String ALERT_SEND_CLAIM_ACTION_API = REQUEST_API + "/alertManageFlexService/sendClaimAction";

    //启用设备接口
    public static final String DEVICE_ACTIVATE_GATEWAY_API = REQUEST_API + "/resourceUIService/activateDevice";

    //停用设备接口
    public static final String DEVICE_DEACTIVATE_GATEWAY_API = REQUEST_API + "/resourceUIService/deactivateDevice";

    //查找project接口
    public static final String FIND_PROJECTS_API = REQUEST_API + "/projectUIService/findProjectsByCondition";

    //查找工单
    public static final String TASK_FIND_TICKETS_API = REQUEST_API + "/ticketTaskService/findTickets";

    //获得KQI/
    public static final String GET_KPI_VALUE_LIST_API = REQUEST_API + "/kpiDataFlexService/getKpiValueList";

    //获得设备的模板
    public static final String GET_MODEL_API = REQUEST_API + "/resourceUIService/getModelByIds";

    //获得设备的模板的KPI定义
    public static final String GET_MODEL_KPIS_API = REQUEST_API + "/resourceUIService/getKpisByModelId";

    //获得设备的模板的属性定义
    public static final String GET_MODEL_ATTRS_API = REQUEST_API + "/resourceUIService/getAttrsByModelId";

    //获得系统配置
    public static final String GET_CONFIG_API = REQUEST_API + "/configUIService/getConfigsByGroupName";

    //获得系统单位
    public static final String GET_UNIT_API = REQUEST_API + "/unitService/getAllUnits";

    //获取资源域
    public static final String GET_DOMAINS_API = REQUEST_API + "/resourceUIService/getDomainsByFilter";

    //获取最新的消息
    public static final String GET_LEAST_MESSAGE_API = REQUEST_API + "/psMessageService/queryMessageByStatusAndUserID";

    //全部的消息接口
    public static final String GET_ALL_MESSAGE_LIST_API = REQUEST_API + "/psMessageService/queryMessageByUserID";
}
