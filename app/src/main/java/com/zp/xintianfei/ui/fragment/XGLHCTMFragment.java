package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class XGLHCTMFragment extends BaseFragment {

    @BindView(id = R.id.fg_xglhc_tm_tv_tm_1, click = true)
    private TextView btn1;
    @BindView(id = R.id.fg_xglhc_tm_tv_tm_2, click = true)
    private TextView btn2;

    @BindView(id = R.id.fg_xglhc_tm_lay_2)
    private ScrollView lay1;
    @BindView(id = R.id.fg_xglhc_tm_lay_3)
    private ScrollView lay2;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_xglhc_tm, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
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
}
