package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.adapter.GambleSimpleAdapter;
import com.zp.xintianfei.adapter.GambleSimpleTodayAdapter;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.E_LOTTERY_TYPE;
import com.zp.xintianfei.bean.GambleSimpleHistory;
import com.zp.xintianfei.bean.GambleSimpleHistoryList;
import com.zp.xintianfei.bean.GambleSimpleTodayHistory;
import com.zp.xintianfei.bean.GambleSimpleTodayHistoryList;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;
import com.zp.xintianfei.widget.ListviewScroll;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class GameJLFragment extends BaseFragment {

//    @BindView(id = R.id.fg_game_jl_tv_name)
//    private TextView tvName;
//    @BindView(id = R.id.fg_game_jl_tv_agent)
//    private TextView tvAgent;
//    @BindView(id = R.id.fg_game_jl_tv_id)
//    private TextView tvId;

    @BindView(id = R.id.fg_game_jl_btn_1, click = true)
    private Button btn1;
    @BindView(id = R.id.fg_game_jl_btn_2, click = true)
    private Button btn2;
    @BindView(id = R.id.fg_game_jl_btn_3, click = true)
    private Button btn3;
    @BindView(id = R.id.fg_game_jl_btn_4, click = true)
    private Button btn4;

    private Button[] btns = new Button[4];

    private int cate;

    @BindView(id = R.id.fg_game_jl_list_gamble)
    private ListviewScroll lvRecord;
    private GambleSimpleTodayHistoryList gambleSimpleTodayHistoryList = new GambleSimpleTodayHistoryList();
    private GambleSimpleTodayAdapter gambleSimpleTodayAdapter;

    private GambleSimpleAdapter gambleSimpleAdapter;
    private GambleSimpleHistoryList gambleSimpleHistoryList = new GambleSimpleHistoryList();

    @BindView(id = R.id.fg_game_jl_tv_cate)
    private TextView tvCate;
    @BindView(id = R.id.fg_game_jl_tv_js)
    private TextView tvJS;
    @BindView(id = R.id.fg_game_jl_tv_ls)
    private TextView tvLS;
    @BindView(id = R.id.fg_game_jl_tv_yk)
    private TextView tvYK;

    private Handler handler;
    private int curr;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_game_jl, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

//        tvName.setText(AppContext.user.getNickname());
//        tvAgent.setText(AppContext.user.getTid() + "");
//        tvId.setText(AppContext.user.getUid() + "");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd", Locale.getDefault());
        String time3 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String time2 = sdf.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String time1 = sdf.format(calendar.getTime());

        btn3.setText(time1);
        btn2.setText(time2);
        btn1.setText(time3);

        btns[0] = btn1;
        btns[1] = btn2;
        btns[2] = btn3;
        btns[3] = btn4;

        tvCate.setText(E_LOTTERY_TYPE.getIndex(cate).name);
    }

    @Override
    protected void initData() {
        super.initData();

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 102) {
                    // 重新获取记录
                    switch (curr) {
                        case 0:
                            getRecordToday();
                            break;
                        case 1:
                            getRecord(0);
                            break;
                        case 2:
                            getRecord(1);
                            break;
                        case 3:
                            getRecord(2);
                            break;
                    }
                }
                return false;
            }
        });

        getRecordToday();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onChange() {
        super.onChange();
        setPosition(0);
        getRecordToday();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.fg_game_jl_btn_1:
                // 今天
                setPosition(0);
                getRecordToday();
                break;
            case R.id.fg_game_jl_btn_2:
                // 昨天
                setPosition(1);
                getRecord(0);
                break;
            case R.id.fg_game_jl_btn_3:
                // 前天
                setPosition(2);
                getRecord(1);
                break;
            case R.id.fg_game_jl_btn_4:
                // 历史
                setPosition(3);
                getRecord(2);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    private void setPosition(int curr) {
        this.curr = curr;
        btn1.setBackgroundResource(R.drawable.btn_round_black);
        btn2.setBackgroundResource(R.drawable.btn_round_black);
        btn3.setBackgroundResource(R.drawable.btn_round_black);
        btn4.setBackgroundResource(R.drawable.btn_round_black);

        btns[curr].setBackgroundResource(R.drawable.btn_round_red);
    }

    /**
     * 获取今天记录
     */
    private void getRecordToday() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                try {
                    JsonUtils jsonUtils = new JsonUtils(str);
                    String betAmountToday = jsonUtils.getString("betAmountToday");
                    String zjAmountToday = jsonUtils.getString("zjAmountToday");
                    String ykAmountToday = jsonUtils.getString("ykAmountToday");
                    BigDecimal bigDecimal1 = new BigDecimal(0);
                    BigDecimal bigDecimal2 = new BigDecimal(0);
                    BigDecimal bigDecimal3 = new BigDecimal(0);
                    if (!StringUtils.isEmpty(betAmountToday))
                        bigDecimal1 = new BigDecimal(betAmountToday).divide(new BigDecimal(100));
                    if (!StringUtils.isEmpty(zjAmountToday))
                        bigDecimal2 = new BigDecimal(zjAmountToday).divide(new BigDecimal(100));
                    if (!StringUtils.isEmpty(ykAmountToday))
                        bigDecimal3 = new BigDecimal(ykAmountToday).divide(new BigDecimal(100));
                    tvLS.setText(bigDecimal1.toString());
                    tvJS.setText(bigDecimal2.toString());
                    tvYK.setText(bigDecimal3.toString());

                    gambleSimpleTodayHistoryList.getList().clear();

                    gambleSimpleTodayHistoryList.parse(str);

                    List<GambleSimpleTodayHistory> list = new ArrayList<>();
                    list.add(new GambleSimpleTodayHistory());
                    list.addAll(gambleSimpleTodayHistoryList.getList());

                    gambleSimpleTodayAdapter = new GambleSimpleTodayAdapter(lvRecord, list, handler);
                    lvRecord.setAdapter(gambleSimpleTodayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    UIHelper.ToastMessage("数据解析错误");
                }
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(getActivity());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiLottery.getRecordToday(cate, callBack);
    }

    private void getRecord(final int type) {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                try {
                    JsonUtils jsonUtils = new JsonUtils(str);
                    String betAmountToday = "";
                    String zjAmountToday = "";
                    String ykAmountToday = "";
                    switch (type) {
                        case 0:
                            betAmountToday = jsonUtils.getString("betAmountYesterday");
                            zjAmountToday = jsonUtils.getString("zjAmountYesterday");
                            ykAmountToday = jsonUtils.getString("ykAmountYesterday");
                            break;
                        case 1:
                            betAmountToday = jsonUtils.getString("betAmountBeforeYesterday");
                            zjAmountToday = jsonUtils.getString("zjAmountBeforeYesterday");
                            ykAmountToday = jsonUtils.getString("ykAmountBeforeYesterday");
                            break;
                        case 2:
                            betAmountToday = jsonUtils.getString("betAmountAll");
                            zjAmountToday = jsonUtils.getString("zjAmountAll");
                            ykAmountToday = jsonUtils.getString("ykAmountAll");
                            break;
                    }
                    BigDecimal bigDecimal1 = new BigDecimal(0);
                    BigDecimal bigDecimal2 = new BigDecimal(0);
                    BigDecimal bigDecimal3 = new BigDecimal(0);
                    if (!StringUtils.isEmpty(betAmountToday))
                        bigDecimal1 = new BigDecimal(betAmountToday).divide(new BigDecimal(100));
                    if (!StringUtils.isEmpty(zjAmountToday))
                        bigDecimal2 = new BigDecimal(zjAmountToday).divide(new BigDecimal(100));
                    if (!StringUtils.isEmpty(ykAmountToday))
                        bigDecimal3 = new BigDecimal(ykAmountToday).divide(new BigDecimal(100));
                    tvLS.setText(bigDecimal1.toString());
                    tvJS.setText(bigDecimal2.toString());
                    tvYK.setText(bigDecimal3.toString());

                    gambleSimpleHistoryList.getList().clear();

                    gambleSimpleHistoryList.parse(str);

                    List<GambleSimpleHistory> list = new ArrayList<>();
                    list.add(new GambleSimpleHistory());
                    list.addAll(gambleSimpleHistoryList.getList());

                    gambleSimpleAdapter = new GambleSimpleAdapter(lvRecord, list, handler);
                    lvRecord.setAdapter(gambleSimpleAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    UIHelper.ToastMessage("数据解析错误");
                }
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(getActivity());
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        switch (type) {
            case 0:
                ApiLottery.getRecordYesterday(cate, callBack);
                break;
            case 1:
                ApiLottery.getRecordBeforeYesterday(cate, callBack);
                break;
            case 2:
                ApiLottery.getRecordAll(cate, callBack);
                break;
        }

    }

    public void setCate(int cate) {
        this.cate = cate;
    }
}
