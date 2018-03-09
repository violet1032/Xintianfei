package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.LogUtil;

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
public class OddsList implements Serializable {

    private List<Odds> list = new ArrayList<>();

    public OddsList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JSONArray jsonArray = j.getJSONArray("info");
        for (int i = 0; i < jsonArray.length(); i++) {
            Odds odds = new Odds();
            odds.parse(jsonArray.getString(i));
            list.add(odds);
        }
        return this;
    }

    public Odds getByType(int type) {
        for (Odds odds :
                list) {
            if (type == odds.getType())
                return odds;
        }
        return null;
    }

    public Odds getByTypeAndRule(int type, String rule) {
        for (Odds odds :
                list) {
            if (type == odds.getType()) {
                LogUtil.logError(OddsList.class,"odds.getRule():"+odds.getRule());
                LogUtil.logError(OddsList.class,"rule:"+rule);
                if (odds.getRule().contains(".") && odds.getRule().contains(rule))
                    return odds;
                else if (odds.getRule().equals(rule))
                    return odds;
            }
        }
        return null;
    }

    public List<Odds> getList() {
        return list;
    }

    public void setList(List<Odds> list) {
        this.list = list;
    }
}
