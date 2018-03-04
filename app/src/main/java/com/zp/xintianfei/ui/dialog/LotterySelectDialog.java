package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.E_LOTTERY_TYPE;
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

    @BindView(id = R.id.dialog_lottery_select_lay_1, click = true)
    private LinearLayout lay1;
    @BindView(id = R.id.dialog_lottery_select_lay_2, click = true)
    private LinearLayout lay2;
    @BindView(id = R.id.dialog_lottery_select_lay_3, click = true)
    private LinearLayout lay3;
    @BindView(id = R.id.dialog_lottery_select_lay_4, click = true)
    private LinearLayout lay4;
    @BindView(id = R.id.dialog_lottery_select_lay_5, click = true)
    private LinearLayout lay5;
    @BindView(id = R.id.dialog_lottery_select_lay_6, click = true)
    private LinearLayout lay6;
    @BindView(id = R.id.dialog_lottery_select_lay_7, click = true)
    private LinearLayout lay7;
    @BindView(id = R.id.dialog_lottery_select_lay_8, click = true)
    private LinearLayout lay8;
    @BindView(id = R.id.dialog_lottery_select_lay_9, click = true)
    private LinearLayout lay9;

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

        Message message = new Message();
        message.what = 3;

        switch (v.getId()) {
            case R.id.dialog_lottery_select_btn_close:
                finish();
                break;
            case R.id.dialog_lottery_select_lay_1:
                message.arg1 = E_LOTTERY_TYPE.bjsc.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_2:
                message.arg1 = E_LOTTERY_TYPE.xgsm.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_3:
                message.arg1 = E_LOTTERY_TYPE.xyft.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_4:
                message.arg1 = E_LOTTERY_TYPE.cqssc.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_5:
                message.arg1 = E_LOTTERY_TYPE.qqlfc.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_6:
                message.arg1 = E_LOTTERY_TYPE.lhc.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_7:
                message.arg1 = E_LOTTERY_TYPE.pcdd.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_8:
                message.arg1 = E_LOTTERY_TYPE.jnd28.value;
                gamehandler.sendMessage(message);
                finish();
                break;
            case R.id.dialog_lottery_select_lay_9:
                message.arg1 = E_LOTTERY_TYPE.jsks.value;
                gamehandler.sendMessage(message);
                finish();
                break;
        }
    }
}
