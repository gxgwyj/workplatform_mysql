package com.junyang.common;

/**
 * 类: MessageEnum <br>
 * 描述: 接口文案返回枚举<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月06日 11:24
 */
public enum MessageEnum {

    SUCCESS("200","成功"),
    FAIL("400","失败");
    private String code;
    private String text;


    MessageEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
