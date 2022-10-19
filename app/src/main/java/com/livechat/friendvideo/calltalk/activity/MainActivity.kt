package com.livechat.friendvideo.calltalk.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.activity.permission.AllPermissionActivity
import com.livechat.friendvideo.calltalk.extras.Constants
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppManage.getInstance(this).loadInterstitialAd(this)
        AppManage.getInstance(this)!!.showNative(native_layout)
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))

    }

    override fun onBackPressed() {
        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }

    }

    fun onRandomVideoCall(view: View) {

        AppManage.getInstance(this).showInterstitialAd(
            this,
            true, {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.RECORD_AUDIO
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    startActivity(Intent(this@MainActivity, PreconnectingActivity::class.java))
                } else {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            AllPermissionActivity::class.java
                        ).putExtra("isWebCalling", "2")
                    )
                }
            }, AppManage.app_innerClickCntSwAd
        )
    }

    fun onVipRoomCall(view: View) {
        AppManage.getInstance(this).showInterstitialAd(
            this,
            true, {
                startActivity(
                    Intent(
                        this@MainActivity,
                        VipActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                )
            }, AppManage.app_innerClickCntSwAd
        )
    }

}