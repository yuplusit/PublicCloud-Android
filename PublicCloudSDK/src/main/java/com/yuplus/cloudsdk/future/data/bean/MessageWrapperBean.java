package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class MessageWrapperBean extends BaseBean {

    private long userId;
    private String sender;
    private String receiver;
    private int    status;
    private int    id;
    private MessageBean message;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.userId);
        dest.writeString(this.sender);
        dest.writeString(this.receiver);
        dest.writeInt(this.status);
        dest.writeInt(this.id);
        dest.writeParcelable(this.message, flags);
    }

    public MessageWrapperBean() {
    }

    protected MessageWrapperBean(Parcel in) {
        this.userId = in.readLong();
        this.sender = in.readString();
        this.receiver = in.readString();
        this.status = in.readInt();
        this.id = in.readInt();
        this.message = in.readParcelable(MessageBean.class.getClassLoader());
    }

    public static final Creator<MessageWrapperBean> CREATOR = new Creator<MessageWrapperBean>() {
        @Override
        public MessageWrapperBean createFromParcel(Parcel source) {
            return new MessageWrapperBean(source);
        }

        @Override
        public MessageWrapperBean[] newArray(int size) {
            return new MessageWrapperBean[size];
        }
    };
}
