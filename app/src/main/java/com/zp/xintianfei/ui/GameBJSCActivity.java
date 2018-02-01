package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.GameCZFragment;
import com.zp.xintianfei.ui.fragment.GameChatFragment;
import com.zp.xintianfei.ui.fragment.GameGZFragment;
import com.zp.xintianfei.ui.fragment.GameJLFragment;
import com.zp.xintianfei.ui.fragment.GameZSFragment;

import org.kymjs.kjframe.ui.BindView;

public class GameBJSCActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id=R.id.act_game_bjsc_btn_cz,click = true)
    private Button btnCZ;
    @BindView(id=R.id.act_game_bjsc_btn_jc,click = true)
    private Button btnJC;
    @BindView(id=R.id.act_game_bjsc_btn_jl,click = true)
    private Button btnJL;
    @BindView(id=R.id.act_game_bjsc_btn_zs,click = true)
    private Button btnZS;
    @BindView(id=R.id.act_game_bjsc_btn_gz,click = true)
    private Button btnGZ;
    @BindView(id=R.id.act_game_bjsc_btn_sx,click = true)
    private Button btnSX;

    private GameChatFragment gameChatFragment = new GameChatFragment();
    private GameCZFragment gameCZFragment = new GameCZFragment();
    private GameJLFragment gameJLFragment = new GameJLFragment();
    private GameZSFragment gameZSFragment = new GameZSFragment();
    private GameGZFragment gameGZFragment = new GameGZFragment();

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GameBJSCActivity.class);
        context.startActivity(intent);
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

        changeFragment(R.id.act_game_bjsc_lay_content, gameChatFragment);
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
                changeFragment(R.id.act_game_bjsc_lay_content,gameCZFragment);
                break;
            case R.id.act_game_bjsc_btn_jc:
                // 竞猜
                changeFragment(R.id.act_game_bjsc_lay_content,gameChatFragment);
                break;
            case R.id.act_game_bjsc_btn_jl:
                // 记录
                changeFragment(R.id.act_game_bjsc_lay_content,gameJLFragment);
                break;
            case R.id.act_game_bjsc_btn_zs:
                // 走势
                changeFragment(R.id.act_game_bjsc_lay_content,gameZSFragment);
                break;
            case R.id.act_game_bjsc_btn_gz:
                // 规则
                changeFragment(R.id.act_game_bjsc_lay_content,gameGZFragment);
                break;
            case R.id.act_game_bjsc_btn_sx:
                // 刷新
                break;
        }
    }
}
