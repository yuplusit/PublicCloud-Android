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
    private int                 uid;
    private int                 modelId;
    private String              modelIdList;
    private int                 granularity;
    private String              granularityUnit;
    private String              unit;
    private int                 saveInterval;
    private int                 keepPeriod;
    private int                 baseKpiId;
    private int                 timeDeviation;
    private int                 instance;
    private boolean             compress;
    private int                 compressTime;
    private int                 deadZoneRange;
    private boolean             interval;
    private int                 intervalTime;
    private int                 serial;
    private boolean             tagModel;
    private boolean             number;
    private boolean             kpi;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
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

    public int getBaseKpiId() {
        return baseKpiId;
    }

    public void setBaseKpiId(int baseKpiId) {
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

    public int getCompressTime() {
        return compressTime;
    }

    public void setCompressTime(int compressTime) {
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

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
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
        dest.writeInt(this.uid);
        dest.writeInt(this.modelId);
        dest.writeString(this.modelIdList);
        dest.writeInt(this.granularity);
        dest.writeString(this.granularityUnit);
        dest.writeString(this.unit);
        dest.writeInt(this.saveInterval);
        dest.writeInt(this.keepPeriod);
        dest.writeInt(this.baseKpiId);
        dest.writeInt(this.timeDeviation);
        dest.writeInt(this.instance);
        dest.writeByte(this.compress ? (byte) 1 : (byte) 0);
        dest.writeInt(this.compressTime);
        dest.writeInt(this.deadZoneRange);
        dest.writeByte(this.interval ? (byte) 1 : (byte) 0);
        dest.writeInt(this.intervalTime);
        dest.writeInt(this.serial);
        dest.writeByte(this.tagModel ? (byte) 1 : (byte) 0);
        dest.writeByte(this.number ? (byte) 1 : (byte) 0);
        dest.writeByte(this.kpi ? (byte) 1 : (byte) 0);
        dest.writeInt(this.values.size());
        for (Map.Entry<String, String> entry : this.values.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public KpiBean() {
    }

    protected KpiBean(Parcel in) {
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
        this.uid = in.readInt();
        this.modelId = in.readInt();
        this.modelIdList = in.readString();
        this.granularity = in.readInt();
        this.granularityUnit = in.readString();
        this.unit = in.readString();
        this.saveInterval = in.readInt();
        this.keepPeriod = in.readInt();
        this.baseKpiId = in.readInt();
        this.timeDeviation = in.readInt();
        this.instance = in.readInt();
        this.compress = in.readByte() != 0;
        this.compressTime = in.readInt();
        this.deadZoneRange = in.readInt();
        this.interval = in.readByte() != 0;
        this.intervalTime = in.readInt();
        this.serial = in.readInt();
        this.tagModel = in.readByte() != 0;
        this.number = in.readByte() != 0;
        this.kpi = in.readByte() != 0;
        int valuesSize = in.readInt();
        this.values = new HashMap<String, String>(valuesSize);
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
