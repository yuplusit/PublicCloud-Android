package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class AlertListBean extends BaseBean {
    private List<AlertBean> data;
    private int total;

    public List<AlertBean> getData() {
        return data;
    }

    public void setData(List<AlertBean> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeInt(this.total);
    }

    public AlertListBean() {
    }

    protected AlertListBean(Parcel in) {
        this.data = in.createTypedArrayList(AlertBean.CREATOR);
        this.total = in.readInt();
    }

    public static final Creator<AlertListBean> CREATOR = new Creator<AlertListBean>() {
        @Override
        public AlertListBean createFromParcel(Parcel source) {
            return new AlertListBean(source);
        }

        @Override
        public AlertListBean[] newArray(int size) {
            return new AlertListBean[size];
        }
    };
}
