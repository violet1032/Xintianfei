package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.E_NUMBER;
import com.zp.xintianfei.bean.GameStatus;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.XGLHCMainFragment;
import com.zp.xintianfei.ui.fragment.XGLHCSXLFragment;
import com.zp.xintianfei.ui.fragment.XGLHCTMBSFragment;
import com.zp.xintianfei.ui.fragment.XGLHCTMFragment;
import com.zp.xintianfei.ui.fragment.XGLHCTMSXFragment;
import com.zp.xintianfei.ui.fragment.XGLHCWSFragment;
import com.zp.xintianfei.ui.fragment.XGLHCZTMFragment;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

public class GameXGLHCActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_game_xglhc_lay)
    private LinearLayout lay;

    private XGLHCMainFragment mainFragment = new XGLHCMainFragment();
    private XGLHCTMFragment xglhctmFragment = new XGLHCTMFragment();
    private XGLHCTMSXFragment xglhctmsxFragment = new XGLHCTMSXFragment();
    private XGLHCTMBSFragment xglhctmbsFragment = new XGLHCTMBSFragment();
    private XGLHCZTMFragment xglhcztmFragment = new XGLHCZTMFragment();
    private XGLHCWSFragment xglhcwsFragment = new XGLHCWSFragment();
    private XGLHCSXLFragment xglhcsxlFragment = new XGLHCSXLFragment();

    private int type = 0;

    private boolean isRun;
    private boolean isOpen;
    private String stage;

    @BindView(id = R.id.act_game_xglhc_tv_stage_2)
    private TextView tvNextStage;
    @BindView(id = R.id.act_game_xglhc_tv_time_1)
    private TextView tvCutdown;
    @BindView(id = R.id.act_game_xglhc_tv_time_2)
    private TextView tvOpen;
    @BindView(id = R.id.act_game_xglhc_tv_stage)
    private TextView tvLastStage;

    @BindView(id = R.id.act_game_xglhc_img_num1)
    private ImageView imgNum1;
    @BindView(id = R.id.act_game_xglhc_img_num2)
    private ImageView imgNum2;
    @BindView(id = R.id.act_game_xglhc_img_num3)
    private ImageView imgNum3;
    @BindView(id = R.id.act_game_xglhc_img_num4)
    private ImageView imgNum4;
    @BindView(id = R.id.act_game_xglhc_img_num5)
    private ImageView imgNum5;
    @BindView(id = R.id.act_game_xglhc_img_num6)
    private ImageView imgNum6;
    @BindView(id = R.id.act_game_xglhc_img_num7)
    private ImageView imgNum7;
    @BindView(id = R.id.act_game_xglhc_img_num1)
    private TextView tvNum1;
    @BindView(id = R.id.act_game_xglhc_img_num2)
    private TextView tvNum2;
    @BindView(id = R.id.act_game_xglhc_img_num3)
    private TextView tvNum3;
    @BindView(id = R.id.act_game_xglhc_img_num4)
    private TextView tvNum4;
    @BindView(id = R.id.act_game_xglhc_img_num5)
    private TextView tvNum5;
    @BindView(id = R.id.act_game_xglhc_img_num6)
    private TextView tvNum6;
    @BindView(id = R.id.act_game_xglhc_img_num7)
    private TextView tvNum7;

    private ImageView[] imgs = new ImageView[7];
    private TextView[] tvs = new TextView[7];

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GameXGLHCActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_game_xglhc);
    }

    @Override
    public void initData() {
        super.initData();

        getGameNextInfo();

        getGameLastInfo();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        title.setText(R.string.game_xglhc_text_1);

        lay.setVisibility(View.GONE);
        changeFragment(R.id.act_game_xglhc_content, mainFragment);

        imgs[0] = imgNum1;
        imgs[1] = imgNum2;
        imgs[2] = imgNum3;
        imgs[3] = imgNum4;
        imgs[4] = imgNum5;
        imgs[5] = imgNum6;
        imgs[6] = imgNum7;
        tvs[0] = tvNum1;
        tvs[1] = tvNum2;
        tvs[2] = tvNum3;
        tvs[3] = tvNum4;
        tvs[4] = tvNum5;
        tvs[5] = tvNum6;
        tvs[6] = tvNum7;
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                if (type == 0)
                    finish();
                else {
                    changeFragment(R.id.act_game_xglhc_content, mainFragment);
                    lay.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void setType(int type) {
        this.type = type;
        lay.setVisibility(View.VISIBLE);
        switch (type) {
            case 1:
                // 特码
                changeFragment(R.id.act_game_xglhc_content, xglhctmFragment);
                break;
            case 3:
                // 尾数
                changeFragment(R.id.act_game_xglhc_content, xglhcwsFragment);
                break;
            case 4:
                // 特码生肖
                changeFragment(R.id.act_game_xglhc_content, xglhctmsxFragment);
                break;
            case 5:
                // 特码波色
                changeFragment(R.id.act_game_xglhc_content, xglhctmbsFragment);
                break;
            case 6:
                // 正特码
                changeFragment(R.id.act_game_xglhc_content, xglhcztmFragment);
                break;
            case 7:
                // 生肖连
                changeFragment(R.id.act_game_xglhc_content, xglhcsxlFragment);
                break;
        }
    }

    /**
     * 获取上一期
     */
    private void getGameLastInfo() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils j = new JsonUtils(str);
                        JsonUtils jsonUtils = j.getJSONUtils("info");
                        String lastStage = jsonUtils.getString("stage");
                        String number = jsonUtils.getString("number");
                        tvLastStage.setText(lastStage);

                        if (StringUtils.isEmpty(number)) {
                            String[] numbers = number.split(",");

                            for (int i = 0; i < numbers.length; i++) {
                                int n = Integer.parseInt(numbers[i]);
                                imgNum1.setImageResource(R.drawable.number_01 + (n - 1));
                                tvNum1.setText(E_NUMBER.getIndex(n).name);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("数据解析错误");
                    }
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(GameXGLHCActivity.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiLottery.getGameLastInfo(10, callBack);
    }

    /**
     * 获取下一期
     */
    private void getGameNextInfo() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    GameStatus gameStatus = new GameStatus().parse(str);
                    isRun = gameStatus.isrun();
                    isOpen = gameStatus.isopen();
                    stage = gameStatus.getStage();

                    tvNextStage.setText(stage);
                    if (!isRun) {
                        tvCutdown.setText("未开盘");
                        tvOpen.setText("未开盘");
                        return;
                    }

                    // 开奖时间
                    long openTime = System.currentTimeMillis() + (gameStatus.getFtime() + gameStatus.getCountdown());
                    String strOpenTime = StringUtils.getDateMDHM(openTime);

                    tvOpen.setText(strOpenTime);
                    if (!isOpen) {
                        tvCutdown.setText("已封盘");
                        return;
                    }

                    // 封盘倒计时
                    setStatus(gameStatus, tvCutdown);
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }
        };
        ApiLottery.getGameNextInfo(10, callBack);
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
                    setStatus(gameStatus, tv);
                }
            }
        };
        ApiLottery.getGameNextInfo(cate, callBack);
    }
}
