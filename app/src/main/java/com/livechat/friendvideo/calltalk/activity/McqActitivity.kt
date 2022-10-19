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
import kotlinx.android.synthetic.main.activity_mcq_actitivity.*
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class McqActitivity : AppCompatActivity() {
    var pos: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcq_actitivity)
        AppManage.getInstance(this).loadInterstitialAd(this)

        setview()
    }

    private fun setview() {
        AppManage.getInstance(this)!!.showNative(native_layout)
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))

        rl1.setOnClickListener {
            pos++
            iv1.visibility = View.VISIBLE
            iv2.visibility = View.GONE
            iv3.visibility = View.GONE
            iv4.visibility = View.GONE
            iv5.visibility = View.GONE
        }
        rl2.setOnClickListener {
            pos++
            iv1.visibility = View.GONE
            iv2.visibility = View.VISIBLE
            iv3.visibility = View.GONE
            iv4.visibility = View.GONE
            iv5.visibility = View.GONE
        }
        rl3.setOnClickListener {
            pos++
            iv1.visibility = View.GONE
            iv2.visibility = View.GONE
            iv3.visibility = View.VISIBLE
            iv4.visibility = View.GONE
            iv5.visibility = View.GONE
        }
        rl4.setOnClickListener {
            pos++
            iv1.visibility = View.GONE
            iv2.visibility = View.GONE
            iv3.visibility = View.GONE
            iv4.visibility = View.VISIBLE
            iv5.visibility = View.GONE
        }
        rl5.setOnClickListener {
            pos++
            iv1.visibility = View.GONE
            iv2.visibility = View.GONE
            iv3.visibility = View.GONE
            iv4.visibility = View.GONE
            iv5.visibility = View.VISIBLE
        }

        btn_next.setOnClickListener {
            if (pos == 0) {
                Toast.makeText(this@McqActitivity, "Please answer the questions", Toast.LENGTH_SHORT)
                    .show()
            } else {
                AppManage.getInstance(this).showInterstitialAd(
                    this,
                    true,  {
                        startActivity(Intent(this@McqActitivity, McqActitivity2::class.java))
                        finish()

                    }, AppManage.app_innerClickCntSwAd
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