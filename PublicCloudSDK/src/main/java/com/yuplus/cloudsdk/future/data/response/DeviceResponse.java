package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.DeviceBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class DeviceResponse extends BaseResponse<DeviceBean> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public DeviceResponse() {
    }

    protected DeviceResponse(Parcel in) {
    }

    public static final Creator<DeviceResponse> CREATOR = new Creator<DeviceResponse>() {
        @Override
        public DeviceResponse createFromParcel(Parcel source) {
            return new DeviceResponse(source);
        }

        @Override
        public DeviceResponse[] newArray(int size) {
            return new DeviceResponse[size];
        }
    };
}
