package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.MemberMoney;
import com.zp.xintianfei.bean.MemberMoneyList;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class ScanFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_person_btn_1, click = true)
    private Button btnExchange1;
    @BindView(id = R.id.fg_person_btn_2, click = true)
    private Button btnExchange2;
    @BindView(id = R.id.fg_person_btn_3, click = true)
    private Button btnScan;

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

    @BindView(id = R.id.fg_scan_tv_1)
    private TextView tvPT;
    @BindView(id = R.id.fg_scan_tv_2)
    private TextView tvAG;
    @BindView(id = R.id.fg_scan_tv_3)
    private TextView tvBBIN;
    @BindView(id = R.id.fg_scan_tv_4)
    private TextView tvBG;
    @BindView(id = R.id.fg_scan_tv_5)
    private TextView tvSS;

    @BindView(id = R.id.fg_main_img_head)
    private ImageView imgHead;

    private Handler handler;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_scan, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText("账户总览");
        imgBack.setVisibility(View.INVISIBLE);

        tvNickname.setText(AppContext.user.getNickname());
        tvID.setText(AppContext.user.getUid() + "");
        tvSum.setText(AppContext.user.getMoney().toString());
        tvSumFanshui.setText(AppContext.user.getFanshui().toString());
        tvSumYongjin.setText(AppContext.user.getYongjin().toString());

        ApiCommon.getNetBitmap(AppContext.user.getAvatar(), imgHead, false);

        getMemberMoney();
    }

    @Override
    protected void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                if (message.what == 101) {
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
            case R.id.fg_person_btn_3:
                // 账户总览
                break;
            case R.id.fg_person_btn_1:
                // 返水兑换
                ExchangeActivity.startActivity(getActivity(), 0);
                break;
            case R.id.fg_person_btn_2:
                // 佣金兑换
                ExchangeActivity.startActivity(getActivity(), 1);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public void changeSum() {
        tvSum.setText(AppContext.user.getMoney().toString());
        tvSumFanshui.setText(AppContext.user.getFanshui().toString());
        tvSumYongjin.setText(AppContext.user.getYongjin().toString());
    }

    private MemberMoneyList memberMoneyList = new MemberMoneyList();

    @Override
    public void onChange() {
        super.onChange();
        getMemberMoney();
    }

    public void getMemberMoney() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        memberMoneyList.parse(str);

                        for (MemberMoney memberMoney :
                                memberMoneyList.getList()) {
                            if (memberMoney.getName().equals("system"))
                                tvPT.setText(memberMoney.getValue() + "");
                            else if (memberMoney.getName().equals("ag"))
                                tvAG.setText(memberMoney.getValue() + "");
                            else if (memberMoney.getName().equals("bb"))
                                tvBBIN.setText(memberMoney.getValue() + "");
                            else if (memberMoney.getName().equals("bg"))
                                tvBG.setText(memberMoney.getValue() + "");
                            else if (memberMoney.getName().equals("ss"))
                                tvSS.setText(memberMoney.getValue() + "");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("解析出错");
                    }
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
        ApiUser.getMemberMoney(callBack);
    }
}
