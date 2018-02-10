package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;

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

    public RechargeHistoryList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JSONArray jsonArray = j.getJSONArray("info");
        for (int i = 0; i < jsonArray.length(); i++) {
            RechargeHistory rechargeHistory = new RechargeHistory();
            rechargeHistory.parse(jsonArray.getString(i));
            list.add(rechargeHistory);
        }
        return this;
    }

    public List<RechargeHistory> getList() {
        return list;
    }

    public void setList(List<RechargeHistory> list) {
        this.list = list;
    }
}
