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
 * 时间:2018/2/3 12:47
 * <p>
 * 版本:
 */
public class GambleHistory implements Serializable {

    private int id;
    private String stage;
    private String gameType;
    private String number;
    private int status;
    private BigDecimal money;
    private int wei;
    private int cate;
    private String create_at;
    private String update_at;
    private BigDecimal win;
    private String open_at;
    private String rate;
    private int result;
    private int type;
    private String strCate;
    private String strType;
    private String strResult;
    private String strStatus;

    public GambleHistory parse(String str) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(str);
        setId(jsonUtils.getInt("id"));
        setStage(jsonUtils.getString("stage"));
        setType(jsonUtils.getInt("type"));
        setNumber(jsonUtils.getString("number"));
        setWei(jsonUtils.getInt("wei"));
        setCate(jsonUtils.getInt("cate"));
        setMoney(new BigDecimal(jsonUtils.getString("money")));
        setStatus(jsonUtils.getInt("state"));
        setResult(jsonUtils.getInt("code"));
        setCreate_at(jsonUtils.getString("create_at"));
        setUpdate_at(jsonUtils.getString("update_at"));
        setOpen_at(jsonUtils.getString("open_at"));
        setWin(new BigDecimal(jsonUtils.getString("win")));
        setRate(jsonUtils.getString("rate"));
        setStrCate(E_LOTTERY_TYPE.getIndex(getCate()).name);
        setStrType(E_GAME_TYPE.getIndex(getCate(), getType()).name);
        setStrStatus(E_STATUE_TYPE.getIndex(getStatus()).name);
        setStrResult(E_RESULT_TYPE.getIndex(getResult()).name);
        return this;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
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

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public BigDecimal getWin() {
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    public String getOpen_at() {
        return open_at;
    }

    public void setOpen_at(String open_at) {
        this.open_at = open_at;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStrCate() {
        return strCate;
    }

    public void setStrCate(String strCate) {
        this.strCate = strCate;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    public String getStrResult() {
        return strResult;
    }

    public void setStrResult(String strResult) {
        this.strResult = strResult;
    }
}
