package com.yuplus.cloudsdk.future;

import com.yuplus.cloudsdk.CloudSDKManager;
import com.yuplus.cloudsdk.config.SDKConfiguration;
import com.yuplus.cloudsdk.log.LogUtils;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class ApiCst {
    private static SDKConfiguration mSdkConfiguration;

    static {
        mSdkConfiguration = CloudSDKManager.getInstance().getSdkConfiguration();
        if (null == mSdkConfiguration) {
            RuntimeException exception = new RuntimeException("the sDKConfiguration is null when use the apiCst class,so you need set the sDKConfiguration.");
            LogUtils.e(exception, "the sDKConfiguration is null when use the apiCst class,so you need set the sDKConfiguration.");
            throw exception;
        }
    }

    public static final String HOST_NAME      = mSdkConfiguration.getHttpHost();
    public static final int    PORT           = mSdkConfiguration.getHttpPort();
    public static final String ROUTE          = mSdkConfiguration.getBasePath();
    public static final String SERVER_ADDRESS = HOST_NAME + (PORT != 80 && PORT > 0 ? (":" + PORT) : "");
    public static final String REQUEST_API    = SERVER_ADDRESS + ROUTE;


    //登录接口
    public static final String LOGIN_API = REQUEST_API + "/userLoginUIService/login";

    //获取当前的用户信息
    public static final String GET_CURRENT_USER_API = REQUEST_API + "/userLoginUIService/getCurrentUser";

    //修改用户信息接口
    public static final String MODIFY_USER_INFO_API = REQUEST_API + "/userUIService/modifyUser";

    //获取用户的角色
    public static final String QUERY_ENTERPRISE_ROLE = REQUEST_API + "/userEnterpriseService/queryEnterpriseRole";

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

    //获取工单流程
    public static final String TICKERT_FLOW_API = REQUEST_API + "/ticketCategoryService/getTicketCategorys";

    //获取工单详情
    public static final String GET_TICKET_NO_DETAIL_API = REQUEST_API + "/ticketLogService/getByTicketNo";

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
