package com.zp.xintianfei.adapter;

import android.widget.AbsListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.GambleFuliHistory;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by Administrator on 2018/2/3 0003.
 */
public class GambleFuliHistoryAdapter extends KJAdapter<GambleFuliHistory> {

    public GambleFuliHistoryAdapter(AbsListView view, Collection<GambleFuliHistory> mDatas) {
        super(view, mDatas, R.layout.listitem_gamble_history_fuli);
    }

    @Override
    public void convert(AdapterHolder helper, GambleFuliHistory item, boolean isScrolling, int position) {
        super.convert(helper, item, isScrolling, position);

        helper.setText(R.id.lt_gamble_history_fuli_tv_1, item.getTime());
        helper.setText(R.id.lt_gamble_history_fuli_tv_2, item.getType());
        helper.setText(R.id.lt_gamble_history_fuli_tv_3, item.getMoney() + "元");
    }
}
