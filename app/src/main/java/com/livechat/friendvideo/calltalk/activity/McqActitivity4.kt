package com.livechat.friendvideo.calltalk.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.activity.permission.AllPermissionActivity
import com.livechat.friendvideo.calltalk.extras.Constants
import com.livechat.friendvideo.calltalk.extras.Utils
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_mcq_actitivity4.*
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class McqActitivity4 : AppCompatActivity() {
    var pos: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcq_actitivity4)
        AppManage.getInstance(this).loadInterstitialAd(this)
        setview()
    }

    private fun setview() {
        AppManage.getInstance(this)!!.showNative(native_layout)
        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))
        rl31.setOnClickListener {
            pos++
            iv31.visibility = View.VISIBLE
            iv32.visibility = View.GONE
            iv33.visibility = View.GONE
            iv34.visibility = View.GONE
            iv35.visibility = View.GONE
            iv36.visibility = View.GONE
            iv37.visibility = View.GONE
            iv38.visibility = View.GONE
        }
        rl32.setOnClickListener {
            pos++
            iv31.visibility = View.GONE
            iv32.visibility = View.VISIBLE
            iv33.visibility = View.GONE
            iv34.visibility = View.GONE
            iv35.visibility = View.GONE
            iv36.visibility = View.GONE
            iv37.visibility = View.GONE
            iv38.visibility = View.GONE

        }
        rl33.setOnClickListener {
            pos++
            iv31.visibility = View.GONE
            iv32.visibility = View.GONE
            iv33.visibility = View.VISIBLE
            iv34.visibility = View.GONE
            iv35.visibility = View.GONE
            iv36.visibility = View.GONE
            iv37.visibility = View.GONE
            iv38.visibility = View.GONE
        }
        rl34.setOnClickListener {
            pos++
            iv31.visibility = View.GONE
            iv32.visibility = View.GONE
            iv33.visibility = View.GONE
            iv34.visibility = View.VISIBLE
            iv35.visibility = View.GONE
            iv36.visibility = View.GONE
            iv37.visibility = View.GONE
            iv38.visibility = View.GONE
        }
        rl35.setOnClickListener {
            pos++
            iv31.visibility = View.GONE
            iv32.visibility = View.GONE
            iv33.visibility = View.GONE
            iv34.visibility = View.GONE
            iv35.visibility = View.VISIBLE
            iv36.visibility = View.GONE
            iv37.visibility = View.GONE
            iv38.visibility = View.GONE
        }
        rl36.setOnClickListener {
            pos++
            iv31.visibility = View.GONE
            iv32.visibility = View.GONE
            iv33.visibility = View.GONE
            iv34.visibility = View.GONE
            iv35.visibility = View.GONE
            iv36.visibility = View.VISIBLE
            iv37.visibility = View.GONE
            iv38.visibility = View.GONE
        }
        rl37.setOnClickListener {
            pos++
            iv31.visibility = View.GONE
            iv32.visibility = View.GONE
            iv33.visibility = View.GONE
            iv34.visibility = View.GONE
            iv35.visibility = View.GONE
            iv36.visibility = View.GONE
            iv37.visibility = View.VISIBLE
            iv38.visibility = View.GONE
        }
        rl38.setOnClickListener {
            pos++
            iv31.visibility = View.GONE
            iv32.visibility = View.GONE
            iv33.visibility = View.GONE
            iv34.visibility = View.GONE
            iv35.visibility = View.GONE
            iv36.visibility = View.GONE
            iv37.visibility = View.GONE
            iv38.visibility = View.VISIBLE
        }

        btn_next4.setOnClickListener {
            if (pos == 0) {
                Toast.makeText(
                    this@McqActitivity4,
                    "Please answer the questions",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                AppManage.getInstance(this).showInterstitialAd(
                    this,
                    true, {
                        PowerPreference.getDefaultFile().setBoolean(Constants.IsQueFinish, true)
                        if (Utils().checkAllPermission(this@McqActitivity4)) {
                            startActivity(
                                Intent(
                                    this@McqActitivity4,
                                    MainActivity::class.java
                                ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            )
                            finish()
                        } else {
                            startActivity(
                                Intent(
                                    this@McqActitivity4,
                                    AllPermissionActivity::class.java
                                ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                            )
                            finish()
                        }


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