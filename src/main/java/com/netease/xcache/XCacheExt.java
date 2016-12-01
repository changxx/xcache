package com.netease.xcache;

/**
 * cache扩展
 *
 * @author changxiangxiang
 * @date 16/11/30
 */
public interface XCacheExt {

    /**
     * 获取配置信息，相应实现可以根据本配置针对group做特殊处理
     *
     * @return
     */
    XCacheConf getXCacheConf();

    /**
     * 设置配置信息
     *
     * @param xCacheConf
     */
    void setXCacheConf(XCacheConf xCacheConf);

}
