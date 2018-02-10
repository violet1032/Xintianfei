package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

public class ExchangeActivity extends BaseActivity {

    private int type; // 0：反水，1：佣金

    @BindView(id = R.id.act_exchange_tv_sum)
    private TextView tvSum;
    @BindView(id = R.id.act_exchange_tv_type)
    private TextView tvSumTitle;
    @BindView(id = R.id.act_exchange_tv_title)
    private TextView tvTitle;
    @BindView(id = R.id.act_exchange_edt_sum)
    private EditText edtSum;
    @BindView(id = R.id.act_exchange_btn_close, click = true)
    private Button btnClose;
    @BindView(id = R.id.act_exchange_btn_sure, click = true)
    private Button btnSure;

    public static void startActivity(Context context, int type) {
        Intent intent = new Intent();
        intent.setClass(context, ExchangeActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_exchange);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        type = getIntent().getIntExtra("type", 0);

        switch (type) {
            case 0:
                tvTitle.setText(R.string.exchange_text_1);
                tvSumTitle.setText(R.string.exchange_text_3);
                edtSum.setHint(R.string.exchange_text_2);
                tvSum.setText(AppContext.user.getFanshui() + "");

                break;
            case 1:
                tvTitle.setText(R.string.exchange_text_5);
                tvSumTitle.setText(R.string.exchange_text_7);
                edtSum.setHint(R.string.exchange_text_6);
                tvSum.setText(AppContext.user.getYongjin() + "");
                break;
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.act_exchange_btn_close:
                finish();
                break;
            case R.id.act_exchange_btn_sure:
                exchange();
                break;
        }
    }

    private void exchange() {
        int money;
        try {
            money = Integer.parseInt(edtSum.getText().toString().trim());
        } catch (Exception e) {
            UIHelper.ToastMessage("请输入正确的兑换点数");
            return;
        }

        FHttpCallBack callBack = new FHttpCallBack(){
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if(result.isOk()){
                    UIHelper.ToastMessage(result.getMsg());
                    finish();
                }else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(ExchangeActivity.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };

        switch (type) {
            case 0:
                // 反水兑换
                ApiUser.exchangeFS(money,callBack);
                break;
            case 1:
                // 佣金兑换
                ApiUser.exchangeYJ(money, callBack);
                break;
        }
    }
}
