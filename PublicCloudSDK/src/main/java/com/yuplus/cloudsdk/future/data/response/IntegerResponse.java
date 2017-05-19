package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class IntegerResponse extends BaseResponse<Integer> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public IntegerResponse() {
    }

    protected IntegerResponse(Parcel in) {
    }

    public static final Creator<IntegerResponse> CREATOR = new Creator<IntegerResponse>() {
        @Override
        public IntegerResponse createFromParcel(Parcel source) {
            return new IntegerResponse(source);
        }

        @Override
        public IntegerResponse[] newArray(int size) {
            return new IntegerResponse[size];
        }
    };
}
