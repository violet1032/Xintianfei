package com.zp.xintianfei.bean;


import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONException;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2016/7/5 17:04
 * <p>
 * 版本:
 */
public class User implements Serializable {
    private int uid;
    private int tid;
    private String username;
    private String nickname;
    private String openid;
    private String avatar;
    private BigDecimal money;
    private BigDecimal fanshui;
    private BigDecimal yongjin;
    private String fs_rate;
    private E_GENDER_TYPE genderType;

    public User parse(String jsonData) {
        try {
            JsonUtils jsonUtils = new JsonUtils(jsonData);
            JsonUtils jsonUtils1 = jsonUtils.getJSONUtils("info");
            setUid(jsonUtils1.getInt("uid"));
            setUsername(jsonUtils1.getString("username"));
            setNickname(jsonUtils1.getString("nickname"));
            setTid(jsonUtils1.getInt("tid"));
            setOpenid(jsonUtils1.getString("openid"));
            setAvatar(jsonUtils1.getString("head"));
            setMoney(jsonUtils1.getBigDecimal("money"));
            setFanshui(jsonUtils1.getBigDecimal("fanshui"));
            setYongjin(jsonUtils1.getBigDecimal("yongjin"));
            setFs_rate(jsonUtils1.getString("fs_rate"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public E_GENDER_TYPE getGenderType() {
        return genderType;
    }

    public void setGenderType(E_GENDER_TYPE genderType) {
        this.genderType = genderType;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getFanshui() {
        return fanshui;
    }

    public void setFanshui(BigDecimal fanshui) {
        this.fanshui = fanshui;
    }

    public BigDecimal getYongjin() {
        return yongjin;
    }

    public void setYongjin(BigDecimal yongjin) {
        this.yongjin = yongjin;
    }

    public String getFs_rate() {
        return fs_rate;
    }

    public void setFs_rate(String fs_rate) {
        this.fs_rate = fs_rate;
    }
}