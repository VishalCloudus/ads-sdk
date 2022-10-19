package com.livechat.friendvideo.calltalk;


import static com.facebook.FacebookSdk.setAutoLogAppEventsEnabled;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatDelegate;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.livechat.friendvideo.calltalk.VPN.VpnMainActivity;
import com.livechat.friendvideo.calltalk.activity.StartScreenActivity;
import com.livechat.friendvideo.calltalk.activity.splash.VpnUtilitiys;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;


public class Application extends android.app.Application {
    private static Context context;
    private static Application mInstance;
    public static String clickBack = "0";
//    AppOpenManager appOpenManager;


    public Application() {
    }

    public static Context getContext() {
        return context;
    }

    public void onCreate() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        context = getApplicationContext();
        super.onCreate();
//        Pref.getInstance().init(getApplicationContext());
        mInstance = this;
//        appOpenManager = new AppOpenManager(this);
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(this);
        setAutoLogAppEventsEnabled(true);

        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(getString(R.string.onesignal_app_id));

        OneSignal.setNotificationOpenedHandler(new OneSignal.OSNotificationOpenedHandler() {
            @Override
            public void notificationOpened(OSNotificationOpenedResult osNotificationOpenedResult) {
                Intent intent = new Intent(mInstance, StartScreenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

//
//        OneSignal.setNotificationOpenedHandler(OneSignal.OSNotificationOpenedHandler {
//            osNotificationOpenedResult ->
//                    val intent = Intent(this, ContinueActivity:: class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)
//        })

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        VpnUtilitiys u = VpnUtilitiys.Companion.getInstance(null, null, null);
        u.ondestroy();
    }
}
