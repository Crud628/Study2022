package com.lan.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:15:30
 * @TODO 资源处理接口
 * 
 */
public interface Resource {
	InputStream getInputStream() throws IOException;

}
