package com.zp.xintianfei.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Chat;
import com.zp.xintianfei.bean.ChatList;
import com.zp.xintianfei.bean.E_LOTTERY_TYPE;
import com.zp.xintianfei.bean.GameStatus;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.dialog.LotterySelectDialog;
import com.zp.xintianfei.ui.fragment.GameBJSCJCFragment;
import com.zp.xintianfei.ui.fragment.GameCZFragment;
import com.zp.xintianfei.ui.fragment.GameGZFragment;
import com.zp.xintianfei.ui.fragment.GameJLFragment;
import com.zp.xintianfei.ui.fragment.GameZSFragment;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameBJSCActivity extends BaseActivity {
    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_game_bjsc_btn_cz, click = true)
    private Button btnCZ;
    @BindView(id = R.id.act_game_bjsc_btn_jc, click = true)
    private Button btnJC;
    @BindView(id = R.id.act_game_bjsc_btn_jl, click = true)
    private Button btnJL;
    @BindView(id = R.id.act_game_bjsc_btn_zs, click = true)
    private Button btnZS;
    @BindView(id = R.id.act_game_bjsc_btn_gz, click = true)
    private Button btnGZ;
    @BindView(id = R.id.act_game_bjsc_btn_sx, click = true)
    private Button btnSX;

    private GameBJSCJCFragment gameBJSCJCFragment = new GameBJSCJCFragment();
    private GameCZFragment gameCZFragment = new GameCZFragment();
    private GameJLFragment gameJLFragment = new GameJLFragment();
    private GameZSFragment gameZSFragment = new GameZSFragment();
    private GameGZFragment gameGZFragment = new GameGZFragment();

    @BindView(id = R.id.act_game_bjsc_lay_back, click = true)
    private LinearLayout btnBack;
    @BindView(id = R.id.act_game_bjsc_btn_recharge, click = true)
    private Button btnRecharge;
    @BindView(id = R.id.act_game_bjsc_btn_withdraw, click = true)
    private Button btnWithdraw;
    @BindView(id = R.id.act_game_bjsc_btn_window, click = true)
    private Button btnWindow;
    @BindView(id = R.id.act_game_bjsc_btn_online, click = true)
    private Button btnOnline;

//    @BindView(id = R.id.act_game_bjsc_btn_quick, click = true)
//    private TextView tvQuick;

    @BindView(id = R.id.act_game_bjsc_webview)
    private WebView webView;

    @BindView(id = R.id.act_game_bjsc_tv_code)
    private TextView tvId;
    @BindView(id = R.id.act_game_bjsc_tv_nickname)
    private TextView tvNickname;
    @BindView(id = R.id.act_game_bjsc_tv_sum)
    private TextView tvSum;
    @BindView(id = R.id.act_game_bjsc_tv_online)
    private TextView tvOnline;

    @BindView(id = R.id.act_game_bjsc_lay_bottom_normal)
    private RelativeLayout layBottomNormal;
    @BindView(id = R.id.act_game_bjscq_lay_bottom_quick)
    private LinearLayout layBottomQuick;

    private int cate;

    @BindView(id = R.id.act_game_bjsc_btn_keybord_quick, click = true)
    private Button btnKeybordQuick;

    private String animateUrl;

    @BindView(id = R.id.act_game_bjsc_img_head)
    private ImageView imgHead;

    private int last_record_id;// 每次获取聊天记录后最后一条记录的id

    private Handler handler;
    private Timer timer;
    private TimerTask timerTask;
    private ChatList chatList = new ChatList();

    @BindView(id = R.id.act_game_bjsc_btn_gamble, click = true)
    private Button btnGamble;

    @BindView(id = R.id.act_game_bjsc_edt_content)
    private EditText edtContent;

    private String stage;
    private boolean isRun;
    private boolean isOpen;

    public static void startActivity(Activity context, int cate) {
        Intent intent = new Intent();
        intent.setClass(context, GameBJSCActivity.class);
        intent.putExtra("cate", cate);
//        context.startActivity(intent);
        context.startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            setResult(1);
            finish();
        } else if (resultCode == 2) {
            setResult(2);
            finish();
        } else if (resultCode == 3) {
            setResult(3);
            finish();
        } else if (resultCode == 4) {
            setResult(4);
            finish();
        }
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_game_bjsc);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        cate = getIntent().getIntExtra("cate", 0);

        title.setText(R.string.main_title);

        changeFragment(R.id.act_game_bjsc_lay_content, gameBJSCJCFragment);

        tvId.setText(AppContext.user.getUid() + "");
        tvNickname.setText(AppContext.user.getNickname());
        tvSum.setText(AppContext.user.getMoney().toString());

        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setDefaultTextEncodingName("gbk");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webView.setWebViewClient(new webviewClient());
        webSettings.setLoadWithOverviewMode(true);

        // 获取动画路径
        getAnimateUrl();

        ApiCommon.getNetBitmap(AppContext.user.getAvatar(), imgHead, false);


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    changeChatList();
                } else if (message.what == 2)
                    tvSum.setText(AppContext.user.getMoney().toString());
                return false;
            }
        });

        if (timer == null)
            timer = new Timer();
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    // 每隔三秒获取一次聊天记录
                    getChatMsgs();
                    // 获取在线人数
                    getOnlineNum();
                    // 更新余额
                    handler.sendEmptyMessage(2);
                }
            };
            timer.schedule(timerTask, 0, 3000);
        }

        // 获取下一期的投注信息
        getNext();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                finish();
                break;
            case R.id.act_game_bjsc_btn_cz:
                // 彩种
