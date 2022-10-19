package com.livechat.friendvideo.calltalk;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.livechat.friendvideo.calltalk.activity.ReportActivity;
import com.livechat.friendvideo.calltalk.api.model.room;
import com.livechat.friendvideo.calltalk.api.retrofit.ApiUtils;
import com.livechat.friendvideo.calltalk.extras.Constants;
import com.livechat.friendvideo.calltalk.extras.Utils;
import com.pesonal.adsdk.AppManage;
import com.preference.PowerPreference;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Web_Call_Activity extends AppCompatActivity implements Handler.Callback {
    WebView call_web;
    private final Handler handler = new Handler(this);
    private static final int USER_LEFT = 1;
    ImageView reportbtn;
    RelativeLayout rlConnecting, rlWebRct;
    Dialog waitingdialog;
    Handler handlertimer;
    Runnable runnable;
    public int userId;
    public String channel;
    public String strName = "";
    public String strProfile = "";
    static boolean appleft;
    static boolean backFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_call);

        AppManage.getInstance(this).loadOpenAd(this);

        AppManage.getInstance(this).showNative(findViewById(R.id.native_layout));
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner));


        rlConnecting = findViewById(R.id.rlConnecting);
        rlWebRct = findViewById(R.id.rlWebRct);
        Glide.with(this).asGif().load(R.drawable.loading_gif).into((ImageView) findViewById(R.id.gif_img));

        GetWebGenerateRoom();


    }


    private void StartVideoCall() {
        appleft = false;
        backFlag = false;


        call_web = findViewById(R.id.call_web);
        reportbtn = findViewById(R.id.reportbtn);
        reportbtn.setVisibility(View.GONE);
        WebSettings webSettings = call_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        call_web.setWebViewClient(new MyBrowser());
        call_web.getSettings().setLoadsImagesAutomatically(true);
        call_web.getSettings().setAllowFileAccess(true);
        call_web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        call_web.getSettings().setMediaPlaybackRequiresUserGesture(false);


        if (Build.VERSION.SDK_INT >= 21) {
            call_web.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            CookieManager.getInstance().setAcceptThirdPartyCookies(call_web, true);
        }

        if (Build.VERSION.SDK_INT < 16) {
            call_web.setBackgroundColor(0x00000000);
        } else {
            call_web.setBackgroundColor(Color.argb(1, 0, 0, 0));
        }
        call_web.getSettings().setDomStorageEnabled(true);

        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReport();
            }
        });

        call_web.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                String[] PERMISSIONS = {PermissionRequest.RESOURCE_AUDIO_CAPTURE, PermissionRequest.RESOURCE_VIDEO_CAPTURE};
                request.grant(PERMISSIONS);
            }


            @Override
            public Bitmap getDefaultVideoPoster() {
                return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
            }


            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                handlertimer = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                       /* if (!appleft) {
                            callcutmehod();
                        }*/
                    }
                };
                int time = Integer.valueOf(AppManage.CallConnectSecond);

                handlertimer.postDelayed(runnable, time * 1000);
                Log.e("WebLog", " -- From line " + consoleMessage.message());
                if (consoleMessage.message().equals("onUserLeft") || consoleMessage.message().equals("onEndAndDestory")) {
                    handler.sendEmptyMessage(USER_LEFT);
                } else if (consoleMessage.message().contains(AppManage.CallConnectKey) || consoleMessage.message().contains(AppManage.CallConnectKey1)) {
//                    Toast.makeText(Web_Call_Activity.this, AppManage.CallConnectKey, Toast.LENGTH_SHORT).show();
                    handler.sendEmptyMessage(3);
                } else if (consoleMessage.message().equals("onNewOfferRecived")) {
                    handler.sendEmptyMessage(2);
                }
                return true;
            }
        });
