package com.livechat.friendvideo.calltalk.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.extras.Constants
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_age.*
import kotlinx.android.synthetic.main.common_native_layout.*

class AgeActivity : AppCompatActivity() {

    var age = 30
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age)
//        Adsmanager.getInstance(this)!!
//            .showallBigNative(native_layout)
        AppManage.getInstance(this).loadInterstitialAd(this)

        AppManage.getInstance(this)!!.showNative(native_layout)

        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))
        if (PowerPreference.getDefaultFile().getString(Constants.NAME) != "") {
            user_name2.setText(PowerPreference.getDefaultFile().getString(Constants.NAME))
        }
//        scrollView.viewTreeObserver.addOnGlobalLayoutListener {
//            scrollView.post(Runnable {
//                scrollView.fullScroll(View.FOCUS_DOWN)
//            })
//        }

        if (PowerPreference.getDefaultFile().getInt(Constants.AGE) != 0) {
            age = PowerPreference.getDefaultFile().getInt(Constants.AGE)
            number_picker!!.value = PowerPreference.getDefaultFile().getInt(Constants.AGE)
        } else {
            number_picker!!.value = 30
        }
        number_picker!!.setOnValueChangedListener { _, _, newVal ->
            age = newVal
        }

        next_btn2.setOnClickListener {
            if (user_name2 != null && user_name2.text.trim().toString() != "") {
                PowerPreference.getDefaultFile()
                    .setString(Constants.NAME, user_name2.text.toString())
                PowerPreference.getDefaultFile().setInt(Constants.AGE, age)

                AppManage.getInstance(this).showInterstitialAd(
                    this,
                    true,  {
                        startActivity(
                            Intent(
                                this@AgeActivity,
                                GenderActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        )
                        finish()

                    },  AppManage.app_innerClickCntSwAd
                )
            } else {
                Toast.makeText(this, "Name is Required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }

    }

}