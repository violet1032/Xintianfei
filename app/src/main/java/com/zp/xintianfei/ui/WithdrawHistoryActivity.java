package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.RechargeHistoryAdapter;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.RechargeHistoryList;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

public class WithdrawHistoryActivity extends BaseActivity {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_recharge_history_list)
    private ListView lv_recharge;
    private RechargeHistoryAdapter adapter;
    private RechargeHistoryList rechargeHistoryList = new RechargeHistoryList();

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, WithdrawHistoryActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.fragment_withdraw_history);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        title.setText(R.string.withdraw_history_text_1);

//        /*******/
//        RechargeHistory rechargeHistory = new RechargeHistory();
//        rechargeHistory.setId(10010);
//        rechargeHistory.setTime("2018-01-08 09:00");
//        rechargeHistory.setMoney(new BigDecimal(10000.0));
//        rechargeHistory.setStatus("状态");
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        rechargeHistoryList.getList().add(rechargeHistory);
//        /*******/


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

    private void getData(){
        FHttpCallBack callBack = new FHttpCallBack(){
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        rechargeHistoryList.parse(str);

                        adapter = new RechargeHistoryAdapter(lv_recharge, rechargeHistoryList.getList());
                        lv_recharge.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("数据解析出错");
                    }

                } else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(WithdrawHistoryActivity.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiLottery.txRecord(callBack);
    }
}
