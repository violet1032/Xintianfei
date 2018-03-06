package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
public class XGLHCSXLFragment extends BaseFragment {

    private LinearLayout[] lays = new LinearLayout[4];

    private int last = -1;
    private int curr = -1;

    private TableRow[] tableRows = new TableRow[6];

    Map<Integer, Float> odds = new HashMap<>();

    private int numberMax = 2;


    private CheckBox[] cboxs = new CheckBox[12];
    @BindView(id = R.id.fg_xglhc_sxl_edt_all)
    private EditText edtAll;
    @BindView(id = R.id.fg_xglhc_sxl_btn_clear, click = true)
    private Button btnClear;
    @BindView(id = R.id.fg_xglhc_sxl_btn_gamble, click = true)
    private Button btnGamble;
    private Handler handler;
    private BigDecimal money = new BigDecimal(0);
    private JSONArray jsonArray = new JSONArray();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_xglhc_sxl, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        lays[0] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_1);
        lays[1] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_2);
        lays[2] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_3);
        lays[3] = parentView.findViewById(R.id.fg_xglhc_ztm_lay_4);

        for (int i = 0; i < lays.length; i++) {
            lays[i].setOnClickListener(new layOnClickListener(i));
        }

        setPosition(0);

        for (int i = 0; i < tableRows.length; i++) {
            tableRows[i] = parentView.findViewById(R.id.fg_xglhc_sxl_row_1 + i);
        }
        for (int i = 0; i < tableRows.length; i++) {
            ((TextView) tableRows[i].getChildAt(2)).setText(odds.get(262) + "");
            ((TextView) tableRows[i].getChildAt(6)).setText(odds.get(262) + "");

            cboxs[i] = (CheckBox) ((LinearLayout) tableRows[i].getChildAt(3)).getChildAt(0);
            cboxs[i + 6] = (CheckBox) ((LinearLayout) tableRows[i].getChildAt(7)).getChildAt(0);
        }
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
                    ApiLottery.gameBetSix(10, ((GameXGLHCActivity) getActivity()).stage, 5, money, jsonArray.toString(), 0, httpCallBack);
                }
                return false;
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_xglhc_sxl_btn_clear:
                // 清空
                clear();
                break;
            case R.id.fg_xglhc_sxl_btn_gamble:
                // 投注
                gamble();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void setPosition(int curr) {
        if (last >= 0) {
            ((LinearLayout) lays[last].getParent()).setBackgroundResource(R.drawable.shape_round_stroke_h_transparent_gray);
            ((ImageView) (lays[last].getChildAt(0))).setImageResource(R.drawable.point_gray);
            ((TextView) (lays[last].getChildAt(1))).setTextColor(getResources().getColor(R.color.gray));
        }

        ((LinearLayout) lays[curr].getParent()).setBackgroundResource(R.drawable.shape_round_stroke_h_transparent_orange_2);
        ((ImageView) (lays[curr].getChildAt(0))).setImageResource(R.drawable.point_yellow);
        ((TextView) (lays[curr].getChildAt(1))).setTextColor(getResources().getColor(R.color.orange_2));


        numberMax = curr + 2;

        last = curr;
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
        for (int i = 0; i < cboxs.length; i++) {
            cboxs[i].setChecked(false);
        }
        edtAll.setText("");
    }

    private String[] strs = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};

    public void gamble() {
        money = new BigDecimal(0);
        jsonArray = new JSONArray();

        if (StringUtils.isEmpty(edtAll.getText().toString().trim())) {
            UIHelper.ToastMessage("请输入金额");
            return;
        }

        int checkNum = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < cboxs.length; i++) {
            if (cboxs[i].isChecked()) {
                checkNum++;
                stringBuffer.append(strs[i]);
            }
        }
        money = money.add(new BigDecimal(edtAll.getText().toString()));
        jsonArray.put(stringBuffer + ":" + edtAll.getText().toString());

        if (checkNum != numberMax) {
            UIHelper.ToastMessage("请选择" + numberMax + "个号码");
            return;
        }
        LogUtil.logError(XGLHCTMFragment.class, "下注:" + jsonArray.toString());

        GambleCompleteDialog.startActivity(getActivity(), jsonArray.toString(), money.floatValue(), ((GameXGLHCActivity) getActivity()).stage, handler);
    }

}
