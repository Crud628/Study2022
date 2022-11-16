package com.lan.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import cn.hutool.core.lang.Assert;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:17:55
 * @TODO URL 资源
 * 
 */
public class UrlResource implements Resource{
	 private final URL url;

	    public UrlResource(URL url) {
	        Assert.notNull(url,"URL must not be null");
	        this.url = url;
	    }

	    @Override
	    public InputStream getInputStream() throws IOException {
	        URLConnection con = this.url.openConnection();
	        try {
	            return con.getInputStream();
	        }
	        catch (IOException ex){
	            if (con instanceof HttpURLConnection){
	                ((HttpURLConnection) con).disconnect();
	            }
	            throw ex;
	        }
	    }
}
