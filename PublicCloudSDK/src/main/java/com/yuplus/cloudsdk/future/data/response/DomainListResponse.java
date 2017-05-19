package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.DomainBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class DomainListResponse extends BaseResponse<List<DomainBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public DomainListResponse() {
    }

    protected DomainListResponse(Parcel in) {
    }

    public static final Creator<DomainListResponse> CREATOR = new Creator<DomainListResponse>() {
        @Override
        public DomainListResponse createFromParcel(Parcel source) {
            return new DomainListResponse(source);
        }

        @Override
        public DomainListResponse[] newArray(int size) {
            return new DomainListResponse[size];
        }
    };
}
