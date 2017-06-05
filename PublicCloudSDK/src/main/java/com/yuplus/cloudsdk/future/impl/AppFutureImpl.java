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
import com.yuplus.cloudsdk.future.handler.DeviceListHandler;
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
    public void getAlertByPage(int start, int pageSize, String domains, String severities, String states, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams()
                .addParam("domain", domains == null ? "" : domains)
                .addParam("nodeType", "")
                .addParam("alertCodes", "")
                .addParam("createTimeFrom", "")
                .addParam("createTimeTo", "")
                .addParam("pageSize", pageSize)
                .addParam("messageFilter", "")
                .addParam("severities", severities)
                .addParam("states", states);
        MapParams params2 = new MapParams()
                .addParam("start", start)
                .addParam("length", pageSize)
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("statCount", true)
                .addParam("total", 0);
        ListParams requestParams = new ListParams()
                .addParam(params1.getParams())
                .addParam(params2.getParams());
        postJSON(ApiCst.GET_ALERT_BY_PAGE_API, requestParams, tag, AlertListHandler.class, listener);
    }

    @Override
    public void getDevicesByConditionWithPage(long projectId, int start, int pageSize, int total, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams()
                .addParam("projectId", projectId > 0L ? String.valueOf(projectId) : "");
        MapParams params2 = new MapParams()
                .addParam("start", start)
                .addParam("length", pageSize)
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("statCount", true)
                .addParam("total", total);
        ListParams requestParams = new ListParams()
                .addParam(params1.getParams())
                .addParam(params2.getParams());
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, DeviceListHandler.class, listener);
    }

    @Override
    public void getDevicesByConditionWithPage(BaseParams params, Object tag, FutureListener listener) {
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, params, tag, DeviceListHandler.class, listener);
    }

    @Override
    public void getDevicesByConditionWithPage(String params, Object tag, FutureListener listener) {
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, params, tag, DeviceListHandler.class, listener);
    }

    @Override
    public void getAllDevices(String domainPath, Object tag, FutureListener listener) {
        MapParams params1 = new MapParams()
                .addParam("domainPath", domainPath);
        MapParams params2 = new MapParams()
                .addParam("start", 0)
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("", true);
        ListParams requestParams = new ListParams()
                .addParam(params1.getParams())
                .addParam(params2.getParams());
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, DeviceListHandler.class, listener);
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
    public void sendAlertRecoverAction(long alertId, Object tag, FutureListener listener) {
        ListParams params1 = new ListParams()
                .addParam(alertId);
        MapParams requestParams = new MapParams()
                .addParam("actionType", "recover")
                .addParam("alertIds", params1.getParams())
                .addParam("recoverAll", true)
                .addParam("resolved", true)
                .addParam("clearOut", true);
        postJSON(ApiCst.ALERT_SEND_RECOVER_ACTION_API, requestParams, tag, AlertActionHandler.class, listener);
    }

    @Override
    public void sendAlertClaimAction(long alertId, Object tag, FutureListener listener) {
        ListParams params1 = new ListParams()
                .addParam(alertId);
        MapParams requestParams = new MapParams()
                .addParam("actionType", "claim")
                .addParam("alertIds", params1.getParams());
        postJSON(ApiCst.ALERT_SEND_CLAIM_ACTION_API, requestParams, tag, AlertActionHandler.class, listener);
    }

    @Override
    public void deviceActivateGateway(long deviceId, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(deviceId);
        postJSON(ApiCst.DEVICE_ACTIVATE_GATEWAY_API, requestParams, tag, DeviceHandler.class, listener);
    }

    @Override
    public void deviceDeactivateGateway(long deviceId, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(deviceId);
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
    public void getModelByIds(List<Long> ids, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(ids);
        postJSON(ApiCst.GET_MODEL_API, requestParams, tag, ModeListHandler.class, listener);
    }

    @Override
    public void getKpisByModelId(long modeId, Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams()
                .addParam(modeId);
        postJSON(ApiCst.GET_MODEL_KPIS_API, requestParams, tag, KpiListHandler.class, listener);
    }

    @Override
    public void getAttrsByModelId(long modeId, Object tag, FutureListener listener) {
        LongParams requestParams = new LongParams()
                .addParam(modeId);
        postJSON(ApiCst.GET_MODEL_ATTRS_API, requestParams, tag, AttrListHandler.class, listener);
    }

    @Override
    public void getAllUnits(Object tag, FutureListener listener) {
        ListParams requestParams = new ListParams();
        postJSON(ApiCst.GET_UNIT_API, requestParams, tag, UnitListHandler.class, listener);
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
