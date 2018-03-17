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
    public final static String getMemberInfo = APP_STORE_HOST + "getMemberInfo"; // 获取用户信息
    public final static String getSystemConfig = APP_STORE_HOST + "getSystemConfig"; // 获取系统配置参数
    public final static String getMemberAlipay = APP_STORE_HOST + "getMemberAlipay"; // 获取用户绑定的支付宝
    public final static String getMemberWeiXin = APP_STORE_HOST + "getMemberWeiXin"; // 获取用户绑定的微信
    public final static String getMemberBank = APP_STORE_HOST + "getMemberBank"; // 获取用户绑定的银行
    public final static String zz = APP_STORE_HOST + "zz"; // 转账
    public final static String getMemberMoney = APP_STORE_HOST + "getMemberMoney"; // 获取转账对象
    public final static String getAgentQRCode = APP_STORE_HOST + "getAgentQRCode"; // 获取会员的推广二维码
    public final static String getAgentPicture = APP_STORE_HOST + "getAgentPicture"; // 获取会员的推广图片
    public final static String getAppVersion = APP_STORE_HOST + "getAppVersion"; // 获取版本

    public final static String getPlazaGameState = APP_STORE_HOST + "getPlazaGameState"; // 获取游戏时间状态信息
    public final static String getGameNextInfo = APP_STORE_HOST + "getGameNextInfo"; // 获取下一期游戏时间状态信息
    public final static String getBankList = APP_STORE_HOST + "getBankList"; // 获取银行列表
    public final static String bindBank = APP_STORE_HOST + "bindBank"; // 绑定银行卡
    public final static String bindWeiXin = APP_STORE_HOST + "bindWeiXin"; // 绑定微信
    public final static String bindAlipay = APP_STORE_HOST + "bindAlipay"; // 绑定支付宝
    public final static String cz = APP_STORE_HOST + "cz"; // 充值
    public final static String tx = APP_STORE_HOST + "tx"; // 提现
    public final static String exchangeYJ = APP_STORE_HOST + "exchangeYJ"; // 佣金兑换
    public final static String exchangeFS = APP_STORE_HOST + "exchangeFS"; // 返水兑换
    public final static String setFsRate = APP_STORE_HOST + "setFsRate"; // 设置返水比例
    public final static String getTJMembers = APP_STORE_HOST + "getTJMembers"; // 获取下级玩家
    public final static String getTJRecords = APP_STORE_HOST + "getTJRecords"; // 获取流水

    public final static String xzRecord = APP_STORE_HOST + "xzRecord"; // 投注记录
    public final static String czRecord = APP_STORE_HOST + "czRecord"; // 充值记录
    public final static String txRecord = APP_STORE_HOST + "txRecord"; // 提现记录
    public final static String getLotteryWei = APP_STORE_HOST + "getLotteryWei"; // 获取位
    public final static String getPlazaGameList = APP_STORE_HOST + "getPlazaGameList"; // 获取游戏列表

    public final static String LOGOUT = APP_STORE_HOST + "api/user/logout"; // 退出登录
    public final static String IMG_UPLOAD = APP_STORE_HOST + "file/upload/avatarUploa";// 图片上传

    public final static String getGameRule = APP_STORE_HOST + "getGameRule";// 获取规则

    public final static String getAnimateUrl = APP_STORE_HOST + "getAnimateUrl"; // 获取动画路径
    public final static String getChatMsgs = APP_STORE_HOST + "getChatMsgs"; // 获取聊天记录
    public final static String gameBet = APP_STORE_HOST + "gameBet"; // 下注
    public final static String getRecordToday = APP_STORE_HOST + "getRecordToday"; // 获取今天彩种记录
    public final static String getRecordYesterday = APP_STORE_HOST + "getRecordYesterday"; // 获取昨天彩种记录
    public final static String getRecordBeforeYesterday = APP_STORE_HOST + "getRecordBeforeYesterday"; // 获取前天彩种记录
    public final static String getRecordAll = APP_STORE_HOST + "getRecordAll"; // 获取所有彩种记录
    public final static String withdraw = APP_STORE_HOST + "withdraw"; // 撤单
    public final static String getTrendsURL = APP_STORE_HOST + "getTrendsURL"; // 获取走势图页面地址
    public final static String getGameLastInfo = APP_STORE_HOST + "getGameLastInfo"; // 获取最后一期信息
    public final static String getOdds = APP_STORE_HOST + "getOdds"; // 获取赔率
    public final static String gameBetSix = APP_STORE_HOST + "gameBetSix"; // 六合彩下注
    public final static String getNotice = APP_STORE_HOST + "getNotice"; // 获取公告
    public final static String quickBetUrl = APP_STORE_HOST + "quickBetUrl"; // 获取急速下注url
    public final static String getGameState = APP_STORE_HOST + "getGameState"; // 获取彩种状态
    public final static String getAllGameState = APP_STORE_HOST + "getAllGameState"; // 获取所有彩种状态


    //-----部分暂未发出来的接口-----

}
