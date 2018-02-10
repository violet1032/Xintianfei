package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.AgentCashActivity;
import com.zp.xintianfei.ui.AgentFeeActivity;
import com.zp.xintianfei.ui.AgentLowerActivity;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class AgentFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_agent_btn_3, click = true)
    private Button btnAgentCash;
    @BindView(id = R.id.fg_agent_btn_2, click = true)
    private Button btnLower;
    @BindView(id = R.id.fg_agent_btn_1, click = true)
    private Button btnFee;

    @BindView(id=R.id.fg_agent_tv_id)
    private TextView tvId;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_agent, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText("代理中心");

        tvId.setText(AppContext.user.getUid()+"");
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                ((MainActivity) getActivity()).setPosition(4);
                break;
            case R.id.fg_agent_btn_1:
                // 下级反水比例
                AgentFeeActivity.startActivity(getActivity());
                break;
            case R.id.fg_agent_btn_2:
                // 下级玩家
                AgentLowerActivity.startActivity(getActivity());
                break;
            case R.id.fg_agent_btn_3:
                // 流水
                AgentCashActivity.startActivity(getActivity());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
