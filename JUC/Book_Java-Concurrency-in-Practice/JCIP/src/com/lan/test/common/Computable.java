package com.lan.test.common;

public interface Computable <A, V> {
    V compute(A arg) throws InterruptedException;
}