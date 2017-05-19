package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.UnitBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class UnitListResponse extends BaseResponse<List<UnitBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public UnitListResponse() {
    }

    protected UnitListResponse(Parcel in) {
    }

    public static final Creator<UnitListResponse> CREATOR = new Creator<UnitListResponse>() {
        @Override
        public UnitListResponse createFromParcel(Parcel source) {
            return new UnitListResponse(source);
        }

        @Override
        public UnitListResponse[] newArray(int size) {
            return new UnitListResponse[size];
        }
    };
}
