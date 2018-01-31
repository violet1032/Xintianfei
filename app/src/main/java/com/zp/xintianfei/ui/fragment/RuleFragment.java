package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class RuleFragment extends BaseFragment {

    private int lastSelected = 1; // 底部之前选中
    private int currSelected = 1; // 底部当前选中

    private Button[] layBtn = new Button[9];

    private ScrollView[] views = new ScrollView[9];

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

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
            views[i] = parentView.findViewById(R.id.fg_rule_lay_1 + i);
        }

        setPosition(0);
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
        if (currSelected != curr) {
            currSelected = curr;

            views[curr].setVisibility(View.VISIBLE);
            layBtn[curr].setTextColor(getResources().getColor(R.color.white));
            layBtn[curr].setBackgroundResource(R.drawable.shape_rounded_h_orange_5);
            if (lastSelected >= 0) {
                views[lastSelected].setVisibility(View.GONE);
                layBtn[lastSelected].setTextColor(getResources().getColor(R.color.black_3));
                layBtn[lastSelected].setBackgroundResource(R.drawable.shape_rounded_h_gray_2);
            }

            lastSelected = currSelected;
        }
    }
}
