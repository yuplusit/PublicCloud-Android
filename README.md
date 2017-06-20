# PublicCloud Android-SDK开发文档

## 修订记录

| 修改时间      | 修改内容              | 版本    | 修改人  | 备注   |
| --------- | ----------------- | ----- | ---- | ---- |
| 2017.5.23 | Android SDK文档初始版本 | 1.0.0 | 朱隆震  | 内部版本 |



## 概述

随着目前目前移动设备的普及以及互联网+的发展，越来越多的硬件设备厂商、工业云平台都提供了移动终端的支持，App作为管理平台的重要终端，
每个厂商都希望可以提供针对自己平台个性化定制的App，但是鉴于App应用开发在硬件领域的技术门槛和高昂成本，导致很多厂家是有心无力，因此我们在原有的《APP框架》基础上，进一步地推出了完整功能的App SDK和基础版App，提供了能够以最小的技术需求、最低的开发成本、最快的开发速度提供定制自己产品App终端的解决方案，并基本覆盖了主流OS系统，Android和IOS，且并不排除以后对其他系统的支持。开发者可在原有的App Sample上，通过我们提供的SDK以及基础版本云App，客户不仅可以通过简单的修改、云打包功能生成自己的通用App，也可以通过SDK高度定制自己的专属App。



## SDK文档简介

本文档详细介绍了Android-SDK和IOS-SDK的安装、开发操作、参数、示例和常见问题处理。



## Android-SDK

### 前言

#### 简介

本文档主要介绍公有云Android SDK的安装和使用。

#### 环境要求

- Android系统版本：4.0及以上
- 必须注册公有云用户账户

版本迭代详情参考 [点击查看]()

#### SDK下载

Android SDK开发包(2017-05-20)版本号1.0：[PublicCloudSDK-Android-1.0]()

