package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.ConfigurationBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/23/2017
 * @desc
 */

public class ConfigurationListResponse extends BaseResponse<List<ConfigurationBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ConfigurationListResponse() {
    }

    protected ConfigurationListResponse(Parcel in) {
    }

    public static final Creator<ConfigurationListResponse> CREATOR = new Creator<ConfigurationListResponse>() {
        @Override
        public ConfigurationListResponse createFromParcel(Parcel source) {
            return new ConfigurationListResponse(source);
        }

        @Override
        public ConfigurationListResponse[] newArray(int size) {
            return new ConfigurationListResponse[size];
        }
    };
}
