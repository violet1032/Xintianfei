package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class GambleSimpleTodayHistoryList implements Serializable {

    private List<GambleSimpleTodayHistory> list = new ArrayList<>();

    public GambleSimpleTodayHistoryList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JSONArray jsonArray = j.getJSONArray("record");
        if (jsonArray != null)
            for (int i = 0; i < jsonArray.length(); i++) {
                GambleSimpleTodayHistory gambleHistory = new GambleSimpleTodayHistory();
                gambleHistory.parse(jsonArray.getString(i));
                list.add(gambleHistory);
            }
        return this;
    }

    public List<GambleSimpleTodayHistory> getList() {
        return list;
    }

    public void setList(List<GambleSimpleTodayHistory> list) {
        this.list = list;
    }
}
