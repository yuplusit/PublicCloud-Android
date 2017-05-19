package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class AlertActionBean extends BaseBean {

    private long          actionId;
    private String        actionType;
    private int           nodeId;
    private String        comment;
    private String        operator;
    private String        actTime;
    private boolean       done;
    private String        domain;
    private List<Long>    alertIds;
    private List<LogBean> logs;

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<Long> getAlertIds() {
        return alertIds;
    }

    public void setAlertIds(List<Long> alertIds) {
        this.alertIds = alertIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.actionId);
        dest.writeString(this.actionType);
        dest.writeInt(this.nodeId);
        dest.writeString(this.comment);
        dest.writeString(this.operator);
        dest.writeString(this.actTime);
        dest.writeByte(this.done ? (byte) 1 : (byte) 0);
        dest.writeString(this.domain);
        dest.writeList(this.alertIds);
        dest.writeTypedList(this.logs);
    }

    public AlertActionBean() {
    }

    protected AlertActionBean(Parcel in) {
        this.actionId = in.readLong();
        this.actionType = in.readString();
        this.nodeId = in.readInt();
        this.comment = in.readParcelable(Object.class.getClassLoader());
        this.operator = in.readString();
        this.actTime = in.readString();
        this.done = in.readByte() != 0;
        this.domain = in.readString();
        this.alertIds = new ArrayList<Long>();
        in.readList(this.alertIds, Long.class.getClassLoader());
        this.logs = in.createTypedArrayList(LogBean.CREATOR);
    }

    public static final Creator<AlertActionBean> CREATOR = new Creator<AlertActionBean>() {
        @Override
        public AlertActionBean createFromParcel(Parcel source) {
            return new AlertActionBean(source);
        }

        @Override
        public AlertActionBean[] newArray(int size) {
            return new AlertActionBean[size];
        }
    };
}
