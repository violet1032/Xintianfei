package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;

import org.json.JSONException;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class GambleSimpleTodayHistory implements Serializable {

    private int id;
    private String stage;
    private String gameType;
    private String number;
    private String time;
    private int status;
    private BigDecimal money;
    private int wei;
    private int cate;
    private BigDecimal win;
    private int code;
    private String strResult;

    public GambleSimpleTodayHistory parse(String str) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(str);
        setId(jsonUtils.getInt("id"));
        setStage(jsonUtils.getString("stage"));
        String type = jsonUtils.getString("type");
        if (!StringUtils.isEmpty(type))
            type = type.replaceAll(" ", "\\\n");
        setGameType(type);
        setNumber(jsonUtils.getString("number"));
        setMoney(new BigDecimal(jsonUtils.getString("money")));
        setStatus(jsonUtils.getInt("state"));
        setWin(new BigDecimal(jsonUtils.getString("win")));
        String time = jsonUtils.getString("time");
        if (!StringUtils.isEmpty(time))
            time = time.replaceAll("<br>", "");
        setTime(time);
        setCode(jsonUtils.getInt("code"));

        String result = "";
        if (getStatus() == E_STATUE_TYPE.none.value)
            result = E_STATUE_TYPE.none.name;
        else
            result = E_RESULT_TYPE.getIndex(getCode()).name;
        setStrResult(result);

        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStrResult() {
        return strResult;
    }

    public void setStrResult(String strResult) {
        this.strResult = strResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getWei() {
        return wei;
    }

    public void setWei(int wei) {
        this.wei = wei;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public BigDecimal getWin() {
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