- github地址:[点击查看](https://github.com/yuplusit/PublicCloud-Android)
- sample地址:[点击查看](https://github.com/yuplusit/PublicCloud-Android)

### 安装

#### 直接引入jar包

当您下载了公有云 Android SDK 的 zip 包后，进行以下步骤（仅对Android studio适用）:

- 在官网或者github上下载sdk包
- 解压后得到jar包，目前包括publiccloud-sdk-android-1.0.jar
- 将以上jar包导入到libs目录下

#### Gradle依赖

```
compile 'com.yuplus.android:cloudsdk:1.0'
```

#### 混淆设置

### 快速接入

#### Step1:权限声明（对于Android6.0以上的机型需要sdk的接入者动态申请响应的权限）

在AndroidManifest.xml中，声明以下权限：

```
<uses-permission android:name="android.permission.WRITE_SETTINGS"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WAKE_LOCK"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
<uses-permission android:name="android.permission.RESTART_PACKAGES"/>
```

#### Step2:在Application类中配置SDK所需得参数并初始化

创建OkHttpClient和SDKConfiguration对象，并自定义配置所需参数。

```
 //若协议室https，则需添加SSLParams
        HttpsCerManager.SSLParams sslParams = HttpsCerManager.getSslSocketFactory(null, null, null);
        //保存cookies值
        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar1)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
    
        SDKConfiguration configuration = new SDKConfiguration.Builder()
                .setAppName(AppCst.APP_NAME)	//App名字
                .setHttpHost(AppCst.SERVER_HOST)//服务器Host地址
                .setHttpPort(AppCst.SERVER_PORT)//端口号
                .setBasePath(AppCst.SERVER_BASE_PATH)//基础路径
                .build();
```

在CloudSDKManager和OkHttpUtils中进行初始化。

```
OkHttpUtils.getInstance(okHttpClient);

CloudSDKManager.getInstance()
                .setApplication(this)
                .initLogUtil(AppCst.APP_NAME)
                .setSdkConfiguration(configuration);
```

#### Step3：Api接入举例

首页，先获取到SDK中网络模块中得AppFutureImpl的实例

```
 AppFutureImpl  appFutureImpl = new AppFutureImpl();
```

然后以登录接口为例，在接口参数当中其中有两个参数这里得注意下：其中tag参数一般指的是调用此方法得当前对象，比如Activity、Fragment、Presenter等，根据这个tag可以取消相对应的任务Future；另外一个为回调监听listener，返回的FutureResult对象中有所需要的参数。详细介绍，请参考Api手册。

```
appFutureImpl.login(account, password, tag, new FutureListener() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                getView().onLoginSuccess((UserBean) result.getAttach());
            }

            @Override
            public void onFailure(FutureResult result) {
                super.onFailure(result);
            }
        });
```

#### Step4：获取数据

获取返回的数据，并对其进行相应的业务处理和展示

```
appFutureImpl.login(account, password, tag, new FutureListener() {

            @Override
            public void onStart() {
                super.onStart();
                getView().showLoading();
            }

            @Override
            public void onSuccess(FutureResult result) {
                super.onSuccess(result);
                //成功回掉，进行相应的业务逻辑处理
                getView().onLoginSuccess((UserBean) result.getAttach());
            }

            @Override
            public void onFailure(FutureResult result) {
                super.onFailure(result);
                Exception ex = result.getException();
                if (null != ex) {
                	//失败回调
                    getView().onLoginFailure(ex.getMessage());
                }
                getView().hideLoading();
            }
        });
```

回调返回的数据对象FutureResult包含以下信息：

| 字段名称      | 类型        | 描述             |
| :-------- | --------- | :------------- |
| code      | int       | 网络请求返回的Code值   |
| success   | boolean   | 任务是否完成         |
| attach    | Object    | 请求成功返回解析后的对象   |
| jsonData  | String    | 请求成功返回的Json字符串 |
| call      | Call      | OkHttp请求Call   |
| exception | Exception | 请求过程中发生异常的信息类  |

#### Step5：任务Future取消

 ```
OkHttpUtils.getInstance().cancel(this);//这里的this指的是之前传入的tag
 ```





### Api手册

#### login

| 功能说明 | 账户登录操作                                   |
| ---- | ---------------------------------------- |
| 方法定义 | void login(String account, String password, Object tag, FutureListener listener) |
| 参数说明 | account :账户名称; password:账户密码; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | UserResponse                             |
| 返回类型 | UserBean(包含在FutureResult的Attach对象中)      |



#### getAlertByPage

| 功能说明 | 获取不同级别的警告数据                              |
| ---- | ---------------------------------------- |
| 方法定义 | void getAlertByPage(int start, int pageSize, String severities, String states, Object tag, FutureListener listener); |
| 参数说明 | start:分页的起始位置; pageSize:分页的大小;severities:告警的严重性(1:警告;2:次要;3:重要;4:严重);states:告警的状态(0:新产生;5:已确认;10:处理中;20:已解决;30:已忽略);tag:网络请求TAG标记; listener:回调操作; |
| 举例说明 | severities="1,2,3,4";states= "0,5,10,20" |
| 解析对象 | AlertListResponse                        |
| 返回类型 | AlertListBean(包含在FutureResult的Attach对象中) |



#### getDevicesByConditionWithPage

| 功能说明 | 获取设备信息(分页)                               |
| ---- | ---------------------------------------- |
| 方法定义 | void getDevicesByConditionWithPage(int start, int pageSize, Object tag, FutureListener listener); |
| 参数说明 | start:分页的起始位置; pageSize:分页的大小; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | AlertListResponse                        |
| 返回类型 | AlertListBean(包含在FutureResult的Attach对象中) |



#### getAllDevicesByCondition

| 功能说明 | 通过条件筛选获取所有的设备信息                          |
| ---- | ---------------------------------------- |
| 方法定义 | getAllDevicesByCondition(BaseParams params, Object tag, FutureListener listener); |
| 参数说明 | params:开放式参数配置; tag:网络请求TAG标记; listener:回调操作; |
| 举例说明 | params.toJson()= [{},{"start":0,"length":10,"sort":"createTime","sortType":"desc","statCount":true}] |
| 解析对象 | AlertListResponse                        |
| 返回类型 | AlertListBean(包含在FutureResult的Attach对象中) |



#### getAllDevices

| 功能说明 | 获取所有的设备信息                                |
| ---- | ---------------------------------------- |
| 方法定义 | getAllDevices(String domainPath, Object tag,FutureListener listener); |
| 参数说明 | domainPath;域路径; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | AlertListResponse                        |
| 返回类型 | AlertListBean(包含在FutureResult的Attach对象中) |



#### getCustomerById

| 功能说明 | 通过id来获取客户的信息                             |
| ---- | ---------------------------------------- |
| 方法定义 | getCustomerById(String customerId, Object tag, FutureListener listener); |
| 参数说明 | customerId:客户ID; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | CustomerReponse                          |
| 返回类型 | CustomerBean(包含在FutureResult的Attach对象中)  |



#### getAllCustomerInfoList

| 功能说明 | 获取所有的客户信息                                |
| ---- | ---------------------------------------- |
| 方法定义 | getAllCustomerInfoList(Object tag,FutureListener listener); |
| 参数说明 | alertId:告警ID; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | CustomerListResponse                     |
| 返回类型 | List<CustomerBean>(包含在FutureResult的Attach对象中) |



#### sendAlertRecoverAction

| 功能说明 | 关闭告警                                     |
| ---- | ---------------------------------------- |
| 方法定义 | sendAlertRecoverAction(String alertId, Object tag, FutureListener listener); |
| 参数说明 | alertId:告警ID; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | AlertActionResponse                      |
| 返回类型 | AlertActionBean(包含在FutureResult的Attach对象中) |



#### sendAlertClaimAction

| 功能说明 | 确认警告                                     |
| ---- | ---------------------------------------- |
| 方法定义 | sendAlertClaimAction(String alertId, Object tag, FutureListener listener); |
| 参数说明 | alertId:告警ID; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | AlertActionResponse                      |
| 返回类型 | AlertActionBean(包含在FutureResult的Attach对象中) |



#### deviceActivateGateway

| 功能说明 | 启用设备                                     |
| ---- | ---------------------------------------- |
| 方法定义 | deviceActivateGateway(String deviceId, Object tag, FutureListener listener); |
| 参数说明 | deviceId:设备ID; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | DeviceResponse                           |
| 返回类型 | DeviceBean(包含在FutureResult的Attach对象中)    |



#### deviceDeactivateGateway

| 功能说明 | 停用设备                                     |
| ---- | ---------------------------------------- |
| 方法定义 | deviceDeactivateGateway(String deviceId, Object tag, FutureListener listener); |
| 参数说明 | deviceId:设备ID; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | DeviceResponse                           |
| 返回类型 | DeviceBean(包含在FutureResult的Attach对象中)    |



#### findProjectsByCondition

| 功能说明 | 通过条件配置查找项目信息                             |
| ---- | ---------------------------------------- |
| 方法定义 | findProjectsByCondition(BaseParams params, Object tag, FutureListener listener); |
| 参数说明 | params:开放式的请求参数配置;tag:网络请求TAG标记; listener:回调操作; |
| 举例说明 | params.toJson()="{\"customerId\":\"45845255441\"}" |
| 解析对象 | ProjectListBean                          |
| 返回类型 | List<ProjectBean>(包含在FutureResult的Attach对象中) |



#### findProjectsByCustomerId

| 功能说明 | 通过客户ID查找项目信息                             |
| ---- | ---------------------------------------- |
| 方法定义 | findProjectsByCustomerId(String customerId, Object tag, FutureListener listener); |
| 参数说明 | customerId:客户ID; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | ProjectListResponse                      |
| 返回类型 | List<ProjectBean>(包含在FutureResult的Attach对象中) |



#### findTicketsByCondition

| 功能说明 | 通过条件筛选查找工单列表                             |
| ---- | ---------------------------------------- |
| 方法定义 | findTicketsByCondition(BaseParams params, Object tag, FutureListener listener); |
| 参数说明 | params:开放式的请求参数配置; tag:网络请求TAG标记; listener:回调操作; |
| 举例说明 | params.toJson()=“[{\"status\": 100}]”    |
| 解析对象 | ProjectListResponse                      |
| 返回类型 | List<ProjectBean>(包含在FutureResult的Attach对象中) |



| 功能说明 | 通过工单状态查找工单列表                             |
| ---- | ---------------------------------------- |
| 方法定义 | findTicketsByCondition(int status, Object tag, FutureListener listener); |
| 参数说明 | status:工单状态; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | ProjectListResponse                      |
| 返回类型 | List<ProjectBean>(包含在FutureResult的Attach对象中) |



#### getKpiValueList

| 功能说明 | 获取系统各级别KPI/KQI数据                         |
| ---- | ---------------------------------------- |
| 方法定义 | getKpiValueList(BaseParams kpiQueryModel, Object tag,FutureListener listener); |
| 参数说明 | kpiQueryModel:开放式的请求参数配置;tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | KpiValueListResponse                     |
| 返回类型 | List<KpiValueBean>(包含在FutureResult的Attach对象中) |



#### getModelByIds

| 功能说明 | 通过模型IDS列表获取模型                            |
| ---- | ---------------------------------------- |
| 方法定义 | getModelByIds(List<String> ids, Object tag,FutureListener listener); |
| 参数说明 | ids:模型id列表; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | ModelListResponse                        |
| 返回类型 | List<ModelBean>(包含在FutureResult的Attach对象中) |



#### getKpisByModelId

| 功能说明 | 通过模型ID列表获取模型KPIS                         |
| ---- | ---------------------------------------- |
| 方法定义 | void getKpisByModelId(String modeId, Object tag, FutureListener listener); |
| 参数说明 | modeId:模型id; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | KpiListResponse                          |
| 返回类型 | KpiBean(包含在FutureResult的Attach对象中)       |



#### getAttrsByModelId

| 功能说明 | 通过模型ID列表获取模型属性定义                         |
| ---- | ---------------------------------------- |
| 方法定义 | void getAttrsByModelId(String modeId, Object tag, FutureListener listener); |
| 参数说明 | modeId:模型id; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | AttrListResponse                         |
| 返回类型 | List<AttrBean>(包含在FutureResult的Attach对象中) |



#### getAllUnits

| 功能说明 | 获得系统内单位的定义                               |
| ---- | ---------------------------------------- |
| 方法定义 | void getAllUnits(Object tag, FutureListener listener); |
| 参数说明 | tag:网络请求TAG标记; listener:回调操作;            |
| 解析对象 | UnitListResponse                         |
| 返回类型 | List<UnitBean>(包含在FutureResult的Attach对象中) |



#### getConfigsByGroupName

| 功能说明 | 根据配置组名获得配置信息                             |
| ---- | ---------------------------------------- |
| 方法定义 | getConfigsByGroupName(String groupName, Object tag, FutureListener listener); |
| 参数说明 | groupName:配置组名; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | ConfigurationListResponse                |
| 返回类型 | List<ConfigurationBean>(包含在FutureResult的Attach对象中) |



#### getDomainsByFilter

| 功能说明 | 获取资源域                                    |
| ---- | ---------------------------------------- |
| 方法定义 | void getDomainsByFilter(BaseParams params, Object tag, FutureListener listener); |
| 参数说明 | params:请求参数配置; tag:网络请求TAG标记; listener:回调操作; |
| 解析对象 | DomainListResponse                       |
| 返回类型 | List<DomainBean>(包含在FutureResult的Attach对象中) |



#### getLatestMessage

| 功能说明 | 获取最新的未读消息                                |
| ---- | ---------------------------------------- |
| 方法定义 | getLatestMessage(Object tag, FutureListener listener); |
| 参数说明 | tag:网络请求TAG标记; listener:回调操作;            |
| 解析对象 | MessageListResponse                      |
| 返回类型 | List<MessageWrapperBean>(包含在FutureResult的Attach对象中) |



#### getAllMessageList

| 功能说明 | 获取全部消息列表                                 |
| ---- | ---------------------------------------- |
| 方法定义 | getAllMessageList(Object tag, FutureListener listener); |
| 参数说明 | tag:网络请求TAG标记; listener:回调操作;            |
| 解析对象 | MessageListResponse                      |
| 返回类型 | List&lt;MessageWrapperBean&gt;(包含在FutureResult的Attach对象中) |



### 错误异常处理

1、Session过期，需要重新登录错误

当请求返回的code=10020时，SDK会进行 错误处理，会发出Action=com.yuplus.cloudsdk.future.Action.LOGIN_AGAIN_ACTION的广播，需要重新登录的话，可在广播中接收这个Action或者在FutureListener的onFailure()中进行处理。

​	



## 常用工具



## 常见问题



## 通用参考

