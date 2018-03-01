package com.zp.xintianfei.adapter;

import android.widget.AbsListView;

import com.zp.xintianfei.AppConfig;
import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.GambleHistory;
import com.zp.xintianfei.utils.LogUtil;
import com.zp.xintianfei.utils.StringUtils;

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
        StringBuffer stringBuffer = new StringBuffer();
        LogUtil.logError(GambleHistoryAdapter.class,"cate:"+item.getCate());
        LogUtil.logError(GambleHistoryAdapter.class,"wei:"+item.getWei());
        if (item.getWei() > 0) {
            String wei = AppConfig.getInstance().getWei(item.getCate(), item.getWei());
            LogUtil.logError(GambleHistoryAdapter.class,"wei2:"+wei);
            if (!StringUtils.isEmpty(wei))
                stringBuffer.append(wei + "-");
        }
        stringBuffer.append(item.getNumber());
        helper.setText(R.id.lt_gamble_history_tv_4, stringBuffer.toString());
        helper.setText(R.id.lt_gamble_history_tv_5, item.getMoney()+"");
        helper.setText(R.id.lt_gamble_history_tv_6, item.getStrStatus());
    }
}
