package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;

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
public class RechargeHistory implements Serializable {

    private int id;
    private String time;
    private String status;
    private BigDecimal money;

    public RechargeHistory parse(String json) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(json);
        setId(jsonUtils.getInt("id"));
        setMoney(jsonUtils.getBigDecimal("money").divide(new BigDecimal(100)));
        setTime(StringUtils.date_fromat_change(jsonUtils.getLong("create_at") * 1000));
        setStatus(E_RECHARGE_STATUE_TYPE.getIndex(jsonUtils.getInt("state")).name);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
