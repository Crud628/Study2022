package com.lan.test.part01.ch02.L2_3;

import com.lan.test.annotations.*;

/**
 * LazyInitRace
 *
 * Race condition in lazy initialization
 * 延迟初始化的静态条件（不要这样做）
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null)
            instance = new ExpensiveObject();
        return instance;
    }
}

class ExpensiveObject { }

