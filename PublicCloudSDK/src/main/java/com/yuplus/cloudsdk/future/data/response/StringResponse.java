package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class StringResponse extends BaseResponse<String> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public StringResponse() {
    }

    protected StringResponse(Parcel in) {
    }

    public static final Creator<StringResponse> CREATOR = new Creator<StringResponse>() {
        @Override
        public StringResponse createFromParcel(Parcel source) {
            return new StringResponse(source);
        }

        @Override
        public StringResponse[] newArray(int size) {
            return new StringResponse[size];
        }
    };
}
