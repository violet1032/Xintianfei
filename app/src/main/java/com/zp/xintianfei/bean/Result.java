package com.zp.xintianfei.bean;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;


/**
 * 结果
 * <p>
 * description:
 * <p>
 * author:zipeng
 * <p>
 * createTime:2015/9/24 18:16
 * <p>
 * version:1.0
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private final static String tag = "Result";

    private String code = "";
    private String msg = "";

    /**
     * 结果实体解析器
     *
     * @param jsonData
     * @return
     */
    public Result parse(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);

            if (jsonObject.has("code"))
                setCode(jsonObject.getString("code"));
            if (jsonObject.has("info"))
                setMsg(jsonObject.getString("info"));

            Log.e(tag, "结果编号:::" + getCode());
            Log.e(tag, "结果信息:::" + getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOk() {
        if (code.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
