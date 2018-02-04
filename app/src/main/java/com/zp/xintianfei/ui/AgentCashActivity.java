package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.AgentCashHistoryAdapter;
import com.zp.xintianfei.bean.AgentCashHistory;
import com.zp.xintianfei.bean.AgentCashHistoryList;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;

public class AgentCashActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_agent_cash_list)
    private ListView lvAgentCash;
    private AgentCashHistoryList agentCashHistoryList = new AgentCashHistoryList();
    private AgentCashHistoryAdapter agentCashHistoryAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AgentCashActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_agent_cash);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        title.setText(R.string.agent_cash_text_1);

        /******/
        AgentCashHistory agentCashHistory = new AgentCashHistory();
        agentCashHistory.setMoney(new BigDecimal(1000));
        agentCashHistory.setType("重庆时时彩");
        agentCashHistory.setTime("2018.01.04");
        agentCashHistory.setStage("2018006");
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        agentCashHistoryList.getList().add(agentCashHistory);
        /******/

        agentCashHistoryAdapter = new AgentCashHistoryAdapter(lvAgentCash, agentCashHistoryList.getList());
        lvAgentCash.setAdapter(agentCashHistoryAdapter);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                finish();
                break;
        }
    }
}
