package com.demo.roombooking.common.resp;

import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * 返回信息实体
 */
@NoArgsConstructor
public class JsonResponse extends LinkedHashMap<String, Object> {



    /**
     * 响应代码
     */
    private static final String CODE = "code";

    /**
     * 响应消息提示
     */
    private static final String INFO = "info";

    /**
     * 响应数据域
     */
    private static final String DATA = "data";

    /**
     * 成功
     */
    public static final int SUCCESS = 1;

    /**
     * 失败
     */
    public static final int FAILURE = 0;


    public JsonResponse(int flag) {
        if (flag == 1) {
            setSuccess();
        } else {
            setFail();
        }
    }

    public JsonResponse(int flag, String info) {
        if (flag == 1) {
            setSuccess(info);
        } else {
            setFail(info);
        }
    }

    public JsonResponse(int flag, Object object) {
        if (flag == 1) {
            setSuccess(object);
        } else {
            setFail(object);
        }
    }

    public JsonResponse(int flag, String info, Object object) {
        if (flag == 1) {
            setSuccess(info, object);
        } else {
            setFail(info, object);
        }
    }

    private void setSuccess() {
        this.put(CODE, SUCCESS);
    }

    private void setSuccess(String info) {
        setSuccess();
        this.put(INFO, info);
    }

    private void setSuccess(Object data) {
        setSuccess();
        this.put(DATA, data);
    }

    private void setSuccess(String info, Object object) {
        setSuccess(info);
        this.put(DATA, object);
    }

    private void setFail() {
        this.put(CODE, FAILURE);
    }

    private void setFail(String info) {
        setFail();
        this.put(INFO, info);
    }

    private void setFail(Object object) {
        setFail();
        this.put(DATA, object);

    }

    private void setFail(String info, Object data) {
        setFail(info);
        this.put(DATA, data);
    }
}
