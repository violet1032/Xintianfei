package com.zp.xintianfei.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
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
    @BindView(id = R.id.act_game_bjsc_btn_keyboard, click = true)
    private Button btnKeyboard;

    @BindView(id = R.id.act_game_bjsc_lay_keyboard_jsks)
    private LinearLayout layKeyboardJSKS;
    @BindView(id = R.id.act_game_bjsc_lay_keyboard_pcdd)
    private LinearLayout layKeyboardPCDD;
    @BindView(id = R.id.act_game_bjsc_lay_keyboard_qqlfc)
    private LinearLayout layKeyboardQQLFC;
    @BindView(id = R.id.act_game_bjsc_lay_keyboard_bjsc)
    private LinearLayout layKeyboardBJSC;

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

    private boolean isTrends = false;
    private boolean smallWindow = false;
    private String trendsUrl;

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

        gameJLFragment.setCate(cate);
        gameGZFragment.setCate(cate);

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

        // 获取动画路径,只有qq两分彩和六合彩没有动画
        if (cate == E_LOTTERY_TYPE.qqlfc.value || cate == E_LOTTERY_TYPE.lhc.value) {
//            btnWindow.setVisibility(View.GONE);
//            webView.setVisibility(View.GONE);
            btnZS.setVisibility(View.GONE);
        } else {
            getAnimateUrl();
        }

        ApiCommon.getNetBitmap(AppContext.user.getAvatar(), imgHead, false);


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    changeChatList();
                } else if (message.what == 2)
                    tvSum.setText(AppContext.user.getMoney().toString());
                else if (message.what == 3) {
                    int c = message.arg1;
                    startActivity(GameBJSCActivity.this, c);
                    finish();
                }
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

        getTrendsURL();

        // 绑定键盘
        keyboardOnclick();
    }

    private void keyboardOnclick() {
        LinearLayout lay = null;
        E_LOTTERY_TYPE e_lottery_type = E_LOTTERY_TYPE.getIndex(cate);
        switch (e_lottery_type) {
            case bjsc:
            case xgsm:
            case xyft:
                lay = layKeyboardBJSC;
                break;
            case cqssc:
            case qqlfc:
                lay = layKeyboardQQLFC;
                break;
            case lhc:
                break;
            case jnd28:
            case pcdd:
                lay = layKeyboardPCDD;
                break;
            case jsks:
                lay = layKeyboardJSKS;
                break;
        }
        if (lay != null) {
            TableRow tableRow11 = lay.findViewById(R.id.keyboard_bjsc_row_1);
            TableRow tableRow22 = lay.findViewById(R.id.keyboard_bjsc_row_2);
            TableRow tableRow33 = lay.findViewById(R.id.keyboard_bjsc_row_3);
            TableRow tableRow44 = lay.findViewById(R.id.keyboard_bjsc_row_4);
            TableRow tableRow55 = lay.findViewById(R.id.keyboard_bjsc_row_5);
            for (int i = 0; i < tableRow11.getChildCount(); i++) {
                tableRow11.getChildAt(i).setOnClickListener(new keyboardOnClick());
            }
            for (int i = 0; i < tableRow22.getChildCount(); i++) {
                tableRow22.getChildAt(i).setOnClickListener(new keyboardOnClick());
            }
            for (int i = 0; i < tableRow33.getChildCount(); i++) {
                tableRow33.getChildAt(i).setOnClickListener(new keyboardOnClick());
            }
            for (int i = 0; i < tableRow44.getChildCount(); i++) {
                tableRow44.getChildAt(i).setOnClickListener(new keyboardOnClick());
            }
            if (tableRow55 != null)
                for (int i = 0; i < tableRow55.getChildCount(); i++) {
                    tableRow55.getChildAt(i).setOnClickListener(new keyboardOnClick());
                }
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.act_game_bjsc_btn_cz:
                // 彩种
//                changeFragment(R.id.act_game_bjsc_lay_content, gameCZFragment);
                LotterySelectDialog.startActivity(GameBJSCActivity.this, handler);
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
//                changeFragment(R.id.act_game_bjsc_lay_content, gameZSFragment);
//                TrendsActivity.startActivity(this, cate);
                if (isTrends) {
                    isTrends = false;
                    if (!StringUtils.isEmpty(animateUrl)) {
                        btnZS.setText("走势");
                        webView.loadUrl(animateUrl);
                    }
                } else {
                    isTrends = true;
                    if (!StringUtils.isEmpty(trendsUrl)) {
                        btnZS.setText("动画");
                        webView.loadUrl(trendsUrl);
                    }
                }
                break;
            case R.id.act_game_bjsc_btn_gz:
                // 规则
                changeFragment(R.id.act_game_bjsc_lay_content, gameGZFragment);
                break;
            case R.id.act_game_bjsc_btn_sx:
                // 刷新
                getAnimateUrl();
//                getChatMsgs();
//                getNext();
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
                if (smallWindow) {
                    smallWindow = false;
                    ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
                    layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.game_flash_height);
                    webView.setLayoutParams(layoutParams);
                    btnWindow.setText("小窗");
                } else {
                    smallWindow = true;
                    ViewGroup.LayoutParams layoutParams = webView.getLayoutParams();
                    layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.banner_height);
                    webView.setLayoutParams(layoutParams);
                    btnWindow.setText("大窗");
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
            case R.id.act_game_bjsc_btn_keyboard:
                // 键盘
                E_LOTTERY_TYPE e_lottery_type = E_LOTTERY_TYPE.getIndex(cate);
                switch (e_lottery_type) {
                    case bjsc:
                    case xgsm:
                    case xyft:
                        if (layKeyboardBJSC.getVisibility() == View.GONE)
                            layKeyboardBJSC.setVisibility(View.VISIBLE);
                        else
                            layKeyboardBJSC.setVisibility(View.GONE);
                        break;
                    case cqssc:
                    case qqlfc:
                        if (layKeyboardQQLFC.getVisibility() == View.GONE)
                            layKeyboardQQLFC.setVisibility(View.VISIBLE);
                        else
                            layKeyboardQQLFC.setVisibility(View.GONE);
                        break;
                    case lhc:
                        break;
                    case jnd28:
                    case pcdd:
                        if (layKeyboardPCDD.getVisibility() == View.GONE)
                            layKeyboardPCDD.setVisibility(View.VISIBLE);
                        else
                            layKeyboardPCDD.setVisibility(View.GONE);
                        break;
                    case jsks:
                        if (layKeyboardJSKS.getVisibility() == View.GONE)
                            layKeyboardJSKS.setVisibility(View.VISIBLE);
                        else
                            layKeyboardJSKS.setVisibility(View.GONE);
                        break;
                }
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

    private void getTrendsURL() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils jsonUtils = new JsonUtils(str);
                        trendsUrl = jsonUtils.getString("info");

                        if (cate == E_LOTTERY_TYPE.qqlfc.value)
                            webView.loadUrl(trendsUrl);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("数据解析错误");
                    }
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }
        };
        ApiLottery.getTrendsURL(cate, callBack);
    }

    private class keyboardOnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            TextView textView = (TextView) view;
            String str = textView.getText().toString().trim();
            if (!StringUtils.isEmpty(str)) {
                if (str.equals("清")) {
                    edtContent.setText("");
                } else if (str.equals("关闭")) {
                    E_LOTTERY_TYPE e_lottery_type = E_LOTTERY_TYPE.getIndex(cate);
                    switch (e_lottery_type) {
                        case bjsc:
                        case xgsm:
                        case xyft:
                            if (layKeyboardBJSC.getVisibility() == View.GONE)
                                layKeyboardBJSC.setVisibility(View.VISIBLE);
                            else
                                layKeyboardBJSC.setVisibility(View.GONE);
                            break;
                        case cqssc:
                        case qqlfc:
                            if (layKeyboardQQLFC.getVisibility() == View.GONE)
                                layKeyboardQQLFC.setVisibility(View.VISIBLE);
                            else
                                layKeyboardQQLFC.setVisibility(View.GONE);
                            break;
                        case lhc:
                            break;
                        case jnd28:
                        case pcdd:
                            if (layKeyboardPCDD.getVisibility() == View.GONE)
                                layKeyboardPCDD.setVisibility(View.VISIBLE);
                            else
                                layKeyboardPCDD.setVisibility(View.GONE);
                            break;
                        case jsks:
                            if (layKeyboardJSKS.getVisibility() == View.GONE)
                                layKeyboardJSKS.setVisibility(View.VISIBLE);
                            else
                                layKeyboardJSKS.setVisibility(View.GONE);
                            break;
                    }
                } else if (str.equals("发送")) {
                    gamble();
                } else if (str.equals("←")) {
                    String s = edtContent.getText().toString();
                    if (s.length() > 0) {
                        edtContent.setText(s.substring(0, s.length() - 1));
                    }
                } else {
                    edtContent.setText(edtContent.getText() + str);
                }
            }
        }
    }
}
