package com.lan.test.part01.ch02.L2_7;

import com.lan.test.common.Widget;

/**
 * NonreentrantDeadlock
 * <p/>
 * Code that would deadlock if intrinsic locks were not reentrant
 * 
 * 如果内置锁是不可重入的，那么这段代码将发生死锁
 *
 * @author Brian Goetz and Tim Peierls
 */
class LoggingWidget extends Widget {
    public synchronized void doSomething() {
        System.out.println(toString() + ": calling doSomething");
        super.doSomething();
    }
}
