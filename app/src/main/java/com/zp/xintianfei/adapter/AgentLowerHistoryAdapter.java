package com.zp.xintianfei.adapter;

import android.widget.AbsListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.AgentLowerHistory;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by Administrator on 2018/2/3 0003.
 */
public class AgentLowerHistoryAdapter extends KJAdapter<AgentLowerHistory> {

    public AgentLowerHistoryAdapter(AbsListView view, Collection<AgentLowerHistory> mDatas) {
        super(view, mDatas, R.layout.listitem_agent_cash);
    }

    @Override
    public void convert(AdapterHolder helper, AgentLowerHistory item, boolean isScrolling, int position) {
        super.convert(helper, item, isScrolling, position);

        helper.setText(R.id.lt_agent_cash_tv_1, item.getId() + "");
        helper.setText(R.id.lt_agent_cash_tv_2, item.getNickname());
        helper.setText(R.id.lt_agent_cash_tv_4, item.getMoney()+"");
        helper.setText(R.id.lt_agent_cash_tv_3, item.getTime());
    }
}
