package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.DeviceListBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc 设备列表响应类
 */

public class DeviceListResponse extends BaseResponse<DeviceListBean> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public DeviceListResponse() {
    }

    protected DeviceListResponse(Parcel in) {
    }

    public static final Creator<DeviceListResponse> CREATOR = new Creator<DeviceListResponse>() {
        @Override
        public DeviceListResponse createFromParcel(Parcel source) {
            return new DeviceListResponse(source);
        }

        @Override
        public DeviceListResponse[] newArray(int size) {
            return new DeviceListResponse[size];
        }
    };
}
