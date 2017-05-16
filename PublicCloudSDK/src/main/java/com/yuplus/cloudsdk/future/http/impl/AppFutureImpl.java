package com.yuplus.cloudsdk.future.http.impl;

import com.yuplus.cloudsdk.base.BaseParams;
import com.yuplus.cloudsdk.future.data.params.IntegerParams;
import com.yuplus.cloudsdk.future.data.params.ListParams;
import com.yuplus.cloudsdk.future.data.params.MapParams;
import com.yuplus.cloudsdk.future.http.ApiCst;
import com.yuplus.cloudsdk.future.http.IAppFuture;
import com.yuplus.cloudsdk.okhttp.OkHttpUtils;
import com.yuplus.cloudsdk.okhttp.callback.BaseCallback;
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
    public void login(String account, String password, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(account)
                .addParam(password);
        postJSON(ApiCst.LOGIN_API, requestParams, tag, callback);
    }

    @Override
    public void getAlertByPage(int start, int pageSize, String severities, String states, Object tag, BaseCallback callback) {
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
        postJSON(ApiCst.GET_ALERT_BY_PAGE_API, requestParams, tag, callback);
    }

    @Override
    public void getDevicesByConditionWithPage(int start, int pageSize, Object tag, BaseCallback callback) {
        MapParams requestParams = new MapParams()
                .addParam("start", String.valueOf(start))
                .addParam("length", String.valueOf(pageSize))
                .addParam("sort", "createTime")
                .addParam("sortType", "desc")
                .addParam("statCount", String.valueOf(true));
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, callback);
    }

    @Override
    public void getAllDevicesByCondition(BaseParams params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, params, tag, callback);
    }

    @Override
    public void getAllDevicesByCondition(String params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, params, tag, callback);
    }

    @Override
    public void getAllDevices(String domainPath, Object tag, BaseCallback callback) {
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
        postJSON(ApiCst.GET_DEVICES_BY_CONDITION_WITHPAGE_API, requestParams, tag, callback);
    }

    @Override
    public void getCustomerById(String customerId, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(customerId);
        postJSON(ApiCst.GET_CUSTOMER_INFO_BY_ID_API, requestParams, tag, callback);
    }

    @Override
    public void getAllCustomerInfoList(Object tag, BaseCallback callback) {
        MapParams requestParams = new MapParams()
                .addParam("orCondition", "")
                .addParam("customerName", "")
                .addParam("customerAddress", "")
                .addParam("domainPath", "")
                .addParam("customerPhone", "");
        postJSON(ApiCst.GET_CUSTOMER_INFO_API, requestParams, tag, callback);
    }

    @Override
    public void sendAlertRecoverAction(String alertId, Object tag, BaseCallback callback) {
        ListParams params1 = new ListParams()
                .addParam(alertId);
        MapParams requestParams = new MapParams()
                .addParam("actionType", "recover")
                .addParam("alertIds", params1.toJson())
                .addParam("recoverAll", String.valueOf(true))
                .addParam("resolved", String.valueOf(true))
                .addParam("clearOut", String.valueOf(true));
        postJSON(ApiCst.ALERT_SEND_RECOVER_ACTION_API, requestParams, tag, callback);
    }

    @Override
    public void sendAlertClaimAction(String alertId, Object tag, BaseCallback callback) {
        ListParams params1 = new ListParams()
                .addParam(alertId);
        MapParams requestParams = new MapParams()
                .addParam("actionType", "claim")
                .addParam("alertIds", params1.toJson());
        postJSON(ApiCst.ALERT_SEND_CLAIM_ACTION_API, requestParams, tag, callback);
    }

    @Override
    public void deviceActivateGateway(String deviceId, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(deviceId);
        postJSON(ApiCst.DEVICE_ACTIVATE_GATEWAY_API, requestParams, tag, callback);
    }

    @Override
    public void deviceDeactivateGateway(String deviceId, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(deviceId);
        postJSON(ApiCst.DEVICE_DEACTIVATE_GATEWAY_API, requestParams, tag, callback);
    }

    @Override
    public void findProjectsByCondition(BaseParams params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.FIND_PROJECTS_API, params, tag, callback);
    }

    @Override
    public void findProjectsByCondition(String params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.FIND_PROJECTS_API, params, tag, callback);
    }

    @Override
    public void findProjectsByCustomerId(String customerId, Object tag, BaseCallback callback) {
        MapParams requestParams = new MapParams()
                .addParam("customerId", customerId);
        postJSON(ApiCst.FIND_PROJECTS_API, requestParams, tag, callback);
    }

    @Override
    public void findTicketsByCondition(BaseParams params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.TASK_FIND_TICKETS_API, params, tag, callback);
    }

    @Override
    public void findTicketsByCondition(String params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.TASK_FIND_TICKETS_API, params, tag, callback);
    }

    @Override
    public void findTicketsByCondition(int status, Object tag, BaseCallback callback) {
        MapParams params1 = new MapParams()
                .addParam("status", String.valueOf(status));
        ListParams requestParams = new ListParams()
                .addParam(params1);
        postJSON(ApiCst.TASK_FIND_TICKETS_API, requestParams, tag, callback);
    }

    @Override
    public void getKpiValueList(BaseParams kpiQueryModel, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam("kpi")
                .addParam(kpiQueryModel.toJson());
        postJSON(ApiCst.GET_KPI_VALUE_LIST_API, requestParams, tag, callback);
    }

    @Override
    public void getKpiValueList(String kpiQueryModel, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam("kpi")
                .addParam(kpiQueryModel);
        postJSON(ApiCst.GET_KPI_VALUE_LIST_API, requestParams, tag, callback);
    }

    @Override
    public void getModelByIds(List<String> ids, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(ids);
        postJSON(ApiCst.GET_MODEL_API, requestParams, tag, callback);
    }

    @Override
    public void getKpisByModelId(String modeId, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(Long.parseLong(modeId));
        postJSON(ApiCst.GET_MODEL_KPIS_API, requestParams, tag, callback);
    }

    @Override
    public void getAttrsByModelId(String modeId, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(Long.parseLong(modeId));
        postJSON(ApiCst.GET_MODEL_ATTRS_API, requestParams, tag, callback);
    }

    @Override
    public void getAllUnits(Object tag, BaseCallback callback) {
        postJSON(ApiCst.GET_UNIT_API, "", tag, callback);
    }

    @Override
    public void getConfigsByGroupName(String groupName, Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams()
                .addParam(groupName);
        postJSON(ApiCst.GET_CONFIG_API, requestParams, tag, callback);
    }

    @Override
    public void getDomainsByFilter(BaseParams params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.GET_DOMAINS_API, params, tag, callback);
    }

    @Override
    public void getDomainsByFilter(String params, Object tag, BaseCallback callback) {
        postJSON(ApiCst.GET_DOMAINS_API, params, tag, callback);
    }

    @Override
    public void getLatestMessage(Object tag, BaseCallback callback) {
        IntegerParams requestParams = new IntegerParams()
                .addParam(0);
        postJSON(ApiCst.GET_LEAST_MESSAGE_API, requestParams, tag, callback);
    }

    @Override
    public void getAllMessageList(Object tag, BaseCallback callback) {
        ListParams requestParams = new ListParams();
        postJSON(ApiCst.GET_ALL_MESSAGE_LIST_API, requestParams, tag, callback);
    }

    /**
     * 采用post参数：普通的JSONString方式)方式请求
     *
     * @param params   转json数据前的Object
     * @param tag      网络请求TAG标记
     * @param callback 回调操作
     * @return Request请求对象
     */
    public void postJSON(final String url, final BaseParams params, final Object tag, final BaseCallback callback) {
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
                    .execute(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 采用post参数：普通的JSONString方式)方式请求
     *
     * @param params   json数据
     * @param tag      网络请求TAG标记
     * @param callBack 回调操作
     * @return Request请求对象
     */
    public void postJSON(final String url, final String params, final Object tag, final BaseCallback callBack) {
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
                    .execute(callBack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
