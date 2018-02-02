package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

public class ExchangeActivity extends BaseActivity {

    private int type; // 0：反水，1：佣金

    @BindView(id = R.id.act_exchange_tv_sum)
    private TextView tvSum;
    @BindView(id = R.id.act_exchange_tv_title)
    private TextView tvTitle;
    @BindView(id = R.id.act_exchange_edt_sum)
    private EditText edtSum;

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

        int type = getIntent().getIntExtra("type", 0);

        switch (type) {
            case 0:
                tvTitle.setText(R.string.exchange_text_1);
                tvSum.setText(R.string.exchange_text_3);
                edtSum.setHint(R.string.exchange_text_2);
                break;
            case 1:
                tvTitle.setText(R.string.exchange_text_5);
                tvSum.setText(R.string.exchange_text_7);
                edtSum.setHint(R.string.exchange_text_6);
                break;
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }
}
