package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class GameBJSCJCFragment extends BaseFragment {

    @BindView(id = R.id.act_game_bjsc_quick_btn_chjc, click = true)
    private Button btnCHJC;
    @BindView(id = R.id.act_game_bjsc_quick_btn_dxds, click = true)
    private Button btnDXDS;
    @BindView(id = R.id.act_game_bjsc_quick_btn_tmjc, click = true)
    private Button btnTMJC;
    @BindView(id = R.id.act_game_bjsc_quick_btn_gyzx, click = true)
    private Button btnGYZX;
    @BindView(id = R.id.act_game_bjsc_quick_btn_lhjc, click = true)
    private Button btnLHJC;
    @BindView(id = R.id.act_game_bjsc_quick_btn_gyhdxds, click = true)
    private Button btnGYHDXDS;
    @BindView(id = R.id.act_game_bjsc_quick_btn_zhdxds, click = true)
    private Button btnZHDXDS;
    @BindView(id = R.id.act_game_bjsc_quick_btn_more, click = true)
    private Button btnMore;
    @BindView(id = R.id.act_game_bjsc_quick_btn_more_close, click = true)
    private Button btnMoreClose;
    @BindView(id = R.id.act_game_bjsc_quick_btn_content_close, click = true)
    private Button btnContentLayClose;

    private LinearLayout[] laysContentNumber = new LinearLayout[7];
    private Button[] btnsContentNumber = new Button[8];

    @BindView(id = R.id.act_game_bjsc_quick_lay_more_dialog)
    private LinearLayout layMore;

    @BindView(id = R.id.act_game_bjsc_quick_lay_content)
    private RelativeLayout layContentNumber;

    @BindView(id = R.id.act_game_bjsc_quick_tv_content_title)
    private TextView tvContentNumberTitle;

    @BindView(id = R.id.fg_bjsc_quick_jc_lay_quick)
    private LinearLayout layQuick;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_game_bjsc_quick_jc, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        laysContentNumber[0] = parentView.findViewById(R.id.act_game_bjsc_quick_lay_content_number_chjc);
        laysContentNumber[1] = parentView.findViewById(R.id.act_game_bjsc_quick_lay_content_number_dxds);
        laysContentNumber[2] = parentView.findViewById(R.id.act_game_bjsc_quick_lay_content_number_tmjc);
        laysContentNumber[3] = parentView.findViewById(R.id.act_game_bjsc_quick_lay_content_number_gyzx);
        laysContentNumber[4] = parentView.findViewById(R.id.act_game_bjsc_quick_lay_content_number_lhjc);
        laysContentNumber[5] = parentView.findViewById(R.id.act_game_bjsc_quick_lay_content_number_gyhdxds);
        laysContentNumber[6] = parentView.findViewById(R.id.act_game_bjsc_quick_lay_content_number_zhdxds);

        btnsContentNumber[0] = btnCHJC;
        btnsContentNumber[1] = btnDXDS;
        btnsContentNumber[2] = btnTMJC;
        btnsContentNumber[3] = btnMore;
        btnsContentNumber[4] = btnGYZX;
        btnsContentNumber[5] = btnLHJC;
        btnsContentNumber[6] = btnGYHDXDS;
        btnsContentNumber[7] = btnZHDXDS;

        showContentnumber(0);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.act_game_bjsc_quick_btn_chjc:
                showContentnumber(0);
                break;
            case R.id.act_game_bjsc_quick_btn_dxds:
                showContentnumber(1);
                break;
            case R.id.act_game_bjsc_quick_btn_tmjc:
                showContentnumber(2);
                break;
            case R.id.act_game_bjsc_quick_btn_gyzx:
                showContentnumber(3);
                layMore.setVisibility(View.GONE);
                break;
            case R.id.act_game_bjsc_quick_btn_lhjc:
                showContentnumber(4);
                layMore.setVisibility(View.GONE);
                break;
            case R.id.act_game_bjsc_quick_btn_gyhdxds:
                showContentnumber(5);
                layMore.setVisibility(View.GONE);
                break;
            case R.id.act_game_bjsc_quick_btn_zhdxds:
                showContentnumber(6);
                layMore.setVisibility(View.GONE);
                break;
            case R.id.act_game_bjsc_quick_btn_more:
                if (layMore.getVisibility() == View.VISIBLE)
                    layMore.setVisibility(View.GONE);
                else if (layMore.getVisibility() == View.GONE)
                    layMore.setVisibility(View.VISIBLE);
                break;
            case R.id.act_game_bjsc_quick_btn_more_close:
                layMore.setVisibility(View.GONE);
                break;
            case R.id.act_game_bjsc_quick_btn_content_close:
                layContentNumber.setVisibility(View.GONE);
                btnsContentNumber[0].setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                btnsContentNumber[1].setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                btnsContentNumber[2].setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                btnsContentNumber[3].setBackgroundResource(R.drawable.shape_rounded_h_black_3);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void showContentnumber(int position) {
        layContentNumber.setVisibility(View.VISIBLE);


        switch (position) {
            case 0:
                tvContentNumberTitle.setText(R.string.game_bjsc_quick_text_1);
                break;
            case 1:
                tvContentNumberTitle.setText(R.string.game_bjsc_quick_text_2);
                break;
            case 2:
                tvContentNumberTitle.setText(R.string.game_bjsc_quick_text_3);
                break;
            case 3:
                tvContentNumberTitle.setText(R.string.game_bjsc_quick_text_5);
                break;
            case 4:
                tvContentNumberTitle.setText(R.string.game_bjsc_quick_text_6);
                break;
            case 5:
                tvContentNumberTitle.setText(R.string.game_bjsc_quick_text_7);
                break;
            case 6:
                tvContentNumberTitle.setText(R.string.game_bjsc_quick_text_8);
                break;
        }

        for (LinearLayout lay : laysContentNumber) {
            lay.setVisibility(View.GONE);
        }
        laysContentNumber[position].setVisibility(View.VISIBLE);

        btnsContentNumber[0].setBackgroundResource(R.drawable.shape_rounded_h_black_3);
        btnsContentNumber[1].setBackgroundResource(R.drawable.shape_rounded_h_black_3);
        btnsContentNumber[2].setBackgroundResource(R.drawable.shape_rounded_h_black_3);
        btnsContentNumber[3].setBackgroundResource(R.drawable.shape_rounded_h_black_3);

        if (position < 3) {
            btnsContentNumber[position].setBackgroundResource(R.drawable.shape_rounded_h_orange_3);
        } else {
            btnsContentNumber[3].setBackgroundResource(R.drawable.shape_rounded_h_orange_3);
        }
    }

    public boolean quickLay() {
        if (layQuick.getVisibility() == View.VISIBLE) {
            layQuick.setVisibility(View.GONE);
            return false;
        } else if (layQuick.getVisibility() == View.GONE) {
            layQuick.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }
}
