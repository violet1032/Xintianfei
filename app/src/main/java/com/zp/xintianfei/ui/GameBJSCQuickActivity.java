package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.GameBJSCQuickJCFragment;

import org.kymjs.kjframe.ui.BindView;

public class GameBJSCQuickActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    private GameBJSCQuickJCFragment gameBJSCQuickJCFragment = new GameBJSCQuickJCFragment();

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, GameBJSCQuickActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_game_bjscquick);
    }

    @Override
    public void initData() {
        super.initData();

        changeFragment(R.id.act_game_bjsc_quick_framelayout, gameBJSCQuickJCFragment);
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                finish();
                break;
        }
    }
}
