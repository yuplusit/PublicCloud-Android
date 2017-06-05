package com.yuplus.cloudsdk.future;

import com.yuplus.cloudsdk.base.BaseParams;
import com.yuplus.cloudsdk.future.listener.FutureListener;

import java.util.List;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public interface IAppFuture {
    /**
     * 账户登录操作
     *
     * @param account  账户名称
     * @param password 账户密码
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     * @return
     */
    void login(String account, String password, Object tag, FutureListener listener);

    /**
     * 获取不同级别的警告数据
     *
     * @param start      分页的起始位置
     * @param pageSize   分页的大小
     * @param domain
     * @param severities 告警的严重性(1:警告;2:次要;3:重要;4:严重) 例如:全部：severities="1,2,3,4"
     * @param states     告警的状态(0:新产生;5:已确认;10:处理中;20:已解决;30:已忽略) @example:全部：states= "0,5,10,20"
     * @param tag        网络请求TAG标记
     * @param listener   回调操作
     */
    void getAlertByPage(int start, int pageSize, String domain, String severities, String states, Object tag, FutureListener listener);

    /**
     * 获取设备信息(分页)
     *
     * @param projectId 项目ID
     * @param start     分页的起始位置
     * @param pageSize  分页的大小
     * @param tag       网络请求TAG标记
     * @param total     列表总数
     * @param listener  回调操作
     */
    void getDevicesByConditionWithPage(long projectId, int start, int pageSize, int total, Object tag, FutureListener listener);

    /**
     * 通过条件筛选获取所有的设备信息
     *
     * @param params   开发式参数配置 例如：params.toJson()= [{},{"start":0,"length":10,"sort":"createTime","sortType":"desc","statCount":true}]
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getDevicesByConditionWithPage(BaseParams params, Object tag, FutureListener listener);

    /**
     * 通过条件筛获取所有的设备信息
     *
     * @param params   开发式参数配置 例如：params=[{},{"start":0,"length":10,"sort":"createTime","sortType":"desc","statCount":true}]
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getDevicesByConditionWithPage(String params, Object tag, FutureListener listener);

    /**
     * 获取所有的设备信息
     *
     * @param domainPath 域路径
     * @param tag        网络请求TAG标记
     * @param listener   回调操作
     */
    void getAllDevices(String domainPath, Object tag, FutureListener listener);

    /**
     * 通过id来获取客户的信息
     *
     * @param customerId 客户ID
     * @param tag        网络请求TAG标记
     * @param listener   回调操作
     */
    void getCustomerById(String customerId, Object tag, FutureListener listener);

    /**
     * 获取所有的客户信息
     *
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getAllCustomerInfoList(Object tag, FutureListener listener);

    /**
     * 关闭告警
     *
     * @param alertId  告警ID
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void sendAlertRecoverAction(long alertId, Object tag, FutureListener listener);

    /**
     * 确认警告
     *
     * @param alertId  告警ID
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void sendAlertClaimAction(long alertId, Object tag, FutureListener listener);

    /**
     * 启用设备
     *
     * @param deviceId 设备ID
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void deviceActivateGateway(long deviceId, Object tag, FutureListener listener);

    /**
     * 停用设备
     *
     * @param deviceId 设备ID
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void deviceDeactivateGateway(long deviceId, Object tag, FutureListener listener);

    /**
     * 通过条件配置查找项目信息
     *
     * @param params   开放式的请求参数配置 例如:params.toJson()="{\"customerId\":\"45845255441\"}"
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void findProjectsByCondition(BaseParams params, Object tag, FutureListener listener);

    /**
     * 通过条件配置查找项目信息
     *
     * @param params   开放式的请求参数配置 例如:params="{\"customerId\":\"45845255441\"}"
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void findProjectsByCondition(String params, Object tag, FutureListener listener);

    /**
     * 通过客户ID查找项目信息
     *
     * @param customerId 客户ID
     * @param tag        网络请求TAG标记
     * @param listener   回调操作
     */
    void findProjectsByCustomerId(String customerId, Object tag, FutureListener listener);

    /**
     * 通过工单状态查找工单列表
     *
     * @param params   开放式的请求参数配置 例如:params.toJson()=[{"status": 100}]
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void findTicketsByCondition(BaseParams params, Object tag, FutureListener listener);

    /**
     * 通过工单状态查找工单列表
     *
     * @param params   开放式的请求参数配置 例如:params=[{"status": 100}]
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void findTicketsByCondition(String params, Object tag, FutureListener listener);

    /**
     * 通过工单状态查找工单列表
     *
     * @param status   工单状态
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void findTicketsByCondition(int status, Object tag, FutureListener listener);

    /**
     * 获取系统各级别KPI/KQI数据
     *
     * @param kpiQueryModel 开放式的请求参数配置 例如:kpiQueryModel.toJson() = {"category":"ci","isRealTimeData":true,"nodeIds":[186793350166066],"kpiCodes":[3022],"startTime":null,"endTime":null,"timeRange":"","statisticType":"psiot","includeInstance":true,"condList":[]}
     * @param tag           网络请求TAG标记
     * @param listener      回调操作
     */
    void getKpiValueList(BaseParams kpiQueryModel, Object tag, FutureListener listener);

    /**
     * 获取系统各级别KPI/KQI数据
     *
     * @param kpiQueryModel 开放式的请求参数配置 例如:kpiQueryModel = {"category":"ci","isRealTimeData":true,"nodeIds":[186793350166066],"kpiCodes":[3022],"startTime":null,"endTime":null,"timeRange":"","statisticType":"psiot","includeInstance":true,"condList":[]}
     * @param tag           网络请求TAG标记
     * @param listener      回调操作
     */
    void getKpiValueList(String kpiQueryModel, Object tag, FutureListener listener);

    /**
     * 通过模型IDS列表获取模型
     *
     * @param ids      模型id列表
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getModelByIds(List<Long> ids, Object tag, FutureListener listener);

    /**
     * 通过模型ID列表获取模型KPIS
     *
     * @param modeId   模型id
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getKpisByModelId(long modeId, Object tag, FutureListener listener);

    /**
     * 通过模型ID列表获取模型属性定义
     *
     * @param modeId   模型id
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getAttrsByModelId(long modeId, Object tag, FutureListener listener);

    /**
     * 获得系统内单位的定义
     *
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getAllUnits(Object tag, FutureListener listener);

    /**
     * 根据配置组名获得配置信息
     *
     * @param groupName 配置组名
     * @param tag       网络请求TAG标记
     * @param listener  回调操作
     */
    void getConfigsByGroupName(String groupName, Object tag, FutureListener listener);

    /**
     * 获取资源域
     *
     * @param params   开放式的请求参数配置 例如:params.toJson = {}
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getDomainsByFilter(BaseParams params, Object tag, FutureListener listener);

    /**
     * 获取资源域
     *
     * @param params   开放式的请求参数配置 例如:params = "{}"
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getDomainsByFilter(String params, Object tag, FutureListener listener);

    /**
     * 获取最新的未读消息
     *
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getLatestMessage(Object tag, FutureListener listener);

    /**
     * 获取全部消息列表
     *
     * @param tag      网络请求TAG标记
     * @param listener 回调操作
     */
    void getAllMessageList(Object tag, FutureListener listener);
}
