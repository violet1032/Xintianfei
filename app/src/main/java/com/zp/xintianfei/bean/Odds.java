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
 * 时间:2018/3/9 16:31
 * <p/>
 * 版本:
 */
public class Odds implements Serializable {
    private int id;
    private int cate;
    private int type;
    private String rule;
    private float rate;

    public Odds parse(String json) {
        try {
            JsonUtils jsonUtils = new JsonUtils(json);
            setId(jsonUtils.getInt("id"));
            setCate(jsonUtils.getInt("cate"));
            setType(jsonUtils.getInt("type"));
            setRule(jsonUtils.getString("rule"));
            setRate((float) jsonUtils.getDouble("rate"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
