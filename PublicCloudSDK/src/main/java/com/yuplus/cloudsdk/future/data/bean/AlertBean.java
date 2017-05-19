package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class AlertBean extends BaseBean {


    /**
     * eventId : 256818526636421
     * alertId : 257410963669650
     * srcEventId : null
     * alertCode : 241395148996082
     * nodeId : 205383111786064
     * domains : /0/186793350166066/187515173236078/187515173236081/
     * nodeTypes : /0/1/9/187515173236065/197979751246185/
     * devName : 泰安中心医院污水（plc）
     * appName : 泰安中心医院污水（plc）
     * relatedNodeId : 0
     * instance : null
     * instance2 : null
     * kpiValue : 0
     * kpiCode : 197979751246190
     * kpiUnit : NA
     * severity : 4
     * title : 故障停机
     * message : 调液池提升泵1运行:关 [62095] 异常
     * arisingTime : 2017-05-04T09:34:46.963+0000
     * receiveTime : 2017-05-04T09:34:46.963+0000
     * agentId : RULE:239865883516064
     * filtered : false
     * tags : null
     * classified : false
     * domainList : ["0","186793350166066","187515173236078","187515173236081"]
     * nodeTypeList : ["0","1","9","187515173236065","197979751246185"]
     * tagList : null
     * count : 3000
     * state : 5
     * causeAlertId : 0
     * related : false
     * firstArisingTime : 2017-05-03T08:35:16.598+0000
     * oldSeverity : 4
     * updateTime : 2017-05-17T10:03:44.018+0000
     * openTime : null
     * closeTime : null
     * openBy : null
     * closeBy : null
     * owner : 润兴管理员
     * orderId : null
     * orderStatus : null
     * comments :
     * incidentId : 0
     * claimTime : 2017-05-17T10:03:44.017+0000
     * claimBy : 润兴管理员
     * upgradeTime : null
     * upgradeCount : 0
     * activeState : 0
     */

    private long         eventId;
    private long         alertId;
    private String       srcEventId;
    private long         alertCode;
    private long         nodeId;
    private String       domains;
    private String       nodeTypes;
    private String       devName;
    private String       appName;
    private int          relatedNodeId;
    private String       kpiValue;
    private long         kpiCode;
    private String       kpiUnit;
    private int          severity;
    private String       title;
    private String       message;
    private String       arisingTime;
    private String       receiveTime;
    private String       agentId;
    private boolean      filtered;
    private boolean      classified;
    private int          count;
    private int          state;
    private int          causeAlertId;
    private boolean      related;
    private String       firstArisingTime;
    private int          oldSeverity;
    private String       updateTime;
    private String       openTime;
    private String       closeTime;
    private String       openBy;
    private String       closeBy;
    private String       owner;
    private String       orderId;
    private String       orderStatus;
    private String       comments;
    private int          incidentId;
    private String       claimTime;
    private String       claimBy;
    private String       upgradeTime;
    private int          upgradeCount;
    private int          activeState;
    private List<String> domainList;
    private List<String> nodeTypeList;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getAlertId() {
        return alertId;
    }

    public void setAlertId(long alertId) {
        this.alertId = alertId;
    }

    public String getSrcEventId() {
        return srcEventId;
    }

    public void setSrcEventId(String srcEventId) {
        this.srcEventId = srcEventId;
    }

    public long getAlertCode() {
        return alertCode;
    }

    public void setAlertCode(long alertCode) {
        this.alertCode = alertCode;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getNodeTypes() {
        return nodeTypes;
    }

    public void setNodeTypes(String nodeTypes) {
        this.nodeTypes = nodeTypes;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getRelatedNodeId() {
        return relatedNodeId;
    }

    public void setRelatedNodeId(int relatedNodeId) {
        this.relatedNodeId = relatedNodeId;
    }

    public String getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(String kpiValue) {
        this.kpiValue = kpiValue;
    }

    public long getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(long kpiCode) {
        this.kpiCode = kpiCode;
    }

    public String getKpiUnit() {
        return kpiUnit;
    }

    public void setKpiUnit(String kpiUnit) {
        this.kpiUnit = kpiUnit;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getArisingTime() {
        return arisingTime;
    }

    public void setArisingTime(String arisingTime) {
        this.arisingTime = arisingTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public boolean isFiltered() {
        return filtered;
    }

    public void setFiltered(boolean filtered) {
        this.filtered = filtered;
    }

    public boolean isClassified() {
        return classified;
    }

    public void setClassified(boolean classified) {
        this.classified = classified;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCauseAlertId() {
        return causeAlertId;
    }

    public void setCauseAlertId(int causeAlertId) {
        this.causeAlertId = causeAlertId;
    }

    public boolean isRelated() {
        return related;
    }

    public void setRelated(boolean related) {
        this.related = related;
    }

    public String getFirstArisingTime() {
        return firstArisingTime;
    }

    public void setFirstArisingTime(String firstArisingTime) {
        this.firstArisingTime = firstArisingTime;
    }

    public int getOldSeverity() {
        return oldSeverity;
    }

    public void setOldSeverity(int oldSeverity) {
        this.oldSeverity = oldSeverity;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOpenBy() {
        return openBy;
    }

    public void setOpenBy(String openBy) {
        this.openBy = openBy;
    }

    public String getCloseBy() {
        return closeBy;
    }

    public void setCloseBy(String closeBy) {
        this.closeBy = closeBy;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public String getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(String claimTime) {
        this.claimTime = claimTime;
    }

    public String getClaimBy() {
        return claimBy;
    }

    public void setClaimBy(String claimBy) {
        this.claimBy = claimBy;
    }

    public String getUpgradeTime() {
        return upgradeTime;
    }

    public void setUpgradeTime(String upgradeTime) {
        this.upgradeTime = upgradeTime;
    }

    public int getUpgradeCount() {
        return upgradeCount;
    }

    public void setUpgradeCount(int upgradeCount) {
        this.upgradeCount = upgradeCount;
    }

    public int getActiveState() {
        return activeState;
    }

    public void setActiveState(int activeState) {
        this.activeState = activeState;
    }

    public List<String> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }

    public List<String> getNodeTypeList() {
        return nodeTypeList;
    }

    public void setNodeTypeList(List<String> nodeTypeList) {
        this.nodeTypeList = nodeTypeList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.eventId);
        dest.writeLong(this.alertId);
        dest.writeString(this.srcEventId);
        dest.writeLong(this.alertCode);
        dest.writeLong(this.nodeId);
        dest.writeString(this.domains);
        dest.writeString(this.nodeTypes);
        dest.writeString(this.devName);
        dest.writeString(this.appName);
        dest.writeInt(this.relatedNodeId);
        dest.writeString(this.kpiValue);
        dest.writeLong(this.kpiCode);
        dest.writeString(this.kpiUnit);
        dest.writeInt(this.severity);
        dest.writeString(this.title);
        dest.writeString(this.message);
        dest.writeString(this.arisingTime);
        dest.writeString(this.receiveTime);
        dest.writeString(this.agentId);
        dest.writeByte(this.filtered ? (byte) 1 : (byte) 0);
        dest.writeByte(this.classified ? (byte) 1 : (byte) 0);
        dest.writeInt(this.count);
        dest.writeInt(this.state);
        dest.writeInt(this.causeAlertId);
        dest.writeByte(this.related ? (byte) 1 : (byte) 0);
        dest.writeString(this.firstArisingTime);
        dest.writeInt(this.oldSeverity);
        dest.writeString(this.updateTime);
        dest.writeString(this.openTime);
        dest.writeString(this.closeTime);
        dest.writeString(this.openBy);
        dest.writeString(this.closeBy);
        dest.writeString(this.owner);
        dest.writeString(this.orderId);
        dest.writeString(this.orderStatus);
        dest.writeString(this.comments);
        dest.writeInt(this.incidentId);
        dest.writeString(this.claimTime);
        dest.writeString(this.claimBy);
        dest.writeString(this.upgradeTime);
        dest.writeInt(this.upgradeCount);
        dest.writeInt(this.activeState);
        dest.writeStringList(this.domainList);
        dest.writeStringList(this.nodeTypeList);
    }

    public AlertBean() {
    }

    protected AlertBean(Parcel in) {
        this.eventId = in.readLong();
        this.alertId = in.readLong();
        this.srcEventId = in.readString();
        this.alertCode = in.readLong();
        this.nodeId = in.readLong();
        this.domains = in.readString();
        this.nodeTypes = in.readString();
        this.devName = in.readString();
        this.appName = in.readString();
        this.relatedNodeId = in.readInt();
        this.kpiValue = in.readString();
        this.kpiCode = in.readLong();
        this.kpiUnit = in.readString();
        this.severity = in.readInt();
        this.title = in.readString();
        this.message = in.readString();
        this.arisingTime = in.readString();
        this.receiveTime = in.readString();
        this.agentId = in.readString();
        this.filtered = in.readByte() != 0;
        this.classified = in.readByte() != 0;
        this.count = in.readInt();
        this.state = in.readInt();
        this.causeAlertId = in.readInt();
        this.related = in.readByte() != 0;
        this.firstArisingTime = in.readString();
        this.oldSeverity = in.readInt();
        this.updateTime = in.readString();
        this.openTime = in.readString();
        this.closeTime = in.readString();
        this.openBy = in.readString();
        this.closeBy = in.readString();
        this.owner = in.readString();
        this.orderId = in.readString();
        this.orderStatus = in.readString();
        this.comments = in.readString();
        this.incidentId = in.readInt();
        this.claimTime = in.readString();
        this.claimBy = in.readString();
        this.upgradeTime = in.readString();
        this.upgradeCount = in.readInt();
        this.activeState = in.readInt();
        this.domainList = in.createStringArrayList();
        this.nodeTypeList = in.createStringArrayList();
    }

    public static final Creator<AlertBean> CREATOR = new Creator<AlertBean>() {
        @Override
        public AlertBean createFromParcel(Parcel source) {
            return new AlertBean(source);
        }

        @Override
        public AlertBean[] newArray(int size) {
            return new AlertBean[size];
        }
    };
}
