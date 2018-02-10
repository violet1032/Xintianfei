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
public enum E_RECHARGE_STATUE_TYPE {
    none(0, "处理中"),
    has(1, "已到账");

    public int value;
    public String name;

    E_RECHARGE_STATUE_TYPE(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static E_RECHARGE_STATUE_TYPE getIndex(int value) {
        E_RECHARGE_STATUE_TYPE[] types = values();
        for (E_RECHARGE_STATUE_TYPE type :
                types) {
            if (type.value == value) {
                return type;
            }
        }
        return none;
    }
}
