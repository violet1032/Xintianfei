package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.MemberMoneykListAdapter;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.MemberMoneyList;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

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
    private MemberMoneyList memberMoneyList = new MemberMoneyList();

    public static void startActivity(Activity activity, Handler handler) {
        Intent intent = new Intent();
        intent.setClass(activity, TransferSelectDialog.class);
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

        getMemberMoney();
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    public void getMemberMoney() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        memberMoneyList.parse(str);

                        memberMoneykListAdapter = new MemberMoneykListAdapter(lvTransferSelect,memberMoneyList.getList(),handler);
                        lvTransferSelect.setAdapter(memberMoneykListAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("解析出错");
                    }
                } else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(TransferSelectDialog.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiUser.getMemberMoney(callBack);
    }
}
