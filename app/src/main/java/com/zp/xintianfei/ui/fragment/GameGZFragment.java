package com.zp.xintianfei.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.bean.Rules;
import com.zp.xintianfei.ui.common.BaseFragment;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/30 0030.
 */
public class GameGZFragment extends BaseFragment {

    @BindView(id=R.id.fg_game_gz_webview)
    private WebView webView;

    private List<Rules> listRules = new ArrayList<>();

    private int cate;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_game_gz, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setDefaultTextEncodingName("gbk");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(0);
    }

    @Override
    protected void initData() {
        super.initData();

        getRules();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public void setCate(int cate){
        this.cate = cate;
    }

    private void getRules() {
        for (int i = 1; i <= 9; i++) {
            FHttpCallBack callBack = new FHttpCallBack() {
                @Override
                public void onSuccess(Map<String, String> headers, byte[] t) {
                    super.onSuccess(headers, t);
                    String str = new String(t);
                    Result result = new Result().parse(str);
                    if (result.isOk()) {
                        Rules rules = new Rules();
                        rules.parse(str);

                        listRules.add(rules);

                        if (rules.getCate() == cate) {
                            webView.loadDataWithBaseURL(null, UIHelper.WEB_STYLE2 + rules.getContent(), "text/html", "utf-8", null);
                        }
                    } else {
                        UIHelper.ToastMessage(result.getMsg());
                    }
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    UIHelper.stopLoadingDialog();
                }

                @Override
                public void onPreStart() {
                    super.onPreStart();
                    UIHelper.showLoadingDialog(getActivity());
                }
            };
            ApiLottery.getGameRule(i, callBack);
        }
    }
}
