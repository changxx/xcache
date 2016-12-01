package com.netease.xcache;

/**
 * XCache 抽象实现
 *
 * @author changxiangxiang
 * @date 16/11/30
 */
public abstract class AbstractXCache implements XCache {

    private XCacheConf xCacheConf;

    @Override
    public XCacheConf getXCacheConf() {
        return xCacheConf;
    }

    @Override
    public void setXCacheConf(XCacheConf xCacheConf) {
        this.xCacheConf = xCacheConf;
    }
}
