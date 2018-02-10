package com.zp.xintianfei.adapter;

import android.widget.AbsListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.GambleZhangbianHistory;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * Created by Administrator on 2018/2/3 0003.
 */
public class GambleZhangbianHistoryAdapter extends KJAdapter<GambleZhangbianHistory> {

    public GambleZhangbianHistoryAdapter(AbsListView view, Collection<GambleZhangbianHistory> mDatas) {
        super(view, mDatas, R.layout.listitem_gamble_history_zhangbian);
    }

    @Override
    public void convert(AdapterHolder helper, GambleZhangbianHistory item, boolean isScrolling, int position) {
        super.convert(helper, item, isScrolling, position);

        helper.setText(R.id.lt_gamble_history_zhangbian_tv_1, item.getTime());
        helper.setText(R.id.lt_gamble_history_zhangbian_tv_2, item.getStrType());
        helper.setText(R.id.lt_gamble_history_zhangbian_tv_3, item.getMoney() + "元");
        helper.setText(R.id.lt_gamble_history_zhangbian_tv_4, item.getBalance() + "元");
    }
}
