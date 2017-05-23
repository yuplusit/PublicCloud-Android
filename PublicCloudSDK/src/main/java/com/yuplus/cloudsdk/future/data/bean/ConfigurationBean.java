package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/23/2017
 * @desc
 */

public class ConfigurationBean extends BaseBean {

    private String domainPath;
    private long    id;
    private String  label;
    private String  createTime;
    private String  modifyTime;
    private String  key;
    private String  value;
    private Long  userId;
    private String  groupName;
    private String  keyDesc;
    private boolean invalid;
    private boolean canDelete;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
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
        dest.writeString(this.key);
        dest.writeString(this.value);
        dest.writeValue(this.userId);
        dest.writeString(this.groupName);
        dest.writeString(this.keyDesc);
        dest.writeByte(this.invalid ? (byte) 1 : (byte) 0);
        dest.writeByte(this.canDelete ? (byte) 1 : (byte) 0);
    }

    public ConfigurationBean() {
    }

    protected ConfigurationBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.key = in.readString();
        this.value = in.readString();
        this.userId = (Long) in.readValue(Long.class.getClassLoader());
        this.groupName = in.readString();
        this.keyDesc = in.readString();
        this.invalid = in.readByte() != 0;
        this.canDelete = in.readByte() != 0;
    }

    public static final Creator<ConfigurationBean> CREATOR = new Creator<ConfigurationBean>() {
        @Override
        public ConfigurationBean createFromParcel(Parcel source) {
            return new ConfigurationBean(source);
        }

        @Override
        public ConfigurationBean[] newArray(int size) {
            return new ConfigurationBean[size];
        }
    };
}
