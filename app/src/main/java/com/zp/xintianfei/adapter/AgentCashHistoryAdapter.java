package com.zp.xintianfei.adapter;

import android.widget.AbsListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.AgentCashHistory;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by Administrator on 2018/2/3 0003.
 */
public class AgentCashHistoryAdapter extends KJAdapter<AgentCashHistory> {

    public AgentCashHistoryAdapter(AbsListView view, Collection<AgentCashHistory> mDatas) {
        super(view, mDatas, R.layout.listitem_agent_cash);
    }

    @Override
    public void convert(AdapterHolder helper, AgentCashHistory item, boolean isScrolling, int position) {
        super.convert(helper, item, isScrolling, position);

        helper.setText(R.id.lt_agent_cash_tv_1, item.getType());
        helper.setText(R.id.lt_agent_cash_tv_2, item.getStage());
        helper.setText(R.id.lt_agent_cash_tv_3, item.getMoney() + "元");
        helper.setText(R.id.lt_agent_cash_tv_4, item.getTime());
    }
}
