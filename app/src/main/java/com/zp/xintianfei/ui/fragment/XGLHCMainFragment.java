package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.GameXGLHCActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class XGLHCMainFragment extends BaseFragment {

    @BindView(id = R.id.act_game_bjsc_lay_tm, click = true)
    private LinearLayout layTM;
    @BindView(id = R.id.act_game_bjsc_lay_pm, click = true)
    private LinearLayout layPM;
    @BindView(id = R.id.act_game_bjsc_lay_ws, click = true)
    private LinearLayout layWS;
    @BindView(id = R.id.act_game_bjsc_lay_tmsx, click = true)
    private LinearLayout layTMSX;
    @BindView(id = R.id.act_game_bjsc_lay_tmbs, click = true)
    private LinearLayout layTMBS;
    @BindView(id = R.id.act_game_bjsc_lay_ztm, click = true)
    private LinearLayout layZTM;
    @BindView(id = R.id.act_game_bjsc_lay_sxl, click = true)
    private LinearLayout laySXL;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_xglhc_main, null);
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
            case R.id.act_game_bjsc_lay_tm:
                ((GameXGLHCActivity) getActivity()).setType(1);
                break;
            case R.id.act_game_bjsc_lay_pm:
                ((GameXGLHCActivity) getActivity()).setType(2);
                break;
            case R.id.act_game_bjsc_lay_ws:
                ((GameXGLHCActivity) getActivity()).setType(3);
                break;
            case R.id.act_game_bjsc_lay_tmsx:
                ((GameXGLHCActivity) getActivity()).setType(4);
                break;
            case R.id.act_game_bjsc_lay_tmbs:
                ((GameXGLHCActivity) getActivity()).setType(5);
                break;
            case R.id.act_game_bjsc_lay_ztm:
                ((GameXGLHCActivity) getActivity()).setType(6);
                break;
            case R.id.act_game_bjsc_lay_sxl:
                ((GameXGLHCActivity) getActivity()).setType(7);
                break;
        }
    }
}
