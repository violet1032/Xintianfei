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
public class GameStatusList implements Serializable {

    private List<GameStatus> list = new ArrayList<>();

    public GameStatusList parse(String str){
        try {
            JsonUtils j = new JsonUtils(str);
            JSONObject jsonObject = j.getJSONObject("info");
            if (jsonObject != null) {
                Iterator<String> i = jsonObject.keys();
                while (i.hasNext()) {
                    String key = i.next();
                    JsonUtils jsonUtils = new JsonUtils(jsonObject.getJSONObject(key).toString());
                    if (jsonUtils != null) {
                        setCate(jsonUtils.getInt("cate"));
                        setIsrun(jsonUtils.getBoolean("isrun"));
                        setIsopen(jsonUtils.getBoolean("isopen"));
                        setCountdown(jsonUtils.getInt("countdown"));
                        setFtime(jsonUtils.getInt("ftime"));
                        setStart_time(jsonUtils.getLong("start_time") * 1000);
                        setEnd_time(jsonUtils.getLong("end_time") * 1000);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public List<GameStatus> getList() {
        return list;
    }

    public void setList(List<GameStatus> list) {
        this.list = list;
    }
}
