package com.lan.springframework.beans.factory;
/**
 * @author Keason
 * @version 创建时间：2022年11月12日 下午6:48:06
 * @TODO 定义 Bean 工厂接口
 * 提升为接口，交由具体子类实现
 * @since 0.01
 */
public interface BeanFactory {

    /**
     * 返回 Bean 的实例对象
     * @param name 要检索的bean的名称
     * @return 实例化的 Bean 对象
     * @throws BeansException 不能获取 Bean 对象，则抛出异常
     */
    Object getBean(String name) throws BeansException;
}
