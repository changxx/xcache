package com.netease.xcache;

import org.springframework.cache.Cache;

/**
 * XCache
 *
 * @author changxiangxiang
 * @date 16/11/30
 */
public interface XCache extends Cache, XCacheExt {

    /**
     * A (wrapper) object representing a cache value.
     */
    interface ValueWrapper {

        /**
         * Return the actual value in the cache.
         */
        Object get();

        /**
         * 从缓存中获取
         */
        boolean fromCache = false;
    }

}
