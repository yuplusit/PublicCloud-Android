package com.yuplus.cloudsdk.future.data.params;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.base.BaseParams;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class DoubleParams extends BaseParams<Double> {

    public DoubleParams() {
        super();
    }

    public DoubleParams addParam(Double value) {
        this.params = value;
        return this;
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this.params);
    }
}
