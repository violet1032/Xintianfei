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
public enum E_RESULT_TYPE {
    lose(0, "未猜中"),
    win(1, "猜中");

    public int value;
    public String name;

    E_RESULT_TYPE(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static E_RESULT_TYPE getIndex(int value) {
        E_RESULT_TYPE[] types = values();
        for (E_RESULT_TYPE type :
                types) {
            if (type.value == value) {
                return type;
            }
        }
        return lose;
    }
}
