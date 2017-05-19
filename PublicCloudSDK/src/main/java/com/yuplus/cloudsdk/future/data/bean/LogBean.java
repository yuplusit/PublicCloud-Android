package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class LogBean extends BaseBean {

    private long actionId;
    private String actionType;
    private String operator;
    private String comments;
    private long   alertId;
    private String actTime;
    private int    result;
    private String paramStr;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getAlertId() {
        return alertId;
    }

    public void setAlertId(long alertId) {
        this.alertId = alertId;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.actionId);
        dest.writeString(this.actionType);
        dest.writeString(this.operator);
        dest.writeString(this.comments);
        dest.writeLong(this.alertId);
        dest.writeString(this.actTime);
        dest.writeInt(this.result);
        dest.writeString(this.paramStr);
    }

    public LogBean() {
    }

    protected LogBean(Parcel in) {
        this.actionId = in.readLong();
        this.actionType = in.readString();
        this.operator = in.readString();
        this.comments = in.readString();
        this.alertId = in.readLong();
        this.actTime = in.readString();
        this.result = in.readInt();
        this.paramStr = in.readString();
    }

    public static final Creator<LogBean> CREATOR = new Creator<LogBean>() {
        @Override
        public LogBean createFromParcel(Parcel source) {
            return new LogBean(source);
        }

        @Override
        public LogBean[] newArray(int size) {
            return new LogBean[size];
        }
    };
}
