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
 * 时间:2018/2/9 15:39
 * <p/>
 * 版本:
 */
public class JLLottery implements Serializable {
    private int id;
    private String name;
    private String logo;
    private boolean isDelete;
    private String home;

    public JLLottery parse(String json) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(json);
        setId(jsonUtils.getInt("id"));
        setName(jsonUtils.getString("name"));
        setLogo(jsonUtils.getString("logo"));
        setIsDelete(jsonUtils.getInt("isDelete") == 1);
        setHome(jsonUtils.getString("home"));
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
