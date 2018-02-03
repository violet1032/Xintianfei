package com.zp.xintianfei.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class RechargeHistoryList implements Serializable {

    private List<RechargeHistory> list = new ArrayList<>();

    public List<RechargeHistory> getList() {
        return list;
    }

    public void setList(List<RechargeHistory> list) {
        this.list = list;
    }
}
