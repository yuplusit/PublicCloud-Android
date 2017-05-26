package com.yuplus.cloudsdk.future.data.params;

import com.alibaba.fastjson.JSON;
import com.yuplus.cloudsdk.base.BaseParams;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class MapParams extends BaseParams<Map<String, Object>> {

    public MapParams() {
        super();
        this.params = new ConcurrentHashMap<String, Object>();
    }

    public MapParams addParam(String key, Object value) {
        if (null != value) {
            this.params.put(key, value);
        }
        return this;
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this.params);
    }
}
