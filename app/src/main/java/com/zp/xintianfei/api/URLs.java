package com.zp.xintianfei.api;

/**
 * Created by Administrator on 2016/5/18.
 */
public class URLs {

    public static String IP = "www.2r9gv.cn"; // ip
//        public static String IP = "192.168.1.119:8087"; // ip
    public static String HOST = IP;
    public final static String HTTP = "http://";
    private final static String URL_SPLITTER = "/";
    public final static String COMMON = "home/app/"; // 公用部分
//        public final static String APP_STORE_HOST = HTTP + HOST + URL_SPLITTER;
    public final static String APP_STORE_HOST = HTTP + HOST + URL_SPLITTER + COMMON;

    public final static String LOGIN = APP_STORE_HOST + "appLogin"; // 微信qq登录
    public final static String getOnlineNum = APP_STORE_HOST + "getOnlineNum"; // 获取在线人数

    public final static String getPlazaGameState = APP_STORE_HOST + "getPlazaGameState"; // 获取游戏时间状态信息
    public final static String getGameNextInfo = APP_STORE_HOST + "getGameNextInfo"; // 获取下一期游戏时间状态信息
    public final static String getBankList = APP_STORE_HOST + "getBankList"; // 获取银行列表
    public final static String bindBank = APP_STORE_HOST + "bindBank"; // 绑定银行卡
    public final static String cz = APP_STORE_HOST + "cz"; // 充值
    public final static String tx = APP_STORE_HOST + "tx"; // 提现

    public final static String LOGOUT = APP_STORE_HOST + "api/user/logout"; // 退出登录
    public final static String REGIST = APP_STORE_HOST + "api/user/regist";//注册
    public final static String IMG_UPLOAD = APP_STORE_HOST + "file/upload/avatarUploa";// 图片上传

    public final static String getGameRule = APP_STORE_HOST + "getGameRule";// 获取规则
    public final static String FORGET_CHANGE = APP_STORE_HOST + "api/user/forgetPswChange";// 忘记密码 修改密码
    public final static String CHANGE_PSW = APP_STORE_HOST + "api/user/changePassword";// 正常修改密码

    //-----部分暂未发出来的接口-----

}
