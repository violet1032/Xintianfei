package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.bean.Rules;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class RuleFragment extends BaseFragment {

    private int lastSelected = 1; // 底部之前选中
    private int currSelected = 1; // 底部当前选中

    private Button[] layBtn = new Button[16];

//    private ScrollView[] views = new ScrollView[9];

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_rule_tv_content)
    private WebView tvRuleContent;

    private List<Rules> listRules = new ArrayList<>();

    private Map<Integer,Integer> map = new HashMap<>();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_rule, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText("规则");
        imgBack.setVisibility(View.INVISIBLE);

//        layBtn[0] = parentView.findViewById(R.id.fg_rule_btn_6);
//        layBtn[1] = parentView.findViewById(R.id.fg_rule_btn_1);
//        layBtn[2] = parentView.findViewById(R.id.fg_rule_btn_5);
//        layBtn[3] = parentView.findViewById(R.id.fg_rule_btn_10);
//        layBtn[4] = parentView.findViewById(R.id.fg_rule_btn_2);
//        layBtn[5] = parentView.findViewById(R.id.fg_rule_btn_13);
//        layBtn[6] = parentView.findViewById(R.id.fg_rule_btn_9);
//        layBtn[7] = parentView.findViewById(R.id.fg_rule_btn_11);
//        layBtn[8] = parentView.findViewById(R.id.fg_rule_btn_7);
//        layBtn[9] = parentView.findViewById(R.id.fg_rule_btn_16);
//        layBtn[10] = parentView.findViewById(R.id.fg_rule_btn_3);
//        layBtn[11] = parentView.findViewById(R.id.fg_rule_btn_8);
//        layBtn[12] = parentView.findViewById(R.id.fg_rule_btn_9);
//        layBtn[13] = parentView.findViewById(R.id.fg_rule_btn_7);
//        layBtn[14] = parentView.findViewById(R.id.fg_rule_btn_7);
//        layBtn[15] = parentView.findViewById(R.id.fg_rule_btn_7);

        layBtn[0] = parentView.findViewById(R.id.fg_rule_btn_1);
        layBtn[1] = parentView.findViewById(R.id.fg_rule_btn_2);
        layBtn[2] = parentView.findViewById(R.id.fg_rule_btn_3);
        layBtn[3] = parentView.findViewById(R.id.fg_rule_btn_4);
        layBtn[4] = parentView.findViewById(R.id.fg_rule_btn_5);
        layBtn[5] = parentView.findViewById(R.id.fg_rule_btn_6);
        layBtn[6] = parentView.findViewById(R.id.fg_rule_btn_7);
        layBtn[7] = parentView.findViewById(R.id.fg_rule_btn_8);
        layBtn[8] = parentView.findViewById(R.id.fg_rule_btn_9);
        layBtn[9] = parentView.findViewById(R.id.fg_rule_btn_10);
        layBtn[10] = parentView.findViewById(R.id.fg_rule_btn_11);
        layBtn[11] = parentView.findViewById(R.id.fg_rule_btn_12);
        layBtn[12] = parentView.findViewById(R.id.fg_rule_btn_13);
        layBtn[13] = parentView.findViewById(R.id.fg_rule_btn_14);
        layBtn[14] = parentView.findViewById(R.id.fg_rule_btn_15);
        layBtn[15] = parentView.findViewById(R.id.fg_rule_btn_16);

        map.put(0,2);
        map.put(1,5);
        map.put(2,13);
        map.put(3,12);
        map.put(4,3);
        map.put(5,1);
        map.put(6,9);
        map.put(7,14);
        map.put(8,15);
        map.put(9,4);
        map.put(10,8);
        map.put(11,18);
        map.put(12,6);
        map.put(13,16);
        map.put(14,17);
        map.put(15,10);

        for (int i = 0; i < layBtn.length; i++) {
            layBtn[i].setOnClickListener(new onclick_bottom(i));
        }

        setPosition(0);

        WebSettings webSettings = tvRuleContent.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setDefaultTextEncodingName("gbk");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        tvRuleContent.setBackgroundColor(0);
        tvRuleContent.getBackground().setAlpha(0);

        getRules();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private class onclick_bottom implements View.OnClickListener {
        private int type;

        public onclick_bottom(int type) {
            this.type = type;
        }

        @Override
        public void onClick(View view) {
            setPosition(type);
        }
    }

    private void setPosition(int curr) {
        int cate = map.get(curr);
        if (currSelected != curr) {
            currSelected = curr;

            boolean has = false;
            for (Rules rules :
                    listRules) {
                if (rules.getCate() == cate) {
                    tvRuleContent.loadDataWithBaseURL(null, UIHelper.WEB_STYLE + rules.getContent(), "text/html", "utf-8", null);
                    has = true;
                }
            }
            if (!has) {
                tvRuleContent.loadDataWithBaseURL(null, "<div class=\"mui-content rule_xs1\" style=\"margin: 10px 10px;color: #ffffff\"><h3>暂无玩法介绍</h3></div>", "text/html", "utf-8", null);
            }

            layBtn[curr].setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
            if (lastSelected >= 0) {
                layBtn[lastSelected].setBackgroundResource(R.drawable.shape_rounded_h_gray_4);
            }

            lastSelected = currSelected;
        }
    }

    private void getRules() {
        for (int i = 0; i < 16; i++) {
            FHttpCallBack callBack = new FHttpCallBack() {
                @Override
                public void onSuccess(Map<String, String> headers, byte[] t) {
                    super.onSuccess(headers, t);
                    String str = new String(t);
                    Result result = new Result().parse(str);
                    if (result.isOk()) {
                        Rules rules = new Rules();
                        rules.parse(str);

                        listRules.add(rules);

                        if (rules.getCate() == 2) {
                            tvRuleContent.loadDataWithBaseURL(null, UIHelper.WEB_STYLE + rules.getContent(), "text/html", "utf-8", null);
                        }
                    } else {
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
                    UIHelper.showLoadingDialog(getActivity());
                }
            };
            ApiLottery.getGameRule(map.get(i), callBack);
        }
    }
}
