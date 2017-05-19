package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.AttrBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class AttrListResponse extends BaseResponse<List<AttrBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public AttrListResponse() {
    }

    protected AttrListResponse(Parcel in) {
    }

    public static final Creator<AttrListResponse> CREATOR = new Creator<AttrListResponse>() {
        @Override
        public AttrListResponse createFromParcel(Parcel source) {
            return new AttrListResponse(source);
        }

        @Override
        public AttrListResponse[] newArray(int size) {
            return new AttrListResponse[size];
        }
    };
}
