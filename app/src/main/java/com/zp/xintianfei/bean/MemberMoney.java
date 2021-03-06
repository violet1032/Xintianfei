package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONException;

import java.io.Serializable;

/**
 * <p/>
 * 描述:
 * <p/>
 * 作者:Administrator
 * <p/>
 * 时间:2018/2/3 12:47
 * <p/>
 * 版本:
 */
public class MemberMoney implements Serializable {

    private int id;
    private String name;
    private float value;
    private String info;

    public MemberMoney parse(String json,int id) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(json);
        setId(id);
        setName(jsonUtils.getString("name"));
        if(name.equals("system"))
            setValue((float)(jsonUtils.getDouble("value")) / 100f);
        else
            setValue((float)(jsonUtils.getDouble("value")));
        setInfo(jsonUtils.getString("info"));
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
