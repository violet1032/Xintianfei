package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiCommon;
import com.zp.xintianfei.ui.ExchangeActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

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
            case R.id.fg_withdraw_btn_bading:
                ((MainActivity)getActivity()).setPosition(9);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
