package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.BankListAdapter;
import com.zp.xintianfei.bean.Bank;
import com.zp.xintianfei.bean.BankList;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * <p/>
 * 描述:
 * <p/>
 * 作者:Administrator
 * <p/>
 * 时间:2018/2/9 15:24
 * <p/>
 * 版本:
 */
public class SelectWithdrawTypeDialog extends BaseActivity {

    @BindView(id = R.id.dialog_select_withdraw_type_list)
    private ListView lvBank;
    private BankList bankList = new BankList();
    private BankListAdapter bankListAdapter;

    private static Handler banddingHandler;
    private Handler handler;

    public static void startActivity(Activity activity, Handler handler) {
        Intent intent = new Intent();
        intent.setClass(activity, SelectWithdrawTypeDialog.class);
        activity.startActivityForResult(intent, 0);

        banddingHandler = handler;
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.dialog_select_withdraw_type);
    }

    @Override
    public void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    Message message1 = new Message();
                    message1.what = 1;
                    message1.obj = message.obj;
                    banddingHandler.sendMessage(message1);
                    finish();
                }
                return false;
            }
        });

        /********/
        Bank bank = new Bank();
        bank.setId(34);
        bank.setName("银行卡");
        Bank bank1 = new Bank();
        bank1.setId(32);
        bank1.setName("支付宝");
        Bank bank2 = new Bank();
        bank2.setId(33);
        bank2.setName("微信");
        bankList.getList().add(bank);
        bankList.getList().add(bank1);
        bankList.getList().add(bank2);
        /********/
        bankListAdapter = new BankListAdapter(lvBank, bankList.getList(), handler);
        lvBank.setAdapter(bankListAdapter);

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
