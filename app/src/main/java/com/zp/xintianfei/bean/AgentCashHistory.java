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
public class AgentCashHistory implements Serializable {

    private int id;
    private String type;
    private String stage;
    private BigDecimal money;
    private String time;

    public AgentCashHistory parse(String json) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(json);
        setStage(jsonUtils.getString("stage"));
        setMoney(jsonUtils.getBigDecimal("money").divide(new BigDecimal(100)));
        setType(E_LOTTERY_TYPE.getIndex(jsonUtils.getInt("cate")).name);
        setTime(StringUtils.date_fromat_change(jsonUtils.getLong("atime") * 1000));
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
