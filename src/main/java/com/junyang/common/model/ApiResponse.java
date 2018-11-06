package com.junyang.common.model;

import java.io.Serializable;

/**
 * 类: ApiResponse <br>
 * 描述: controller接口返回通用格式<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月05日 18:09
 */
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 331323334269718028L;

    private String res_code; // 响应码
    private String res_msg;  // 响应消息
    private T res_data; // 业务数据


    public ApiResponse() {
        
    }

    public ApiResponse(String res_code, String res_msg, T res_data) {
        this.res_code = res_code;
        this.res_msg = res_msg;
        this.res_data = res_data;
    }

    public String getRes_code() {
        return res_code;
    }

    public void setRes_code(String res_code) {
        this.res_code = res_code;
    }

    public String getRes_msg() {
        return res_msg;
    }

    public void setRes_msg(String res_msg) {
        this.res_msg = res_msg;
    }

    public T getRes_data() {
        return res_data;
    }

    public void setRes_data(T res_data) {
        this.res_data = res_data;
    }
}