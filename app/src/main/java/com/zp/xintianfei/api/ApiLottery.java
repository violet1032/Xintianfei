package com.zp.xintianfei.api;

import com.zp.xintianfei.AppContext;

import java.math.BigDecimal;
import java.net.URLEncoder;
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
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getPlazaGameList;
        AppContext.http.get(url, params, callBack, false, false);
    }

    /**
     * 获取动画路径
     *
     * @param cate
     * @param callBack
     */
    public static void getAnimateUrl(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        // 地址
        String url = URLs.getAnimateUrl;
        AppContext.http.get(url, params, callBack, false, false);
    }

    /**
     * 获取聊天记录
     *
     * @param cate
     * @param last_record_id
     * @param callBack
     */
    public static void getChatMsgs(int cate, int last_record_id, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        params.put("last_record_id", last_record_id);
        // 地址
        String url = URLs.getChatMsgs;
        AppContext.http.get(url, params, callBack, false, false);
    }

    /**
     * 下注
     * @param cate
     * @param stage
     * @param content
     * @param callBack
     */
    public static void gameBet(int cate, String stage,String content, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("cate", cate);
        params.put("stage", stage);
        params.put("content", content);
        // 地址
        String url = URLs.gameBet;
        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getRecordToday(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("cate", cate);
        // 地址
        String url = URLs.getRecordToday;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void getRecordYesterday(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("cate", cate);
        // 地址
        String url = URLs.getRecordYesterday;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void getRecordBeforeYesterday(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("cate", cate);
        // 地址
        String url = URLs.getRecordBeforeYesterday;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void getRecordAll(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("cate", cate);
        // 地址
        String url = URLs.getRecordAll;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void withdraw(int id, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("id", id);
        // 地址
        String url = URLs.withdraw;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void getTrendsURL(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        // 地址
        String url = URLs.getTrendsURL;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void getGameLastInfo(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        // 地址
        String url = URLs.getGameLastInfo;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void getOdds(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        // 地址
        String url = URLs.getOdds;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void quickBetUrl(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("token", AppContext.user.getOpenid());
        params.put("cate", cate);
        // 地址
        String url = URLs.quickBetUrl;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void gameBetSix(int cate,String stage,int type,BigDecimal money,String number,int wei, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        params.put("stage", stage);
        params.put("type", type);
        params.put("money", money);
        params.put("number", URLEncoder.encode(number));
        params.put("wei", wei);
        // 地址
        String url = URLs.gameBetSix;
        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getGameState(int cate, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("cate", cate);
        // 地址
        String url = URLs.getGameState;
        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void getAllGameState(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        // 地址
        String url = URLs.getAllGameState;
        AppContext.http.get(url, params, callBack, false, false);
    }
}
