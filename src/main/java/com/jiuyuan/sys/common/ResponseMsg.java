package com.jiuyuan.sys.common;

import java.util.HashMap;
import java.util.Map;

public class ResponseMsg {
    /**
     * 状态码 成功:200 内部错误:500
     * 失败：401 没找到资源：404
     */
    private int status;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Map<String, Object> result = new HashMap<String, Object>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }


    public ResponseMsg() {
        super();
    }

    public ResponseMsg(int code, String msg) {
        super();
        this.status = code;
        this.msg = msg;
    }

    public ResponseMsg(int code, String msg, Map<String, Object> result) {
        super();
        this.status = code;
        this.msg = msg;
        this.result = result;
    }

    /**
     * Response sunccess message
     * @return
     */
    public static ResponseMsg success(String successMsg){
        return new ResponseMsg(200,successMsg);
    }

    /**
     * Response error message
     * @return
     */
    public static ResponseMsg error(){
        return new ResponseMsg(500,"内部错误，请联系管理员！");
    }

    /**
     * Response failed message
     * @return
     */
    public static ResponseMsg failed(String failedMsg){
        return new ResponseMsg(401,failedMsg);
    }

    /**
     * put message into Response
     * @param key message key
     * @param value message
     * @return
     */
    public ResponseMsg push(String key, Object value){
        this.getResult().put(key, value);
        return this;
    }

    public ResponseMsg failedMsg(String msg){
        this.push("failedMsg",msg);
        return this;
    }
    public ResponseMsg errorMsg(String msg){
        this.push("errorMsg",msg);
        return this;
    }
}
