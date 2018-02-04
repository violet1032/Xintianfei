package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.adapter.RechargeHistoryAdapter;
import com.zp.xintianfei.bean.RechargeHistory;
import com.zp.xintianfei.bean.RechargeHistoryList;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class WithdrawHistoryFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.act_recharge_history_list)
    private ListView lv_recharge;
    private RechargeHistoryAdapter adapter;
    private RechargeHistoryList rechargeHistoryList = new RechargeHistoryList();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_withdraw_history, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText(R.string.withdraw_history_text_1);

        /*******/
        RechargeHistory rechargeHistory = new RechargeHistory();
        rechargeHistory.setId(10010);
        rechargeHistory.setTime("2018-01-08 09:00");
        rechargeHistory.setMoney(new BigDecimal(10000.0));
        rechargeHistory.setStatus("状态");
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        rechargeHistoryList.getList().add(rechargeHistory);
        /*******/

        adapter = new RechargeHistoryAdapter(lv_recharge, rechargeHistoryList.getList());
        lv_recharge.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                ((MainActivity) getActivity()).setPosition(4);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
