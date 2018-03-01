package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.LogUtil;
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
public class GambleZhangbianHistory implements Serializable {

    private int id;
    private String type;
    private String time;
    private int cate;
    private String strCate;
    private String stage;
    private BigDecimal money;
    private BigDecimal balance;
    private String strType;

    public GambleZhangbianHistory parse(String str) throws JSONException {
        JsonUtils jsonUtils = new JsonUtils(str);
        setId(jsonUtils.getInt("id"));
        setStrType(jsonUtils.getString("info"));
        setCate(jsonUtils.getInt("cate"));
        setStrCate(E_LOTTERY_TYPE.getIndex(getCate()).name);
        setMoney(jsonUtils.getBigDecimal("coin").divide(new BigDecimal(100)));
        setBalance(jsonUtils.getBigDecimal("balance").divide(new BigDecimal(100)));
        setStage(jsonUtils.getString("stage"));
        LogUtil.logError(GambleZhangbianHistory.class,"create_at:"+(jsonUtils.getLong("create_at") * 1000));
        setTime(StringUtils.date_fromat_change(jsonUtils.getLong("create_at") * 1000));
        return this;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public String getStrCate() {
        return strCate;
    }

    public void setStrCate(String strCate) {
        this.strCate = strCate;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }
}
