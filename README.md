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

请转至Wiki

## 常用工具



## 常见问题



## 通用参考

