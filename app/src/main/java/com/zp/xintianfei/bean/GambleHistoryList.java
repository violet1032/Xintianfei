package com.zp.xintianfei.bean;

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
public class GambleHistoryList implements Serializable {

    private List<GambleHistory> list = new ArrayList<>();

    public List<GambleHistory> getList() {
        return list;
    }

    public void setList(List<GambleHistory> list) {
        this.list = list;
    }
}
