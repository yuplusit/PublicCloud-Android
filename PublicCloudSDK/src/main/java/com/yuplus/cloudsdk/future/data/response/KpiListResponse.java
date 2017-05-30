package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.KpiBean;
import com.yuplus.cloudsdk.future.data.params.ListParams;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class KpiListResponse extends BaseResponse<List<KpiBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public KpiListResponse() {
    }

    protected KpiListResponse(Parcel in) {
    }

    public static final Creator<KpiListResponse> CREATOR = new Creator<KpiListResponse>() {
        @Override
        public KpiListResponse createFromParcel(Parcel source) {
            return new KpiListResponse(source);
        }

        @Override
        public KpiListResponse[] newArray(int size) {
            return new KpiListResponse[size];
        }
    };
}
