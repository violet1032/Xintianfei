package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class RechargeFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_recharge_lay_wechat, click = true)
    private LinearLayout layWechat;
    @BindView(id = R.id.fg_recharge_lay_alipay, click = true)
    private LinearLayout layAlipay;
    @BindView(id = R.id.fg_recharge_lay_card, click = true)
    private LinearLayout layCard;

    @BindView(id = R.id.fg_recharge_lay_wechat_content)
    private LinearLayout layWechatContent;
    @BindView(id = R.id.fg_recharge_lay_alipay_content)
    private LinearLayout layAlipayContent;
    @BindView(id = R.id.fg_recharge_lay_card_content)
    private LinearLayout layCardContent;

    @BindView(id = R.id.fg_recharge_btn_1, click = true)
    private Button btnExchange1;
    @BindView(id = R.id.fg_recharge_btn_2, click = true)
    private Button btnExchange2;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_recharge, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText("充值");
        imgBack.setVisibility(View.INVISIBLE);

        layWechatContent.setVisibility(View.VISIBLE);
        layAlipayContent.setVisibility(View.GONE);
        layCardContent.setVisibility(View.GONE);

        layWechat.setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
        layAlipay.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
        layCard.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_recharge_lay_wechat:
                // 微信
                layWechatContent.setVisibility(View.VISIBLE);
                layAlipayContent.setVisibility(View.GONE);
                layCardContent.setVisibility(View.GONE);

                layWechat.setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
                layAlipay.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                layCard.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                break;
            case R.id.fg_recharge_lay_alipay:
                // 支付宝
                layWechatContent.setVisibility(View.GONE);
                layAlipayContent.setVisibility(View.VISIBLE);
                layCardContent.setVisibility(View.GONE);
                layWechat.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                layAlipay.setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
                layCard.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                break;
            case R.id.fg_recharge_lay_card:
                // 银行卡
                layWechatContent.setVisibility(View.GONE);
                layAlipayContent.setVisibility(View.GONE);
                layCardContent.setVisibility(View.VISIBLE);
                layWechat.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                layAlipay.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                layCard.setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
                break;
            case R.id.fg_recharge_btn_1:
                ExchangeActivity.startActivity(getActivity(), 0);
                break;
            case R.id.fg_recharge_btn_2:
                ExchangeActivity.startActivity(getActivity(), 1);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
