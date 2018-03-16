package com.zp.xintianfei.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zp.xintianfei.AppConfig;
import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.GameBJSCActivity;
import com.zp.xintianfei.ui.GameXGLHCActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.UIHelper;
import com.zp.xintianfei.widget.MarqueeTextView;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class MainFragment extends BaseFragment {
    @BindView(id = R.id.fg_tx_nickname)
    private TextView tvNickname;
    @BindView(id = R.id.fg_tx_id)
    private TextView tvID;
    @BindView(id = R.id.fg_tx_sum)
    private TextView tvSum;

    @BindView(id = R.id.fg_main_img_head)
    private ImageView imgHead;

//    private TextView[] tvStatus = new TextView[9];

    private Timer timer;
    private TimerTask timerTask;

    private Handler handler;

//    private GameStatusList gameStatusList;

    @BindView(id = R.id.fg_main_tv_msg)
    private MarqueeTextView tvNotice;

    @BindView(id = R.id.fg_main_img_1, click = true)
    private ImageView img1;
    @BindView(id = R.id.fg_main_img_2, click = true)
    private ImageView img2;
    @BindView(id = R.id.fg_main_img_3, click = true)
    private ImageView img3;
    @BindView(id = R.id.fg_main_img_4, click = true)
    private ImageView img4;
    @BindView(id = R.id.fg_main_img_5, click = true)
    private ImageView img5;
    @BindView(id = R.id.fg_main_img_6, click = true)
    private ImageView img6;
    @BindView(id = R.id.fg_main_img_7, click = true)
    private ImageView img7;
    @BindView(id = R.id.fg_main_img_8, click = true)
    private ImageView img8;
    @BindView(id = R.id.fg_main_img_9, click = true)
    private ImageView img9;
    @BindView(id = R.id.fg_main_img_10, click = true)
    private ImageView img10;
    @BindView(id = R.id.fg_main_img_11, click = true)
    private ImageView img11;
    @BindView(id = R.id.fg_main_img_12, click = true)
    private ImageView img12;
    @BindView(id = R.id.fg_main_img_13, click = true)
    private ImageView img13;
    @BindView(id = R.id.fg_main_img_14, click = true)
    private ImageView img14;
    @BindView(id = R.id.fg_main_img_15, click = true)
    private ImageView img15;
    @BindView(id = R.id.fg_main_img_16, click = true)
    private ImageView img16;
    @BindView(id = R.id.fg_main_img_17, click = true)
    private ImageView img17;
    @BindView(id = R.id.fg_main_img_18, click = true)
    private ImageView img18;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_main, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        tvNickname.setText(AppContext.user.getNickname());
        tvID.setText(AppContext.user.getUid() + "");
        tvSum.setText(AppContext.user.getMoney().toString());

        ApiCommon.getNetBitmap(AppContext.user.getAvatar(), imgHead, false);

//        for (int i = 0; i < 9; i++) {
//            tvStatus[i] = parentView.findViewById(R.id.fg_main_tx_1 + i * 2);
//        }

        Glide.with(getActivity()).load(R.drawable.cate_02_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img1);
        Glide.with(getActivity()).load(R.drawable.cate_05_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img2);
        Glide.with(getActivity()).load(R.drawable.cate_13_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img3);

        Glide.with(getActivity()).load(R.drawable.cate_01_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img6);
        Glide.with(getActivity()).load(R.drawable.cate_09_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img7);
        Glide.with(getActivity()).load(R.drawable.cate_15_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img9);

        Glide.with(getActivity()).load(R.drawable.cate_04_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img10);
        Glide.with(getActivity()).load(R.drawable.cate_18_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img12);

        Glide.with(getActivity()).load(R.drawable.cate_06_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img13);
        Glide.with(getActivity()).load(R.drawable.cate_17_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img15);

        Glide.with(getActivity()).load(R.drawable.cate_11_gif)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img16);
    }

    @Override
    protected void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 101) {
                    // 更新余额
                    tvSum.setText(AppContext.user.getMoney().toString());
                } else if (message.what == 8) {
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((MainActivity) getActivity()).setPosition(8);
                        }
                    };
                    timer.schedule(timerTask, 200);
                } else if (message.what == 3) {
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((MainActivity) getActivity()).setPosition(3);
                        }
                    };
                    timer.schedule(timerTask, 200);
                } else if (message.what == 40) {
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((MainActivity) getActivity()).setPosition(0);
                        }
                    };
                    timer.schedule(timerTask, 200);
                } else if (message.what == 41) {
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((MainActivity) getActivity()).setPosition(1);
                        }
                    };
                    timer.schedule(timerTask, 200);
                } else if (message.what == 42) {
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((MainActivity) getActivity()).setPosition(2);
                        }
                    };
                    timer.schedule(timerTask, 200);
                } else if (message.what == 43) {
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((MainActivity) getActivity()).setPosition(3);
                        }
                    };
                    timer.schedule(timerTask, 200);
                } else if (message.what == 44) {
                    Timer timer = new Timer();
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            ((MainActivity) getActivity()).setPosition(4);
                        }
                    };
                    timer.schedule(timerTask, 200);
                }
                return false;
            }
        });

        getNotice();

        // 定时刷新余额
        Timer timer1 = new Timer();
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(101);
            }
        };
        timer1.schedule(timerTask1, 1000, 1000);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_main_img_1:
                // 北京赛车
                getGameStatus(2);
                break;
            case R.id.fg_main_img_2:
                // 香港赛马
                getGameStatus(5);
                break;
            case R.id.fg_main_img_3:
                // 澳门赛狗
                getGameStatus(13);
                break;
            case R.id.fg_main_img_4:
                // 极速赛车
                getGameStatus(12);
                break;
            case R.id.fg_main_img_5:
                // 幸运飞艇
                getGameStatus(3);
                break;
            case R.id.fg_main_img_6:
                getGameStatus(1);
                break;
            case R.id.fg_main_img_7:
                getGameStatus(9);
                break;
            case R.id.fg_main_img_8:
                getGameStatus(14);
                break;
            case R.id.fg_main_img_9:
                getGameStatus(15);
                break;
            case R.id.fg_main_img_10:
                getGameStatus(4);
                break;
            case R.id.fg_main_img_11:
                getGameStatus(8);
                break;
            case R.id.fg_main_img_12:
                getGameStatus(18);
                break;
            case R.id.fg_main_img_13:
                getGameStatus(6);
                break;
            case R.id.fg_main_img_14:
                getGameStatus(16);
                break;
            case R.id.fg_main_img_15:
                getGameStatus(17);
                break;
            case R.id.fg_main_img_16:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(AppConfig.getInstance().getGameList(11));
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.fg_main_img_17:
                intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                content_url = Uri.parse(AppConfig.getInstance().getGameList(19));
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.fg_main_img_18:
                getGameStatus(10);
                break;
        }

