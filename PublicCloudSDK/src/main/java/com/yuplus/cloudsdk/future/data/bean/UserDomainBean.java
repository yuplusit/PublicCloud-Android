package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class UserDomainBean extends BaseBean {

    private String domainID;
    private String domainPath;
    private String name;
    private int priviledge;
    private String userID;

    public String getDomainID() {
        return domainID;
    }

    public void setDomainID(String domainID) {
        this.domainID = domainID;
    }

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriviledge() {
        return priviledge;
    }

    public void setPriviledge(int priviledge) {
        this.priviledge = priviledge;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.domainID);
        dest.writeString(this.domainPath);
        dest.writeString(this.name);
        dest.writeInt(this.priviledge);
        dest.writeString(this.userID);
    }

    public UserDomainBean() {
    }

    protected UserDomainBean(Parcel in) {
        this.domainID = in.readString();
        this.domainPath = in.readString();
        this.name = in.readString();
        this.priviledge = in.readInt();
        this.userID = in.readString();
    }

    public static final Creator<UserDomainBean> CREATOR = new Creator<UserDomainBean>() {
        @Override
        public UserDomainBean createFromParcel(Parcel source) {
            return new UserDomainBean(source);
        }

        @Override
        public UserDomainBean[] newArray(int size) {
            return new UserDomainBean[size];
        }
    };
}
