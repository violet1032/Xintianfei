package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;
import java.util.Map;

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

    @BindView(id = R.id.fg_recharge_btn_sure, click = true)
    private Button btnSure;

    private int bankId;

    @BindView(id = R.id.fg_recharge_edt_id)
    private EditText edtName;
    @BindView(id = R.id.fg_recharge_edt_sum)
    private EditText edtMoney;

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
        bankId = 33;

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

                bankId = 33;
                break;
            case R.id.fg_recharge_lay_alipay:
                // 支付宝
                layWechatContent.setVisibility(View.GONE);
                layAlipayContent.setVisibility(View.VISIBLE);
                layCardContent.setVisibility(View.GONE);
                layWechat.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                layAlipay.setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
                layCard.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                bankId = 32;
                break;
            case R.id.fg_recharge_lay_card:
                // 银行卡
                layWechatContent.setVisibility(View.GONE);
                layAlipayContent.setVisibility(View.GONE);
                layCardContent.setVisibility(View.VISIBLE);
                layWechat.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                layAlipay.setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                layCard.setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
                bankId = 34;
                break;
            case R.id.fg_recharge_btn_1:
                ExchangeActivity.startActivity(getActivity(), 0);
                break;
            case R.id.fg_recharge_btn_2:
                ExchangeActivity.startActivity(getActivity(), 1);
                break;
            case R.id.fg_recharge_btn_sure:
                // 充值
                recharge();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void recharge() {
        String name = edtName.getText().toString().trim();
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = new BigDecimal(edtMoney.getText().toString().trim());
        } catch (Exception e) {
            UIHelper.ToastMessage("请输入正确的金额");
            return;
        }

        if (StringUtils.isEmpty(name)) {
            UIHelper.ToastMessage("请输入转账昵称");
            return;
        }
        if (bigDecimal == null) {
            UIHelper.ToastMessage("请输入正确的金额");
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
                    edtName.setText("");
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

        ApiUser.cz(bankId, name, bigDecimal, callBack);
    }
}
