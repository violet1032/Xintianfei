package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.BankListAdapter;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.BankList;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

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
public class SelectBankDialog extends BaseActivity {

    @BindView(id = R.id.dialog_select_bank_list)
    private ListView lvBank;
    private BankList bankList = new BankList();
    private BankListAdapter bankListAdapter;

    private static Handler banddingHandler;
    private Handler handler;

    public static void startActivity(Activity activity, Handler handler) {
        Intent intent = new Intent();
        intent.setClass(activity, SelectBankDialog.class);
        activity.startActivityForResult(intent, 0);

        banddingHandler = handler;
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.dialog_select_bank);
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

//        /********/
//        Bank bank = new Bank();
//        bank.setName("中国工商银行");
//        Bank bank1 = new Bank();
//        bank1.setName("中国农业银行");
//        Bank bank2 = new Bank();
//        bank2.setName("中国人民银行");
//        Bank bank3 = new Bank();
//        bank3.setName("中国建设银行");
//        Bank bank4 = new Bank();
//        bank4.setName("交通银行");
//        Bank bank5 = new Bank();
//        bank5.setName("招商银行");
//        bankList.getList().add(bank);
//        bankList.getList().add(bank1);
//        bankList.getList().add(bank2);
//        bankList.getList().add(bank3);
//        bankList.getList().add(bank4);
//        bankList.getList().add(bank5);
//        /********/
//        bankListAdapter = new BankListAdapter(lvBank, bankList.getList(), handler);
//        lvBank.setAdapter(bankListAdapter);

        getBank();
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    private void getBank(){
        FHttpCallBack callBack = new FHttpCallBack(){
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if(result.isOk()){
                    bankList.parse(str);

                    bankListAdapter = new BankListAdapter(lvBank, bankList.getList(), handler);
                    lvBank.setAdapter(bankListAdapter);
                }else{
                    UIHelper.ToastMessage(result.getMsg());
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(SelectBankDialog.this);
            }
        };
        ApiUser.getBankList(callBack);
    }
}
