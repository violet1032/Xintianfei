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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class PersonFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;
    @BindView(id = R.id.umeng_banner_tv_right, click = true)
    private TextView tvRight;

    @BindView(id = R.id.fg_person_lay_9, click = true)
    private RelativeLayout layTransfer;
    @BindView(id = R.id.fg_person_lay_10, click = true)
    private RelativeLayout layRecharge;
    @BindView(id = R.id.fg_person_lay_11, click = true)
    private RelativeLayout layWithdraw;
    @BindView(id = R.id.fg_person_lay_8, click = true)
    private LinearLayout layAgent;
    @BindView(id = R.id.fg_person_lay_7, click = true)
    private LinearLayout layRechargeHistory;
    @BindView(id = R.id.fg_person_lay_6, click = true)
    private LinearLayout layWithdrawHistory;
    @BindView(id = R.id.fg_person_lay_3, click = true)
    private LinearLayout layGambleHistory;
    @BindView(id = R.id.fg_person_lay_4, click = true)
    private LinearLayout layOnline;
    @BindView(id = R.id.fg_person_lay_5, click = true)
    private LinearLayout layDownload;

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

    @BindView(id = R.id.fg_main_img_head)
    private ImageView imgHead;

    private Handler handler;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_person, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText("个人中心");
        imgBack.setVisibility(View.INVISIBLE);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("退出");

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
            case R.id.fg_person_lay_9:
                // 转账
                ((MainActivity) getActivity()).setPosition(15);
                break;
            case R.id.fg_person_lay_10:
                // 充值
                ((MainActivity) getActivity()).setPosition(0);
                break;
            case R.id.fg_person_lay_11:
                // 提现
                ((MainActivity) getActivity()).setPosition(1);
                break;
            case R.id.fg_person_lay_8:
                // 代理中心
                ((MainActivity) getActivity()).setPosition(5);
                break;
            case R.id.fg_person_btn_1:
                // 返水兑换
                ExchangeActivity.startActivity(getActivity(), 0);
                break;
            case R.id.fg_person_btn_2:
                // 佣金兑换
                ExchangeActivity.startActivity(getActivity(), 1);
                break;
            case R.id.fg_person_lay_7:
                // 充值记录
                ((MainActivity) getActivity()).setPosition(6);
                break;
            case R.id.fg_person_lay_6:
                // 提现记录
                ((MainActivity) getActivity()).setPosition(7);
                break;
            case R.id.fg_person_lay_3:
                // 投注记录
                ((MainActivity) getActivity()).setPosition(8);
                break;
            case R.id.fg_person_lay_4:
                // 客服人员
                ((MainActivity) getActivity()).setPosition(10);
                break;
            case R.id.fg_person_lay_5:
                // app下载
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(AppContext.downLoadUrl);
                intent.setData(content_url);
                startActivity(intent);
                break;
            case R.id.umeng_banner_tv_right:
                System.exit(0);
                break;
            case R.id.fg_person_btn_3:
                // 账户总览
                ((MainActivity) getActivity()).setPosition(16);
                break;
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
}
