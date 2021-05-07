package com.bydyx.reflex.exception;

/**
 * @author q.f
 * @date 2020/8/4 10:39
 */
public class ClassInitRtException extends RuntimeException {

    public ClassInitRtException(Exception e) {
        super(e);
    }
}