package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class ProjectBean extends BaseBean {

    private String domainPath;
    private long   id;
    private String label;
    private String createTime;
    private String modifyTime;
    private String orCondition;
    private String conditionField;

    private ValuesBean values;
    private String     projectName;
    private int        distributorId;
    private String     distributorName;
    private String     projectAddress;
    private String     installDate;
    private String     debugFinishDate;
    private String     qualityCloseDate;
    private long       customerId;
    private String     customerName;
    private String     projectNo;
    private String     startTime;
    private String     risingTime;

    //需拼接
    private int deviceCount;
    private int alertCount;
    private int orderCount;

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

    public String getOrCondition() {
        return orCondition;
    }

    public void setOrCondition(String orCondition) {
        this.orCondition = orCondition;
    }

    public String getConditionField() {
        return conditionField;
    }

    public void setConditionField(String conditionField) {
        this.conditionField = conditionField;
    }

    public ValuesBean getValues() {
        return values;
    }

    public void setValues(ValuesBean values) {
        this.values = values;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getDebugFinishDate() {
        return debugFinishDate;
    }

    public void setDebugFinishDate(String debugFinishDate) {
        this.debugFinishDate = debugFinishDate;
    }

    public String getQualityCloseDate() {
        return qualityCloseDate;
    }

    public void setQualityCloseDate(String qualityCloseDate) {
        this.qualityCloseDate = qualityCloseDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRisingTime() {
        return risingTime;
    }

    public void setRisingTime(String risingTime) {
        this.risingTime = risingTime;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public int getAlertCount() {
        return alertCount;
    }

    public void setAlertCount(int alertCount) {
        this.alertCount = alertCount;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public static class ValuesBean extends BaseBean {
        private String standardAddress;
        private double longitude;
        private double latitude;

        public String getStandardAddress() {
            return standardAddress;
        }

        public void setStandardAddress(String standardAddress) {
            this.standardAddress = standardAddress;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.standardAddress);
            dest.writeDouble(this.longitude);
            dest.writeDouble(this.latitude);
        }

        public ValuesBean() {
        }

        protected ValuesBean(Parcel in) {
            this.standardAddress = in.readString();
            this.longitude = in.readDouble();
            this.latitude = in.readDouble();
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
        dest.writeString(this.orCondition);
        dest.writeString(this.conditionField);
        dest.writeParcelable(this.values, flags);
        dest.writeString(this.projectName);
        dest.writeInt(this.distributorId);
        dest.writeString(this.distributorName);
        dest.writeString(this.projectAddress);
        dest.writeString(this.installDate);
        dest.writeString(this.debugFinishDate);
        dest.writeString(this.qualityCloseDate);
        dest.writeLong(this.customerId);
        dest.writeString(this.customerName);
        dest.writeString(this.projectNo);
        dest.writeString(this.startTime);
        dest.writeString(this.risingTime);
        dest.writeInt(this.deviceCount);
        dest.writeInt(this.alertCount);
        dest.writeInt(this.orderCount);
    }

    public ProjectBean() {
    }

    protected ProjectBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.orCondition = in.readString();
        this.conditionField = in.readString();
        this.values = in.readParcelable(ValuesBean.class.getClassLoader());
        this.projectName = in.readString();
        this.distributorId = in.readInt();
        this.distributorName = in.readString();
        this.projectAddress = in.readString();
        this.installDate = in.readString();
        this.debugFinishDate = in.readString();
        this.qualityCloseDate = in.readString();
        this.customerId = in.readLong();
        this.customerName = in.readString();
        this.projectNo = in.readString();
        this.startTime = in.readString();
        this.risingTime = in.readString();
        this.deviceCount = in.readInt();
        this.alertCount = in.readInt();
        this.orderCount = in.readInt();
    }

    public static final Creator<ProjectBean> CREATOR = new Creator<ProjectBean>() {
        @Override
        public ProjectBean createFromParcel(Parcel source) {
            return new ProjectBean(source);
        }

        @Override
        public ProjectBean[] newArray(int size) {
            return new ProjectBean[size];
        }
    };
}
