/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zp.xintianfei;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.zp.xintianfei.api.ApiUser;
import com.zp.xintianfei.api.FHttpCallBack;
import com.zp.xintianfei.api.FHttpClient;
import com.zp.xintianfei.bean.Result;
import com.zp.xintianfei.bean.User;
import com.zp.xintianfei.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapConfig;
import org.kymjs.kjframe.http.HttpConfig;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author kymjs (https://www.kymjs.com/)
 * @since 2015-3
 */
public class AppContext extends Application {
    public static int screenW;
    public static int screenH;

    public static Context applicationContext;
    public static AppContext appContext;

    // 获取网络图片对象
    public static KJBitmap bitmap;

    public static User user;

    // http相关
    public static FHttpClient http;
    // 设备屏幕宽高
    public static int screenHeight, screenWidth;
    public static int versionCode;
    public static String versionName;

    public static String downLoadUrl;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpConfig.CACHEPATH = AppConfig.httpCachePath;
        CrashHandler.create(this);

        applicationContext = getApplicationContext();
        appContext = this;

        // 获取屏幕尺寸
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        // 获取版本信息
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // http实例化
        HttpConfig config = new HttpConfig();
        HttpConfig.DEBUG = true;
        HttpConfig.TIMEOUT = AppConfig.getInstance().getHttpTimeout();
        config.cacheTime = AppConfig.getInstance().getHttpCacheTime();
        http = new FHttpClient(config);
        BitmapConfig bitmapConfig = new BitmapConfig();
        bitmapConfig.cacheTime = AppConfig.getInstance().getBitmapCacheTime();
        bitmap = new KJBitmap(bitmapConfig);

        user = new User();

        // 配置友盟
        PlatformConfig.setWeixin("wx60ad3c3c49c77ed8", "c1254aec048bc287404a82e560b15fac");
        PlatformConfig.setQQZone("101463498", "b1c5b154d39958000334a33aaf88289e"); // 正式版
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba"); // 测试版
        Config.DEBUG = true;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateUserInfo();
            }
        };
        timer.schedule(timerTask, 3000, 3000);


        getSystemConfig();
    }

    public void updateUserInfo() {
        if (user.getUid() > 0) {
            FHttpCallBack callBack = new FHttpCallBack() {
                @Override
                public void onSuccess(Map<String, String> headers, byte[] t) {
                    super.onSuccess(headers, t);
                    String str = new String(t);
                    Result result = new Result().parse(str);
                    if (result.isOk()) {
                        AppContext.user.parse(str,false);
                    }
                }
            };
            ApiUser.getMemberInfo(callBack);
        }
    }

    private void getSystemConfig() {
        FHttpCallBack callBack = new FHttpCallBack() {
            @Override
            public void onSuccess(Map<String, String> headers, byte[] t) {
                super.onSuccess(headers, t);
                String str = new String(t);
                Result result = new Result().parse(str);
                if (result.isOk()) {
                    // 解析参数
                    try {
                        JsonUtils j = new JsonUtils(str);
                        JSONArray jsonArray = j.getJSONArray("info");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JsonUtils jsonUtils = new JsonUtils(jsonArray.getString(i));
                            AppConfig.getInstance().mPreSet(jsonUtils.getString("name"), jsonUtils.getString("value"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        ApiUser.getSystemConfig(callBack);
    }

    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }

        return true;
    }
}
