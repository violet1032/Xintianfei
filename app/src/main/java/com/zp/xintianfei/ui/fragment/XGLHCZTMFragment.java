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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
public class XGLHCZTMFragment extends BaseFragment {

    @BindView(id = R.id.fg_xglhc_tm_tv_tm_1, click = true)
    private TextView btn1;
    @BindView(id = R.id.fg_xglhc_tm_tv_tm_2, click = true)
    private TextView btn2;

    @BindView(id = R.id.fg_xglhc_tm_lay_2)
    private ScrollView lay1;
    @BindView(id = R.id.fg_xglhc_tm_lay_3)
    private ScrollView lay2;

    private LinearLayout[] lays = new LinearLayout[6];

    private int last = -1;
    private int curr = -1;

    private TableRow[] tableRows = new TableRow[25];

    Map<Integer, Float> odds = new HashMap<>();

    private int wei = 0;

    private EditText[] edts = new EditText[49];
    @BindView(id = R.id.fg_xglhc_ztm_edt_all)
    private EditText edtAll;
    @BindView(id = R.id.fg_xglhc_ztm_btn_clear, click = true)
    private Button btnClear;
    @BindView(id = R.id.fg_xglhc_ztm_btn_gamble, click = true)
    private Button btnGamble;
    private Handler handler;
    private BigDecimal money = new BigDecimal(0);
    private JSONArray jsonArray = new JSONArray();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_xglhc_ztm, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        lays[0] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_1);
        lays[1] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_2);
        lays[2] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_3);
        lays[3] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_4);
        lays[4] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_5);
        lays[5] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_6);

        for (int i = 0; i < lays.length; i++) {
            lays[i].setOnClickListener(new layOnClickListener(i));
        }

        setPosition(0);

        for (int i = 0; i < tableRows.length; i++) {
            try {
                tableRows[i] = parentView.findViewById(R.id.fg_xglhc_ztm_row_1 + i);
            } catch (Exception e) {
                tableRows[i] = parentView.findViewById(R.id.fg_xglhc_ztm_row_1 + i + 1);
            }
        }
        for (int i = 0; i < tableRows.length; i++) {
            if (i == tableRows.length - 1) {
                ((TextView) tableRows[i].getChildAt(3)).setText("");
                ((TextView) tableRows[i].getChildAt(8)).setText(odds.get(254) + "");
                edts[i * 2] = (EditText) ((LinearLayout) tableRows[i].getChildAt(9)).getChildAt(0);
            } else {
                ((TextView) tableRows[i].getChildAt(3)).setText(odds.get(254) + "");
                ((TextView) tableRows[i].getChildAt(8)).setText(odds.get(254) + "");
                edts[i + 12 * (i / 12)] = (EditText) ((LinearLayout) tableRows[i].getChildAt(4)).getChildAt(0);
                edts[i + 12 * (i / 12) + 12] = (EditText) ((LinearLayout) tableRows[i].getChildAt(9)).getChildAt(0);
            }
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
                    ApiLottery.gameBetSix(10, ((GameXGLHCActivity) getActivity()).stage, 1, money, jsonArray.toString(), wei, httpCallBack);
                }
                return false;
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.fg_xglhc_ztm_btn_clear:
                // 清空
                clear();
                break;
            case R.id.fg_xglhc_ztm_btn_gamble:
                // 投注
                gamble();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fg_xglhc_tm_tv_tm_1:
                btn2.setBackgroundColor(getResources().getColor(R.color.black_3));
                btn1.setBackgroundColor(getResources().getColor(R.color.orange_2));
                lay1.setVisibility(View.VISIBLE);
                lay2.setVisibility(View.GONE);
                break;
            case R.id.fg_xglhc_tm_tv_tm_2:
                btn1.setBackgroundColor(getResources().getColor(R.color.black_3));
                btn2.setBackgroundColor(getResources().getColor(R.color.orange_2));
                lay2.setVisibility(View.VISIBLE);
                lay1.setVisibility(View.GONE);
                break;
        }
    }

    private void setPosition(int curr) {
        if (last >= 0) {
            lays[last].setBackgroundResource(R.drawable.shape_round_stroke_h_transparent_gray);
            ((ImageView) (lays[last].getChildAt(0))).setImageResource(R.drawable.point_gray);
            ((TextView) (lays[last].getChildAt(1))).setTextColor(getResources().getColor(R.color.gray));
        }

        lays[curr].setBackgroundResource(R.drawable.shape_round_stroke_h_transparent_orange_2);
        ((ImageView) (lays[curr].getChildAt(0))).setImageResource(R.drawable.point_yellow);
        ((TextView) (lays[curr].getChildAt(1))).setTextColor(getResources().getColor(R.color.orange_2));

        last = curr;

        wei = curr + 1;
    }

    private class layOnClickListener implements View.OnClickListener {
        private int index;

        public layOnClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            setPosition(index);
        }
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

    public void gamble() {
        money = new BigDecimal(0);
        jsonArray = new JSONArray();
        boolean has = false;
        for (int i = 0; i < edts.length; i++) {
            EditText edt = edts[i];
            if (!StringUtils.isEmpty(edt.getText().toString().trim())) {
                has = true;
                String str = StringUtils.zeroFill(i + 1) + ":" + edt.getText().toString();
                money = money.add(new BigDecimal(edt.getText().toString()));
                jsonArray.put(str);
            }
        }
        if (!has) {
            UIHelper.ToastMessage("请至少选择一注");
            return;
        }
        LogUtil.logError(XGLHCTMFragment.class, "下注:" + jsonArray.toString());

        GambleCompleteDialog.startActivity(getActivity(), jsonArray.toString(), money.floatValue(), ((GameXGLHCActivity) getActivity()).stage, wei, handler);
    }
}
