package com.crystal.stetho;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class CommonResponse {

    protected int errNum;
    protected String errMsg;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
