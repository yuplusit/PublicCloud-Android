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

public class ProjectListBean extends BaseResponse<List<ProjectBean>> {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public ProjectListBean() {
    }

    protected ProjectListBean(Parcel in) {
    }

    public static final Creator<ProjectListBean> CREATOR = new Creator<ProjectListBean>() {
        @Override
        public ProjectListBean createFromParcel(Parcel source) {
            return new ProjectListBean(source);
        }

        @Override
        public ProjectListBean[] newArray(int size) {
            return new ProjectListBean[size];
        }
    };
}
