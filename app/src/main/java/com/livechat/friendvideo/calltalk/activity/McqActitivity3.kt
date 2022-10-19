package com.livechat.friendvideo.calltalk.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.livechat.friendvideo.calltalk.extras.Constants
import com.livechat.friendvideo.calltalk.R
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_mcq_actitivity3.*
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class McqActitivity3 : AppCompatActivity() {
    var pos: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcq_actitivity3)
        AppManage.getInstance(this).loadInterstitialAd(this)
        setview();
    }

    private fun setview() {
        AppManage.getInstance(this)!!.showNative(native_layout)
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))
        rl21.setOnClickListener {
            pos++
            iv21.visibility = View.VISIBLE
            iv22.visibility = View.GONE
            iv23.visibility = View.GONE
        }
        rl22.setOnClickListener {
            pos++
            iv21.visibility = View.GONE
            iv22.visibility = View.VISIBLE
            iv23.visibility = View.GONE
        }
        rl23.setOnClickListener {
            pos++
            iv21.visibility = View.GONE
            iv22.visibility = View.GONE
            iv23.visibility = View.VISIBLE
        }

        btn_next3.setOnClickListener {
            if (pos == 0) {
                Toast.makeText(
                    this@McqActitivity3,
                    "Please answer the questions",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                AppManage.getInstance(this).showInterstitialAd(this,true,
                    {
                        startActivity(Intent(this@McqActitivity3, McqActitivity4::class.java))
                        finish()


                    },  AppManage.app_innerClickCntSwAd
                )

            }
        }

    }

    override fun onBackPressed() {
        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }
    }
}