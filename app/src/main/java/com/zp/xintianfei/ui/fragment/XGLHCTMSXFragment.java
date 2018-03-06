package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.GameXGLHCActivity;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.ui.dialog.GambleCompleteDialog;
import com.zp.xintianfei.utils.LogUtil;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONArray;
import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class XGLHCTMSXFragment extends BaseFragment {

    private TableRow[] tableRows = new TableRow[6];

    Map<Integer, Float> odds = new HashMap<>();

    private EditText[] edts = new EditText[12];
    @BindView(id = R.id.fg_xglhc_tmsx_edt_all)
    private EditText edtAll;
    @BindView(id = R.id.fg_xglhc_tmsx_btn_clear, click = true)
    private Button btnClear;
    @BindView(id = R.id.fg_xglhc_tmsx_btn_gamble, click = true)
    private Button btnGamble;
    private Handler handler;
    private BigDecimal money = new BigDecimal(0);
    private JSONArray jsonArray = new JSONArray();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_xglhc_tmsx, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        for (int i = 0; i < tableRows.length; i++) {
            tableRows[i] = parentView.findViewById(R.id.fg_xglhc_tmsx_row_1 + i);
        }
        for (int i = 0; i < tableRows.length; i++) {
            ((TextView) tableRows[i].getChildAt(2)).setText(odds.get(255) + "");
            ((TextView) tableRows[i].getChildAt(6)).setText(odds.get(255) + "");

            edts[i] = (EditText) ((LinearLayout) tableRows[i].getChildAt(3)).getChildAt(0);
            edts[i + 6] = (EditText) ((LinearLayout) tableRows[i].getChildAt(7)).getChildAt(0);
        }


        edtAll.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                for (int j = 0; j < edts.length; j++) {
                    edts[j].setText(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    FHttpCallBack httpCallBack = new FHttpCallBack() {
                        @Override
                        public void onSuccess(Map<String, String> headers, byte[] t) {
                            super.onSuccess(headers, t);
                            String str = new String(t);
                            Result result = new Result().parse(str);
                            if (result.isOk()) {
                                UIHelper.ToastMessage("下注成功");
                                clear();
                            } else
                                UIHelper.ToastMessage(result.getMsg());
                        }

                        @Override
                        public void onPreStart() {
                            super.onPreStart();
                            UIHelper.showLoadingDialog(getActivity());
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            UIHelper.stopLoadingDialog();
                        }
                    };
                    ApiLottery.gameBetSix(10, ((GameXGLHCActivity) getActivity()).stage, 2, money, jsonArray.toString(), 0, httpCallBack);
                }
                return false;
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_xglhc_tmsx_btn_clear:
                // 清空
                clear();
                break;
            case R.id.fg_xglhc_tmsx_btn_gamble:
                // 投注
                gamble();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public void setOdds(Map<Integer, Float> odds) {
        this.odds = odds;
    }

    private void clear() {
        for (int i = 0; i < edts.length; i++) {
            LogUtil.logError(XGLHCTMFragment.class, "i:" + i);
            edts[i].setText("");
        }
        edtAll.setText("");
    }

    private String[] strs = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
    public void gamble() {
        money = new BigDecimal(0);
        jsonArray = new JSONArray();
        boolean has = false;
        for (int i = 0; i < edts.length; i++) {
            EditText edt = edts[i];
            if (!StringUtils.isEmpty(edt.getText().toString().trim())) {
                has = true;
                String str = strs[i] + ":" + edt.getText().toString();
                money = money.add(new BigDecimal(edt.getText().toString()));
                jsonArray.put(str);
            }
        }
        if (!has) {
            UIHelper.ToastMessage("请至少选择一注");
            return;
        }
        LogUtil.logError(XGLHCTMFragment.class, "下注:" + jsonArray.toString());

        GambleCompleteDialog.startActivity(getActivity(), jsonArray.toString(), money.floatValue(), ((GameXGLHCActivity) getActivity()).stage, handler);
    }
}
