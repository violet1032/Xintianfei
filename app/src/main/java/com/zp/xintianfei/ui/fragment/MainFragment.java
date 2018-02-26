package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.E_LOTTERY_TYPE;
import com.zp.xintianfei.bean.GameStatus;
import com.zp.xintianfei.bean.GameStatusList;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.GambleActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.StringUtils;

import org.kymjs.kjframe.ui.BindView;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class MainFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_main_lay_1, click = true)
    private LinearLayout layBJSC;
    @BindView(id = R.id.fg_main_lay_2, click = true)
    private LinearLayout layXGSM;
    @BindView(id = R.id.fg_main_lay_3, click = true)
    private LinearLayout layXYFT;
    @BindView(id = R.id.fg_main_lay_4, click = true)
    private LinearLayout layCQSSC;
    @BindView(id = R.id.fg_main_lay_5, click = true)
    private LinearLayout layQQLFC;
    @BindView(id = R.id.fg_main_lay_6, click = true)
    private LinearLayout layXGLHC;
    @BindView(id = R.id.fg_main_lay_7, click = true)
    private LinearLayout layPCDD;
    @BindView(id = R.id.fg_main_lay_8, click = true)
    private LinearLayout layJND28;
    @BindView(id = R.id.fg_main_lay_9, click = true)
    private LinearLayout layJSKS;

    @BindView(id = R.id.fg_tx_nickname)
    private TextView tvNickname;
    @BindView(id = R.id.fg_tx_id)
    private TextView tvID;
    @BindView(id = R.id.fg_tx_sum)
    private TextView tvSum;

    @BindView(id = R.id.fg_main_img_head)
    private ImageView imgHead;

    private TextView[] tvStatus = new TextView[9];

    private Timer timer;
    private TimerTask timerTask;

    private Handler handler;

    private GameStatusList gameStatusList;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_main, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText(R.string.main_title);
        imgBack.setVisibility(View.INVISIBLE);

        tvNickname.setText(AppContext.user.getNickname());
        tvID.setText(AppContext.user.getUid() + "");
        tvSum.setText(AppContext.user.getMoney().toString());

        ApiCommon.getNetBitmap(AppContext.user.getAvatar(), imgHead, false);

        for (int i = 0; i < 9; i++) {
            tvStatus[i] = parentView.findViewById(R.id.fg_main_tx_1 + i * 2);
        }
    }

    @Override
    protected void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    for (TextView tv :
                            tvStatus) {
//                        if (tv.getTag(R.id.tag_type) != null) {
//                            if ((int) (tv.getTag(R.id.tag_type)) == 0) {
//                                // 倒计时类型
//                                if (tv.getTag(R.id.tag_countdown) != null) {
//                                    int countdown = (int) tv.getTag(R.id.tag_countdown) - 1;
//                                    if (countdown < 0) {
//                                        // 变为封盘
//                                    } else {
//                                        tv.setText(StringUtils.getCountdown(countdown - 1));
//                                        tv.setTag(R.id.tag_countdown, countdown - 1);
//                                    }
//                                }
//                            } else if ((int) tv.getTag(R.id.tag_type) == 1) {
//                                // 已关盘
//                            } else if ((int) tv.getTag(R.id.tag_type) == 2) {
//                                // 已封盘
//                            }
//                        }
                        if (gameStatusList != null) {
                            setStatus(gameStatusList.getMap().get(tv.getTag(R.id.tag_cate)), tv);
                        }
                    }

                }
                return false;
            }
        });

        getGameStatus();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_main_lay_1:
//                GameBJSCActivity.startActivity(getActivity());
                GambleActivity.startActivity(getActivity(), E_LOTTERY_TYPE.bjsc.value);
                break;
            case R.id.fg_main_lay_2:
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.xgsm.value);
                break;
            case R.id.fg_main_lay_3:
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.xyft.value);
                break;
            case R.id.fg_main_lay_4:
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.cqssc.value);
                break;
            case R.id.fg_main_lay_5:
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.qqlfc.value);
                break;
            case R.id.fg_main_lay_6:
