package com.lan.springframework.context;

import com.lan.springframework.beans.factory.ListableBeanFactory;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:32:43
 * @description 应用上下文接口 Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 * 
 */
public interface ApplicationContext extends ListableBeanFactory  {

}
