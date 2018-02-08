package com.zp.xintianfei.bean;

import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
public class GameStatusList implements Serializable {

    private Map<Integer, GameStatus> map = new HashMap<>();

    public GameStatusList parse(String str) {
        try {
            JsonUtils j = new JsonUtils(str);
            JSONObject jsonObject = j.getJSONObject("info");
            if (jsonObject != null) {
                Iterator<String> i = jsonObject.keys();
                while (i.hasNext()) {
                    String key = i.next();
                    GameStatus gameStatus = new GameStatus();
                    gameStatus.parseJson(jsonObject.getJSONObject(key).toString());
                    map.put(gameStatus.getCate(), gameStatus);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Map<Integer, GameStatus> getMap() {
        return map;
    }

    public void setMap(Map<Integer, GameStatus> map) {
        this.map = map;
    }
}
