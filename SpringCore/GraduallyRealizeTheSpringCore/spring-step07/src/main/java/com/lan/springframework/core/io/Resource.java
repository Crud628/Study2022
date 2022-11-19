package com.lan.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Keason
 * @version 创建时间：2022年11月19日 上午11:42:46
 * @TODO
 * 
 */
public interface Resource {
	 InputStream getInputStream() throws IOException;
}
