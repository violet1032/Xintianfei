package com.zp.xintianfei.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zp.xintianfei.R;
import com.zp.xintianfei.ui.common.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

import java.util.Map;

public class LoginActivity extends BaseActivity {

    @BindView(id = R.id.act_login_btn_login, click = true)
    private Button btnLogin;
    @BindView(id = R.id.act_login_btn_login_wexin, click = true)
    private Button btnLoginWexin;
    @BindView(id = R.id.act_login_btn_login_qq, click = true)
    private Button btnLoginQQ;

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
            case R.id.act_login_btn_login:
                MainActivity.startActivity(this);
                finish();
                break;
            case R.id.act_login_btn_login_wexin:
                Log.e("loginactivity","调用授权");
                Toast.makeText(LoginActivity.this, "调用授权", Toast.LENGTH_LONG).show();
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
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                };
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;
            case R.id.act_login_btn_login_qq:
                Log.e("loginactivity","调用授权");
                Toast.makeText(LoginActivity.this, "调用QQ授权", Toast.LENGTH_LONG).show();
                UMAuthListener umAuthListenerQQ = new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Toast.makeText(LoginActivity.this, "开始QQ授权", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

                        for (String key : map.keySet()) {
                            Log.e("loginactivity", "QQ授权返回key:" + key + " content:" + map.get(key));
                        }
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                };
                UMShareAPI.get(this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListenerQQ);
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();

        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }

    @Override
    public void initWidget() {
        super.initWidget();
    }
}
