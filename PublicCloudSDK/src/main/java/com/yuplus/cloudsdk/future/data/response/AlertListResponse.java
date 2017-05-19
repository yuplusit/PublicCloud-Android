package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.AlertListBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc 告警列表响应类
 */

public class AlertListResponse extends BaseResponse<AlertListBean> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public AlertListResponse() {
    }

    protected AlertListResponse(Parcel in) {
    }

    public static final Creator<AlertListResponse> CREATOR = new Creator<AlertListResponse>() {
        @Override
        public AlertListResponse createFromParcel(Parcel source) {
            return new AlertListResponse(source);
        }

        @Override
        public AlertListResponse[] newArray(int size) {
            return new AlertListResponse[size];
        }
    };
}
