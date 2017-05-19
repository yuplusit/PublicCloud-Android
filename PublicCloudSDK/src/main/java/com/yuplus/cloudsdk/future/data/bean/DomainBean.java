package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class DomainBean extends BaseBean {

    private String domainPath;
    private long   id;
    private String label;
    private String createTime;
    private String modifyTime;

    private ValuesBean   values;
    private int          gatewayId;
    private int          customerId;
    private int          projectId;
    private String       externalDevId;
    private int          modelId;
    private String       category;
    private String       domains;
    private List<String> extendDomains;
    private String       sn;
    private String       accessMode;
    private long         parentID;
    private String       name;
    private String       description;
    private int          layer;

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

    public int getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(int gatewayId) {
        this.gatewayId = gatewayId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getExternalDevId() {
        return externalDevId;
    }

    public void setExternalDevId(String externalDevId) {
        this.externalDevId = externalDevId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
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

    public List<String> getExtendDomains() {
        return extendDomains;
    }

    public void setExtendDomains(List<String> extendDomains) {
        this.extendDomains = extendDomains;
    }

    public Object getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Object getAccessMode() {
        return accessMode;
    }

    public void setAccessMode(String accessMode) {
        this.accessMode = accessMode;
    }

    public long getParentID() {
        return parentID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public static class ValuesBean extends BaseBean {
        private String standardAddress;
        private String longitude;
        private String latitude;

        public String getStandardAddress() {
            return standardAddress;
        }

        public void setStandardAddress(String standardAddress) {
            this.standardAddress = standardAddress;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.standardAddress);
            dest.writeString(this.longitude);
            dest.writeString(this.latitude);
        }

        public ValuesBean() {
        }

        protected ValuesBean(Parcel in) {
            this.standardAddress = in.readString();
            this.longitude = in.readString();
            this.latitude = in.readString();
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
        dest.writeParcelable(this.values, flags);
        dest.writeInt(this.gatewayId);
        dest.writeInt(this.customerId);
        dest.writeInt(this.projectId);
        dest.writeString(this.externalDevId);
        dest.writeInt(this.modelId);
        dest.writeString(this.category);
        dest.writeString(this.domains);
        dest.writeStringList(this.extendDomains);
        dest.writeString(this.sn);
        dest.writeString(this.accessMode);
        dest.writeLong(this.parentID);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.layer);
    }

    public DomainBean() {
    }

    protected DomainBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.values = in.readParcelable(ValuesBean.class.getClassLoader());
        this.gatewayId = in.readInt();
        this.customerId = in.readInt();
        this.projectId = in.readInt();
        this.externalDevId = in.readString();
        this.modelId = in.readInt();
        this.category = in.readString();
        this.domains = in.readString();
        this.extendDomains = in.createStringArrayList();
        this.sn = in.readString();
        this.accessMode = in.readString();
        this.parentID = in.readLong();
        this.name = in.readString();
        this.description = in.readParcelable(Object.class.getClassLoader());
        this.layer = in.readInt();
    }

    public static final Creator<DomainBean> CREATOR = new Creator<DomainBean>() {
        @Override
        public DomainBean createFromParcel(Parcel source) {
            return new DomainBean(source);
        }

        @Override
        public DomainBean[] newArray(int size) {
            return new DomainBean[size];
        }
    };
}
