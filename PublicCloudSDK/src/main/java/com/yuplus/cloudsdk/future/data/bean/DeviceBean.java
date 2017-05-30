package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class DeviceBean extends BaseBean {

    private String domainPath;
    private long id;
    private String label;
    private String createTime;
    private String modifyTime;

    private ValuesBean values;
    private long gatewayId;
    private long customerId;
    private long projectId;
    private String externalDevId;
    private long modelId;
    private String category;
    private String domains;
    private String sn;
    private String activeTime;
    private String managedStatus;

    private PhysicalConfigBean physicalConfig;

    private String customerName;
    private int severity;
    private int onlineStatus;


    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public ValuesBean getValues() {
        return values;
    }

    public void setValues(ValuesBean values) {
        this.values = values;
    }

    public long getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(long gatewayId) {
        this.gatewayId = gatewayId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getExternalDevId() {
        return externalDevId;
    }

    public void setExternalDevId(String externalDevId) {
        this.externalDevId = externalDevId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getManagedStatus() {
        return managedStatus;
    }

    public void setManagedStatus(String managedStatus) {
        this.managedStatus = managedStatus;
    }

    public PhysicalConfigBean getPhysicalConfig() {
        return physicalConfig;
    }

    public void setPhysicalConfig(PhysicalConfigBean physicalConfig) {
        this.physicalConfig = physicalConfig;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public static class ValuesBean extends BaseBean {
        private String modelType;
        private String series;
        private String modelNo;

        public String getModelType() {
            return modelType;
        }

        public void setModelType(String modelType) {
            this.modelType = modelType;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getModelNo() {
            return modelNo;
        }

        public void setModelNo(String modelNo) {
            this.modelNo = modelNo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.modelType);
            dest.writeString(this.series);
            dest.writeString(this.modelNo);
        }

        public ValuesBean() {
        }

        protected ValuesBean(Parcel in) {
            this.modelType = in.readString();
            this.series = in.readString();
            this.modelNo = in.readString();
        }

        public static final Creator<ValuesBean> CREATOR = new Creator<ValuesBean>() {
            @Override
            public ValuesBean createFromParcel(Parcel source) {
                return new ValuesBean(source);
            }

            @Override
            public ValuesBean[] newArray(int size) {
                return new ValuesBean[size];
            }
        };
    }

    public static class PhysicalConfigBean extends BaseBean {
        private boolean autoActive;
        private int stationNo;

        private AnalysisConfigsBean analysisConfigs;
        private String accessProtocol;
        private String analysisProtocol;

        public boolean isAutoActive() {
            return autoActive;
        }

        public void setAutoActive(boolean autoActive) {
            this.autoActive = autoActive;
        }

        public int getStationNo() {
            return stationNo;
        }

        public void setStationNo(int stationNo) {
            this.stationNo = stationNo;
        }

        public AnalysisConfigsBean getAnalysisConfigs() {
            return analysisConfigs;
        }

        public void setAnalysisConfigs(AnalysisConfigsBean analysisConfigs) {
            this.analysisConfigs = analysisConfigs;
        }

        public String getAccessProtocol() {
            return accessProtocol;
        }

        public void setAccessProtocol(String accessProtocol) {
            this.accessProtocol = accessProtocol;
        }

        public String getAnalysisProtocol() {
            return analysisProtocol;
        }

        public void setAnalysisProtocol(String analysisProtocol) {
            this.analysisProtocol = analysisProtocol;
        }

        public static class AnalysisConfigsBean extends BaseBean {
            private String byteOrder16;
            private String byteOrder32;
            private String floatbyteOrder;

            public String getByteOrder16() {
                return byteOrder16;
            }

            public void setByteOrder16(String byteOrder16) {
                this.byteOrder16 = byteOrder16;
            }

            public String getByteOrder32() {
                return byteOrder32;
            }

            public void setByteOrder32(String byteOrder32) {
                this.byteOrder32 = byteOrder32;
            }

            public String getFloatbyteOrder() {
                return floatbyteOrder;
            }

            public void setFloatbyteOrder(String floatbyteOrder) {
                this.floatbyteOrder = floatbyteOrder;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.byteOrder16);
                dest.writeString(this.byteOrder32);
                dest.writeString(this.floatbyteOrder);
            }

            public AnalysisConfigsBean() {
            }

            protected AnalysisConfigsBean(Parcel in) {
                this.byteOrder16 = in.readString();
                this.byteOrder32 = in.readString();
                this.floatbyteOrder = in.readString();
            }

            public static final Creator<AnalysisConfigsBean> CREATOR = new Creator<AnalysisConfigsBean>() {
                @Override
                public AnalysisConfigsBean createFromParcel(Parcel source) {
                    return new AnalysisConfigsBean(source);
                }

                @Override
                public AnalysisConfigsBean[] newArray(int size) {
                    return new AnalysisConfigsBean[size];
                }
            };
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.autoActive ? (byte) 1 : (byte) 0);
            dest.writeInt(this.stationNo);
            dest.writeParcelable(this.analysisConfigs, flags);
            dest.writeString(this.accessProtocol);
            dest.writeString(this.analysisProtocol);
        }

        public PhysicalConfigBean() {
        }

        protected PhysicalConfigBean(Parcel in) {
            this.autoActive = in.readByte() != 0;
            this.stationNo = in.readInt();
            this.analysisConfigs = in.readParcelable(AnalysisConfigsBean.class.getClassLoader());
            this.accessProtocol = in.readString();
            this.analysisProtocol = in.readString();
        }

        public static final Creator<PhysicalConfigBean> CREATOR = new Creator<PhysicalConfigBean>() {
            @Override
            public PhysicalConfigBean createFromParcel(Parcel source) {
                return new PhysicalConfigBean(source);
            }

            @Override
            public PhysicalConfigBean[] newArray(int size) {
                return new PhysicalConfigBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.domainPath);
        dest.writeLong(this.id);
        dest.writeString(this.label);
        dest.writeString(this.createTime);
        dest.writeString(this.modifyTime);
        dest.writeParcelable(this.values, flags);
        dest.writeLong(this.gatewayId);
        dest.writeLong(this.customerId);
        dest.writeLong(this.projectId);
        dest.writeString(this.externalDevId);
        dest.writeLong(this.modelId);
        dest.writeString(this.category);
        dest.writeString(this.domains);
        dest.writeString(this.sn);
        dest.writeString(this.activeTime);
        dest.writeString(this.managedStatus);
        dest.writeString(this.customerName);
        dest.writeInt(this.severity);
        dest.writeInt(this.onlineStatus);
        dest.writeParcelable(this.physicalConfig, flags);
    }

    public DeviceBean() {
    }

    protected DeviceBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.values = in.readParcelable(ValuesBean.class.getClassLoader());
        this.gatewayId = in.readLong();
        this.customerId = in.readLong();
        this.projectId = in.readLong();
        this.externalDevId = in.readString();
        this.modelId = in.readLong();
        this.category = in.readString();
        this.domains = in.readString();
        this.sn = in.readString();
        this.activeTime = in.readString();
        this.managedStatus = in.readString();
        this.customerName = in.readString();
        this.severity = in.readInt();
        this.onlineStatus = in.readInt();
        this.physicalConfig = in.readParcelable(PhysicalConfigBean.class.getClassLoader());
    }

    public static final Creator<DeviceBean> CREATOR = new Creator<DeviceBean>() {
        @Override
        public DeviceBean createFromParcel(Parcel source) {
            return new DeviceBean(source);
        }

        @Override
        public DeviceBean[] newArray(int size) {
            return new DeviceBean[size];
        }
    };
}
