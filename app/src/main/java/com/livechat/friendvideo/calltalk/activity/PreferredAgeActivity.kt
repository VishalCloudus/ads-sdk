package com.livechat.friendvideo.calltalk.activity

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.extras.Constants
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_prefered_age.*
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class PreferredAgeActivity : AppCompatActivity() {
    var pos = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefered_age)
        AppManage.getInstance(this).loadInterstitialAd(this)
        AppManage.getInstance(this)!!.showNative(native_layout)
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))

        when (PowerPreference.getDefaultFile().getString(Constants.PREFERRED_AGE)) {
            "YOUNG" -> {
                pos = 1
                changeUi()
            }
            "MEDIUM" -> {
                pos = 2
                changeUi()
            }
            "OLD" -> {
                pos = 3
                changeUi()
            }
        }

        btn_young.setOnClickListener {
            PowerPreference.getDefaultFile().setString(Constants.PREFERRED_AGE, "YOUNG")
            pos = 1
            changeUi()
        }

        btn_medium.setOnClickListener {
            PowerPreference.getDefaultFile().setString(Constants.PREFERRED_AGE, "MEDIUM")
            pos = 2
            changeUi()
        }

        btn_old.setOnClickListener {
            PowerPreference.getDefaultFile().setString(Constants.PREFERRED_AGE, "OLD")
            pos = 3
            changeUi()
        }

        btn_next.setOnClickListener {

            if (pos != 0) {
                AppManage.getInstance(this).showInterstitialAd(
                    this,
                    true,   {
                        startActivity(
                            Intent(
                                this@PreferredAgeActivity,
                                AgeActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        )
                        finish()
                    },AppManage.app_innerClickCntSwAd
                )

            } else {
                Toast.makeText(
                    this,
                    "Select Preferred Age...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun changeUi() {
        when (pos) {
            1 -> {
                check_young.visibility = VISIBLE
                check_medium.visibility = GONE
                check_old.visibility = GONE
            }
            2 -> {
                check_young.visibility = GONE
                check_medium.visibility = VISIBLE
                check_old.visibility = GONE
            }
            3 -> {
                check_young.visibility = GONE
                check_medium.visibility = GONE
                check_old.visibility = VISIBLE
            }
        }
    }

    override fun onBackPressed() {
        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }

    }


}