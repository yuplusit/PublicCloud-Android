package com.yuplus.cloudsdk.future.data.response;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseResponse;
import com.yuplus.cloudsdk.future.data.bean.ProjectBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class ProjectListResponse extends BaseResponse<List<ProjectBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ProjectListResponse() {
    }

    protected ProjectListResponse(Parcel in) {
    }

    public static final Creator<ProjectListResponse> CREATOR = new Creator<ProjectListResponse>() {
        @Override
        public ProjectListResponse createFromParcel(Parcel source) {
            return new ProjectListResponse(source);
        }

        @Override
        public ProjectListResponse[] newArray(int size) {
            return new ProjectListResponse[size];
        }
    };
}
