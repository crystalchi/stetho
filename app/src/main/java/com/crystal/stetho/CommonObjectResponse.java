package com.crystal.stetho;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class  CommonObjectResponse<T> extends CommonResponse {
    private T retData;

    public T getRetData() {
        return retData;
    }

    public void setRetData(T retData) {
        this.retData = retData;
    }
}