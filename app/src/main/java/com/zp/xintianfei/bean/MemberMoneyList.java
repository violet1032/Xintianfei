package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
public class MemberMoneyList implements Serializable {

    private List<MemberMoney> list = new ArrayList<>();

    public MemberMoneyList parse(String str) throws JSONException {
        JsonUtils j = new JsonUtils(str);
        JSONObject jsonObject = j.getJSONObject("info");
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String id = it.next();
            MemberMoney memberMoney = new MemberMoney();
            memberMoney.parse(jsonObject.getJSONObject(id).toString(), Integer.parseInt(id));
            list.add(memberMoney);
        }
        return this;
    }

    public List<MemberMoney> getList() {
        return list;
    }

    public void setList(List<MemberMoney> list) {
        this.list = list;
    }
}
