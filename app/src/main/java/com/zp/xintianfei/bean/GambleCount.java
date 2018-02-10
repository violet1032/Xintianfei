package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONException;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/2/10 0010.
 */
public class GambleCount implements Serializable{
    private BigDecimal total_cz_today;
    private BigDecimal total_cz_yesterday;
    private BigDecimal total_tx_today;
    private BigDecimal total_tx_yesterday;
    private BigDecimal total_xz_today;
    private BigDecimal total_xz_yesterday;
    private BigDecimal total_yl_today;
    private BigDecimal total_yl_yesterday;
    private BigDecimal total_zj_today;
    private BigDecimal total_zj_yesterday;

    public GambleCount parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JsonUtils jsonUtils = j.getJSONUtils("info");
        setTotal_cz_today(jsonUtils.getBigDecimal("total_cz_today"));
        setTotal_cz_yesterday(jsonUtils.getBigDecimal("total_cz_yesterday"));
        setTotal_tx_today(jsonUtils.getBigDecimal("total_tx_today"));
        setTotal_tx_yesterday(jsonUtils.getBigDecimal("total_tx_yesterday"));
        setTotal_xz_today(jsonUtils.getBigDecimal("total_xz_today"));
        setTotal_xz_yesterday(jsonUtils.getBigDecimal("total_xz_yesterday"));
        setTotal_yl_today(jsonUtils.getBigDecimal("total_yl_today"));
        setTotal_yl_yesterday(jsonUtils.getBigDecimal("total_yl_yesterday"));
        setTotal_zj_today(jsonUtils.getBigDecimal("total_zj_today"));
        setTotal_zj_yesterday(jsonUtils.getBigDecimal("total_zj_yesterday"));
        return this;
    }

    public BigDecimal getTotal_cz_today() {
        return total_cz_today;
    }

    public void setTotal_cz_today(BigDecimal total_cz_today) {
        this.total_cz_today = total_cz_today;
    }

    public BigDecimal getTotal_cz_yesterday() {
        return total_cz_yesterday;
    }

    public void setTotal_cz_yesterday(BigDecimal total_cz_yesterday) {
        this.total_cz_yesterday = total_cz_yesterday;
    }

    public BigDecimal getTotal_tx_today() {
        return total_tx_today;
    }

    public void setTotal_tx_today(BigDecimal total_tx_today) {
        this.total_tx_today = total_tx_today;
    }

    public BigDecimal getTotal_tx_yesterday() {
        return total_tx_yesterday;
    }

    public void setTotal_tx_yesterday(BigDecimal total_tx_yesterday) {
        this.total_tx_yesterday = total_tx_yesterday;
    }

    public BigDecimal getTotal_xz_today() {
        return total_xz_today;
    }

    public void setTotal_xz_today(BigDecimal total_xz_today) {
        this.total_xz_today = total_xz_today;
    }

    public BigDecimal getTotal_xz_yesterday() {
        return total_xz_yesterday;
    }

    public void setTotal_xz_yesterday(BigDecimal total_xz_yesterday) {
        this.total_xz_yesterday = total_xz_yesterday;
    }

    public BigDecimal getTotal_yl_today() {
        return total_yl_today;
    }

    public void setTotal_yl_today(BigDecimal total_yl_today) {
        this.total_yl_today = total_yl_today;
    }

    public BigDecimal getTotal_yl_yesterday() {
        return total_yl_yesterday;
    }

    public void setTotal_yl_yesterday(BigDecimal total_yl_yesterday) {
        this.total_yl_yesterday = total_yl_yesterday;
    }

    public BigDecimal getTotal_zj_today() {
        return total_zj_today;
    }

    public void setTotal_zj_today(BigDecimal total_zj_today) {
        this.total_zj_today = total_zj_today;
    }

    public BigDecimal getTotal_zj_yesterday() {
        return total_zj_yesterday;
    }

    public void setTotal_zj_yesterday(BigDecimal total_zj_yesterday) {
        this.total_zj_yesterday = total_zj_yesterday;
    }
}
