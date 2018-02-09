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
public class BankList implements Serializable {
    private List<Bank> list = new ArrayList<>();

    public BankList parse(String str) {
        try {
            JsonUtils j = new JsonUtils(str);
            JSONArray jsonArray = j.getJSONArray("info");
            for (int i = 0; i < jsonArray.length(); i++) {
                Bank bank = new Bank().parse(jsonArray.getString(i));
                list.add(bank);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<Bank> getList() {
        return list;
    }

    public void setList(List<Bank> list) {
        this.list = list;
    }
}
