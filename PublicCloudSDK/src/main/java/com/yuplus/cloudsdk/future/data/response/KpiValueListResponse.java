package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.KpiValueBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class KpiValueListResponse extends BaseResponse<List<KpiValueBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public KpiValueListResponse() {
    }

    protected KpiValueListResponse(Parcel in) {
    }

    public static final Creator<KpiValueListResponse> CREATOR = new Creator<KpiValueListResponse>() {
        @Override
        public KpiValueListResponse createFromParcel(Parcel source) {
            return new KpiValueListResponse(source);
        }

        @Override
        public KpiValueListResponse[] newArray(int size) {
            return new KpiValueListResponse[size];
        }
    };
}
