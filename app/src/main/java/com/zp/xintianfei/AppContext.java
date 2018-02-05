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

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.zp.xintianfei.api.FHttpClient;
import com.zp.xintianfei.bean.User;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.bitmap.BitmapConfig;
import org.kymjs.kjframe.http.HttpConfig;

/**
 * 
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
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        Config.DEBUG = true;
    }
}
