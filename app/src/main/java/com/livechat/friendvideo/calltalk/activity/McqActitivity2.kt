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
import kotlinx.android.synthetic.main.activity_mcq_actitivity2.*
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class McqActitivity2 : AppCompatActivity() {
    var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcq_actitivity2)
        AppManage.getInstance(this).loadInterstitialAd(this)
        setview()
    }

    private fun setview() {
        AppManage.getInstance(this)!!.showNative( native_layout)
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))

        rl11.setOnClickListener {
            pos++
            iv11.visibility = View.VISIBLE
            iv12.visibility = View.GONE
            iv13.visibility = View.GONE
            iv14.visibility = View.GONE
            iv15.visibility = View.GONE
        }
        rl12.setOnClickListener {
            pos++
            iv11.visibility = View.GONE
            iv12.visibility = View.VISIBLE
            iv13.visibility = View.GONE
            iv14.visibility = View.GONE
            iv15.visibility = View.GONE
        }
        rl13.setOnClickListener {
            pos++
            iv11.visibility = View.GONE
            iv12.visibility = View.GONE
            iv13.visibility = View.VISIBLE
            iv14.visibility = View.GONE
            iv15.visibility = View.GONE
        }
        rl14.setOnClickListener {
            pos++
            iv11.visibility = View.GONE
            iv12.visibility = View.GONE
            iv13.visibility = View.GONE
            iv14.visibility = View.VISIBLE
            iv15.visibility = View.GONE
        }
        rl15.setOnClickListener {
            pos++
            iv11.visibility = View.GONE
            iv12.visibility = View.GONE
            iv13.visibility = View.GONE
            iv14.visibility = View.GONE
            iv15.visibility = View.VISIBLE
        }

        btn_next2.setOnClickListener {
            if (pos == 0) {
                Toast.makeText(this@McqActitivity2, "Please answer the questions", Toast.LENGTH_SHORT).show()
            } else {
                AppManage.getInstance(this).showInterstitialAd(this,true,
                    {
                        startActivity(Intent(this@McqActitivity2, McqActitivity3::class.java))
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