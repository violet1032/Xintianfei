package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseFragment;

import org.kymjs.kjframe.ui.BindView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class GameJLFragment extends BaseFragment {

    @BindView(id = R.id.fg_game_jl_tv_name)
    private TextView tvName;
    @BindView(id = R.id.fg_game_jl_tv_agent)
    private TextView tvAgent;
    @BindView(id = R.id.fg_game_jl_tv_id)
    private TextView tvId;

    @BindView(id = R.id.fg_game_jl_btn_1, click = true)
    private Button btn1;
    @BindView(id = R.id.fg_game_jl_btn_2, click = true)
    private Button btn2;
    @BindView(id = R.id.fg_game_jl_btn_3, click = true)
    private Button btn3;
    @BindView(id = R.id.fg_game_jl_btn_4, click = true)
    private Button btn4;

    private Button[] btns = new Button[4];

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_game_jl, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        tvName.setText(AppContext.user.getNickname());
        tvAgent.setText(AppContext.user.getTid()+"");
        tvId.setText(AppContext.user.getUid()+"");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.getDefault());
        String time3 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String time2 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String time1 = sdf.format(calendar.getTime());

        btn1.setText(time1);
        btn2.setText(time2);
        btn3.setText(time3);

        btns[0] = btn1;
        btns[1] = btn2;
        btns[2] = btn3;
        btns[3] = btn4;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_game_jl_btn_1:
                // 前天
                setPosition(0);
                break;
            case R.id.fg_game_jl_btn_2:
                // 昨天
                setPosition(1);
                break;
            case R.id.fg_game_jl_btn_3:
                // 今天
                setPosition(2);
                break;
            case R.id.fg_game_jl_btn_4:
                // 历史
                setPosition(3);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void setPosition(int curr){
        btn1.setBackgroundResource(R.drawable.btn_round_black);
        btn2.setBackgroundResource(R.drawable.btn_round_black);
        btn3.setBackgroundResource(R.drawable.btn_round_black);
        btn4.setBackgroundResource(R.drawable.btn_round_black);

        btns[curr].setBackgroundResource(R.drawable.btn_round_red);
    }
}
