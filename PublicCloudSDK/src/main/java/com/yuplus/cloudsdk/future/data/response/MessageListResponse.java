package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.MessageWrapperBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class MessageListResponse extends BaseResponse<List<MessageWrapperBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public MessageListResponse() {
    }

    protected MessageListResponse(Parcel in) {
    }

    public static final Creator<MessageListResponse> CREATOR = new Creator<MessageListResponse>() {
        @Override
        public MessageListResponse createFromParcel(Parcel source) {
            return new MessageListResponse(source);
        }

        @Override
        public MessageListResponse[] newArray(int size) {
            return new MessageListResponse[size];
        }
    };
}
