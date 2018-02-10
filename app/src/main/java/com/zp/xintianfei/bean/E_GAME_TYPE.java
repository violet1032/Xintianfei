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
public enum E_GAME_TYPE {
    cqssc_lh(1, 1, "龙虎"),
    cqssc_dw(1, 2, "定位"),
    cqssc_lm(1, 3, "连码"),
    cqssc_hywf(1, 4, "花样玩法"),
    cqssc_zh(1, 5, "总和"),
    cqssc_dw2(1, 6, "定位2"),
    bjsc_jcch(2, 1, "竞猜车号"),
    bjsc_gyhz(2, 2, "冠亚和值"),
    bjsc_dxds(2, 3, "大小单双"),
    bjsc_lhd(2, 4, "龙虎斗"),
    bjsc_zx(2, 5, "庄闲"),
    xyft_jcch(3, 1, "竞猜船号"),
    xyft_gyhz(3, 2, "冠亚和值"),
    xyft_dxds(3, 3, "大小单双"),
    xyft_lhd(3, 4, "龙虎斗"),
    xyft_zx(3, 5, "庄闲"),
    pcdd_zh(4, 1, "猜组合"),
    pcdd_tm(4, 2, "猜特码"),
    pcdd_dxds(4, 3, "猜大小单双"),
    pcdd_jdjx(4, 4, "猜极大极小"),
    pcdd_qwwf(4, 5, "趣味玩法"),
    xgsm_jcch(5, 1, "竞猜马号"),
    xgsm_gyhz(5, 2, "冠亚和值"),
    xgsm_dxds(5, 3, "大小单双"),
    xgsm_lhd(5, 4, "龙虎斗"),
    xgsm_zx(5, 5, "庄闲"),
    jsks_hztm(6, 1, "和值特码"),
    jsks_sthd(6, 2, "三同号单选"),
    jsks_ethf(6, 3, "二同号复选"),
    jsks_sbth(6, 4, "三不同号"),
    jsks_slht(6, 5, "三连号通选"),
    jsks_dxds(6, 6, "大小单双"),
    txffc_lh(7, 1, "龙虎"),
    txffc_dw(7, 2, "定位"),
    txffc_dm(7, 3, "单码"),
    txffc_hywf(7, 4, "花样玩法"),
    txffc_zh(7, 5, "总和"),
    txffc_sw2(7, 6, "定位2"),
    jnd28_zh(8, 1, "猜组合"),
    jnd28_tm(8, 2, "猜特码"),
    jnd28_dxds(8, 3, "猜大小单双"),
    jnd28_jdjx(8, 4, "猜极大极小"),
    jnd28_qwwf(8, 5, "趣味玩法"),
    qqlfc_qwwf(9, 1, ""),
    qqlfc_lh(9, 1, "龙虎"),
    qqlfc_dw(9, 2, "定位"),
    qqlfc_dm(9, 3, "连码"),
    qqlfc_hywf(9, 4, "花样玩法"),
    qqlfc_zh(9, 5, "总和"),
    qqlfc_sw2(9, 6, "定位2"),
    lhc_tm(10, 6, "特码"),
    lhc_tmsx(10, 6, "特码生肖"),
    lhc_tmbs(10, 6, "特码波色"),
    lhc_ztm(10, 6, "正特码"),
    lhc_pm(10, 6, "平码"),
    lhc_ws(10, 6, "尾数"),
    lhc_sxl(10, 6, "生肖连");

    public int value;
    public int cate;
    public String name;

    E_GAME_TYPE(int cate, int value, String name) {
        this.value = value;
        this.cate = cate;
        this.name = name;
    }

    public static E_GAME_TYPE getIndex(int cate, int value) {
        E_GAME_TYPE[] types = values();
        for (E_GAME_TYPE type :
                types) {
            if (type.value == value && type.cate == cate) {
                return type;
            }
        }
        return cqssc_lh;
    }
}
