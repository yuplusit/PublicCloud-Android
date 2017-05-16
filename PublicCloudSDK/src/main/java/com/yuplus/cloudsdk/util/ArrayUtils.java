package com.yuplus.cloudsdk.util;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc 数组工具类
 */

public class ArrayUtils {
    /**
     * 判断数组数据是否为空
     * @param array
     * @return
     */
    public static<T> boolean isEmpty(T[] array) {
        return array == null? true : array.length == 0;
    }

    /**
     * 判断数组数据是否为非空
     * @param array
     * @return
     */
    public static<T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }


    /**
     * 获取数组数据长度
     * @param array
     * @return
     */
    public static<T> int getCount(T[] array) {
        return array == null ? 0 : array.length;
    }
}
