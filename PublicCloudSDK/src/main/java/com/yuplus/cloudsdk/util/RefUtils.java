package com.yuplus.cloudsdk.util;

import com.yuplus.cloudsdk.log.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc 反射工具类
 * <p>
 * <li>getObject(Class, Object[])							创建指定类对象的对象 </li>
 * <li>getObject(String, Object[])							创建指定类名的对象</li>
 * <li>invokeMothod(Class, Object[], String, Object[])		通过类名，运行指定方法 </li>
 * <li>invokeMothod(String, Object[], String, Object[])	通过类对象，运行指定方法</li>
 * <li>invokeMothod(Object, String, Object[])				通过对象，运行指定方法 </li>
 * <li>isClassExist(String)
 * </p>
 */

public class RefUtils {
    /**
     * 创建指定类对象的对象
     *
     * @param cls
     * @param ConstructorParams
     * @return
     */
    public static Object getObject(Class cls, Object[] ConstructorParams) {
        if (cls == null || ConstructorParams == null) return null;
        Object result = null;

        try {
            //获取构造函数参数类型
            Class cparamTypes[] = getParamTypes(ConstructorParams);

            // 利用newInstance()方法，获取构造方法的实例
            Constructor ct = cls.getConstructor(cparamTypes);
            result = ct.newInstance(ConstructorParams);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * 创建指定类名的对象
     *
     * @param className
     * @param ConstructorParams
     * @return
     */
    public static Object getObject(String className, Object[] ConstructorParams) {
        if (StringUtils.isBlank(className) || ConstructorParams == null) return null;
        Object result = null;

        try {
            Class cls = Class.forName(className);
            Class cparamTypes[] = getParamTypes(ConstructorParams);
            Constructor ct = cls.getConstructor(cparamTypes);
            result = ct.newInstance(ConstructorParams);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * 通过类名，运行指定方法
     *
     * @param className
     * @param ConstructorParams
     * @param methodName
     * @param methodParams
     * @return
     */
    public static Object invokeMothod(String className,
                                      Object[] ConstructorParams,
                                      String methodName,
                                      Object[] methodParams) {
        return invokeMothod(getObject(className, ConstructorParams), methodName, methodParams);
    }

    /**
     * 通过类对象，运行指定方法
     *
     * @param cls
     * @param ConstructorParams
     * @param methodName
     * @param methodParams
     * @return
     */
    public static Object invokeMothod(Class cls,
                                      Object[] ConstructorParams,
                                      String methodName,
                                      Object[] methodParams) {
        return invokeMothod(getObject(cls, ConstructorParams), methodName, methodParams);
    }

    /**
     * 通过对象，运行指定方法
     *
     * @param obj          类对象
     * @param methodName   方法名
     * @param methodParams 参数值
     * @return 失败返回null
     */
    public static Object invokeMothod(Object obj, String methodName, Object[] methodParams) {
        if (obj == null
                || StringUtils.isBlank(methodName)
                || methodParams == null) {
            return null;
        }

        Class cls = obj.getClass();
        Object result = null;

        try {
            //获取方法参数类型
            Class paramTypes[] = getParamTypes(methodParams);

            // 获取指定方法
            Method method = cls.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            result = method.invoke(obj, methodParams);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }


    /**
     * 判断类是否存在
     *
     * @param className
     * @return
     */
    public static boolean isClassExist(String className) {
        if (StringUtils.isBlank(className)) return false;

        try {
            Class cls = Class.forName(className);
            return true;
        } catch (Exception ex) {
            LogUtils.e(className + "is not found!");
        }

        return false;
    }


    /**
     * 获取参数类型
     *
     * @param params
     * @return
     */
    private static Class[] getParamTypes(Object[] params) {
        int size = params.length;
        Class[] types = new Class[size];

        for (int i = 0; i < size; i++) {
            types[i] = params[i].getClass();
        }

        return types;
    }
}
