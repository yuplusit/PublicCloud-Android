package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.TicketCategoryBean;

import java.util.List;

/**
 * @user longzhen
 * @date 6/13/2017
 * @desc
 */

public class TicketCategoryReponse extends BaseResponse<List<TicketCategoryBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public TicketCategoryReponse() {
    }

    protected TicketCategoryReponse(Parcel in) {
    }

    public static final Creator<TicketCategoryReponse> CREATOR = new Creator<TicketCategoryReponse>() {
        @Override
        public TicketCategoryReponse createFromParcel(Parcel source) {
            return new TicketCategoryReponse(source);
        }

        @Override
        public TicketCategoryReponse[] newArray(int size) {
            return new TicketCategoryReponse[size];
        }
    };
}
