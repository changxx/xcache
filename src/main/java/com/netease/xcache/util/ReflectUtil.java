package com.netease.xcache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author hzchangxiangxiang
 */
public class ReflectUtil {

    private static final Logger log = LoggerFactory.getLogger(ReflectUtil.class);

    /**
     * 获取字段
     */
    public static Field getField(Class objectClass, String fieldName) {
        if (objectClass == null || fieldName == null || fieldName.equals("")) {
            return null;
        }
        try {
            return objectClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    public static Method getGetMethod(Class objectClass, String fieldName) {

        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));

        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
            // ignore
        }

        return null;
    }

    /**
     * java反射bean的set方法
     */
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = objectClass.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            return objectClass.getMethod(sb.toString(), parameterTypes);
        } catch (Exception e) {
            log.error("error: ", e);
        }
        return null;
    }


    /**
     * 执行set方法
     */
    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[]{value});
        } catch (Exception e) {
            log.error("error: ", e);
        }
    }

    /**
     * 执行get方法
     */
    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            log.error("error: ", e);
        }
        return null;
    }
}
