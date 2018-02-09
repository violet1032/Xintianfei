package com.zp.xintianfei.api;


import com.zp.xintianfei.AppContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p/>
 * 描述:
 * <p/>
 * 作者:Administrator
 * <p/>
 * 时间:2018/2/5 15:09
 * <p/>
 * 版本:
 */
public class ApiUser {

    public static void appLogin(String unionid, String openid, String nickname, String headimgurl, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("unionid", unionid);
        params.put("openid", openid);
        params.put("nickname", nickname);
        params.put("headimgurl", headimgurl);

        // 地址
        String url = URLs.LOGIN;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void logout(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        // 地址
        String url = URLs.LOGOUT;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getOnlineNum(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        // 地址
        String url = URLs.getOnlineNum;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getBankList(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        // 地址
        String url = URLs.getBankList;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void bindBank(int bankid, String number, String realname, String bankname, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("bank_branch", bankname);
        params.put("bank_owner", realname);
        params.put("bank_account", number);
        params.put("bankid", bankid);
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.bindBank;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void cz(int bankid, String name, BigDecimal money, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("money", money);
        params.put("bankid", bankid);
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.cz;

        AppContext.http.get(url, params, callBack, false, false);
    }
    public static void tx(int bankid, BigDecimal money, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("money", money);
        params.put("bankid", bankid);
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.cz;

        AppContext.http.get(url, params, callBack, false, false);
    }
}
