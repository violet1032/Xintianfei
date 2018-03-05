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
public enum E_NUMBER {
    num_1(1, "鸡"),
    num_2(2, "猴"),
    num_3(3, "羊"),
    num_4(4, "马"),
    num_5(5, "蛇"),
    num_6(6, "龙"),
    num_7(7, "兔"),
    num_8(8, "虎"),
    num_9(9, "牛"),
    num_10(10, "鼠"),
    num_11(11, "猪"),
    num_12(12, "狗"),
    num_13(13, "鸡"),
    num_14(14, "猴"),
    num_15(15, "羊"),
    num_16(16, "马"),
    num_17(17, "蛇"),
    num_18(18, "龙"),
    num_19(19, "兔"),
    num_20(20, "虎"),
    num_21(21, "牛"),
    num_22(22, "鼠"),
    num_23(23, "猪"),
    num_24(24, "狗"),
    num_25(25, "鸡"),
    num_26(26, "猴"),
    num_27(27, "羊"),
    num_28(28, "马"),
    num_29(29, "蛇"),
    num_30(30, "龙"),
    num_31(31, "兔"),
    num_32(32, "虎"),
    num_33(33, "牛"),
    num_34(34, "鼠"),
    num_35(35, "猪"),
    num_36(36, "狗"),
    num_37(37, "鸡"),
    num_38(38, "猴"),
    num_39(39, "羊"),
    num_40(40, "马"),
    num_41(41, "蛇"),
    num_42(42, "龙"),
    num_43(43, "兔"),
    num_44(44, "虎"),
    num_45(45, "牛"),
    num_46(46, "鼠"),
    num_47(47, "猪"),
    num_48(48, "狗"),
    num_49(49, "鸡");

    public int value;
    public String name;

    E_NUMBER(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static E_NUMBER getIndex(int value) {
        E_NUMBER[] types = values();
        for (E_NUMBER type :
                types) {
            if (type.value == value) {
                return type;
            }
        }
        return num_1;
    }
}
