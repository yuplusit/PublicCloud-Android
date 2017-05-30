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
    private long kpiCode;
    private int quality;
    private long value;
    private String arisingTime;
    private String insertTime;
    private String agentId;
    private String valueStr;
    private long resourceId;
    private long computeTaskId;
    private long dataSerialNumber;
    private String dataTime;
    private String granularityUnit;
    private String aggregateType;
    private String aggregateStatus;
    private String forecastMethod;
    private Integer compressCount;
    private String domainPath;
    private long modelId;
    private String kpiName;
    private String extendDomains;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public long getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(long kpiCode) {
        this.kpiCode = kpiCode;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
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

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(long resourceId) {
        this.resourceId = resourceId;
    }

    public long getComputeTaskId() {
        return computeTaskId;
    }

    public void setComputeTaskId(long computeTaskId) {
        this.computeTaskId = computeTaskId;
    }

    public long getDataSerialNumber() {
        return dataSerialNumber;
    }

    public void setDataSerialNumber(long dataSerialNumber) {
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

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
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
        dest.writeLong(this.kpiCode);
        dest.writeInt(this.quality);
        dest.writeLong(this.value);
        dest.writeString(this.arisingTime);
        dest.writeString(this.insertTime);
        dest.writeString(this.agentId);
        dest.writeString(this.valueStr);
        dest.writeLong(this.resourceId);
        dest.writeLong(this.computeTaskId);
        dest.writeLong(this.dataSerialNumber);
        dest.writeString(this.dataTime);
        dest.writeString(this.granularityUnit);
        dest.writeString(this.aggregateType);
        dest.writeString(this.aggregateStatus);
        dest.writeString(this.forecastMethod);
        dest.writeValue(this.compressCount);
        dest.writeString(this.domainPath);
        dest.writeLong(this.modelId);
        dest.writeString(this.kpiName);
        dest.writeString(this.extendDomains);
    }

    public KpiValueBean() {
    }

    protected KpiValueBean(Parcel in) {
        this.nodeId = in.readLong();
        this.kpiCode = in.readLong();
        this.quality = in.readInt();
        this.value = in.readLong();
        this.arisingTime = in.readString();
        this.insertTime = in.readString();
        this.agentId = in.readString();
        this.valueStr = in.readString();
        this.resourceId = in.readLong();
        this.computeTaskId = in.readLong();
        this.dataSerialNumber = in.readLong();
        this.dataTime = in.readString();
        this.granularityUnit = in.readString();
        this.aggregateType = in.readString();
        this.aggregateStatus = in.readString();
        this.forecastMethod = in.readString();
        this.compressCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.domainPath = in.readString();
        this.modelId = in.readLong();
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
