package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc 客户列表响应类
 */

public class CustomerListResponse extends BaseResponse<List<CustomerBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public CustomerListResponse() {
    }

    protected CustomerListResponse(Parcel in) {
    }

    public static final Creator<CustomerListResponse> CREATOR = new Creator<CustomerListResponse>() {
        @Override
        public CustomerListResponse createFromParcel(Parcel source) {
            return new CustomerListResponse(source);
        }

        @Override
        public CustomerListResponse[] newArray(int size) {
            return new CustomerListResponse[size];
        }
    };
}
