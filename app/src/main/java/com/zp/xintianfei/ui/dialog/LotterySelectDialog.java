package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p/>
 * 描述:
 * <p/>
 * 作者:Administrator
 * <p/>
 * 时间:2018/2/9 15:24
 * <p/>
 * 版本:
 */
public class LotterySelectDialog extends BaseActivity {

    @BindView(id = R.id.dialog_lottery_select_btn_close, click = true)
    private Button btnClose;

    private static Handler gamehandler;

    public static void startActivity(Activity activity, Handler handler) {
        Intent intent = new Intent();
        intent.setClass(activity, LotterySelectDialog.class);
        activity.startActivityForResult(intent, 0);

        gamehandler = handler;
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.dialog_lottery_select);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.dialog_lottery_select_btn_close:
                finish();
                break;
        }
    }
}
