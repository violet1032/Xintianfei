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
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.E_LOTTERY_TYPE;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.GameBJSCActivity;
import com.zp.xintianfei.ui.GameXGLHCActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.UIHelper;
import com.zp.xintianfei.widget.MarqueeTextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.ui.BindView;

import java.util.HashMap;
import java.util.Iterator;
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
    @BindView(id = R.id.fg_main_img_19, click = true)
    private ImageView img19;
    @BindView(id = R.id.fg_main_img_20, click = true)
    private ImageView img20;

    @BindView(id = R.id.fg_main_lay_main)
    private ScrollView scrollView;
    @BindView(id = R.id.fg_main_lay_lower)
    private TableLayout layLower;

    private Map<Integer, ImageView> map = new HashMap<>();

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

        map.put(1, img6);
        map.put(2, img1);
        map.put(3, img5);
        map.put(4, img10);
        map.put(5, img2);
        map.put(6, img13);
        map.put(8, img11);
        map.put(1, img7);
        map.put(1, img18);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);
        map.put(1, img1);

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

//        Glide.with(getActivity()).load(R.drawable.cate_11_gif)
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(img16);
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

        if (timer == null)
            timer = new Timer();
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    getAllGameState();
                }
            };
            timer.schedule(timerTask, 0, 5000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
        if (timerTask != null)
            timerTask.cancel();
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
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(AppConfig.getInstance().getGameList(11));
//                intent.setData(content_url);
//                startActivity(intent);
                // 真人视讯
                showLower(20);
                break;
            case R.id.fg_main_img_17:
//                intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                content_url = Uri.parse(AppConfig.getInstance().getGameList(19));
//                intent.setData(content_url);
//                startActivity(intent);
                // 体育赛事
                showLower(21);
                break;
            case R.id.fg_main_img_18:
                getGameStatus(10);
                break;
            case R.id.fg_main_img_19:
                // 电子游艺
                showLower(22);
                break;
            case R.id.fg_main_img_20:
                // 捕鱼达人
                showLower(23);
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

    public void getAllGameState() {
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

                        if (jsonUtils.getString("1").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_01_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img6);
                        else
                            img6.setImageResource(R.drawable.cate_01_gray);

                        if (jsonUtils.getString("2").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_02_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img1);
                        else
                            img1.setImageResource(R.drawable.cate_02_gray);

                        if (jsonUtils.getString("3").equals("1"))
                            img5.setImageResource(R.drawable.cate_03);
                        else
                            img5.setImageResource(R.drawable.cate_03_gray);

                        if (jsonUtils.getString("4").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_04_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img10);
                        else
                            img10.setImageResource(R.drawable.cate_04_gray);

                        if (jsonUtils.getString("5").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_05_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img2);
                        else
                            img2.setImageResource(R.drawable.cate_05_gray);

                        if (jsonUtils.getString("6").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_06_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img13);
                        else
                            img13.setImageResource(R.drawable.cate_06_gray);

                        if (jsonUtils.getString("8").equals("1"))
                            img11.setImageResource(R.drawable.cate_08);
                        else
                            img11.setImageResource(R.drawable.cate_08_gray);

                        if (jsonUtils.getString("9").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_09_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img7);
                        else
                            img7.setImageResource(R.drawable.cate_09_gray);

                        if (jsonUtils.getString("10").equals("1"))
                            img18.setImageResource(R.drawable.cate_10);
                        else
                            img18.setImageResource(R.drawable.cate_10_gray);

                        if (jsonUtils.getString("12").equals("1"))
                            img4.setImageResource(R.drawable.cate_12);
                        else
                            img4.setImageResource(R.drawable.cate_12_gray);

                        if (jsonUtils.getString("13").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_13_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img3);
                        else
                            img3.setImageResource(R.drawable.cate_13_gray);

                        if (jsonUtils.getString("14").equals("1"))
                            img8.setImageResource(R.drawable.cate_14);
                        else
                            img8.setImageResource(R.drawable.cate_14_gray);

                        if (jsonUtils.getString("15").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_15_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img9);
                        else
                            img9.setImageResource(R.drawable.cate_15_gray);

                        if (jsonUtils.getString("16").equals("1"))
                            img14.setImageResource(R.drawable.cate_16);
                        else
                            img14.setImageResource(R.drawable.cate_16_gray);

                        if (jsonUtils.getString("17").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_17_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img15);
                        else
                            img15.setImageResource(R.drawable.cate_17_gray);

                        if (jsonUtils.getString("18").equals("1"))
                            Glide.with(getActivity()).load(R.drawable.cate_18_gif)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(img12);
                        else
                            img12.setImageResource(R.drawable.cate_18_gray);

                        if (jsonUtils.getString("20").equals("1"))
                            img17.setImageResource(R.drawable.cate_23);
                        else
                            img16.setImageResource(R.drawable.cate_23_gray);

                        if (jsonUtils.getString("21").equals("1"))
                            img17.setImageResource(R.drawable.cate_22);
                        else
                            img17.setImageResource(R.drawable.cate_22_gray);

                        if (jsonUtils.getString("22").equals("1"))
                            img19.setImageResource(R.drawable.cate_21);
                        else
                            img19.setImageResource(R.drawable.cate_21_gray);

                        if (jsonUtils.getString("23").equals("1"))
                            img20.setImageResource(R.drawable.cate_20);
                        else
                            img20.setImageResource(R.drawable.cate_20_gray);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ApiLottery.getAllGameState(callBack);
    }

    private void showLower(int cate) {
        layLower.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        getRealGameList(cate);
    }

    public void showMain() {
        if (layLower != null)
            layLower.setVisibility(View.GONE);
        if (scrollView != null)
            scrollView.setVisibility(View.VISIBLE);
    }

    private void getRealGameList(int cate) {
        layLower.removeAllViews();
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils j = new JsonUtils(str);
                        JSONObject jsonObject = j.getJSONObject("info");
                        Iterator<String> it = jsonObject.keys();

                        int size = 0;
                        while (it.hasNext()) {
                            String key = it.next();
                            JsonUtils jsonUtils = new JsonUtils(jsonObject.getJSONObject(key).toString());
                            int cate = Integer.parseInt(jsonUtils.getString("cate"));
                            String name = jsonUtils.getString("name");
                            final String url = jsonUtils.getString("url");

                            TableRow tableRow;
                            boolean isNew = false;
                            if (size % 2 == 0) {
                                isNew = true;
                                // 新建tablerow
                                tableRow = new TableRow(layLower.getContext());
                            } else {
                                tableRow = (TableRow) layLower.getChildAt(layLower.getChildCount() - 1);
                            }

                            if (tableRow != null) {

                                ImageView imageView = new ImageView(layLower.getContext());
                                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                                        getResources().getDimensionPixelOffset(R.dimen.main_cate_img_width_2),
                                        getResources().getDimensionPixelOffset(R.dimen.main_cate_img_height_2));
                                layoutParams.weight = 1;
                                if (cate == E_LOTTERY_TYPE.ag.value) {
                                    imageView.setImageResource(R.drawable.cate_11);
                                } else if (cate == E_LOTTERY_TYPE.bbin.value) {
                                    imageView.setImageResource(R.drawable.cate_19);
                                } else if (cate == E_LOTTERY_TYPE.bg.value) {
                                    imageView.setImageResource(R.drawable.cate_26_gif);
                                } else if (cate == E_LOTTERY_TYPE.mg.value) {
                                    imageView.setImageResource(R.drawable.cate_27_gif);
                                } else if (cate == E_LOTTERY_TYPE.ss.value) {
                                    imageView.setImageResource(R.drawable.cate_28_gif);
                                } else if (cate == E_LOTTERY_TYPE.pt.value) {
                                    imageView.setImageResource(R.drawable.cate_29_gif);
                                } else if (cate == E_LOTTERY_TYPE.sunbet.value) {
                                    imageView.setImageResource(R.drawable.cate_30_gif);
                                }

                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent();
                                        intent.setAction("android.intent.action.VIEW");
                                        Uri uri = Uri.parse(url);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                });

                                tableRow.addView(imageView, layoutParams);
                            }

                            if (isNew) {
                                TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(0, UIHelper.dip2px(10), 0, 0);
                                layLower.addView(tableRow, layoutParams);
                            }
                            size++;
                        }

                        if (size % 2 != 0) {
                            // 单数 在创建一个空图片
                            TableRow tableRow = (TableRow) layLower.getChildAt(layLower.getChildCount() - 1);

                            if (tableRow != null) {
                                ImageView imageView = new ImageView(layLower.getContext());
                                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                                        getResources().getDimensionPixelOffset(R.dimen.main_cate_img_width_2),
                                        getResources().getDimensionPixelOffset(R.dimen.main_cate_img_height_2));
                                layoutParams.weight = 1;

                                tableRow.addView(imageView, layoutParams);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    UIHelper.ToastMessage(result.getMsg());
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
        ApiLottery.getRealGameList(cate, callBack);
    }

}
