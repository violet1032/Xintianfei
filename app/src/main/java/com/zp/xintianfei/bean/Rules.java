package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONException;

import java.io.Serializable;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2018/2/5 19:08
 * <p>
 * 版本:
 */
public class Rules implements Serializable {
    private int cate;
    private String content;
    private String title;

    public Rules parse(String json) {
        try {
            JsonUtils jsonUtils = new JsonUtils(json);
            JsonUtils jsonUtils1 = jsonUtils.getJSONUtils("info");
            if (jsonUtils1 != null) {
                setCate(jsonUtils1.getInt("cate"));
                setContent(jsonUtils1.getString("content"));
                setTitle(jsonUtils1.getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
