package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class KpiValueBean extends BaseBean {

    private long nodeId;
    private int    kpiCode;
    private int    quality;
    private int    value;
    private String arisingTime;
    private String insertTime;
    private String agentId;
    private String valueStr;
    private int    resourceId;
    private int    computeTaskId;
    private int    dataSerialNumber;
    private String dataTime;
    private String granularityUnit;
    private String aggregateType;
    private String aggregateStatus;
    private String forecastMethod;
    private Integer compressCount;
    private String domainPath;
    private int    modelId;
    private String kpiName;
    private String extendDomains;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public int getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(int kpiCode) {
        this.kpiCode = kpiCode;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getArisingTime() {
        return arisingTime;
    }

    public void setArisingTime(String arisingTime) {
        this.arisingTime = arisingTime;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getComputeTaskId() {
        return computeTaskId;
    }

    public void setComputeTaskId(int computeTaskId) {
        this.computeTaskId = computeTaskId;
    }

    public int getDataSerialNumber() {
        return dataSerialNumber;
    }

    public void setDataSerialNumber(int dataSerialNumber) {
        this.dataSerialNumber = dataSerialNumber;
    }

    public Object getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getGranularityUnit() {
        return granularityUnit;
    }

    public void setGranularityUnit(String granularityUnit) {
        this.granularityUnit = granularityUnit;
    }

    public Object getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public Object getAggregateStatus() {
        return aggregateStatus;
    }

    public void setAggregateStatus(String aggregateStatus) {
        this.aggregateStatus = aggregateStatus;
    }

    public String getForecastMethod() {
        return forecastMethod;
    }

    public void setForecastMethod(String forecastMethod) {
        this.forecastMethod = forecastMethod;
    }

    public Object getCompressCount() {
        return compressCount;
    }

    public void setCompressCount(Integer compressCount) {
        this.compressCount = compressCount;
    }

    public Object getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public Object getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public Object getExtendDomains() {
        return extendDomains;
    }

    public void setExtendDomains(String extendDomains) {
        this.extendDomains = extendDomains;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.nodeId);
        dest.writeInt(this.kpiCode);
        dest.writeInt(this.quality);
        dest.writeInt(this.value);
        dest.writeString(this.arisingTime);
        dest.writeString(this.insertTime);
        dest.writeString(this.agentId);
        dest.writeString(this.valueStr);
        dest.writeInt(this.resourceId);
        dest.writeInt(this.computeTaskId);
        dest.writeInt(this.dataSerialNumber);
        dest.writeString(this.dataTime);
        dest.writeString(this.granularityUnit);
        dest.writeString(this.aggregateType);
        dest.writeString(this.aggregateStatus);
        dest.writeString(this.forecastMethod);
        dest.writeValue(this.compressCount);
        dest.writeString(this.domainPath);
        dest.writeInt(this.modelId);
        dest.writeString(this.kpiName);
        dest.writeString(this.extendDomains);
    }

    public KpiValueBean() {
    }

    protected KpiValueBean(Parcel in) {
        this.nodeId = in.readLong();
        this.kpiCode = in.readInt();
        this.quality = in.readInt();
        this.value = in.readInt();
        this.arisingTime = in.readString();
        this.insertTime = in.readString();
        this.agentId = in.readString();
        this.valueStr = in.readString();
        this.resourceId = in.readInt();
        this.computeTaskId = in.readInt();
        this.dataSerialNumber = in.readInt();
        this.dataTime = in.readString();
        this.granularityUnit = in.readString();
        this.aggregateType = in.readString();
        this.aggregateStatus = in.readString();
        this.forecastMethod = in.readString();
        this.compressCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.domainPath = in.readString();
        this.modelId = in.readInt();
        this.kpiName = in.readString();
        this.extendDomains = in.readString();
    }

    public static final Creator<KpiValueBean> CREATOR = new Creator<KpiValueBean>() {
        @Override
        public KpiValueBean createFromParcel(Parcel source) {
            return new KpiValueBean(source);
        }

        @Override
        public KpiValueBean[] newArray(int size) {
            return new KpiValueBean[size];
        }
    };
}
