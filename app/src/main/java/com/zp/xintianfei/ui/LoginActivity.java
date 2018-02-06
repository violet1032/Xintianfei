package com.zp.xintianfei.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zp.xintianfei.AppConfig;
import com.zp.xintianfei.AppContext;
import com.zp.xintianfei.R;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.ui.common.BaseActivity;
import com.zp.xintianfei.utils.LogUtil;
import com.zp.xintianfei.utils.StringUtils;
import com.zp.xintianfei.utils.UIHelper;

import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

public class LoginActivity extends BaseActivity {

    @BindView(id = R.id.act_login_lay_weixin, click = true)
    private LinearLayout layLoginWexin;
    @BindView(id = R.id.act_login_lay_qq, click = true)
    private LinearLayout layLoginQQ;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setRootView() {
        super.setRootView();
        setContentView(R.layout.activity_login);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.act_login_lay_weixin:
                if (StringUtils.isEmpty(AppConfig.getInstance().getLoginWxUnionid())) {
                    // 调用授权
                    UMAuthListener umAuthListener = new UMAuthListener() {
                        @Override
                        public void onStart(SHARE_MEDIA share_media) {
                            Toast.makeText(LoginActivity.this, "开始授权", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

                            for (String key : map.keySet()) {
                                Log.e("loginactivity", "微信授权返回key:" + key + " content:" + map.get(key));

                            }
                            // 登录
                            appLogin("o0sHx0OfhmesT5WAA", "o0sHx0OfhmesT5WAA",
                                    "安卓测试", "http://img1.imgtn.bdimg.com/it/u=1889601151,2922519040&fm=27&gp=0.jpg");
                        }

                        @Override
                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                        }

                        @Override
                        public void onCancel(SHARE_MEDIA share_media, int i) {

                        }
                    };
                    UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                }
                break;
            case R.id.act_login_lay_qq:
//                if (StringUtils.isEmpty(AppConfig.getInstance().getLoginWxUnionid())) {
                // 调用授权
                UMAuthListener umAuthListenerQQ = new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        // 登录
//                        for (String key : map.keySet()) {
//                            Log.e("loginactivity", "QQ授权返回key:" + key + " content:" + map.get(key));
//                        }

                        String openid = map.get("openid");
                        String headimgurl = map.get("profile_image_url");
                        String nickname = map.get("name");
                        appLogin(openid, openid, nickname, headimgurl);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                };
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListenerQQ);
//                }

                break;
        }
    }

    @Override
    public void initData() {
        super.initData();

//        if (Build.VERSION.SDK_INT >= 23) {
//            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
//            ActivityCompat.requestPermissions(this, mPermissionList, 123);
//        }

        // 自动登录
//        if (!StringUtils.isEmpty(AppConfig.getInstance().getLoginWxUnionid())) {
//            // 自动登录
//            appLogin(AppConfig.getInstance().getLoginWxUnionid(), AppConfig.getInstance().getLoginWxOpenid(),
//                    AppConfig.getInstance().getLoginWxNickname(), AppConfig.getInstance().getLoginWxNickname());
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    private void appLogin(String unionid, String openid, String nickname, String headimgurl) {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onFinish() {
                super.onFinish();
                UIHelper.stopLoadingDialog();
            }

            @Override
            public void onPreStart() {
                super.onPreStart();
                UIHelper.showLoadingDialog(LoginActivity.this);
            }

            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result();
                result.parse(str);
                if (result.isOk()) {
                    // 登录成功
                    AppContext.user.parse(str);

                    // 缓存信息
                    AppConfig.getInstance().setLoginWxHeadimgurl(AppContext.user.getAvatar());
                    AppConfig.getInstance().setLoginWxUnionid(AppContext.user.getOpenid());
                    AppConfig.getInstance().setLoginWxOpenid(AppContext.user.getOpenid());
                    AppConfig.getInstance().setLoginWxNickname(AppContext.user.getNickname());

                    LogUtil.logError(LoginActivity.class, "3");
                    MainActivity.startActivity(LoginActivity.this);
                    finish();
                } else {
                    UIHelper.ToastMessage(result.getMsg());
                }
            }

            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);

            }
        };
        ApiUser.appLogin(unionid, openid, nickname, headimgurl, callBack);
    }
}
