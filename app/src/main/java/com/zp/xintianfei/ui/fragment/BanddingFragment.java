package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class BanddingFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

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

//    @BindView(id = R.id.fg_bandding_select_bank, click = true)
//    private LinearLayout laySelectBank;
    @BindView(id = R.id.fg_bandding_tv_name)
    private TextView tvName;

    private Handler handler;

    @BindView(id = R.id.fg_bandding_edt_bankname)
    private EditText edtBankname;
    @BindView(id = R.id.fg_bandding_edt_realname)
    private EditText edtRealname;
    @BindView(id = R.id.fg_bandding_edt_number)
    private EditText edtNumber;
    @BindView(id = R.id.fg_bandding_edt_renumber)
    private EditText edtReNumber;
    @BindView(id = R.id.fg_bandding_btn_bandding, click = true)
    private Button btnBadding;

    private int bankId = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_bandding, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText(R.string.bandding_text_1);

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
                }

                return false;
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_recharge_btn_1:
                ExchangeActivity.startActivity(getActivity(), 0);
                break;
            case R.id.fg_recharge_btn_2:
                ExchangeActivity.startActivity(getActivity(), 1);
                break;
            case R.id.umeng_banner_img_left:
                ((MainActivity) getActivity()).setPosition(1);
                break;
//            case R.id.fg_bandding_select_bank:
//                // 选择银行
//                SelectBankDialog.startActivity(getActivity(), handler);
//                break;
            case R.id.fg_bandding_btn_bandding:
                badding();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void badding() {
        String bankName = edtBankname.getText().toString().trim();
        String realName = edtRealname.getText().toString().trim();
        String number = edtNumber.getText().toString().trim();
        String renumber = edtReNumber.getText().toString().trim();


//        if (bankId == 0) {
//            UIHelper.ToastMessage("请选择银行类型");
//            return;
//        }

        if (StringUtils.isEmpty(bankName)) {
            UIHelper.ToastMessage("请输入银行名称");
            return;
        }
        if (StringUtils.isEmpty(realName)) {
            UIHelper.ToastMessage("请输入持卡人姓名");
            return;
        }
        if (StringUtils.isEmpty(number)) {
            UIHelper.ToastMessage("请输入银行卡号");
            return;
        }
        if (!renumber.equals(number)) {
            UIHelper.ToastMessage("两次输入银行卡号不一样，请重新输入");
            return;
        }

        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    UIHelper.ToastMessage("绑定成功");
                    ((MainActivity) getActivity()).setPosition(1);
                } else {
                    UIHelper.ToastMessage(result.getMsg());
                }
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
        ApiUser.bindBank(bankId, number, realName, bankName, callBack);
    }
}
