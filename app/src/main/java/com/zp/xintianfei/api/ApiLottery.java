package com.zp.xintianfei.api;

import com.zp.xintianfei.AppContext;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2018/2/5 18:43
 * <p>
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
        params.put("cate", cate);
        // 地址
        String url = URLs.getGameNextInfo;

        AppContext.http.get(url, params, callBack, false, false);

    }

    public static void xzRecord(int type_today, int type_yesterday, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        if (type_today >= 0)
            params.put("type_today", type_today);
        if (type_yesterday >= 0)
            params.put("type_yesterday", type_yesterday);
        // 地址
        String url = URLs.xzRecord;
        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void czRecord(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.czRecord;
        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void txRecord(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.txRecord;
        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getLotteryWei(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        // 地址
        String url = URLs.getLotteryWei;
        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getPlazaGameList(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid",AppContext.user.getUid());
        // 地址
        String url = URLs.getPlazaGameList;
        AppContext.http.get(url, params, callBack, false, false);
    }
}
