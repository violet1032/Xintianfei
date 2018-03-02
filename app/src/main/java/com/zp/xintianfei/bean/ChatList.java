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
public class ChatList implements Serializable {
    private List<Chat> list = new ArrayList<>();

    public ChatList parse(String str) {
        try {
            JsonUtils j = new JsonUtils(str);
            JSONArray jsonArray = j.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                Chat chat = new Chat().parse(jsonArray.getString(i));
                list.add(chat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<Chat> getList() {
        return list;
    }

    public void setList(List<Chat> list) {
        this.list = list;
    }
}
