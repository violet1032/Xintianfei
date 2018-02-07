package com.zp.xintianfei.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.GameBJSCJCFragment;
import com.zp.xintianfei.ui.fragment.GameCZFragment;
import com.zp.xintianfei.ui.fragment.GameGZFragment;
import com.zp.xintianfei.ui.fragment.GameJLFragment;
import com.zp.xintianfei.ui.fragment.GameZSFragment;
import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

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

    @BindView(id = R.id.act_game_bjsc_btn_quick, click = true)
    private TextView tvQuick;

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

    @BindView(id = R.id.act_game_bjsc_btn_keybord_quick, click = true)
    private Button btnKeybordQuick;


    public static void startActivity(Activity context) {
        Intent intent = new Intent();
        intent.setClass(context, GameBJSCActivity.class);
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

        title.setText(R.string.main_title);

        changeFragment(R.id.act_game_bjsc_lay_content, gameBJSCJCFragment);

        tvId.setText(AppContext.user.getUid() + "");
        tvNickname.setText(AppContext.user.getNickname());
        tvSum.setText(AppContext.user.getMoney().toString());

        getOnlineNum();
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
                changeFragment(R.id.act_game_bjsc_lay_content, gameCZFragment);
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
            case R.id.act_game_bjsc_btn_quick:
                // 快速下注
                boolean b = gameBJSCJCFragment.quickLay();
                if (b) {
                    layBottomQuick.setVisibility(View.VISIBLE);
                    layBottomNormal.setVisibility(View.GONE);
                } else {
                    layBottomQuick.setVisibility(View.GONE);
                    layBottomNormal.setVisibility(View.VISIBLE);
                }
                break;
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
            case R.id.act_game_bjsc_btn_keybord_quick:
                b = gameBJSCJCFragment.quickLay();
                if (b) {
                    layBottomQuick.setVisibility(View.VISIBLE);
                    layBottomNormal.setVisibility(View.GONE);
                } else {
                    layBottomQuick.setVisibility(View.GONE);
                    layBottomNormal.setVisibility(View.VISIBLE);
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
}
