package com.netease.xcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * XCacheManager
 *
 * @author changxiangxiang
 * @date 16/11/30
 */
public class XCacheManager extends org.springframework.cache.support.AbstractCacheManager {

    private static final Logger log = LoggerFactory.getLogger(XCacheManager.class);

    private XCache xCache;

    @Override
    protected Collection<? extends Cache> loadCaches() {
        List<Cache> caches = new ArrayList<>();
        for (XCacheConf xCacheConf : XCacheProperties.getXCacheConfs()) {
            try {
                XCache xCacheClient = xCache.getClass().newInstance();
                BeanUtils.copyProperties(xCache, xCacheClient);
                xCacheClient.setXCacheConf(xCacheConf);
                caches.add(xCacheClient);
            } catch (InstantiationException e) {
                log.error("xcache manager init InstantiationException error, xCacheConf: {}", xCacheConf, e);
            } catch (IllegalAccessException e) {
                log.error("xcache manager init IllegalAccessException error, xCacheConf: {}", xCacheConf, e);
            }
        }
        return caches;
    }

    public void setxCache(XCache xCache) {
        this.xCache = xCache;
    }
}
