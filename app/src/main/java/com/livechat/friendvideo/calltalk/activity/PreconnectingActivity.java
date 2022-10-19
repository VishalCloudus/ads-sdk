package com.livechat.friendvideo.calltalk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.livechat.friendvideo.calltalk.Application;
import com.livechat.friendvideo.calltalk.R;
import com.livechat.friendvideo.calltalk.Web_Call_Activity;
import com.pesonal.adsdk.AppManage;

public class PreconnectingActivity extends AppCompatActivity {
    boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preconnecting);
        Application.clickBack = "0";
        Glide.with(this).asGif().load(R.drawable.loading_gif).into((ImageView) findViewById(R.id.gif_img));
        AppManage.getInstance(this).loadInterstitialAd(this);
        AppManage.getInstance(this).showNative(findViewById(R.id.native_layout));
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner));
    }

    @Override
    protected void onResume() {
        super.onResume();
        isBack = false;
        if (Application.clickBack.equalsIgnoreCase("0")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!isBack) {
                        Intent intent = new Intent(PreconnectingActivity.this, Web_Call_Activity.class);
                        startActivity(intent);
                        Log.e("===", "Connecting_Repeat" + AppManage.Connecting_Repeat);
                        if (AppManage.Connecting_Repeat.equalsIgnoreCase("1")) {
                            finish();
                        }
                    }

                }
            }, 1000);

        } else {
            finish();
            Application.clickBack = "0";
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBack = true;
    }
}