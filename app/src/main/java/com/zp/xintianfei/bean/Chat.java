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
public class Chat implements Serializable {
    private int id;
    private String user_name;
    private String head;
    private String content;
    private String kind;
    private boolean is_admin;
    private String timestr;

    public Chat parse(String json) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(json);
        setId(jsonUtils.getInt("id"));
        setHead(jsonUtils.getString("head"));
        setContent(jsonUtils.getString("content"));
        setIs_admin(jsonUtils.getInt("is_admin") == 1);
        setKind(jsonUtils.getString("kind"));
        setTimestr(jsonUtils.getString("timestr"));
        setUser_name(jsonUtils.getString("user_name"));
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean is_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getTimestr() {
        return timestr;
    }

    public void setTimestr(String timestr) {
        this.timestr = timestr;
    }
}
