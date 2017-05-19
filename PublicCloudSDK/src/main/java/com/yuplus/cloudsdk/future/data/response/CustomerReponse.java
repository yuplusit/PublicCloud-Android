package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.CustomerBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc 客户响应类
 */

public class CustomerReponse extends BaseResponse<CustomerBean> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public CustomerReponse() {
    }

    protected CustomerReponse(Parcel in) {
    }

    public static final Creator<CustomerReponse> CREATOR = new Creator<CustomerReponse>() {
        @Override
        public CustomerReponse createFromParcel(Parcel source) {
            return new CustomerReponse(source);
        }

        @Override
        public CustomerReponse[] newArray(int size) {
            return new CustomerReponse[size];
        }
    };
}
