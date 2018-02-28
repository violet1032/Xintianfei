package com.zp.xintianfei.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zp.xintianfei.AppConfig;
import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.MainActivity;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class OnlineFragment extends BaseFragment {

    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id = R.id.fg_online_lay_weixin, click = true)
    private LinearLayout layWeixin;
    @BindView(id = R.id.fg_online_lay_qq, click = true)
    private LinearLayout layQQ;
    @BindView(id = R.id.fg_online_lay_customer, click = true)
    private LinearLayout layCustomer;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_online, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        title.setText(R.string.online_text_1);
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
            case R.id.fg_online_lay_weixin:
                ((MainActivity) getActivity()).setPosition(11);
                break;
            case R.id.fg_online_lay_qq:
                ((MainActivity) getActivity()).setPosition(12);
                break;
            case R.id.fg_online_lay_customer:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(AppConfig.getInstance().getmPre().getString("online_service", ""));
                intent.setData(content_url);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
