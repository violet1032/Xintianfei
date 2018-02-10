package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class AgentFeeActivity extends BaseActivity {
    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_agent_fee_edt)
    private EditText edtFee;

    @BindView(id = R.id.act_agent_fee_btn, click = true)
    private Button btnSure;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AgentFeeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_agent_fee);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        title.setText(R.string.agent_fee_text_1);

        edtFee.setText(AppContext.user.getFs_rate());
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                finish();
                break;
            case R.id.act_agent_fee_btn:
                setFee();
                break;
        }
    }

    public void setFee() {
        final int value;
        try {
            value = Integer.parseInt(edtFee.getText().toString().trim());
        } catch (Exception e) {
            UIHelper.ToastMessage("请输入正确的返水比例");
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
                    AppContext.user.setFs_rate(value+"");
                    finish();
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(AgentFeeActivity.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiUser.setFsRate(value, callBack);
    }
}
