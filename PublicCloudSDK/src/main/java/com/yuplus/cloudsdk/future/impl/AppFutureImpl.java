package com.yuplus.cloudsdk.future.impl;

import com.yuplus.cloudsdk.base.BaseParams;
import com.yuplus.cloudsdk.future.ApiCst;
import com.yuplus.cloudsdk.future.IAppFuture;
import com.yuplus.cloudsdk.future.data.params.IntegerParams;
import com.yuplus.cloudsdk.future.data.params.ListParams;
import com.yuplus.cloudsdk.future.data.params.LongParams;
import com.yuplus.cloudsdk.future.data.params.MapParams;
import com.yuplus.cloudsdk.future.handler.AlertActionHandler;
import com.yuplus.cloudsdk.future.handler.AlertListHandler;
import com.yuplus.cloudsdk.future.handler.AttrListHandler;
import com.yuplus.cloudsdk.future.handler.BaseHandler;
import com.yuplus.cloudsdk.future.handler.ConfigurationListHandler;
import com.yuplus.cloudsdk.future.handler.CustomerHandler;
import com.yuplus.cloudsdk.future.handler.CustomerListHandler;
import com.yuplus.cloudsdk.future.handler.DeviceHandler;
import com.yuplus.cloudsdk.future.handler.DomainListHandler;
import com.yuplus.cloudsdk.future.handler.KpiListHandler;
import com.yuplus.cloudsdk.future.handler.KpiValueListHandler;
import com.yuplus.cloudsdk.future.handler.MessageListHandler;
import com.yuplus.cloudsdk.future.handler.ModeListHandler;
import com.yuplus.cloudsdk.future.handler.ProjectListHandler;
import com.yuplus.cloudsdk.future.handler.TicketListHandler;
import com.yuplus.cloudsdk.future.handler.UnitListHandler;
import com.yuplus.cloudsdk.future.handler.UserHandler;
import com.yuplus.cloudsdk.future.listener.FutureListener;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.util.StringUtils;

import java.util.List;

