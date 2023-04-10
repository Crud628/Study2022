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

    public synchronized int getNext() {
        return nextValue++;
    }
}
