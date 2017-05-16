package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.BooleanBean;


/**
 * @user longzhen
 * @date 2017/1/4
 * @desc Boolean类型的网络响应类
 */

public class BooleanResponse extends BaseResponse<BooleanBean> {

    public BooleanResponse() {
    }

    protected BooleanResponse(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
        this.data = in.readParcelable(BooleanResponse.class.getClassLoader());
        this.stackTrace = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.message);
        dest.writeString(this.stackTrace);
        dest.writeParcelable(this.data, flags);
    }

    public static final Creator<BooleanResponse> CREATOR = new Creator<BooleanResponse>() {
        @Override
        public BooleanResponse createFromParcel(Parcel source) {
            return new BooleanResponse(source);
        }

        @Override
        public BooleanResponse[] newArray(int size) {
            return new BooleanResponse[size];
        }
    };
}
