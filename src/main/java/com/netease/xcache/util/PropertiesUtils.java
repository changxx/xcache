package com.netease.xcache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtils
 *
 * @author changxiangxiang
 * @date 16/12/1
 */
public class PropertiesUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtils.class);

    private final static Properties xcacheProperties = new Properties();

    private static final String CONF_FILE_NAME = "xcache.properties";

    static {
        LOG.debug("init xcache properties running...");
        InputStream in;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONF_FILE_NAME);
            xcacheProperties.load(in);
        } catch (FileNotFoundException e1) {
            LOG.warn("init xcache properties FileNotFoundException...");
        } catch (IOException e2) {
            LOG.warn("init xcache properties IOException...", e2.getMessage());
        } catch (Exception e3) {
            LOG.error("init xcache properties Exception..." + e3.getMessage());
        }
    }

    public static String getFromProperties(String key) {
        if (null == key) {
            return null;
        }
        return xcacheProperties.getProperty(key);
    }
}
