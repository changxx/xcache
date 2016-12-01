package com.netease.xcache;

import com.netease.xcache.util.PropertiesUtils;
import com.netease.xcache.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * xcache配置
 *
 * @author changxiangxiang
 * @date 16/11/30
 */
public class XCacheProperties {

    private static final Logger log = LoggerFactory.getLogger(XCacheProperties.class);

    // xcache group配置key
    private static final String XCACHE_GROUP_PROPERTIES_KEY = "xcache.group";

    // 配置项
    private static List<Field> xCacheConfField = new ArrayList<>();

    // 配置信息－配置文件解析结果
    private static List<XCacheConf> xCacheConfs = new ArrayList<>();

    static {
        for (Field field : XCacheConf.class.getDeclaredFields()) {
            xCacheConfField.add(field);
        }
        // 所有分组
        String groups = PropertiesUtils.getFromProperties(XCACHE_GROUP_PROPERTIES_KEY);
        if (groups != null && !groups.equals("")) {
            for (String groupName : groups.split(",")) {
                try {
                    XCacheConf xCacheConf = new XCacheConf();
                    xCacheConf.setGroup(groupName);
                    for (Field field : xCacheConfField) {
                        String propertyVal = PropertiesUtils.getFromProperties(groupName + "." + field.getName());
                        if (propertyVal == null) {
                            continue;
                        }
                        ReflectUtil.invokeSet(xCacheConf, field.getName(), XCacheProperties.stringToObject(propertyVal, field.getGenericType()));
                    }
                    xCacheConfs.add(xCacheConf);
                } catch (Exception e) {
                    log.error("xcache init conf error, group: {}", groupName, e);
                }
            }
        }
    }

    private static Object stringToObject(String str, Type type) {
        if (type.equals(Long.class)) {
            return Long.parseLong(str);
        } else if (type.equals(Double.class)) {
            return Double.parseDouble(str);
        } else if (type.equals(Integer.class)) {
            return Integer.parseInt(str);
        } else if (type.equals(Boolean.class)) {
            return Boolean.parseBoolean(str);
        } else if (type.equals(Short.class)) {
            return Short.parseShort(str);
        } else if (type.equals(Float.class)) {
            return Float.parseFloat(str);
        }
        log.error("xcache string parse error, type is not support, str: {}, type: {}", str, type);
        return null;
    }

    public static void main(String[] args) {
        for (Field field : XCacheConf.class.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }

    public static List<XCacheConf> getXCacheConfs() {
        return xCacheConfs;
    }
}
