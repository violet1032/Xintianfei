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
public class AgentLowerHistory implements Serializable {

    private int id;
    private String nickname;
    private BigDecimal money;
    private String time;

    public AgentLowerHistory parse(String json) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(json);
        setId(jsonUtils.getInt("id"));
        setNickname(jsonUtils.getString("nickname"));
        setMoney(jsonUtils.getBigDecimal("money").divide(new BigDecimal(100)));
        setTime(StringUtils.date_fromat_change(jsonUtils.getLong("create_at")*1000));
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
