package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class RuleFragment extends BaseFragment {

    private int lastSelected = 1; // 底部之前选中
    private int currSelected = 1; // 底部当前选中

    private Button[] layBtn = new Button[9];

//    private ScrollView[] views = new ScrollView[9];

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_rule_tv_title)
    private TextView tvRuleTitle;
    @BindView(id = R.id.fg_rule_tv_content)
    private TextView tvRuleContent;

    private List<Rules> listRules = new ArrayList<>();

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

        layBtn[0] = parentView.findViewById(R.id.fg_rule_btn_1);
        layBtn[1] = parentView.findViewById(R.id.fg_rule_btn_2);
        layBtn[2] = parentView.findViewById(R.id.fg_rule_btn_3);
        layBtn[3] = parentView.findViewById(R.id.fg_rule_btn_4);
        layBtn[4] = parentView.findViewById(R.id.fg_rule_btn_5);
        layBtn[5] = parentView.findViewById(R.id.fg_rule_btn_6);
        layBtn[6] = parentView.findViewById(R.id.fg_rule_btn_7);
        layBtn[7] = parentView.findViewById(R.id.fg_rule_btn_8);
        layBtn[8] = parentView.findViewById(R.id.fg_rule_btn_9);

        for (int i = 0; i < layBtn.length; i++) {
            layBtn[i].setOnClickListener(new onclick_bottom(i));
        }

        setPosition(0);
    }

    @Override
    protected void initData() {
        super.initData();

        getRules();
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
        if (currSelected != curr) {
            currSelected = curr;

            boolean has = false;
            for (Rules rules :
                    listRules) {
                if (rules.getCate() == curr + 1) {
                    tvRuleTitle.setText(rules.getTitle() + "玩法介绍:");
                    tvRuleContent.setText(rules.getContent());
                    has = true;
                }
            }
            if (!has) {
                tvRuleTitle.setText("暂无玩法介绍");
                tvRuleContent.setText("");
            }

            layBtn[curr].setTextColor(getResources().getColor(R.color.white));
            layBtn[curr].setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
            if (lastSelected >= 0) {
                layBtn[lastSelected].setTextColor(getResources().getColor(R.color.black_3));
                layBtn[lastSelected].setBackgroundResource(R.drawable.shape_rounded_h_gray_2);
            }

            lastSelected = currSelected;
        }
    }

    private void getRules() {
        for (int i = 1; i <= 9; i++) {
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

                        if (rules.getCate() == 1) {
                            tvRuleTitle.setText(rules.getTitle() + "玩法介绍:");
                            tvRuleContent.setText(rules.getContent());
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
            ApiLottery.getGameRule(i, callBack);
        }
    }
}
