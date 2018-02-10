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
public class GambleZhangbianHistoryList implements Serializable {

    private List<GambleZhangbianHistory> listToday = new ArrayList<>();
    private List<GambleZhangbianHistory> listYestoday = new ArrayList<>();

    public GambleZhangbianHistoryList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JsonUtils jsonUtils = j.getJSONUtils("info");
        JSONArray jsonArray = jsonUtils.getJSONArray("zb_today");
        for (int i = 0; i < jsonArray.length(); i++) {
            GambleZhangbianHistory gambleHistory = new GambleZhangbianHistory();
            gambleHistory.parse(jsonArray.getString(i));
            listToday.add(gambleHistory);
        }
        JSONArray jsonArray2 = jsonUtils.getJSONArray("zb_yesterday");
        for (int i = 0; i < jsonArray2.length(); i++) {
            GambleZhangbianHistory gambleHistory = new GambleZhangbianHistory();
            gambleHistory.parse(jsonArray2.getString(i));
            listYestoday.add(gambleHistory);
        }
        return this;
    }

    public List<GambleZhangbianHistory> getListToday() {
        return listToday;
    }

    public void setListToday(List<GambleZhangbianHistory> listToday) {
        this.listToday = listToday;
    }

    public List<GambleZhangbianHistory> getListYestoday() {
        return listYestoday;
    }

    public void setListYestoday(List<GambleZhangbianHistory> listYestoday) {
        this.listYestoday = listYestoday;
    }
}
