package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.AgentCashHistoryAdapter;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.AgentCashHistoryList;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.dialog.DataTimePicker;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Calendar;
import java.util.Map;

public class AgentCashActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_agent_cash_list)
    private ListView lvAgentCash;
    private AgentCashHistoryList agentCashHistoryList = new AgentCashHistoryList();
    private AgentCashHistoryAdapter agentCashHistoryAdapter;

    @BindView(id = R.id.act_agent_cash_edt_from, click = true)
    private TextView edtFrom;
    @BindView(id = R.id.act_agent_cash_edt_to, click = true)
    private TextView edtTo;
    @BindView(id = R.id.act_agent_cash_btn_search, click = true)
    private Button btnSearch;
    private int dateType = 0;

    long from;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AgentCashActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null && data.getAction() != null) {
                if (data.getAction().equals(DataTimePicker.SELECT_DATE_ACTION)) {
                    int type = data.getIntExtra("type", 0);
                    if (type == 0) {
                        // 日期
                        int years = data.getIntExtra("years", 0);
                        int months = data.getIntExtra("months", 0);
                        int days = data.getIntExtra("days", 0);
                        Calendar calendar = java.util.Calendar.getInstance();
                        calendar.set(Calendar.YEAR, years);
                        calendar.set(Calendar.MONTH, months);
                        calendar.set(Calendar.DAY_OF_MONTH, days);
                        if (dateType == 0)
                            edtFrom.setText(StringUtils.getDateYMD(calendar.getTimeInMillis()));
                        else if (dateType == 1)
                            edtTo.setText(StringUtils.getDateYMD(calendar.getTimeInMillis()));
                    } else {
                        // 时间
                    }
                }
            }
        }
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

//        /******/
//        AgentCashHistory agentCashHistory = new AgentCashHistory();
//        agentCashHistory.setMoney(new BigDecimal(1000));
//        agentCashHistory.setType("重庆时时彩");
//        agentCashHistory.setTime("2018.01.04");
//        agentCashHistory.setStage("2018006");
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        agentCashHistoryList.getList().add(agentCashHistory);
//        /******/
//
//        agentCashHistoryAdapter = new AgentCashHistoryAdapter(lvAgentCash, agentCashHistoryList.getList());
//        lvAgentCash.setAdapter(agentCashHistoryAdapter);
        getData();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                finish();
                break;
            case R.id.act_agent_cash_btn_search:
                getData();
                break;
            case R.id.act_agent_cash_edt_from:
                dateType = 0;
                String from = edtFrom.getText().toString().trim();
                DataTimePicker.startActivity(AgentCashActivity.this, from, 0);
                break;
            case R.id.act_agent_cash_edt_to:
                dateType = 1;
                String to = edtFrom.getText().toString().trim();
                DataTimePicker.startActivity(AgentCashActivity.this, to, 0);
                break;
        }
    }

    private void getData() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        if (agentCashHistoryList != null)
                            agentCashHistoryList.getList().clear();
                        agentCashHistoryList.parse(str);
                        agentCashHistoryAdapter = new AgentCashHistoryAdapter(lvAgentCash, agentCashHistoryList.getList());
                        lvAgentCash.setAdapter(agentCashHistoryAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("数据解析错误");
                    }
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(AgentCashActivity.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        String from = edtFrom.getText().toString();
        String to = edtTo.getText().toString();
        long lFrom = 0;
        long lTo = 0;
        if (!StringUtils.isEmpty(from))
            lFrom = StringUtils.date_fromat_change_4(from);
        if (!StringUtils.isEmpty(to))
            lTo = StringUtils.date_fromat_change_4(to);

        ApiUser.getTJRecords(lFrom, lTo, callBack);
    }
}
