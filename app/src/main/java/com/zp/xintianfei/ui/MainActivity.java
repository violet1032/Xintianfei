package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.xcinfo.album.ui.ChooseDialog;
import com.zp.xintianfei.AppConfig;
import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.ui.fragment.AgentFragment;
import com.zp.xintianfei.ui.fragment.BanddingAlipayFragment;
import com.zp.xintianfei.ui.fragment.BanddingFragment;
import com.zp.xintianfei.ui.fragment.BanddingWeixinFragment;
import com.zp.xintianfei.ui.fragment.GambleHistoryFragment;
import com.zp.xintianfei.ui.fragment.MainFragment;
import com.zp.xintianfei.ui.fragment.OnlineFragment;
import com.zp.xintianfei.ui.fragment.OnlineQQFragment;
import com.zp.xintianfei.ui.fragment.OnlineWeixinFragment;
import com.zp.xintianfei.ui.fragment.PersonFragment;
import com.zp.xintianfei.ui.fragment.RechargeFragment;
import com.zp.xintianfei.ui.fragment.RechargeHistoryFragment;
import com.zp.xintianfei.ui.fragment.RuleFragment;
import com.zp.xintianfei.ui.fragment.TransferFragment;
import com.zp.xintianfei.ui.fragment.WithdrawFragment;
import com.zp.xintianfei.ui.fragment.WithdrawHistoryFragment;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private int lastSelected = 1; // 底部之前选中
    private int currSelected = 1; // 底部当前选中

    private RechargeFragment rechargeFragment = new RechargeFragment();
    private WithdrawFragment withdrawFragment = new WithdrawFragment();
    private MainFragment mainFragment = new MainFragment();
    private RuleFragment ruleFragment = new RuleFragment();
    private PersonFragment personFragment = new PersonFragment();
    private AgentFragment agentFragment = new AgentFragment();
    private RechargeHistoryFragment rechargeHistoryFragment = new RechargeHistoryFragment();
    private WithdrawHistoryFragment withdrawHistoryFragment = new WithdrawHistoryFragment();
    private GambleHistoryFragment gambleHistoryFragment = new GambleHistoryFragment();
    private BanddingFragment banddingFragment = new BanddingFragment();
    private OnlineFragment onlineFragment = new OnlineFragment();
    private OnlineWeixinFragment onlineWeixinFragment = new OnlineWeixinFragment();
    private OnlineQQFragment onlineQQFragment = new OnlineQQFragment();
    private BanddingWeixinFragment banddingWeixinFragment = new BanddingWeixinFragment();
    private BanddingAlipayFragment banddingAlipayFragment = new BanddingAlipayFragment();
    private TransferFragment transferFragment = new TransferFragment();

    private LinearLayout[] layBottoms = new LinearLayout[5];
    private LinearLayout[] layBottomsSelect = new LinearLayout[5];

    @BindView(id = R.id.act_main_lay_recharge, click = true)
    private LinearLayout layRecharge;
    @BindView(id = R.id.act_main_lay_withdraw, click = true)
    private LinearLayout layWithdraw;
    @BindView(id = R.id.act_main_lay_main, click = true)
    private LinearLayout layMain;
    @BindView(id = R.id.act_main_lay_rule, click = true)
    private LinearLayout layRule;
    @BindView(id = R.id.act_main_lay_person, click = true)
    private LinearLayout layPerson;

    public static int imgUploadType = 0;//0:微信，1：支付宝

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            setPosition(0);
        } else if (resultCode == 2) {
            setPosition(1);
        } else if (resultCode == 3) {
            setPosition(10);
        } else if (resultCode == 4) {
            setPosition(2);
        }
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (!StringUtils.isEmpty(data.getAction())) {
                    if (data.getAction().equals(ChooseDialog.ACTION_SELECTED_PICS)) {
                        // 图片上传
                        ArrayList<String> uris = (ArrayList<String>) data.getSerializableExtra(ChooseDialog.PUT_INTENT);
                        for (String str :
                                uris) {
                            // 遍历图片上传
                            if (imgUploadType == 0)
                                banddingWeixinFragment.imgUpLoad(str);
                            else
                                banddingAlipayFragment.imgUpLoad(str);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        super.initData();

        getLotteryWei();
        getPlazaGameList();
    }

    @Override
    public void initWidget() {
        super.initWidget();

        setPosition(2);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.act_main_lay_recharge:
                // 充值
                setPosition(0);
                break;
            case R.id.act_main_lay_withdraw:
                setPosition(1);
                // 提现
                break;
            case R.id.act_main_lay_main:
                setPosition(2);
                // 大厅
                break;
            case R.id.act_main_lay_rule:
                setPosition(3);
                // 规则
                break;
            case R.id.act_main_lay_person:
                setPosition(4);
                // 个人中心
                break;
        }
    }

    public void setPosition(int curr) {
        if (currSelected != curr) {
            currSelected = curr;
            // 将所有先变为常态
            // 设置为选中状态
            switch (curr) {
                case 0:
                    // 充值
                    changeFragment(R.id.act_main_content, rechargeFragment);
                    break;
                case 1:
                    // 提现
                    changeFragment(R.id.act_main_content, withdrawFragment);
                    break;
                case 2:
                    // 大厅
                    changeFragment(R.id.act_main_content, mainFragment);
                    break;
                case 3:
                    // 规则
                    changeFragment(R.id.act_main_content, ruleFragment);
                    break;
                case 4:
                    // 个人中心
                    changeFragment(R.id.act_main_content, personFragment);
                    break;
                case 5:
                    // 代理中心
                    changeFragment(R.id.act_main_content, agentFragment);
                    break;
                case 6:
                    // 充值记录
                    changeFragment(R.id.act_main_content, rechargeHistoryFragment);
                    break;
                case 7:
                    // 提现记录
                    changeFragment(R.id.act_main_content, withdrawHistoryFragment);
                    break;
                case 8:
                    // 投注记录
                    changeFragment(R.id.act_main_content, gambleHistoryFragment);
                    break;
                case 9:
                    // 绑定银行卡
                    changeFragment(R.id.act_main_content, banddingFragment);
                    break;
                case 10:
                    // 客服中心
                    changeFragment(R.id.act_main_content, onlineFragment);
                    break;
                case 11:
                    // 微信客服
                    changeFragment(R.id.act_main_content, onlineWeixinFragment);
                    break;
                case 12:
                    // QQ客服
                    changeFragment(R.id.act_main_content, onlineQQFragment);
                    break;
                case 13:
                    // 绑定微信
                    changeFragment(R.id.act_main_content, banddingWeixinFragment);
                    break;
                case 14:
                    // 绑定支付宝
                    changeFragment(R.id.act_main_content, banddingAlipayFragment);
                    break;
                case 15:
                    // 转账
                    changeFragment(R.id.act_main_content, transferFragment);
                    break;
            }

            lastSelected = currSelected;
        }
    }

    private void getLotteryWei() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils j = new JsonUtils(str);
                        JsonUtils jsonUtils = j.getJSONUtils("info");
                        JSONArray jsonArray = jsonUtils.getJSONArray("ssc");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            AppConfig.getInstance().putWei(1, i + 1, jsonArray.getString(i));
                        }

                        JSONArray jsonArray2 = jsonUtils.getJSONArray("car");
                        for (int i = 0; i < jsonArray2.length(); i++) {
                            AppConfig.getInstance().putWei(2, i + 1, jsonArray2.getString(i));
                        }

                        JSONArray jsonArray3 = jsonUtils.getJSONArray("airship");
                        for (int i = 0; i < jsonArray3.length(); i++) {
                            AppConfig.getInstance().putWei(3, i + 1, jsonArray3.getString(i));
                        }

                        JSONArray jsonArray4 = jsonUtils.getJSONArray("eight");
                        for (int i = 0; i < jsonArray4.length(); i++) {
                            AppConfig.getInstance().putWei(4, i + 1, jsonArray4.getString(i));
                        }

                        JSONArray jsonArray5 = jsonUtils.getJSONArray("horse");
                        for (int i = 0; i < jsonArray5.length(); i++) {
                            AppConfig.getInstance().putWei(5, i + 1, jsonArray5.getString(i));
                        }

                        JSONArray jsonArray6 = jsonUtils.getJSONArray("fast");
                        for (int i = 0; i < jsonArray6.length(); i++) {
                            AppConfig.getInstance().putWei(6, i + 1, jsonArray6.getString(i));
                        }

                        JSONArray jsonArray7 = jsonUtils.getJSONArray("tencent");
                        for (int i = 0; i < jsonArray7.length(); i++) {
                            AppConfig.getInstance().putWei(7, i + 1, jsonArray7.getString(i));
                        }

                        JSONArray jsonArray8 = jsonUtils.getJSONArray("singapore");
                        for (int i = 0; i < jsonArray8.length(); i++) {
                            AppConfig.getInstance().putWei(8, i + 1, jsonArray8.getString(i));
                        }

                        JSONArray jsonArray9 = jsonUtils.getJSONArray("branch");
                        for (int i = 0; i < jsonArray9.length(); i++) {
                            AppConfig.getInstance().putWei(9, i + 1, jsonArray9.getString(i));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ApiLottery.getLotteryWei(callBack);
    }

    private void getPlazaGameList() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    try {
                        JsonUtils j = new JsonUtils(str);
                        JSONObject jsonObject = j.getJSONObject("info");
                        Iterator<String> keys = jsonObject.keys();
                        while (keys.hasNext()) {
                            String key = keys.next();
                            JsonUtils jsonUtils2 = new JsonUtils(jsonObject.getString(key));
                            int cate = jsonUtils2.getInt("cate");
                            if (cate == 0) {
                                String name = jsonUtils2.getString("name");
                                if (!StringUtils.isEmpty(name) && name.equals("android"))
                                    AppContext.downLoadUrl = jsonUtils2.getString("url");
                            } else {
                                String url = jsonUtils2.getString("url");
                                AppConfig.getInstance().putGameList(cate, url);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ApiLottery.getPlazaGameList(callBack);
    }
}
