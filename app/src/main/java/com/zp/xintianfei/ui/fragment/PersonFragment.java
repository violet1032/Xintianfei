package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class PersonFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

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

    @BindView(id = R.id.fg_person_btn_1, click = true)
    private Button btnExchange1;
    @BindView(id = R.id.fg_person_btn_2, click = true)
    private Button btnExchange2;

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
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_person_lay_9:
                // 转账
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
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
