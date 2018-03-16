package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

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

    @BindView(id = R.id.dialog_lottery_img_1, click = true)
    private ImageView img1;
    @BindView(id = R.id.dialog_lottery_img_2, click = true)
    private ImageView img2;
    @BindView(id = R.id.dialog_lottery_img_3, click = true)
    private ImageView img3;
    @BindView(id = R.id.dialog_lottery_img_4, click = true)
    private ImageView img4;
    @BindView(id = R.id.dialog_lottery_img_5, click = true)
    private ImageView img5;
    @BindView(id = R.id.dialog_lottery_img_6, click = true)
    private ImageView img6;
    @BindView(id = R.id.dialog_lottery_img_7, click = true)
    private ImageView img7;
    @BindView(id = R.id.dialog_lottery_img_8, click = true)
    private ImageView img8;
    @BindView(id = R.id.dialog_lottery_img_9, click = true)
    private ImageView img9;
    @BindView(id = R.id.dialog_lottery_img_10, click = true)
    private ImageView img10;
    @BindView(id = R.id.dialog_lottery_img_11, click = true)
    private ImageView img11;
    @BindView(id = R.id.dialog_lottery_img_12, click = true)
    private ImageView img12;
    @BindView(id = R.id.dialog_lottery_img_13, click = true)
    private ImageView img13;
    @BindView(id = R.id.dialog_lottery_img_14, click = true)
    private ImageView img14;
    @BindView(id = R.id.dialog_lottery_img_15, click = true)
    private ImageView img15;
    @BindView(id = R.id.dialog_lottery_img_16, click = true)
    private ImageView img16;

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
//            case R.id.dialog_lottery_select_lay_1:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.bjsc.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.bjsc.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.bjsc.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_2:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.xgsm.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.xgsm.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.xgsm.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_3:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.xyft.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.xyft.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.xyft.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_4:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.cqssc.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.cqssc.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.cqssc.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_5:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.qqlfc.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.qqlfc.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.qqlfc.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_6:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.lhc.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.lhc.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.lhc.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_7:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.pcdd.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.pcdd.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.pcdd.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_8:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.jnd28.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.jnd28.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.jnd28.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
//            case R.id.dialog_lottery_select_lay_9:
//                if (mapHasnotrun.get(E_LOTTERY_TYPE.bjsc.value) != null && mapHasnotrun.get(E_LOTTERY_TYPE.jsks.value))
//                    UIHelper.ToastMessage("彩种已关盘");
//                else {
//                    message.arg1 = E_LOTTERY_TYPE.jsks.value;
//                    gamehandler.sendMessage(message);
//                    finish();
//                }
//                break;
            case R.id.dialog_lottery_img_1:
                // 北京赛车
                getGameStatus(2);
                break;
            case R.id.dialog_lottery_img_2:
                // 香港赛马
                getGameStatus(5);
                break;
            case R.id.dialog_lottery_img_3:
                // 澳门赛狗
                getGameStatus(13);
                break;
            case R.id.dialog_lottery_img_4:
                // 极速赛车
                getGameStatus(12);
                break;
            case R.id.dialog_lottery_img_5:
                // 幸运飞艇
                getGameStatus(3);
                break;
            case R.id.dialog_lottery_img_6:
                getGameStatus(1);
                break;
            case R.id.dialog_lottery_img_7:
                getGameStatus(9);
                break;
            case R.id.dialog_lottery_img_8:
                getGameStatus(14);
                break;
            case R.id.dialog_lottery_img_9:
                getGameStatus(15);
                break;
            case R.id.dialog_lottery_img_10:
                getGameStatus(4);
                break;
            case R.id.dialog_lottery_img_11:
                getGameStatus(8);
                break;
            case R.id.dialog_lottery_img_12:
                getGameStatus(18);
                break;
            case R.id.dialog_lottery_img_13:
                getGameStatus(6);
                break;
            case R.id.dialog_lottery_img_14:
                getGameStatus(16);
                break;
            case R.id.dialog_lottery_img_15:
                getGameStatus(17);
                break;
            case R.id.dialog_lottery_img_16:
                getGameStatus(10);
                break;
        }
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
                            Message message = new Message();
                            message.what = 3;
                            message.arg1 = cate;
                            gamehandler.sendMessage(message);
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(LotterySelectDialog.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiLottery.getGameState(cate, callBack);
    }

}
