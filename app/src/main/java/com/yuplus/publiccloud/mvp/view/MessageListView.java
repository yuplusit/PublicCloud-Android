package com.yuplus.publiccloud.mvp.view;

import com.yuplus.cloudsdk.future.data.bean.MessageWrapperBean;
import com.yuplus.publiccloud.mvp.base.IView;

import java.util.List;

/**
 * Created by zlzsa on 2017/6/4.
 */

public interface MessageListView extends IView {
    void onRenderMessageData(List<MessageWrapperBean> data);

    void onFailure(final String msg);
}
