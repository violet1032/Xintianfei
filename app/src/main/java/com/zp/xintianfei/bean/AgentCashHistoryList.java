package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;

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
public class AgentCashHistoryList implements Serializable {

    private List<AgentCashHistory> list = new ArrayList<>();

    public AgentCashHistoryList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JSONArray jsonArray = j.getJSONArray("info");
        for (int i = 0;i<jsonArray.length();i++){
            AgentCashHistory agentCashHistory = new AgentCashHistory();
            agentCashHistory.parse(jsonArray.getString(i));
            list.add(agentCashHistory);
        }
        return this;
    }

    public List<AgentCashHistory> getList() {
        return list;
    }

    public void setList(List<AgentCashHistory> list) {
        this.list = list;
    }
}
