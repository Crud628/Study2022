package com.lan.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

import cn.hutool.core.lang.Assert;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:27:09
 * @TODO 默认实现的资源处理器
 * 
 */
public class DefaultResourceLoader implements ResourceLoader {
	@Override
	public Resource getResource(String location) {
		Assert.notNull(location, "Location must not be null");
		if (location.startsWith(CLASSPATH_URL_PREFIX)) {
			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
		} else {
			try {
				URL url = new URL(location);
				return new UrlResource(url);
			} catch (MalformedURLException e) {
				return new FileSystemResource(location);
			}
		}
	}
}