import okhttp3.MediaType;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class AppFutureImpl implements IAppFuture {

    @Override
    public void login(String account, String password, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(account)
                .addParam(password);
        postJSON(ApiCst.LOGIN_API, requestParams, tag, UserHandler.class, listener);
    }

    @Override
    public void getAlertByPage(int start, int pageSize, String severities, String states, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams()
                .addParam("domain", "")
                .addParam("nodeType", "")
                .addParam("alertCodes", "")
                .addParam("createTimeFrom", "")
                .addParam("pageSize", String.valueOf(pageSize))
                .addParam("messageFilter", "")
                .addParam("severities", severities)
                .addParam("states", states);
        MapParams params2 = new MapParams()
                .addParam("start", String.valueOf(start))
                .addParam("length", String.valueOf(pageSize))
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("statCount", String.valueOf(true))
                .addParam("total", "0");
        ListParams requestParams = new ListParams()
                .addParam(params1)
                .addParam(params2);
        postJSON(ApiCst.GET_ALERT_BY_PAGE_API, requestParams, tag, AlertListHandler.class, listener);
    }

    @Override
    public void getDevicesByConditionWithPage(int start, int pageSize, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams();
        MapParams params2 = new MapParams()
                .addParam("start", String.valueOf(start))
                .addParam("length", String.valueOf(pageSize))
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("statCount", String.valueOf(true));
        ListParams requestParams = new ListParams()
                .addParam(params1)
                .addParam(params2);
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, AlertListHandler.class, listener);
    }

    @Override
    public void getAllDevicesByCondition(BaseParams params, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams();
        ListParams requestParams = new ListParams()
                .addParam(params1)
                .addParam(params);
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, AlertListHandler.class, listener);
    }

    @Override
    public void getAllDevicesByCondition(String params, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams();
        ListParams requestParams = new ListParams()
                .addParam(params1)
                .addParam(params);
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, AlertListHandler.class, listener);
    }

    @Override
    public void getAllDevices(String domainPath, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams()
                .addParam("domainPath", domainPath);
        MapParams params2 = new MapParams()
                .addParam("start", String.valueOf(0))
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("", String.valueOf(true));
        ListParams requestParams = new ListParams()
                .addParam(params1)
                .addParam(params2);
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, AlertListHandler.class, listener);
    }

    @Override
    public void getCustomerById(String customerId, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(Long.parseLong(customerId));
        postJSON(ApiCst.GET_CUSTOMER_INFO_BY_ID_API, requestParams, tag, CustomerHandler.class, listener);
    }

    @Override
    public void getAllCustomerInfoList(Object tag, FutureListener listener) {
        MapParams requestParams = new MapParams()
                .addParam("orCondition", "")
                .addParam("customerName", "")
                .addParam("customerAddress", "")
                .addParam("domainPath", "")
                .addParam("customerPhone", "");
        postJSON(ApiCst.GET_CUSTOMER_INFO_API, requestParams, tag, CustomerListHandler.class, listener);
    }

    @Override
    public void sendAlertRecoverAction(String alertId, Object tag, FutureListener listener) {
        ListParams params1 = new ListParams()
                .addParam(Long.parseLong(alertId));
        MapParams requestParams = new MapParams()
                .addParam("actionType", "recover")
                .addParam("alertIds", params1.getParams())
                .addParam("recoverAll", true)
                .addParam("resolved", true)
                .addParam("clearOut", true);
        postJSON(ApiCst.ALERT_SEND_RECOVER_ACTION_API, requestParams, tag, AlertActionHandler.class, listener);
    }

    @Override
    public void sendAlertClaimAction(String alertId, Object tag, FutureListener listener) {
        ListParams params1 = new ListParams()
                .addParam(Long.parseLong(alertId));
        MapParams requestParams = new MapParams()
                .addParam("actionType", "claim")
                .addParam("alertIds", params1.getParams());
        postJSON(ApiCst.ALERT_SEND_CLAIM_ACTION_API, requestParams, tag, AlertActionHandler.class, listener);
    }

    @Override
    public void deviceActivateGateway(String deviceId, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(Long.parseLong(deviceId));
        postJSON(ApiCst.DEVICE_ACTIVATE_GATEWAY_API, requestParams, tag, DeviceHandler.class, listener);
    }

    @Override
    public void deviceDeactivateGateway(String deviceId, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(Long.parseLong(deviceId));
        postJSON(ApiCst.DEVICE_DEACTIVATE_GATEWAY_API, requestParams, tag, DeviceHandler.class, listener);
    }

    @Override
    public void findProjectsByCondition(BaseParams params, Object tag, FutureListener listener) {
        postJSON(ApiCst.FIND_PROJECTS_API, params, tag, ProjectListHandler.class, listener);
    }

    @Override
    public void findProjectsByCondition(String params, Object tag, FutureListener listener) {
        postJSON(ApiCst.FIND_PROJECTS_API, params, tag, ProjectListHandler.class, listener);
    }

    @Override
    public void findProjectsByCustomerId(String customerId, Object tag, FutureListener listener) {
        MapParams requestParams = new MapParams()
                .addParam("customerId", customerId);
        postJSON(ApiCst.FIND_PROJECTS_API, requestParams, tag, ProjectListHandler.class, listener);
    }

    @Override
    public void findTicketsByCondition(BaseParams params, Object tag, FutureListener listener) {
        postJSON(ApiCst.TASK_FIND_TICKETS_API, params, tag, ProjectListHandler.class, listener);
    }

    @Override
    public void findTicketsByCondition(String params, Object tag, FutureListener listener) {
        postJSON(ApiCst.TASK_FIND_TICKETS_API, params, tag, TicketListHandler.class, listener);
    }

    @Override
    public void findTicketsByCondition(int status, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams()
                .addParam("status", String.valueOf(status));
        ListParams requestParams = new ListParams()
                .addParam(params1);
        postJSON(ApiCst.TASK_FIND_TICKETS_API, requestParams, tag, TicketListHandler.class, listener);
    }

    @Override
    public void getKpiValueList(BaseParams kpiQueryModel, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam("kpi")
                .addParam(kpiQueryModel.getParams());
        postJSON(ApiCst.GET_KPI_VALUE_LIST_API, requestParams, tag, KpiValueListHandler.class, listener);
    }

    @Override
    public void getKpiValueList(String kpiQueryModel, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam("kpi")
                .addParam(kpiQueryModel);
        postJSON(ApiCst.GET_KPI_VALUE_LIST_API, requestParams, tag, KpiValueListHandler.class, listener);
    }

    @Override
    public void getModelByIds(List<String> ids, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(ids);
        postJSON(ApiCst.GET_MODEL_API, requestParams, tag, ModeListHandler.class, listener);
    }

    @Override
    public void getKpisByModelId(String modeId, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(Long.parseLong(modeId));
        postJSON(ApiCst.GET_MODEL_KPIS_API, requestParams, tag, KpiListHandler.class, listener);
    }

    @Override
    public void getAttrsByModelId(String modeId, Object tag, FutureListener listener) {
        LongParams requestParams = new LongParams()
                .addParam(Long.parseLong(modeId));
        postJSON(ApiCst.GET_MODEL_ATTRS_API, requestParams, tag, AttrListHandler.class, listener);
    }

    @Override
    public void getAllUnits(Object tag, FutureListener listener) {
        postJSON(ApiCst.GET_UNIT_API, "", tag, UnitListHandler.class, listener);
    }

    @Override
    public void getConfigsByGroupName(String groupName, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(groupName);
        postJSON(ApiCst.GET_CONFIG_API, requestParams, tag, ConfigurationListHandler.class, listener);
    }

    @Override
    public void getDomainsByFilter(BaseParams params, Object tag, FutureListener listener) {
        postJSON(ApiCst.GET_DOMAINS_API, params, tag, DomainListHandler.class, listener);
    }

    @Override
    public void getDomainsByFilter(String params, Object tag, FutureListener listener) {
        postJSON(ApiCst.GET_DOMAINS_API, params, tag, DomainListHandler.class, listener);
    }

    @Override
    public void getLatestMessage(Object tag, FutureListener listener) {
        IntegerParams requestParams = new IntegerParams()
                .addParam(0);
        postJSON(ApiCst.GET_LEAST_MESSAGE_API, requestParams, tag, MessageListHandler.class, listener);
    }

    @Override
    public void getAllMessageList(Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams();
        postJSON(ApiCst.GET_ALL_MESSAGE_LIST_API, requestParams, tag, MessageListHandler.class, listener);
    }

    /**
     * 采用post参数：普通的JSONString方式)方式请求
     *
     * @param params   转json数据前的Object
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     * @return Request请求对象
     */
    public void postJSON(final String url, final BaseParams params, final Object tag, final Class<? extends BaseHandler> handlerCls, final FutureListener listener) {
        String requestContent;
        if (null == params) {
            requestContent = "";
        } else {
            requestContent = params.toJson();
        }
        try {
            OkHttpUtils.postString()
                    .url(url)
                    .tag(tag)
                    .content(requestContent)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .handlerCls(handlerCls)
                    .execute(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用post参数：普通的JSONString方式)方式请求
     *
     * @param params   json数据
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     * @return Request请求对象
     */
    public void postJSON(final String url, final String params, final Object tag, Class<? extends BaseHandler> handlerCls, final FutureListener listener) {
        String requestContent;
        if (StringUtils.isBlank(params)) {
            requestContent = "";
        } else {
            requestContent = params;
        }
        try {
            OkHttpUtils.postString()
                    .url(url)
                    .tag(tag)
                    .content(requestContent)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .handlerCls(handlerCls)
                    .execute(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
