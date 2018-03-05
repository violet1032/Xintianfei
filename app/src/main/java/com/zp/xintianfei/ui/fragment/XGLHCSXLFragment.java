package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseFragment;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class XGLHCSXLFragment extends BaseFragment {

    private LinearLayout[] lays = new LinearLayout[4];

    private int last = -1;
    private int curr = -1;

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
