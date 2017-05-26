package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.ProjectBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * @user longzhen
 * @date 5/26/2017
 * @desc
 */

public interface ProjectListView extends IView {
    void onRenderProjectData(List<ProjectBean> data);

    void onFailure(final String msg);
}