//                GameXGLHCActivity.startActivity(getActivity());
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.lhc.value);
                break;
            case R.id.fg_main_lay_7:
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.pcdd.value);
                break;
            case R.id.fg_main_lay_8:
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.jnd28.value);
                break;
            case R.id.fg_main_lay_9:
                GambleActivity.startActivity(getActivity(),E_LOTTERY_TYPE.jsks.value);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    /**
     * 获取游戏状态
     */
    private void getGameStatus() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    gameStatusList = new GameStatusList().parse(str);
                    for (Integer key :
                            gameStatusList.getMap().keySet()) {
                        GameStatus gameStatus = gameStatusList.getMap().get(key);
                        switch (gameStatus.getCate()) {
                            case 1:
                                // 时时彩
                                setStatus(gameStatus, tvStatus[3]);
                                break;
                            case 2:
                                // 赛车
                                setStatus(gameStatus, tvStatus[0]);
                                break;
                            case 3:
                                // 飞艇
                                setStatus(gameStatus, tvStatus[2]);
                                break;
                            case 4:
                                // PC蛋蛋
                                setStatus(gameStatus, tvStatus[6]);
                                break;
                            case 5:
                                // 赛马
                                setStatus(gameStatus, tvStatus[1]);
                                break;
                            case 6:
                                // 江苏快三
                                setStatus(gameStatus, tvStatus[8]);
                                break;
                            case 8:
                                // 加拿大28
                                setStatus(gameStatus, tvStatus[7]);
                                break;
                            case 9:
                                // qq两分彩
                                setStatus(gameStatus, tvStatus[4]);
                                break;
                            case 10:
                                // 六合彩
                                setStatus(gameStatus, tvStatus[5]);
                                break;
                        }
                    }

                    // 开启定时器
                    timer = new Timer();
                    timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(1);
                        }
                    };
                    timer.schedule(timerTask, 1000, 1000);
                }
            }
        };
        ApiLottery.getPlazaGameState(callBack);
    }

    private void setStatus(GameStatus gameStatus, TextView textView) {
        if (!gameStatus.isrun()) {
            // 已关盘
            textView.setText(R.string.fragment_main_text_4);
            textView.setTag(R.id.tag_cate, gameStatus.getCate());

            // 判断是否到达开盘时间
            if ((System.currentTimeMillis() - gameStatus.getStart_time()) / 1000 % 30 == 0) {
                // 请求下一期数据
                getNext(gameStatus.getCate(), textView);
            }
        } else if (!gameStatus.isopen()) {
            // 已封盘
            textView.setText(R.string.fragment_main_text_5);
            textView.setTag(R.id.tag_cate, gameStatus.getCate());

            // 判断是否应该请求下一期数据
            gameStatus.setCountdown(gameStatus.getCountdown() - 1);
            if (gameStatus.getFtime() + gameStatus.getCountdown() == 0) {
                // 请求下一期
                getNext(gameStatus.getCate(), textView);
            }
        } else {
            textView.setText(StringUtils.getCountdown(gameStatus.getCountdown()));
            textView.setTag(R.id.tag_cate, gameStatus.getCate());
            gameStatus.setCountdown(gameStatus.getCountdown() - 1);

            if (gameStatus.getCountdown() <= 0) {
                gameStatus.setIsopen(false);
            }
        }
    }

    private void getNext(int cate, final TextView tv) {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    GameStatus gameStatus = new GameStatus().parse(str);
                    gameStatusList.getMap().put(gameStatus.getCate(), gameStatus);
                    setStatus(gameStatusList.getMap().get(gameStatus.getCate()), tv);
                }
            }
        };
        ApiLottery.getGameNextInfo(cate, callBack);
    }
}
