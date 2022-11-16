package com.lan.springframework.context;

import com.lan.springframework.beans.BeansException;

/**
 * @author Keason
 * @version 创建时间：2022年11月16日 下午9:34:12
 * @description SPI 接口配置应用上下文 SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link com.lan.springframework.context.ApplicationContext} interface.
 * 
 */
public interface ConfigurableApplicationContext extends ApplicationContext  {
    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
