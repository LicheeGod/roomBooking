package com.demo.roombooking.common.exception;

/**
 * @author vinko
 */
public class NotSupportTableTypeException extends RuntimeException {

    public NotSupportTableTypeException() {
        super("不支持类型");
    }

}
