package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.GambleFuliHistoryAdapter;
import com.zp.xintianfei.adapter.GambleHistoryAdapter;
import com.zp.xintianfei.adapter.GambleZhangbianHistoryAdapter;
import com.zp.xintianfei.bean.GambleFuliHistory;
import com.zp.xintianfei.bean.GambleFuliHistoryList;
import com.zp.xintianfei.bean.GambleHistory;
import com.zp.xintianfei.bean.GambleHistoryList;
import com.zp.xintianfei.bean.GambleZhangbianHistory;
import com.zp.xintianfei.bean.GambleZhangbianHistoryList;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class GambleHistoryFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_gamble_history_list)
    private ListView lvGamble;
    private GambleHistoryAdapter gambleHistoryAdapter;
    private GambleHistoryList gambleHistoryList = new GambleHistoryList();
    @BindView(id = R.id.act_gamble_history_list_zhangbian)
    private ListView lvGambleZhangbian;
    private GambleZhangbianHistoryAdapter gambleZhangbianHistoryAdapter;
    private GambleZhangbianHistoryList gambleZhangbianHistoryList = new GambleZhangbianHistoryList();
    @BindView(id = R.id.act_gamble_history_list_fuli)
    private ListView lvGambleFuli;
    private GambleFuliHistoryAdapter gambleFuliHistoryAdapter;
    private GambleFuliHistoryList gambleFuliHistoryList = new GambleFuliHistoryList();

    @BindView(id = R.id.fg_gamble_history_lay_todaygamble)
    private RelativeLayout layGamble;
    @BindView(id = R.id.fg_gamble_history_lay_todayzhangbian)
    private RelativeLayout layZhangbian;
    @BindView(id = R.id.fg_gamble_history_lay_todayfuli)
    private RelativeLayout layFuli;

    @BindView(id = R.id.fg_gamble_history_btn_today, click = true)
    private Button btnToday;
    @BindView(id = R.id.fg_gamble_history_btn_yeaterday, click = true)
    private Button btnYestoday;
    @BindView(id = R.id.fg_gamble_history_btn_todaygamble, click = true)
    private Button btnTodayGamble;
    @BindView(id = R.id.fg_gamble_history_btn_todayzhangbian, click = true)
    private Button btnTodayZhangbian;
    @BindView(id = R.id.fg_gamble_history_btn_todayfuli, click = true)
    private Button btnTodayFuli;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_gamble_history, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText(R.string.gamble_history_text_1);

        /******/
        GambleHistory gambleHistory = new GambleHistory();
        gambleHistory.setType("江苏快三");
        gambleHistory.setStatus("2018006");
        gambleHistory.setGameType("三不同竞猜");
        gambleHistory.setNumber("23/34/56");
        gambleHistory.setStatus("状态");
        gambleHistory.setMoney(new BigDecimal(1000));
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);
        gambleHistoryList.getList().add(gambleHistory);


        GambleZhangbianHistory gambleZhangbianHistory = new GambleZhangbianHistory();
        gambleZhangbianHistory.setTime("2018.01.01");
        gambleZhangbianHistory.setType("充值");
        gambleZhangbianHistory.setMoney(new BigDecimal(1000));
        gambleZhangbianHistory.setBalance(new BigDecimal(1000));
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);
        gambleZhangbianHistoryList.getList().add(gambleZhangbianHistory);

        GambleFuliHistory gambleFuliHistory = new GambleFuliHistory();
        gambleFuliHistory.setTime("2018.01.04");
        gambleFuliHistory.setType("福利类型");
        gambleFuliHistory.setMoney(new BigDecimal(1000));
        gambleFuliHistoryList.getList().add(gambleFuliHistory);
        gambleFuliHistoryList.getList().add(gambleFuliHistory);
        gambleFuliHistoryList.getList().add(gambleFuliHistory);
        gambleFuliHistoryList.getList().add(gambleFuliHistory);
        gambleFuliHistoryList.getList().add(gambleFuliHistory);
        gambleFuliHistoryList.getList().add(gambleFuliHistory);
        /******/
        gambleHistoryAdapter = new GambleHistoryAdapter(lvGamble, gambleHistoryList.getList());
        lvGamble.setAdapter(gambleHistoryAdapter);
        gambleZhangbianHistoryAdapter = new GambleZhangbianHistoryAdapter(lvGambleZhangbian, gambleZhangbianHistoryList.getList());
        lvGambleZhangbian.setAdapter(gambleZhangbianHistoryAdapter);
        gambleFuliHistoryAdapter = new GambleFuliHistoryAdapter(lvGambleFuli, gambleFuliHistoryList.getList());
        lvGambleFuli.setAdapter(gambleFuliHistoryAdapter);

        // 初始化 今日，今日投注
        btnToday.setBackgroundResource(R.drawable.btn_left);
        btnYestoday.setBackgroundResource(R.drawable.btn_right_black);
        btnTodayGamble.setText(R.string.gamble_history_text_4);
        btnTodayZhangbian.setText(R.string.gamble_history_text_5);
        btnTodayFuli.setText(R.string.gamble_history_text_6);
        btnTodayGamble.setBackgroundResource(R.drawable.btn_round);
        btnTodayZhangbian.setBackgroundResource(R.drawable.btn_round_black);
        btnTodayFuli.setBackgroundResource(R.drawable.btn_round_black);
        layGamble.setVisibility(View.VISIBLE);
        layZhangbian.setVisibility(View.GONE);
        layFuli.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                ((MainActivity) getActivity()).setPosition(4);
                break;
            case R.id.fg_gamble_history_btn_today:
                btnToday.setBackgroundResource(R.drawable.btn_left);
                btnYestoday.setBackgroundResource(R.drawable.btn_right_black);
                btnTodayGamble.setText(R.string.gamble_history_text_4);
                btnTodayZhangbian.setText(R.string.gamble_history_text_5);
                btnTodayFuli.setText(R.string.gamble_history_text_6);
                break;
            case R.id.fg_gamble_history_btn_yeaterday:
                btnToday.setBackgroundResource(R.drawable.btn_left_black);
                btnYestoday.setBackgroundResource(R.drawable.btn_right);
                btnTodayGamble.setText(R.string.gamble_history_text_21);
                btnTodayZhangbian.setText(R.string.gamble_history_text_22);
                btnTodayFuli.setText(R.string.gamble_history_text_23);
                break;
            case R.id.fg_gamble_history_btn_todaygamble:
                btnTodayGamble.setBackgroundResource(R.drawable.btn_round);
                btnTodayZhangbian.setBackgroundResource(R.drawable.btn_round_black);
                btnTodayFuli.setBackgroundResource(R.drawable.btn_round_black);
                layGamble.setVisibility(View.VISIBLE);
                layZhangbian.setVisibility(View.GONE);
                layFuli.setVisibility(View.GONE);
                break;
            case R.id.fg_gamble_history_btn_todayzhangbian:
                btnTodayGamble.setBackgroundResource(R.drawable.btn_round_black);
                btnTodayZhangbian.setBackgroundResource(R.drawable.btn_round);
                btnTodayFuli.setBackgroundResource(R.drawable.btn_round_black);
                layGamble.setVisibility(View.GONE);
                layZhangbian.setVisibility(View.VISIBLE);
                layFuli.setVisibility(View.GONE);
                break;
            case R.id.fg_gamble_history_btn_todayfuli:
                btnTodayGamble.setBackgroundResource(R.drawable.btn_round_black);
                btnTodayZhangbian.setBackgroundResource(R.drawable.btn_round_black);
                btnTodayFuli.setBackgroundResource(R.drawable.btn_round);
                layGamble.setVisibility(View.GONE);
                layZhangbian.setVisibility(View.GONE);
                layFuli.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
