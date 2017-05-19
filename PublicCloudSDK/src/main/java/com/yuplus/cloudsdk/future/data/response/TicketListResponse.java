package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.TicketBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc 工单列表响应类
 */

public class TicketListResponse extends BaseResponse<List<TicketBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public TicketListResponse() {
    }

    protected TicketListResponse(Parcel in) {
    }

    public static final Creator<TicketListResponse> CREATOR = new Creator<TicketListResponse>() {
        @Override
        public TicketListResponse createFromParcel(Parcel source) {
            return new TicketListResponse(source);
        }

        @Override
        public TicketListResponse[] newArray(int size) {
            return new TicketListResponse[size];
        }
    };
}
