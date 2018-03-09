package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Odds;
import com.zp.xintianfei.bean.OddsList;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class QuickBJSCFragment extends BaseFragment {

    private int cate;

    private int last = -1;
    private int curr = -1;

    private TextView[] types = new TextView[5];

    private ScrollView[] lays = new ScrollView[5];

    private LinearLayout[] laysDWD = new LinearLayout[25];
    private LinearLayout[] laysLMP = new LinearLayout[19];
    private LinearLayout[] laysGYH = new LinearLayout[26];
    private LinearLayout[] laysGYZX = new LinearLayout[3];
    private LinearLayout[] laysLH = new LinearLayout[8];

    private Map<Integer,List<Integer>> mapWeis = new HashMap<>(); // 选择位的数组
    private Map<Integer,List<String>> mapNumber = new HashMap<>(); // 选择数字的数组

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_quick_bjsc, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        types[0] = parentView.findViewById(R.id.fg_quick_bjsc_tv_game_1);
        types[1] = parentView.findViewById(R.id.fg_quick_bjsc_tv_game_2);
        types[2] = parentView.findViewById(R.id.fg_quick_bjsc_tv_game_3);
        types[3] = parentView.findViewById(R.id.fg_quick_bjsc_tv_game_4);
        types[4] = parentView.findViewById(R.id.fg_quick_bjsc_tv_game_5);

        lays[0] = parentView.findViewById(R.id.fg_quick_bjsc_lay_dwd);
        lays[1] = parentView.findViewById(R.id.fg_quick_bjsc_lay_lmp);
        lays[2] = parentView.findViewById(R.id.fg_quick_bjsc_lay_gyh);
        lays[3] = parentView.findViewById(R.id.fg_quick_bjsc_lay_gyzx);
        lays[4] = parentView.findViewById(R.id.fg_quick_bjsc_lay_lh);

        for (int i = 0; i < types.length; i++) {
            types[i].setOnClickListener(new typeBtnOnClickListener(i));
        }

        setPosition(0);

        for (int i = 0; i < laysDWD.length; i++) {
            laysDWD[i] = parentView.findViewById(R.id.layout_game_bjsc_quick_dwd_lay_1 + i);
            laysDWD[i].setOnClickListener(new numberBtnOnClickListener());
        }
        for (int i = 0; i < laysLMP.length; i++) {
            laysLMP[i] = parentView.findViewById(R.id.layout_game_bjsc_quick_lmp_lay_1 + i);
            laysLMP[i].setOnClickListener(new numberBtnOnClickListener());
        }
        for (int i = 0; i < laysGYH.length; i++) {
            laysGYH[i] = parentView.findViewById(R.id.layout_game_bjsc_quick_gyh_lay_1 + i);
            laysGYH[i].setOnClickListener(new numberBtnOnClickListener());
        }
        for (int i = 0; i < laysGYZX.length; i++) {
            laysGYZX[i] = parentView.findViewById(R.id.layout_game_bjsc_quick_gyzx_lay_1 + i);
            laysGYZX[i].setOnClickListener(new numberBtnOnClickListener());
        }
        for (int i = 0; i < laysLH.length; i++) {
            laysLH[i] = parentView.findViewById(R.id.layout_game_bjsc_quick_lh_lay_1 + i);
            laysLH[i].setOnClickListener(new numberBtnOnClickListener());
        }
    }

    @Override
    protected void initData() {
        super.initData();

        getOdds();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    //    private Map<Integer, Float> odds = new HashMap<>();
    private OddsList oddsList = new OddsList();

    public void getOdds() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        oddsList.parse(str);

                        setOdds();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("赔率数据解析错误");
                    }
//                    try {
//                        JsonUtils j = new JsonUtils(str);
//                        JSONArray jsonArray = j.getJSONArray("info");
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JsonUtils jsonUtils = new JsonUtils(jsonArray.getString(i));
//                            odds.put(jsonUtils.getInt("id"), (float) jsonUtils.getDouble("rate"));
//                        }
//
//                        setOdds();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        UIHelper.ToastMessage("赔率数据解析错误");
//                    }
                }
            }
        };
        ApiLottery.getOdds(cate, callBack);
    }

    public void setPosition(int index) {
        curr = index;
        if (last >= 0) {
            types[last].setTextColor(getResources().getColor(R.color.white));
            types[last].setBackgroundResource(R.drawable.bg_btn_black_shade);
            lays[last].setVisibility(View.GONE);
        }

        types[index].setTextColor(getResources().getColor(R.color.orange_2));
        types[index].setBackgroundResource(R.drawable.bg_btn_red_shade);
        lays[index].setVisibility(View.VISIBLE);
        last = index;
    }

    private class typeBtnOnClickListener implements View.OnClickListener {
        private int index;

        public typeBtnOnClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            setPosition(index);
        }
    }

    private class numberBtnOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            TextView text = (TextView)((LinearLayout) view).getChildAt(0);
            String str = text.getText().toString();

            int tag = (Integer)view.getTag();

            if(tag >= 0){
                // 选择了位
                // 判断是否已经选择
                switch (curr){
                    case 0:
                        break;
                }
            }else if (tag == -1){
                // 选择了号码
            }else if(str.equals("大")){

            }else if (str.equals("小")){

            }else if (str.equals("单")){

            }else if (str.equals("双")){

            }else if (str.equals("清")){

            } else {

            }
        }
    }

    private void setOdds() {
        for (LinearLayout linearLayout :
                laysDWD) {
            if (linearLayout.getChildCount() > 1) {
                Odds odds = oddsList.getByType(1);
                if (odds != null)
                    ((TextView) linearLayout.getChildAt(1)).setText("x" + odds.getRate() + "");
            }
        }

        for (LinearLayout linearLayout :
                laysLMP) {
            if (linearLayout.getChildCount() > 1) {
                String str = ((TextView) linearLayout.getChildAt(0)).getText().toString();
                Odds odds = oddsList.getByTypeAndRule(3, str);
                if (odds != null)
                    ((TextView) linearLayout.getChildAt(1)).setText("x" + odds.getRate() + "");
            }
        }

        for (LinearLayout linearLayout :
                laysGYH) {
            if (linearLayout.getChildCount() > 1) {
                String str = ((TextView) linearLayout.getChildAt(0)).getText().toString();
                Odds odds = oddsList.getByTypeAndRule(2, str);
                if (odds != null)
                    ((TextView) linearLayout.getChildAt(1)).setText("x" + odds.getRate() + "");
            }
        }

        for (LinearLayout linearLayout :
                laysGYZX) {
            if (linearLayout.getChildCount() > 1) {
                Odds odds = oddsList.getByType(4);
                if (odds != null)
                    ((TextView) linearLayout.getChildAt(1)).setText("x" + odds.getRate() + "");
            }
        }

        for (LinearLayout linearLayout :
                laysLH) {
            if (linearLayout.getChildCount() > 1) {
                Odds odds = oddsList.getByType(5);
                if (odds != null)
                    ((TextView) linearLayout.getChildAt(1)).setText("x" + odds.getRate() + "");
            }
        }
    }
}
