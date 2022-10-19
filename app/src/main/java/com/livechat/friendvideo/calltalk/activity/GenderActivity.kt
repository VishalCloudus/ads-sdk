package com.livechat.friendvideo.calltalk.activity

import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.extras.Constants
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_gender.*
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class GenderActivity : AppCompatActivity() {
    var value = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)


        AppManage.getInstance(this)!!.showNative(native_layout)
        AppManage.getInstance(this).loadInterstitialAd(this)

        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))

        if (PowerPreference.getDefaultFile().getString(Constants.GENDER) == "MALE") {
            value = 1
            changeUi()
        } else if (PowerPreference.getDefaultFile().getString(Constants.GENDER) == "FEMALE") {
            value = 2
            changeUi()
        }

        linear_male.setOnClickListener {
            value = 1
            changeUi()
            PowerPreference.getDefaultFile().setString(Constants.GENDER, "MALE")
        }

        linear_female.setOnClickListener {
            value = 2
            changeUi()
            PowerPreference.getDefaultFile().setString(Constants.GENDER, "FEMALE")
        }

        next_btn.setOnClickListener {
            if (value != 0) {
                AppManage.getInstance(this).showInterstitialAd(
                    this,
                    true, {
                        startActivity(
                            Intent(
                                this@GenderActivity,
                                HobbyActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        )
                        finish()

                    },  AppManage.app_innerClickCntSwAd
                )

            } else {
                Toast.makeText(this, "Gender is Required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeUi() {
        when (value) {
            1 -> {
                card_1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
                card_2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                tick_male.visibility = VISIBLE
                tick_female.visibility = INVISIBLE

            }
            2 -> {
                card_1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
                tick_male.visibility = INVISIBLE
                tick_female.visibility = VISIBLE
            }
        }
    }

    override fun onBackPressed() {

        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }
    }

}