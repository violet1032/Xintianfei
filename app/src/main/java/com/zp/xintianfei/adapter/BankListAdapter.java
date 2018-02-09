package com.zp.xintianfei.adapter;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.bean.Bank;

import org.kymjs.kjframe.widget.AdapterHolder;
import org.kymjs.kjframe.widget.KJAdapter;

import java.util.Collection;

/**
 * <p/>
 * 描述:
 * <p/>
 * 作者:Administrator
 * <p/>
 * 时间:2018/2/9 15:38
 * <p/>
 * 版本:
 */
public class BankListAdapter extends KJAdapter<Bank> {

    private Handler handler;

    public BankListAdapter(AbsListView view, Collection<Bank> mDatas,Handler handler) {
        super(view, mDatas, R.layout.listitem_textview);
        this.handler = handler;
    }

    @Override
    public void convert(final AdapterHolder helper, final Bank item, boolean isScrolling, int position) {
        super.convert(helper, item, isScrolling, position);
        helper.setText(R.id.listitem_textview_tv, item.getName());
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message();
                message.what = 1;
                message.obj = item;
                handler.sendMessage(message);
            }
        });
    }
}
