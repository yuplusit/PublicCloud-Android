package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class CustomerBean extends BaseBean {

    private String domainPath;
    private long   id;
    private String label;
    private String createTime;
    private String modifyTime;
    private String orCondition;
    private String conditionField;

    private ValuesBean values;
    private String     customerName;
    private String     duty;
    private String     customerContact;
    private String     customerPhone;
    private String     customerEmail;
    private String     customerAddress;
    private String     description;
    private String     risingTime;

    //后拼接
    private int    deviceCount;
    private int    alertCount;
    private int    orderCount;
    private String imageUrl;

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

    public Object getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Object getOrCondition() {
        return orCondition;
    }

    public void setOrCondition(String orCondition) {
        this.orCondition = orCondition;
    }

    public Object getConditionField() {
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        dest.writeString(this.customerName);
        dest.writeString(this.duty);
        dest.writeString(this.customerContact);
        dest.writeString(this.customerPhone);
        dest.writeString(this.customerEmail);
        dest.writeString(this.customerAddress);
        dest.writeString(this.description);
        dest.writeString(this.risingTime);
        dest.writeInt(this.deviceCount);
        dest.writeInt(this.alertCount);
        dest.writeInt(this.orderCount);
        dest.writeString(this.imageUrl);
    }

    public CustomerBean() {
    }

    protected CustomerBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.orCondition = in.readString();
        this.conditionField = in.readString();
        this.values = in.readParcelable(ValuesBean.class.getClassLoader());
        this.customerName = in.readString();
        this.duty = in.readString();
        this.customerContact = in.readString();
        this.customerPhone = in.readString();
        this.customerEmail = in.readString();
        this.customerAddress = in.readString();
        this.description = in.readString();
        this.risingTime = in.readString();
        this.deviceCount = in.readInt();
        this.alertCount = in.readInt();
        this.orderCount = in.readInt();
        this.imageUrl = in.readString();
    }

    public static final Creator<CustomerBean> CREATOR = new Creator<CustomerBean>() {
        @Override
        public CustomerBean createFromParcel(Parcel source) {
            return new CustomerBean(source);
        }

        @Override
        public CustomerBean[] newArray(int size) {
            return new CustomerBean[size];
        }
    };
}
