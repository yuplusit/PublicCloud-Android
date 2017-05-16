package com.yuplus.cloudsdk.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc List集合工具类
 */

public class ListUtils {
    /**
     * 判断集合list数据是否为空
     *
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null) {
            return true;
        }

        return list.size() == 0;
    }

    /**
     * 判断集合list数据是否为非空
     *
     * @param list
     * @return
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return !isEmpty(list);
    }


    /**
     * 获取集合list数据长度
     *
     * @param list
     * @return
     */
    public static <T> int getCount(List<T> list) {
        return list == null ? 0 : list.size();
    }

    /**
     * 反转数据
     *
     * @param sourceList
     * @return
     */
    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }

        List<V> invertList = new ArrayList<V>(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        return invertList;
    }
}
