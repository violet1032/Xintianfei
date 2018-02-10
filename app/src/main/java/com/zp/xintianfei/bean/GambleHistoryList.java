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
public class GambleHistoryList implements Serializable {

    private List<GambleHistory> listToday = new ArrayList<>();
    private List<GambleHistory> listYestoday = new ArrayList<>();

    public GambleHistoryList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JsonUtils jsonUtils = j.getJSONUtils("info");
        JSONArray jsonArray = jsonUtils.getJSONArray("list_today");
        for (int i = 0; i < jsonArray.length(); i++) {
            GambleHistory gambleHistory = new GambleHistory();
            gambleHistory.parse(jsonArray.getString(i));
            listToday.add(gambleHistory);
        }
        JSONArray jsonArray2 = jsonUtils.getJSONArray("list_yesterday");
        for (int i = 0; i < jsonArray2.length(); i++) {
            GambleHistory gambleHistory = new GambleHistory();
            gambleHistory.parse(jsonArray2.getString(i));
            listYestoday.add(gambleHistory);
        }
        return this;
    }

    public List<GambleHistory> getListToday() {
        return listToday;
    }

    public void setListToday(List<GambleHistory> listToday) {
        this.listToday = listToday;
    }

    public List<GambleHistory> getListYestoday() {
        return listYestoday;
    }

    public void setListYestoday(List<GambleHistory> listYestoday) {
        this.listYestoday = listYestoday;
    }
}
