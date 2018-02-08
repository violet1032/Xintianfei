package com.zp.xintianfei.api;

import com.zp.xintianfei.AppContext;

import java.util.HashMap;
import java.util.Map;

/**
 * <p/>
 * 描述:
 * <p/>
 * 作者:Administrator
 * <p/>
 * 时间:2018/2/5 18:43
 * <p/>
 * 版本:
 */
public class ApiLottery {
    public static void getGameRule(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        // 地址
        String url = URLs.getGameRule;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getPlazaGameState(FHttpCallBack callBack) {// 参数设置
        Map<String, Object> params = new HashMap<>();
        // 地址
        String url = URLs.getPlazaGameState;

        AppContext.http.get(url, params, callBack, false, false);

    }

    /**
     * 获取下一期游戏时间状态
     *
     * @param cate
     * @param callBack
     */
    public static void getGameNextInfo(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate",cate);
        // 地址
        String url = URLs.getGameNextInfo;

        AppContext.http.get(url, params, callBack, false, false);

    }
}
