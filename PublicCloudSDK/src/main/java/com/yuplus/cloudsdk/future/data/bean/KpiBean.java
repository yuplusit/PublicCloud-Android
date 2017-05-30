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

public class KpiBean extends BaseBean {

    private String domainPath;
    private long id;
    private String label;
    private String createTime;
    private String modifyTime;
    private String name;
    private String description;
    private String icon;
    private boolean canEdit;
    private boolean noSave;
    private long uid;
    private long modelId;
    private String modelIdList;
    private int granularity;
    private String granularityUnit;
    private String unit;
    private int saveInterval;
    private int keepPeriod;
    private long baseKpiId;
    private int timeDeviation;
    private int instance;
    private boolean compress;
    private long compressTime;
    private int deadZoneRange;
    private boolean interval;
    private long intervalTime;
    private long serial;
    private boolean tagModel;
    private boolean number;
    private boolean kpi;
    private Map<String, String> values;

    //需后面拼接
    private long value;
    private String arisingTime;
    private long nodeId;

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

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public String getModelIdList() {
        return modelIdList;
    }

    public void setModelIdList(String modelIdList) {
        this.modelIdList = modelIdList;
    }

    public int getGranularity() {
        return granularity;
    }

    public void setGranularity(int granularity) {
        this.granularity = granularity;
    }

    public String getGranularityUnit() {
        return granularityUnit;
    }

    public void setGranularityUnit(String granularityUnit) {
        this.granularityUnit = granularityUnit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getSaveInterval() {
        return saveInterval;
    }

    public void setSaveInterval(int saveInterval) {
        this.saveInterval = saveInterval;
    }

    public int getKeepPeriod() {
        return keepPeriod;
    }

    public void setKeepPeriod(int keepPeriod) {
        this.keepPeriod = keepPeriod;
    }

    public long getBaseKpiId() {
        return baseKpiId;
    }

    public void setBaseKpiId(long baseKpiId) {
        this.baseKpiId = baseKpiId;
    }

    public int getTimeDeviation() {
        return timeDeviation;
    }

    public void setTimeDeviation(int timeDeviation) {
        this.timeDeviation = timeDeviation;
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    public boolean isCompress() {
        return compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    public long getCompressTime() {
        return compressTime;
    }

    public void setCompressTime(long compressTime) {
        this.compressTime = compressTime;
    }

    public int getDeadZoneRange() {
        return deadZoneRange;
    }

    public void setDeadZoneRange(int deadZoneRange) {
        this.deadZoneRange = deadZoneRange;
    }

    public boolean isInterval() {
        return interval;
    }

    public void setInterval(boolean interval) {
        this.interval = interval;
    }

    public long getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public boolean isTagModel() {
        return tagModel;
    }

    public void setTagModel(boolean tagModel) {
        this.tagModel = tagModel;
    }

    public boolean isNumber() {
        return number;
    }

    public void setNumber(boolean number) {
        this.number = number;
    }

    public boolean isKpi() {
        return kpi;
    }

    public void setKpi(boolean kpi) {
        this.kpi = kpi;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getArisingTime() {
        return arisingTime;
    }

    public void setArisingTime(String arisingTime) {
        this.arisingTime = arisingTime;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
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
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.icon);
        dest.writeByte(this.canEdit ? (byte) 1 : (byte) 0);
        dest.writeByte(this.noSave ? (byte) 1 : (byte) 0);
        dest.writeLong(this.uid);
        dest.writeLong(this.modelId);
        dest.writeString(this.modelIdList);
        dest.writeInt(this.granularity);
        dest.writeString(this.granularityUnit);
        dest.writeString(this.unit);
        dest.writeInt(this.saveInterval);
        dest.writeInt(this.keepPeriod);
        dest.writeLong(this.baseKpiId);
        dest.writeInt(this.timeDeviation);
        dest.writeInt(this.instance);
        dest.writeByte(this.compress ? (byte) 1 : (byte) 0);
        dest.writeLong(this.compressTime);
        dest.writeInt(this.deadZoneRange);
        dest.writeByte(this.interval ? (byte) 1 : (byte) 0);
        dest.writeLong(this.intervalTime);
        dest.writeLong(this.serial);
        dest.writeByte(this.tagModel ? (byte) 1 : (byte) 0);
        dest.writeByte(this.number ? (byte) 1 : (byte) 0);
        dest.writeByte(this.kpi ? (byte) 1 : (byte) 0);
        dest.writeInt(this.values.size());
        dest.writeLong(this.value);
        dest.writeString(this.arisingTime);
        dest.writeLong(this.nodeId);
        for (Map.Entry<String, String> entry : this.values.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public KpiBean() {
    }

    protected KpiBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
        this.canEdit = in.readByte() != 0;
        this.noSave = in.readByte() != 0;
        this.uid = in.readLong();
        this.modelId = in.readLong();
        this.modelIdList = in.readString();
        this.granularity = in.readInt();
        this.granularityUnit = in.readString();
        this.unit = in.readString();
        this.saveInterval = in.readInt();
        this.keepPeriod = in.readInt();
        this.baseKpiId = in.readLong();
        this.timeDeviation = in.readInt();
        this.instance = in.readInt();
        this.compress = in.readByte() != 0;
        this.compressTime = in.readLong();
        this.deadZoneRange = in.readInt();
        this.interval = in.readByte() != 0;
        this.intervalTime = in.readLong();
        this.serial = in.readLong();
        this.tagModel = in.readByte() != 0;
        this.number = in.readByte() != 0;
        this.kpi = in.readByte() != 0;
        int valuesSize = in.readInt();
        this.values = new HashMap<String, String>(valuesSize);
        this.value = in.readLong();
        this.arisingTime = in.readString();
        this.nodeId = in.readLong();
        for (int i = 0; i < valuesSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.values.put(key, value);
        }
    }

    public static final Creator<KpiBean> CREATOR = new Creator<KpiBean>() {
        @Override
        public KpiBean createFromParcel(Parcel source) {
            return new KpiBean(source);
        }

        @Override
        public KpiBean[] newArray(int size) {
            return new KpiBean[size];
        }
    };
}
