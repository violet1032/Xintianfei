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
public enum E_STATUE_TYPE {
    none(0, "未开奖"),
    has(1, "开奖");

    public int value;
    public String name;

    E_STATUE_TYPE(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static E_STATUE_TYPE getIndex(int value) {
        E_STATUE_TYPE[] types = values();
        for (E_STATUE_TYPE type :
                types) {
            if (type.value == value) {
                return type;
            }
        }
        return none;
    }
}
