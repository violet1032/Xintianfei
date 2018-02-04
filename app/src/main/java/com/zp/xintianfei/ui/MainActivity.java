package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.AgentFragment;
import com.zp.xintianfei.ui.fragment.BanddingFragment;
import com.zp.xintianfei.ui.fragment.GambleHistoryFragment;
import com.zp.xintianfei.ui.fragment.MainFragment;
import com.zp.xintianfei.ui.fragment.PersonFragment;
import com.zp.xintianfei.ui.fragment.RechargeFragment;
import com.zp.xintianfei.ui.fragment.RechargeHistoryFragment;
import com.zp.xintianfei.ui.fragment.RuleFragment;
import com.zp.xintianfei.ui.fragment.WithdrawFragment;
import com.zp.xintianfei.ui.fragment.WithdrawHistoryFragment;

import org.kymjs.kjframe.ui.BindView;

public class MainActivity extends BaseActivity {

    private int lastSelected = 1; // 底部之前选中
    private int currSelected = 1; // 底部当前选中

    private RechargeFragment rechargeFragment = new RechargeFragment();
    private WithdrawFragment withdrawFragment = new WithdrawFragment();
    private MainFragment mainFragment = new MainFragment();
    private RuleFragment ruleFragment = new RuleFragment();
    private PersonFragment personFragment = new PersonFragment();
    private AgentFragment agentFragment = new AgentFragment();
    private RechargeHistoryFragment rechargeHistoryFragment = new RechargeHistoryFragment();
    private WithdrawHistoryFragment withdrawHistoryFragment = new WithdrawHistoryFragment();
    private GambleHistoryFragment gambleHistoryFragment = new GambleHistoryFragment();
    private BanddingFragment banddingFragment = new BanddingFragment();

    private LinearLayout[] layBottoms = new LinearLayout[5];
    private LinearLayout[] layBottomsSelect = new LinearLayout[5];

    @BindView(id = R.id.act_main_lay_recharge, click = true)
    private LinearLayout layRecharge;
    @BindView(id = R.id.act_main_lay_withdraw, click = true)
    private LinearLayout layWithdraw;
    @BindView(id = R.id.act_main_lay_main, click = true)
    private LinearLayout layMain;
    @BindView(id = R.id.act_main_lay_rule, click = true)
    private LinearLayout layRule;
    @BindView(id = R.id.act_main_lay_person, click = true)
    private LinearLayout layPerson;

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
                setPosition(4);
                // 个人中心
                break;
        }
    }

    public void setPosition(int curr) {
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
                case 5:
                    // 代理中心
                    changeFragment(R.id.act_main_content, agentFragment);
                    break;
                case 6:
                    // 充值记录
                    changeFragment(R.id.act_main_content, rechargeHistoryFragment);
                    break;
                case 7:
                    // 提现记录
                    changeFragment(R.id.act_main_content, withdrawHistoryFragment);
                    break;
                case 8:
                    // 投注记录
                    changeFragment(R.id.act_main_content, gambleHistoryFragment);
                    break;
                case 9:
                    // 绑定银行卡
                    changeFragment(R.id.act_main_content, banddingFragment);
                    break;
            }

            lastSelected = currSelected;
        }
    }
}
