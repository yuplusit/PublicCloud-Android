package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class DeviceListBean extends BaseBean {
    private List<DeviceBean> data;
    private int              total;

    public List<DeviceBean> getData() {
        return data;
    }

    public void setData(List<DeviceBean> data) {
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

    public DeviceListBean() {
    }

    protected DeviceListBean(Parcel in) {
        this.data = in.createTypedArrayList(DeviceBean.CREATOR);
        this.total = in.readInt();
    }

    public static final Creator<DeviceListBean> CREATOR = new Creator<DeviceListBean>() {
        @Override
        public DeviceListBean createFromParcel(Parcel source) {
            return new DeviceListBean(source);
        }

        @Override
        public DeviceListBean[] newArray(int size) {
            return new DeviceListBean[size];
        }
    };
}
