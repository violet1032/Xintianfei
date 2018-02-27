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
import android.widget.LinearLayout;
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
import com.zp.xintianfei.ui.dialog.TransferSelectDialog;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class TransferFragment extends BaseFragment {

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
    @BindView(id = R.id.fg_transfer_btn_sure, click = true)
    private Button btnSure;

    @BindView(id = R.id.fg_main_img_head)
    private ImageView imgHead;

    private int bankId;

    @BindView(id = R.id.fg_transfer_edt_sum)
    private EditText edtMoney;
    @BindView(id = R.id.fg_transfer_lay_out, click = true)
    private LinearLayout layOut;
    @BindView(id = R.id.fg_transfer_lay_in, click = true)
    private LinearLayout layIn;

    private int outId = 0;
    private int inId = 0;

    private Handler handler;

    private int type = 0;// 0 :转出 1：转入

    private MemberMoneyList memberMoneyList = new MemberMoneyList();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_transfer, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText("转账");
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
                    MemberMoney memberMoney = (MemberMoney) message.obj;

                    if (type == 0) {
                        ((TextView) layOut.getChildAt(0)).setText(memberMoney.getInfo() + " 余额点数：" + memberMoney.getValue());
                        outId = memberMoney.getId();
                    } else {
                        ((TextView) layIn.getChildAt(0)).setText(memberMoney.getInfo() + " 余额点数：" + memberMoney.getValue());
                        inId = memberMoney.getId();
                    }
                }
                return false;
            }
        });

        getMemberMoney();
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
            case R.id.fg_transfer_lay_out:
                // 转出
                type = 0;
                TransferSelectDialog.startActivity(getActivity(), handler, memberMoneyList);
                break;
            case R.id.fg_transfer_lay_in:
                // 转入
                type = 1;
                TransferSelectDialog.startActivity(getActivity(), handler, memberMoneyList);
                break;
            case R.id.fg_transfer_btn_sure:
                // 转账
                transfer();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void transfer() {
        BigDecimal bigDecimal;
        try {
            bigDecimal = new BigDecimal(edtMoney.getText().toString().trim());
        } catch (Exception e) {
            UIHelper.ToastMessage("请输入正确的金额");
            return;
        }

        if (outId == 0) {
            UIHelper.ToastMessage("请选择转出");
            return;
        }
        if (inId == 0) {
            UIHelper.ToastMessage("请选择转入");
            return;
        }

        if (outId == inId) {
            UIHelper.ToastMessage("转出和转入不能相同");
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

        ApiUser.zz(outId, inId, bigDecimal, callBack);
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
