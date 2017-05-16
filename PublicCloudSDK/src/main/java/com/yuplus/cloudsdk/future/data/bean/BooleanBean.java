package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;


/**
 * @user longzhen
 * @date 2017/1/4
 * @desc
 */

public class BooleanBean extends BaseBean {

    protected boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.result ? (byte) 1 : (byte) 0);
    }

    public BooleanBean() {
    }

    protected BooleanBean(Parcel in) {
        this.result = in.readByte() != 0;
    }

    public static final Creator<BooleanBean> CREATOR = new Creator<BooleanBean>() {
        @Override
        public BooleanBean createFromParcel(Parcel source) {
            return new BooleanBean(source);
        }

        @Override
        public BooleanBean[] newArray(int size) {
            return new BooleanBean[size];
        }
    };
}
