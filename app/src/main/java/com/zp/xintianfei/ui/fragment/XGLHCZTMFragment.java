package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

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
}
