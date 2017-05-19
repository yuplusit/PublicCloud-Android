package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class AttrBean extends BaseBean {

    private String              domainPath;
    private int                 id;
    private String              label;
    private String              createTime;
    private String              modifyTime;
    private String              name;
    private String              description;
    private String              icon;
    private boolean             canEdit;
    private boolean             noSave;
    private long                uid;
    private String              dataType;
    private long                modelId;
    private String              sourceValue;
    private String              attrType;
    private int                 attrTypeSort;
    private Map<String, String> values;

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isNoSave() {
        return noSave;
    }

    public void setNoSave(boolean noSave) {
        this.noSave = noSave;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public int getAttrTypeSort() {
        return attrTypeSort;
    }

    public void setAttrTypeSort(int attrTypeSort) {
        this.attrTypeSort = attrTypeSort;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.domainPath);
        dest.writeInt(this.id);
        dest.writeString(this.label);
        dest.writeString(this.createTime);
        dest.writeString(this.modifyTime);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.icon);
        dest.writeByte(this.canEdit ? (byte) 1 : (byte) 0);
        dest.writeByte(this.noSave ? (byte) 1 : (byte) 0);
        dest.writeLong(this.uid);
        dest.writeString(this.dataType);
        dest.writeLong(this.modelId);
        dest.writeString(this.sourceValue);
        dest.writeString(this.attrType);
        dest.writeInt(this.attrTypeSort);
        dest.writeInt(this.values.size());
        for (Map.Entry<String, String> entry : this.values.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public AttrBean() {
    }

    protected AttrBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readInt();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
        this.canEdit = in.readByte() != 0;
        this.noSave = in.readByte() != 0;
        this.uid = in.readLong();
        this.dataType = in.readString();
        this.modelId = in.readLong();
        this.sourceValue = in.readString();
        this.attrType = in.readString();
        this.attrTypeSort = in.readInt();
        int valuesSize = in.readInt();
        this.values = new HashMap<String, String>(valuesSize);
        for (int i = 0; i < valuesSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.values.put(key, value);
        }
    }

    public static final Creator<AttrBean> CREATOR = new Creator<AttrBean>() {
        @Override
        public AttrBean createFromParcel(Parcel source) {
            return new AttrBean(source);
        }

        @Override
        public AttrBean[] newArray(int size) {
            return new AttrBean[size];
        }
    };
}
