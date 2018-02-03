package com.zp.xintianfei.ui.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.bean.RechargeHistory;

import java.util.List;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2018/2/3 12:46
 * <p>
 * 版本:
 */
public class RechargeHistoryAdapter extends BaseAdapter {
    private ListView lv;
    private List<RechargeHistory> list;
    private LayoutInflater inflater;

    public RechargeHistoryAdapter(ListView lv, List<RechargeHistory> list) {
        this.lv = lv;
        this.list = list;
        inflater = LayoutInflater.from(AppContext.appContext);
    }

    @Override
    public int getCount() {
        return list.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.listitem_recharge_history, viewGroup, false);
            holder = new ViewHolder();

            holder.tvID = view.findViewById(R.id.lt_recharge_history_tv_id);
            holder.tvTime = view.findViewById(R.id.lt_recharge_history_tv_time);
            holder.tvMoney = view.findViewById(R.id.lt_recharge_history_tv_money);
            holder.tvStatus = view.findViewById(R.id.lt_recharge_history_tv_status);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (position == 0) {
            holder.tvID.setText("ID");
            holder.tvTime.setText("时间");
            holder.tvMoney.setText("金额");
            holder.tvStatus.setText("状态");
            holder.tvID.setTypeface(Typeface.DEFAULT_BOLD);
            holder.tvTime.setTypeface(Typeface.DEFAULT_BOLD);
            holder.tvMoney.setTypeface(Typeface.DEFAULT_BOLD);
            holder.tvStatus.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            RechargeHistory rechargeHistory = list.get(position - 1);
            holder.tvID.setText(rechargeHistory.getId() + "");
            holder.tvTime.setText(rechargeHistory.getTime());
            holder.tvMoney.setText(rechargeHistory.getMoney().toString()+"");
            holder.tvStatus.setText(rechargeHistory.getStatus());
            holder.tvID.setTypeface(Typeface.DEFAULT);
            holder.tvTime.setTypeface(Typeface.DEFAULT);
            holder.tvMoney.setTypeface(Typeface.DEFAULT);
            holder.tvStatus.setTypeface(Typeface.DEFAULT);
        }
        return view;
    }

    private class ViewHolder {
        private TextView tvID;
        private TextView tvTime;
        private TextView tvMoney;
        private TextView tvStatus;
    }
}
