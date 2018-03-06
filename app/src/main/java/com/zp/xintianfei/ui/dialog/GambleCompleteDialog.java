package com.zp.xintianfei.ui.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.json.JSONArray;
import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;

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
public class GambleCompleteDialog extends BaseActivity {

    private static Handler gambleHandler;

    @BindView(id = R.id.dialog_gamble_complete_tv_content)
    private TextView tvContent;
    @BindView(id = R.id.dialog_gamble_complete_tv_stage)
    private TextView tvStage;
    @BindView(id = R.id.dialog_gamble_complete_tv_money)
    private TextView tvMoney;

    @BindView(id = R.id.dialog_gamble_complete_btn_exit, click = true)
    private Button btnCancel;
    @BindView(id = R.id.dialog_gamble_complete_btn_sure, click = true)
    private Button btnSure;

    public static void startActivity(Activity activity, String jsonArray, float money, String stage, Handler handler) {
        Intent intent = new Intent();
        intent.setClass(activity, GambleCompleteDialog.class);
        intent.putExtra("data", jsonArray);
        intent.putExtra("money", money);
        intent.putExtra("stage", stage);
        activity.startActivityForResult(intent, 0);

        gambleHandler = handler;
    }

    public static void startActivity(Activity activity, String jsonArray, float money, String stage, int wei, Handler handler) {
        Intent intent = new Intent();
        intent.setClass(activity, GambleCompleteDialog.class);
        intent.putExtra("data", jsonArray);
        intent.putExtra("money", money);
        intent.putExtra("stage", stage);
        intent.putExtra("wei", wei);
        activity.startActivityForResult(intent, 0);

        gambleHandler = handler;
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.dialog_gamble_complete);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        String j = getIntent().getStringExtra("data");
        String stage = getIntent().getStringExtra("stage");
        tvStage.setText("期号:" + stage + " " + tvStage.getText());
        BigDecimal money = new BigDecimal(getIntent().getFloatExtra("money", 0));
        tvMoney.setText("总金额:" + money + " " + tvMoney.getText());

        int wei = getIntent().getIntExtra("wei", 0);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONArray jsonArray = new JSONArray(j);
            for (int i = 0; i < jsonArray.length(); i++) {
                String str = jsonArray.getString(i).replaceAll(":", " 金额:");
                stringBuffer.append("内容：");
                if (wei > 0) {
                    stringBuffer.append("正" + wei + "特 ");
                }
                stringBuffer.append(str);
                stringBuffer.append("\n");
            }
            tvContent.setText(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.dialog_gamble_complete_btn_exit:
                finish();
                break;
            case R.id.dialog_gamble_complete_btn_sure:
                gambleHandler.sendEmptyMessage(1);
                finish();
                break;
        }
    }
}
