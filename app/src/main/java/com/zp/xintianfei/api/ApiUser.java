package com.zp.xintianfei.api;


import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.utils.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2018/2/5 15:09
 * <p>
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
        String url = URLs.tx;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void exchangeYJ(int money, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("money", money);
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.exchangeYJ;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void exchangeFS(int money, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("money", money);
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.exchangeFS;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void setFsRate(int value, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("value", value);
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.setFsRate;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getTJMembers(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getTJMembers;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void getTJRecords(long from, long to, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        if (from > 0)
            params.put("from", from / 1000);
        if (to > 0)
            params.put("to", to / 1000);
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getTJRecords;

        AppContext.http.get(url, params, callBack, false, false);
    }

    public static void bindAlipay(String username, String account, String path, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("zfb_username", username);
        params.put("zfb_account", account);
        File file = null;
        if (!StringUtils.isEmpty(path))
            file = new File(path);
        // 地址
        String url = URLs.bindAlipay;

        AppContext.http.postFile(url, params, file, callBack);
    }

    public static void bindWeiXin(String username, String path, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("wx_username", username);
        File file = null;
        if (!StringUtils.isEmpty(path))
            file = new File(path);
        // 地址
        String url = URLs.bindWeiXin;

        AppContext.http.postFile(url, params, file, callBack);
    }

    public static void getMemberInfo(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getMemberInfo;

        AppContext.http.get(url, params, callBack);
    }

    public static void getSystemConfig(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        // 地址
        String url = URLs.getSystemConfig;

        AppContext.http.get(url, params, callBack);
    }

    public static void getMemberBank(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getMemberBank;

        AppContext.http.get(url, params, callBack);
    }

    public static void getMemberWeiXin(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getMemberWeiXin;

        AppContext.http.get(url, params, callBack);
    }

    public static void getMemberAlipay(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getMemberAlipay;

        AppContext.http.get(url, params, callBack);
    }

    public static void getMemberMoney(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getMemberMoney;

        AppContext.http.get(url, params, callBack);
    }

    public static void zz(int transfer_out, int transfer_in, BigDecimal money, FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        params.put("transfer_out", transfer_out);
        params.put("transfer_in", transfer_in);
        params.put("money", money);
        // 地址
        String url = URLs.zz;

        AppContext.http.get(url, params, callBack);
    }
    public static void getAgentQRCode(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("uid", AppContext.user.getUid());
        // 地址
        String url = URLs.getAgentQRCode;

        AppContext.http.get(url, params, callBack);
    }
    public static void getAppVersion(FHttpCallBack callBack) {
        // 参数设置
        Map<String, Object> params = new HashMap<>();
        params.put("type", 0);
        // 地址
        String url = URLs.getAppVersion;

        AppContext.http.get(url, params, callBack);
    }
}
