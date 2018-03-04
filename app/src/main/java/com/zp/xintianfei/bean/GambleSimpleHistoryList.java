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
public class GambleSimpleHistoryList implements Serializable {

    private List<GambleSimpleHistory> list = new ArrayList<>();

    public GambleSimpleHistoryList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JSONArray jsonArray = j.getJSONArray("record");
        if (jsonArray != null)
            for (int i = 0; i < jsonArray.length(); i++) {
                GambleSimpleHistory gambleHistory = new GambleSimpleHistory();
                gambleHistory.parse(jsonArray.getString(i));
                list.add(gambleHistory);
            }
        return this;
    }

    public List<GambleSimpleHistory> getList() {
        return list;
    }

    public void setList(List<GambleSimpleHistory> list) {
        this.list = list;
    }
}
