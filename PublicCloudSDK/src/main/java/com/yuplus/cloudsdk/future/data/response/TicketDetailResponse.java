package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.TicketDetailBean;

import java.util.List;

/**
 * @user longzhen
 * @date 6/12/2017
 * @desc
 */

public class TicketDetailResponse extends BaseResponse<List<TicketDetailBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public TicketDetailResponse() {
    }

    protected TicketDetailResponse(Parcel in) {
    }

    public static final Creator<TicketDetailResponse> CREATOR = new Creator<TicketDetailResponse>() {
        @Override
        public TicketDetailResponse createFromParcel(Parcel source) {
            return new TicketDetailResponse(source);
        }

        @Override
        public TicketDetailResponse[] newArray(int size) {
            return new TicketDetailResponse[size];
        }
    };
}
