package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.XGLHCMainFragment;
import com.zp.xintianfei.ui.fragment.XGLHCTMBSFragment;
import com.zp.xintianfei.ui.fragment.XGLHCTMFragment;
import com.zp.xintianfei.ui.fragment.XGLHCTMSXFragment;

import org.kymjs.kjframe.ui.BindView;

public class GameXGLHCActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id=R.id.act_game_xglhc_lay)
    private LinearLayout lay;

    private XGLHCMainFragment mainFragment = new XGLHCMainFragment();
    private XGLHCTMFragment xglhctmFragment = new XGLHCTMFragment();
    private XGLHCTMSXFragment xglhctmsxFragment = new XGLHCTMSXFragment();
    private XGLHCTMBSFragment xglhctmbsFragment = new XGLHCTMBSFragment();

    private int type = 0;

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
    }

    @Override
    public void initWidget() {
        super.initWidget();

        title.setText(R.string.game_xglhc_text_1);

        lay.setVisibility(View.GONE);
        changeFragment(R.id.act_game_xglhc_content, mainFragment);
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

    public void setType(int type){
        this.type = type;
        lay.setVisibility(View.VISIBLE);
        switch (type){
            case 1:
                // 特码
                changeFragment(R.id.act_game_xglhc_content, xglhctmFragment);
                break;
            case 4:
                //
                changeFragment(R.id.act_game_xglhc_content, xglhctmsxFragment);
                break;
            case 5:
                //
                changeFragment(R.id.act_game_xglhc_content, xglhctmbsFragment);
                break;
        }
    }
}
