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
 * 时间:2018/2/7 17:11
 * <p/>
 * 版本:
 */
public class GameStatus implements Serializable {
    private int cate;
    private boolean isrun;
    private boolean isopen;
    private int countdown;
    private int ftime;
    private long start_time;
    private long end_time;

    public GameStatus parse(String str) {
        try {
            JsonUtils jsonUtils = new JsonUtils(str);
            if (jsonUtils != null) {
                setCate(jsonUtils.getInt("cate"));
                setIsrun(jsonUtils.getBoolean("isrun"));
                setIsopen(jsonUtils.getBoolean("isopen"));
                setCountdown(jsonUtils.getInt("countdown"));
                setFtime(jsonUtils.getInt("ftime"));
                setStart_time(jsonUtils.getLong("start_time") * 1000);
                setEnd_time(jsonUtils.getLong("end_time") * 1000);
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

    public boolean isrun() {
        return isrun;
    }

    public void setIsrun(boolean isrun) {
        this.isrun = isrun;
    }

    public boolean isopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public int getFtime() {
        return ftime;
    }

    public void setFtime(int ftime) {
        this.ftime = ftime;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }
}
