package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.AgentLowerHistoryAdapter;
import com.zp.xintianfei.bean.AgentLowerHistory;
import com.zp.xintianfei.bean.AgentLowerHistoryList;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;

/**
 *
 */
public class AgentLowerActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_agent_lower_list)
    private ListView lvAgentLower;
    private AgentLowerHistoryList agentLowerHistoryList = new AgentLowerHistoryList();
    private AgentLowerHistoryAdapter agentLowerHistoryAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AgentLowerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_agent_lower);
    }

    @Override
    public void initData() {
        super.initData();
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

    @Override
    public void initWidget() {
        super.initWidget();

        title.setText(R.string.agent_lower_text_1);

        /******/
        AgentLowerHistory agentLowerHistory = new AgentLowerHistory();
        agentLowerHistory.setMoney(new BigDecimal(1000));
        agentLowerHistory.setId(100213);
        agentLowerHistory.setTime("2018.01.04");
        agentLowerHistory.setNickname("社会牛哥");
        agentLowerHistoryList.getList().add(agentLowerHistory);
        /******/

        agentLowerHistoryAdapter = new AgentLowerHistoryAdapter(lvAgentLower, agentLowerHistoryList.getList());
        lvAgentLower.setAdapter(agentLowerHistoryAdapter);
    }
}
