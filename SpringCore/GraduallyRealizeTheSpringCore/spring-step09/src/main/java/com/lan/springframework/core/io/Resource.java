package com.lan.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Keason
 * @version 创建时间：2022年11月21日 下午9:24:34
 * @TODO
 * 
 */
public interface Resource {
	InputStream getInputStream() throws IOException;

}
