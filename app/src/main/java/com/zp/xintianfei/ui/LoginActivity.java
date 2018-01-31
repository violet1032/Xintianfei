package com.zp.xintianfei.ui;

import android.view.View;
import android.widget.Button;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(id = R.id.act_login_btn_login, click = true)
    private Button btnLogin;

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_login);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.act_login_btn_login:
                MainActivity.startActivity(this);
                finish();
                break;
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }
}
