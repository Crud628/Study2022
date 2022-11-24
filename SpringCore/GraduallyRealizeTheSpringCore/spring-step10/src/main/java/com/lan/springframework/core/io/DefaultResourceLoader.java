package com.lan.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

import cn.hutool.core.lang.Assert;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午11:04:56
 * @TODO
 * 
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }

}
