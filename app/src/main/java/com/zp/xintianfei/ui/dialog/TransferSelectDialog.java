package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.MemberMoneykListAdapter;
import com.zp.xintianfei.bean.MemberMoneyList;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p>
 * 描述:
 * <p>
 * 作者:Administrator
 * <p>
 * 时间:2018/2/9 15:24
 * <p>
 * 版本:
 */
public class TransferSelectDialog extends BaseActivity {

    @BindView(id = R.id.dialog_select_transfer_list)
    private ListView lvTransferSelect;
    private MemberMoneykListAdapter memberMoneykListAdapter;

    private static Handler transferHandler;
    private Handler handler;
    private MemberMoneyList memberMoneyList;

    public static void startActivity(Activity activity, Handler handler, MemberMoneyList memberMoneyList) {
        Intent intent = new Intent();
        intent.setClass(activity, TransferSelectDialog.class);
        intent.putExtra("memberMoneyList",memberMoneyList);
        activity.startActivityForResult(intent, 0);
        transferHandler = handler;
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.dialog_select_transfer);
    }

    @Override
    public void initData() {
        super.initData();

        memberMoneyList = (MemberMoneyList)getIntent().getSerializableExtra("memberMoneyList");

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    Message message1 = new Message();
                    message1.what = 1;
                    message1.obj = message.obj;
                    transferHandler.sendMessage(message1);
                    finish();
                }
                return false;
            }
        });

        memberMoneykListAdapter = new MemberMoneykListAdapter(lvTransferSelect,memberMoneyList.getList(),handler);
        lvTransferSelect.setAdapter(memberMoneykListAdapter);
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }
}