//        switch (v.getId()) {
//            case R.id.fg_main_lay_1:
//                String tv = ((TextView) layBJSC.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.bjsc.value);
//                }
//                break;
//            case R.id.fg_main_lay_2:
//                tv = ((TextView) layXGSM.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.xgsm.value);
//                }
//                break;
//            case R.id.fg_main_lay_3:
//                tv = ((TextView) layXYFT.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.xyft.value);
//                }
//                break;
//            case R.id.fg_main_lay_4:
//                tv = ((TextView) layCQSSC.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.cqssc.value);
//                }
//                break;
//            case R.id.fg_main_lay_5:
//                tv = ((TextView) layQQLFC.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.qqlfc.value);
//                }
//                break;
//            case R.id.fg_main_lay_6:
//                tv = ((TextView) layXGLHC.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameXGLHCActivity.startActivity(getActivity(), handler);
//                }
////                GameXGLHCActivity.startActivity(getActivity(), handler);
//                break;
//            case R.id.fg_main_lay_7:
//                tv = ((TextView) layPCDD.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.pcdd.value);
//                }
//                break;
//            case R.id.fg_main_lay_8:
//                tv = ((TextView) layJND28.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.jnd28.value);
//                }
//                break;
//            case R.id.fg_main_lay_9:
//                tv = ((TextView) layJSKS.getChildAt(1)).getText().toString().trim();
//                if (tv.equals("已关盘")) {
//                    UIHelper.ToastMessage("彩种已关盘");
//                } else {
//                    GameBJSCActivity.startActivity(getActivity(), E_LOTTERY_TYPE.jsks.value);
//                }
//                break;
//            case R.id.fg_main_lay_10:
//                // 真人娱乐
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(AppConfig.getInstance().getGameList(11));
//                intent.setData(content_url);
//                startActivity(intent);
//                break;
//            case R.id.fg_main_lay_13:
//                // 安卓下载
//                intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                content_url = Uri.parse(AppConfig.getInstance().getmPre().getString("android_update_url", ""));
//                intent.setData(content_url);
//                startActivity(intent);
//                break;
//            case R.id.fg_main_lay_12:
//                // IOS下载
//                intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                content_url = Uri.parse(AppConfig.getInstance().getmPre().getString("ios_update_url", ""));
//                intent.setData(content_url);
//                startActivity(intent);
//                break;
//        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    /**
     * 获取游戏状态
     */
    private void getGameStatus(final int cate) {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils jsonUtils = new JsonUtils(str);
                        int state = jsonUtils.getInt("info");
                        if (state == 0) {
                            UIHelper.ToastMessage("彩种已关盘");
                        } else {
                            if (cate == 10)
                                GameXGLHCActivity.startActivity(getActivity(), handler);
                            else
                                GameBJSCActivity.startActivity(getActivity(), cate);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(getActivity());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiLottery.getGameState(cate, callBack);
    }

    public void changeSum() {
        tvSum.setText(AppContext.user.getMoney().toString());
    }

    public void getNotice() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    tvNotice.setText(result.getMsg());
                }
            }
        };
        ApiUser.getNotice(callBack);
    }
}
