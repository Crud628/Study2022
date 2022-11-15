package com.lan.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.lan.springframework.util.ClassUtils;

import cn.hutool.core.lang.Assert;

/**
 * @author Keason
 * @version 创建时间：2022年11月15日 下午11:00:03
 * @TODO
 * 
 */
public class ClassPathResource implements Resource {
	 private final String path;

	    private ClassLoader classLoader;

	    public ClassPathResource(String path) {
	        this(path, (ClassLoader) null);
	    }

	    public ClassPathResource(String path, ClassLoader classLoader) {
	        Assert.notNull(path, "Path must not be null");
	        this.path = path;
	        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
	    }

	    @Override
	    public InputStream getInputStream() throws IOException {
	        InputStream is = classLoader.getResourceAsStream(path);
	        if (is == null) {
	            throw new FileNotFoundException(
	                    this.path + " cannot be opened because it does not exist");
	        }
	        return is;
	    }
}