//        Toast.makeText(this, channel, Toast.LENGTH_SHORT).show();
        call_web.loadUrl(AppManage.WebViewUrl + channel);
        Log.e("====", "onCreate1: " + AppManage.WebViewUrl + channel);
        Log.e("====", "onCreate2: " + PowerPreference.getDefaultFile().getString(Constants.AppRtcWebOnline, ""));
        Log.e("====", "onCreate3: " + AppManage.CallConnectKey);
        Log.e("====", "onCreate4: " + AppManage.WebConnectingSecond);
    }

    private void GetWebGenerateRoom() {
        userId = new Random().nextInt(999999999);

        if (AppManage.app_rtc_url != null && !AppManage.app_rtc_url.isEmpty()) {
            new ApiUtils().getWebService(AppManage.app_rtc_url).getVideo(
                    PowerPreference.getDefaultFile()
                            .getString(Constants.AppRtcWebOnline),
                    userId, "", "a").enqueue(new Callback<room>() {
                @Override
                public void onResponse(@NonNull Call<room> call, @NonNull Response<room> response) {
                    Log.e("====", "onResponse: " + response);
                    if (response.body() != null) {
                        Log.e("====", "onResponse: 1 " + response.body().getFlag());
                        channel = response.body().getChannel();
                        strName = response.body().getName();
                        strProfile = response.body().getProfile();
                        StartVideoCall();
                        if (!appleft) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!backFlag) {
                                        rlConnecting.setVisibility(View.GONE);
                                        rlWebRct.setVisibility(View.VISIBLE);
                                        waitingdialog = new Dialog(Web_Call_Activity.this);
                                        waitingdialog.setCancelable(true);
                                        waitingdialog.setContentView(R.layout.waiting_dialog_lay);
                                        Window window = waitingdialog.getWindow();
                                        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        if (!isFinishing()) {
                                            waitingdialog.show();
                                        }
                                    }
                                }
                            }, Long.parseLong(AppManage.WebConnectingSecond) * 1000);
                        }
                    } else {
                        Log.e("====", "onResponse: body is null");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<room> call, @NonNull Throwable t) {
                    Log.e("====", "onFailure: " + t.getMessage());
                }
            });
        } else {
            Toast.makeText(this, "Something is wrong...!!", Toast.LENGTH_SHORT).show();
        }

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            handler.sendEmptyMessage(USER_LEFT);
            return true;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        Log.e("====", "handleMessage: " + msg.what);
        if (msg.what == USER_LEFT) {
            AppManage.getInstance(Web_Call_Activity.this).showOpenAndInterAd(this, new AppManage.MyCallback() {
                @Override
                public void callbackCall() {
                    try {
//                        super.onBackPressed();
                        callcutmehod();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return true;
        } else if (msg.what == 2) {
            // Toast.makeText(Web_Call_Activity.this, "New User Arrived", Toast.LENGTH_SHORT).show();
        } else if (msg.what == 3) {
            if (rlConnecting.getVisibility() == View.VISIBLE) {
                rlConnecting.setVisibility(View.GONE);
                rlWebRct.setVisibility(View.VISIBLE);
            }
            if (waitingdialog != null) {
                if (waitingdialog.isShowing()) {
                    waitingdialog.dismiss();
                }
            }

            reportbtn.setVisibility(View.VISIBLE);
            reportbtn.setImageResource(R.drawable.report_user);
            appleft = true;
        }
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        callcutmehod();
    }

    public void onBackPressed() {
        if (rlConnecting.getVisibility() == View.VISIBLE || !appleft) {
            callcutmehod();
            Application.clickBack = "1";
        }
    }


    public void callcutmehod() {
        appleft = true;
        backFlag = true;
        if (call_web != null) {
            call_web.destroy();
        }
        if (waitingdialog != null) {
            if (waitingdialog.isShowing()) {
                waitingdialog.dismiss();
            }
        }
        deleteRoom();
        finish();
    }

    private void deleteRoom() {
        try {
            new ApiUtils().getAPIService(AppManage.app_rtc_url)
                    .getVideo(PowerPreference.getDefaultFile().getString(Constants.AppRtcWebOffline, ""),
                            userId, channel, "")
                    .enqueue(new Callback<room>() {
                        @Override
                        public void onResponse(@NonNull Call<room> call, @NonNull Response<room> response) {
                            if (response.isSuccessful()) {
                                Log.e("TAG", "deleteRoom: api 0 " + new Gson().toJson(response.body()));
                                if (response.body() != null) {
//                                    Log.e(TAG, "callVideoCallApi: " + response.body().getMessage());

                                } else {
                                    Log.e("TAG", "callVideoCallApi: " + "body is null");
                                }
                            } else {
                                Log.e("TAG", "onResponse: " + "Some Error Occurred.!");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<room> call, @NonNull Throwable t) {
                            Log.e("TAG", "deleteRoom: " + t.getMessage());
                        }
                    });
        } catch (Exception e) {
            Log.e("TAG", "deleteRoom catch: " + e.getMessage());
        }
    }


    public void onReport() {
        AppManage.getInstance(Web_Call_Activity.this).showInterstitialAd(Web_Call_Activity.this, false, new AppManage.MyCallback() {
            @Override
            public void callbackCall() {
                startActivity(new Intent(Web_Call_Activity.this, ReportActivity.class).putExtra("name",strName).putExtra("image",strProfile));
                callcutmehod();
            }
        }, AppManage.app_innerClickCntSwAd);
    }


}