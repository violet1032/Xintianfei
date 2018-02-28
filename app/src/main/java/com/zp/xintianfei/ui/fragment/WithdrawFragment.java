package com.zp.xintianfei.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppConfig;
import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Bank;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.ui.dialog.SelectWithdrawTypeDialog;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class WithdrawFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_withdraw_btn_1, click = true)
    private Button btnExchange1;
    @BindView(id = R.id.fg_withdraw_btn_2, click = true)
    private Button btnExchange2;

    @BindView(id = R.id.fg_withdraw_btn_bading, click = true)
    private LinearLayout layBadding;
    @BindView(id = R.id.fg_withdraw_btn_online, click = true)
    private LinearLayout layOnline;

    @BindView(id = R.id.fg_tx_nickname)
    private TextView tvNickname;
    @BindView(id = R.id.fg_tx_id)
    private TextView tvID;
    @BindView(id = R.id.fg_tx_sum)
    private TextView tvSum;
    @BindView(id = R.id.fg_tx_fanshui_sum)
    private TextView tvSumFanshui;
    @BindView(id = R.id.fg_tx_yongjin_sum)
    private TextView tvSumYongjin;

    @BindView(id = R.id.fg_main_img_head)
    private ImageView imgHead;

    @BindView(id = R.id.fg_withdraw_edt_sum)
    private EditText edtMoney;
    @BindView(id = R.id.fg_withdraw_btn_sure, click = true)
    private Button btnWithdraw;

    @BindView(id = R.id.fg_withdraw_lay_weixin, click = true)
    private LinearLayout layOnlineWeixin;
    @BindView(id = R.id.fg_withdraw_lay_qq, click = true)
    private LinearLayout layOnlineQQ;
    @BindView(id = R.id.fg_withdraw_lay_customer, click = true)
    private LinearLayout layOnlineCustomer;

    @BindView(id = R.id.fg_withdraw_btn_bading_weixin, click = true)
    private LinearLayout layBandingWeixin;
    @BindView(id = R.id.fg_withdraw_btn_bading_alipay, click = true)
    private LinearLayout layBandingAlipay;
    @BindView(id = R.id.fg_withdraw_btn_bading, click = true)
    private LinearLayout layBandingCard;

    @BindView(id = R.id.fg_withdraw_select_type, click = true)
    private LinearLayout laySelectType;

    private Handler handler;
    @BindView(id = R.id.fg_bandding_tv_name)
    private TextView tvName;
    private int bankId;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_withdraw, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText("提现");
        imgBack.setVisibility(View.INVISIBLE);

        tvNickname.setText(AppContext.user.getNickname());
        tvID.setText(AppContext.user.getUid() + "");
        tvSum.setText(AppContext.user.getMoney().toString());
        tvSumFanshui.setText(AppContext.user.getFanshui().toString());
        tvSumYongjin.setText(AppContext.user.getYongjin().toString());

        ApiCommon.getNetBitmap(AppContext.user.getAvatar(), imgHead, false);
    }

    @Override
    protected void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                if (message.what == 1) {
                    Bank bank = (Bank) message.obj;
                    tvName.setText(bank.getName());
                    bankId = bank.getId();
                }else if (message.what == 101) {
                    tvSum.setText(AppContext.user.getMoney().toString());
                    tvSumFanshui.setText(AppContext.user.getFanshui().toString());
                    tvSumYongjin.setText(AppContext.user.getYongjin().toString());
                }

                return false;
            }
        });

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(101);
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_withdraw_btn_1:
                ExchangeActivity.startActivity(getActivity(), 0);
                break;
            case R.id.fg_withdraw_btn_2:
                ExchangeActivity.startActivity(getActivity(), 1);
                break;
            case R.id.fg_withdraw_btn_sure:
                withdraw();
                break;
            case R.id.fg_withdraw_lay_weixin:
                ((MainActivity) getActivity()).setPosition(11);
                break;
            case R.id.fg_withdraw_lay_qq:
                ((MainActivity) getActivity()).setPosition(12);
                break;
            case R.id.fg_withdraw_lay_customer:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(AppConfig.getInstance().getmPre().getString("online_service", ""));
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.fg_withdraw_btn_bading:
                ((MainActivity) getActivity()).setPosition(9);
                break;
            case R.id.fg_withdraw_btn_bading_weixin:
                ((MainActivity) getActivity()).setPosition(13);
                break;
            case R.id.fg_withdraw_btn_bading_alipay:
                ((MainActivity) getActivity()).setPosition(14);
                break;
            case R.id.fg_withdraw_select_type:
                SelectWithdrawTypeDialog.startActivity(getActivity(), handler);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void withdraw() {
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = new BigDecimal(edtMoney.getText().toString().trim());
        } catch (Exception e) {
            UIHelper.ToastMessage("请输入正确的金额");
            return;
        }

        if (bigDecimal == null) {
            UIHelper.ToastMessage("请输入正确的金额");
            return;
        }

        if (bankId == 0) {
            UIHelper.ToastMessage("请选择提现类别");
            return;
        }

        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    UIHelper.ToastMessage(result.getMsg());
                    edtMoney.setText("");
                } else
                    UIHelper.ToastMessage(result.getMsg());
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
        ApiUser.tx(bankId, bigDecimal, callBack);
    }

    public void changeSum(){
        tvSum.setText(AppContext.user.getMoney().toString());
        tvSumFanshui.setText(AppContext.user.getFanshui().toString());
        tvSumYongjin.setText(AppContext.user.getYongjin().toString());
    }
}
