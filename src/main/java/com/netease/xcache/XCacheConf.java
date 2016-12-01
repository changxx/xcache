package com.netease.xcache;

import java.io.Serializable;

/**
 * 缓存命名空间配置
 *
 * @author changxiangxiang
 * @date 16/11/30
 */
public class XCacheConf implements Serializable {

    private static final long serialVersionUID = -2723922937812120561L;

    private String group;           // 命名空间

    private Long expire;        // 缓存过期时间 单位秒

    public XCacheConf() {
    }

    public XCacheConf(String group, Long expire) {
        this.group = group;
        this.expire = expire;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "XCacheConf{" +
                "group='" + group + '\'' +
                ", expire=" + expire +
                '}';
    }
}
