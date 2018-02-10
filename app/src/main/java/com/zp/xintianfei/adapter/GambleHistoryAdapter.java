package com.zp.xintianfei.adapter;

import android.widget.AbsListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.GambleHistory;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by Administrator on 2018/2/3 0003.
 */
public class GambleHistoryAdapter extends KJAdapter<GambleHistory> {

    public GambleHistoryAdapter(AbsListView view, Collection<GambleHistory> mDatas) {
        super(view, mDatas, R.layout.listitem_gamble_history);
    }

    @Override
    public void convert(AdapterHolder helper, GambleHistory item, boolean isScrolling, int position) {
        super.convert(helper, item, isScrolling, position);

        helper.setText(R.id.lt_gamble_history_tv_1, item.getStrCate());
        helper.setText(R.id.lt_gamble_history_tv_2, item.getStage());
        helper.setText(R.id.lt_gamble_history_tv_3, item.getStrType());
        helper.setText(R.id.lt_gamble_history_tv_4, item.getNumber());
        helper.setText(R.id.lt_gamble_history_tv_5, item.getMoney() + "元");
        helper.setText(R.id.lt_gamble_history_tv_6, item.getStrStatus());
    }
}
