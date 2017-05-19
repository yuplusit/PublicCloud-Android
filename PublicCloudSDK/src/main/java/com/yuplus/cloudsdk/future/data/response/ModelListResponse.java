package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.ModelBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class ModelListResponse extends BaseResponse<List<ModelBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ModelListResponse() {
    }

    protected ModelListResponse(Parcel in) {
    }

    public static final Creator<ModelListResponse> CREATOR = new Creator<ModelListResponse>() {
        @Override
        public ModelListResponse createFromParcel(Parcel source) {
            return new ModelListResponse(source);
        }

        @Override
        public ModelListResponse[] newArray(int size) {
            return new ModelListResponse[size];
        }
    };
}
