package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.AlertActionBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class AlertActionResponse extends BaseResponse<AlertActionBean> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public AlertActionResponse() {
    }

    protected AlertActionResponse(Parcel in) {
    }

    public static final Creator<AlertActionResponse> CREATOR = new Creator<AlertActionResponse>() {
        @Override
        public AlertActionResponse createFromParcel(Parcel source) {
            return new AlertActionResponse(source);
        }

        @Override
        public AlertActionResponse[] newArray(int size) {
            return new AlertActionResponse[size];
        }
    };
}
