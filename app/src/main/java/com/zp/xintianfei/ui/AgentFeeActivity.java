package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

public class AgentFeeActivity extends BaseActivity {
    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_agent_fee_edt_fanshui)
    private EditText edtFeeFanshui;
    @BindView(id = R.id.act_agent_fee_edt_yongjin)
    private EditText edtFeeYongjin;

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

        edtFeeFanshui.setText((int) (50 * (Float.parseFloat(AppContext.user.getFs_rate()) / 100f)) + "");
        edtFeeFanshui.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (StringUtils.isEmpty(charSequence.toString())) {
                    edtFeeYongjin.setText(50 + "");
                    return;
                }
                int fanshui = 0;
                try {
                    fanshui = Integer.parseInt(charSequence.toString());
                    if (fanshui <= 50) {
                        edtFeeYongjin.setText((50 - fanshui) + "");
                    } else
                        UIHelper.ToastMessage("请正确输入50以内的整数值，可包括50");
                } catch (Exception e) {
                    UIHelper.ToastMessage("请正确输入50以内的整数值，可包括50");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtFeeYongjin.setEnabled(false);
        edtFeeYongjin.setText((int) (50 - 50 * (Float.parseFloat(AppContext.user.getFs_rate()) / 100f)) + "");
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
            value = Integer.parseInt(edtFeeFanshui.getText().toString().trim());
            if (value > 50) {
                UIHelper.ToastMessage("请正确输入50以内的整数值，可包括50");
                return;
            }
        } catch (Exception e) {
            UIHelper.ToastMessage("请正确输入50以内的整数值，可包括50");
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
                    AppContext.user.setFs_rate(value + "");
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
        ApiUser.setFsRate((int) ((float) value / 50f * 100), callBack);
    }
}
