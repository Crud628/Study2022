package com.lan.test.brief;

import com.lan.test.annotations.GuardedBy;
import com.lan.test.annotations.ThreadSafe;


/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class Sequence {
    @GuardedBy("this") private int nextValue;
    /**
     * 返回一个唯一的数值
     */
    public synchronized int getNext() {
        return nextValue++;
    }
}
