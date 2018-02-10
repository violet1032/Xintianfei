package com.zp.xintianfei.bean;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2016/7/5 10:35
 * <p>
 * 版本:
 */
public enum E_LOTTERY_TYPE {
    cqssc(1, "重庆时时彩"),
    bjsc(2, "北京赛车"),
    xyft(3, "幸运飞艇"),
    pcdd(4, "PC蛋蛋"),
    xgsm(5, "香港赛马"),
    jsks(6, "江苏快三"),
    txffc(7, "腾讯分分彩"),
    jnd28(8, "加拿大28"),
    qqlfc(9, "QQ两分彩"),
    lhc(10, "六合彩"),
    ag(11, "AG");

    public int value;
    public String name;

    E_LOTTERY_TYPE(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static E_LOTTERY_TYPE getIndex(int value) {
        E_LOTTERY_TYPE[] types = values();
        for (E_LOTTERY_TYPE type :
                types) {
            if (type.value == value) {
                return type;
            }
        }
        return cqssc;
    }
}
