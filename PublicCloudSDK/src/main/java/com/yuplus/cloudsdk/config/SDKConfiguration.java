package com.yuplus.cloudsdk.config;

/**
 * @user longzhen
 * @date 5/10/2017
 * @desc SDK相关配置
 */

public class SDKConfiguration {
    final String  appName;
    final boolean debug;
    final String  httpHost;
    final int     httpPort;
    final String  basePath;
    final long    readTimeout;
    final long    writeTimeout;
    final long    connTimeout;
    final String  deviceId;
    final int     versionCode;
    final int     versionName;
    final int     osVersion;

    public SDKConfiguration(Builder builder) {
        this.appName = builder.appName;
        this.debug = builder.debug;
        this.httpHost = builder.httpHost;
        this.httpPort = builder.httpPort;
        this.basePath = builder.basePath;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
        this.connTimeout = builder.connTimeout;
        this.deviceId = builder.deviceId;
        this.versionCode = builder.versionCode;
        this.versionName = builder.versionName;
        this.osVersion = builder.osVersion;
    }

    public String getAppName() {
        return appName;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getHttpHost() {
        return httpHost;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public String getBasePath() {
        return basePath;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public long getConnTimeout() {
        return connTimeout;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public int getVersionName() {
        return versionName;
    }

    public int getOsVersion() {
        return osVersion;
    }

    public static final class Builder {
        String  appName;
        boolean debug;
        String  httpHost;
        int     httpPort;
        String  basePath;
        long    readTimeout;
        long    writeTimeout;
        long    connTimeout;
        String  deviceId;
        int     versionCode;
        int     versionName;
        int     osVersion;

        public Builder() {
        }

        public Builder setAppName(String appName) {
            this.appName = appName;
            return this;
        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setHttpHost(String httpHost) {
            this.httpHost = httpHost;
            return this;
        }

        public Builder setHttpPort(int httpPort) {
            this.httpPort = httpPort;
            return this;
        }

        public Builder setBasePath(String basePath) {
            this.basePath = basePath;
            return this;
        }

        public Builder setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setConnTimeout(long connTimeout) {
            this.connTimeout = connTimeout;
            return this;
        }

        public Builder setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public Builder setVersionCode(int versionCode) {
            this.versionCode = versionCode;
            return this;
        }

        public Builder setVersionName(int versionName) {
            this.versionName = versionName;
            return this;
        }

        public Builder setOsVersion(int osVersion) {
            this.osVersion = osVersion;
            return this;
        }

        public SDKConfiguration build() {
            SDKConfiguration configuration = new SDKConfiguration(this);
            return configuration;
        }
    }
}
