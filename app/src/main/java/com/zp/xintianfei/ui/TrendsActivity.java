package com.zp.xintianfei.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiLottery;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.utils.JsonUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.json.JSONException;
import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

/**
 * Created by Administrator on 2018/3/4 0004.
 */
public class TrendsActivity extends BaseActivity{
    @BindView(id = R.id.umeng_banner_title)
    private TextView title;
    @BindView(id = R.id.umeng_banner_img_left, click = true)
    private ImageView imgBack;

    @BindView(id= R.id.act_trends_web)
    private WebView webView;

    private int cate;

    private String url;

    public static void startActivity(Activity context, int cate) {
        Intent intent = new Intent();
        intent.setClass(context, TrendsActivity.class);
        intent.putExtra("cate", cate);
        context.startActivityForResult(intent, 0);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_trends);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        title.setText("走势");

        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setDefaultTextEncodingName("gbk");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new webviewClient());

        webSettings.setDisplayZoomControls(false);
    }

    @Override
    public void initData() {
        super.initData();

        cate = getIntent().getIntExtra("cate",0);

        getTrendsURL();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.umeng_banner_img_left:
                finish();
                break;
        }
    }

    private void getTrendsURL(){
        FHttpCallBack callBack = new FHttpCallBack(){
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if(result.isOk()){
                    try {
                        JsonUtils jsonUtils = new JsonUtils(str);
                        url = jsonUtils.getString("info");
                        webView.loadUrl(url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        UIHelper.ToastMessage("数据解析错误");
                    }
                }else
                    UIHelper.ToastMessage(result.getMsg());
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(TrendsActivity.this);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }
        };
        ApiLottery.getTrendsURL(cate,callBack);
    }

    private class webviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (!url.startsWith("http") & !url.startsWith("https")) {
                return false;
            } else {
                view.loadUrl(url);
                return true;
            }
        }
    }
}
