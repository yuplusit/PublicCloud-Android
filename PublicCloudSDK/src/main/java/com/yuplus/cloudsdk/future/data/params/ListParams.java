package com.yuplus.cloudsdk.future.data.params;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.base.BaseParams;

import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class ListParams extends BaseParams<List> {

    public ListParams() {
        super();
        this.params = new ArrayList();
    }

    public ListParams addParam(Object object) {
        this.params.add(object);
        return this;
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this.params);
    }
}
