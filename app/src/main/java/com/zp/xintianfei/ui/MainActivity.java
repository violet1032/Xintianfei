package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.MainFragment;
import com.zp.xintianfei.ui.fragment.PersonFragment;
import com.zp.xintianfei.ui.fragment.RechargeFragment;
import com.zp.xintianfei.ui.fragment.RuleFragment;
import com.zp.xintianfei.ui.fragment.WithdrawFragment;

public class MainActivity extends BaseActivity {

    private int lastSelected = 1; // 底部之前选中
    private int currSelected = 1; // 底部当前选中

    private RechargeFragment rechargeFragment = new RechargeFragment();
    private WithdrawFragment withdrawFragment = new WithdrawFragment();
    private MainFragment mainFragment = new MainFragment();
    private RuleFragment ruleFragment = new RuleFragment();
    private PersonFragment personFragment = new PersonFragment();

    private LinearLayout[] layBottoms = new LinearLayout[4];
    private LinearLayout[] layBottomsSelect = new LinearLayout[4];

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        layBottoms[0] = (LinearLayout) findViewById(R.id.act_main_lay_recharge);
        layBottoms[1] = (LinearLayout) findViewById(R.id.act_main_lay_withdraw);
        layBottoms[2] = (LinearLayout) findViewById(R.id.act_main_lay_main);
        layBottoms[3] = (LinearLayout) findViewById(R.id.act_main_lay_rule);
        layBottoms[4] = (LinearLayout) findViewById(R.id.act_main_lay_person);

        setPosition(2);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.act_main_lay_recharge:
                // 充值
                setPosition(0);
                break;
            case R.id.act_main_lay_withdraw:
                setPosition(1);
                // 提现
                break;
            case R.id.act_main_lay_main:
                setPosition(2);
                // 大厅
                break;
            case R.id.act_main_lay_rule:
                setPosition(3);
                // 规则
                break;
            case R.id.act_main_lay_person:
                setPosition(3);
                // 个人中心
                break;
        }
    }

    private void setPosition(int curr) {
        if (currSelected != curr) {
            currSelected = curr;
            // 将所有先变为常态
            // 设置为选中状态
            switch (curr) {
                case 0:
                    // 充值
                    changeFragment(R.id.act_main_content, rechargeFragment);
                    break;
                case 1:
                    // 提现
                    changeFragment(R.id.act_main_content, withdrawFragment);
                    break;
                case 2:
                    // 大厅
                    changeFragment(R.id.act_main_content, mainFragment);
                    break;
                case 3:
                    // 规则
                    changeFragment(R.id.act_main_content, ruleFragment);
                    break;
                case 4:
                    // 个人中心
                    changeFragment(R.id.act_main_content, personFragment);
                    break;
            }

            lastSelected = currSelected;
        }
    }
}