//                changeFragment(R.id.act_game_bjsc_lay_content, gameCZFragment);
                LotterySelectDialog.startActivity(GameBJSCActivity.this,handler);
                break;
            case R.id.act_game_bjsc_btn_jc:
                // 竞猜
                changeFragment(R.id.act_game_bjsc_lay_content, gameBJSCJCFragment);
                break;
            case R.id.act_game_bjsc_btn_jl:
                // 记录
                changeFragment(R.id.act_game_bjsc_lay_content, gameJLFragment);
                break;
            case R.id.act_game_bjsc_btn_zs:
                // 走势
                changeFragment(R.id.act_game_bjsc_lay_content, gameZSFragment);
                break;
            case R.id.act_game_bjsc_btn_gz:
                // 规则
                changeFragment(R.id.act_game_bjsc_lay_content, gameGZFragment);
                break;
            case R.id.act_game_bjsc_btn_sx:
                // 刷新
                break;
//            case R.id.act_game_bjsc_btn_quick:
//                // 快速下注
//                boolean b = gameBJSCJCFragment.quickLay();
//                if (b) {
//                    layBottomQuick.setVisibility(View.VISIBLE);
//                    layBottomNormal.setVisibility(View.GONE);
//                } else {
//                    layBottomQuick.setVisibility(View.GONE);
//                    layBottomNormal.setVisibility(View.VISIBLE);
//                }
//                break;
            case R.id.act_game_bjsc_lay_back:
                setResult(4);
                finish();
                break;
            case R.id.act_game_bjsc_btn_recharge:
                setResult(1);
                finish();
                break;
            case R.id.act_game_bjsc_btn_withdraw:
                setResult(2);
                finish();
                break;
            case R.id.act_game_bjsc_btn_window:
                // 隐藏动画
                if (webView.getVisibility() == View.VISIBLE) {
                    webView.setVisibility(View.GONE);
                } else if (webView.getVisibility() == View.GONE) {
                    webView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.act_game_bjsc_btn_online:
                setResult(3);
                finish();
                break;
//            case R.id.act_game_bjsc_btn_keybord_quick:
//                b = gameBJSCJCFragment.quickLay();
//                if (b) {
//                    layBottomQuick.setVisibility(View.VISIBLE);
//                    layBottomNormal.setVisibility(View.GONE);
//                } else {
//                    layBottomQuick.setVisibility(View.GONE);
//                    layBottomNormal.setVisibility(View.VISIBLE);
//                }
//                break;
            case R.id.act_game_bjsc_quick_lay_clear:
                gameBJSCJCFragment.clearGamble();
                break;
            case R.id.act_game_bjsc_btn_gamble:
                // 下注
                gamble();
                break;
        }
    }

    public void getOnlineNum() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils jsonUtils = new JsonUtils(str);
                        int onlineNum = jsonUtils.getInt("info");
                        tvOnline.setText("" + onlineNum);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ApiUser.getOnlineNum(callBack);
    }

    public void handleGamble(String tag) {

    }

    public void getAnimateUrl() {
        if (cate == E_LOTTERY_TYPE.bjsc.value || cate == E_LOTTERY_TYPE.xgsm.value || cate == E_LOTTERY_TYPE.xyft.value) {
            FHttpCallBack callBack = new FHttpCallBack() {
                @Override
                public void onSuccess(Map<String, String> headers, byte[] t) {
                    super.onSuccess(headers, t);
                    String str = new String(t);
                    Result result = new Result().parse(str);
                    if (result.isOk()) {
                        try {
                            JsonUtils jsonUtils = new JsonUtils(str);
                            animateUrl = jsonUtils.getString("info");

                            webView.loadUrl(animateUrl);
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
                    UIHelper.showLoadingDialog(GameBJSCActivity.this);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    UIHelper.stopLoadingDialog();
                }
            };
            ApiLottery.getAnimateUrl(cate, callBack);
        }
    }

    private class webviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (!animateUrl.startsWith("http") & !animateUrl.startsWith("https")) {
                return false;
            } else {
                view.loadUrl(animateUrl);
                return true;
            }
        }
    }

    private void getChatMsgs() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils jsonUtils = new JsonUtils(str);
                        last_record_id = jsonUtils.getInt("last_record_id");

                        chatList.getList().clear();
                        chatList.parse(str);

                        if (chatList.getList().size() > 0)
                            handler.sendEmptyMessage(1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ApiLottery.getChatMsgs(cate, last_record_id, callBack);
    }

    private void changeChatList() {
        for (Chat chat :
                chatList.getList()) {
            gameBJSCJCFragment.addChatHistory(chat);
        }
    }

    private void gamble() {
        String content = edtContent.getText().toString().trim();
        if (StringUtils.isEmpty(content)) {
            UIHelper.ToastMessage("请输入下注内容");
            return;
        }

        if (StringUtils.isEmpty(stage)) {
            UIHelper.ToastMessage("后台获取期号错误，暂不能下注");
            return;
        }

        if (!isRun) {
            UIHelper.ToastMessage("该彩种暂未开启下注，请稍后再试");
            return;
        }
        if (!isOpen) {
            UIHelper.ToastMessage("该彩种已封盘，请等待下一期");
            return;
        }

        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);

                if (result.isOk()) {
                    UIHelper.ToastMessage("下注成功");
                    edtContent.setText("");
                    // 获取一次用户信息更新
                    AppContext.appContext.updateUserInfo();
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(GameBJSCActivity.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiLottery.gameBet(cate, stage, content, callBack);
    }

    private void getNext() {
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
                }

            }
        };
        ApiLottery.getGameNextInfo(cate, callBack);
    }
}
