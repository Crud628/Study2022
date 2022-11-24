package com.lan.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Keason
 * @version 创建时间：2022年11月24日 下午10:58:35
 * @TODO
 * 
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
