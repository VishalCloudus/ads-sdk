package com.livechat.friendvideo.calltalk.activity

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.activity.permission.AllPermissionActivity
import com.livechat.friendvideo.calltalk.extras.Constants
import com.livechat.friendvideo.calltalk.extras.Utils
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_hobby.*
import kotlinx.android.synthetic.main.common_banner_layout.*
import kotlinx.android.synthetic.main.common_native_layout.*

class HobbyActivity : AppCompatActivity() {
    var value = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hobby)

        AppManage.getInstance(this)!!.showNative(native_layout)
        AppManage.getInstance(this).loadInterstitialAd(this)


        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))

        when (PowerPreference.getDefaultFile().getString(Constants.HOBBY)) {
            "READING" -> {
                value = 1
                changeUi()
            }
            "SINGING" -> {
                value = 2
                changeUi()
            }
            "ART" -> {
                value = 3
                changeUi()
            }
            "WRITING" -> {
                value = 4
                changeUi()
            }
        }


        card_1.setOnClickListener {
            value = 1
            PowerPreference.getDefaultFile().setString(Constants.HOBBY, "READING")
            changeUi()
        }

        card_2.setOnClickListener {
            value = 2
            PowerPreference.getDefaultFile().setString(Constants.HOBBY, "SINGING")
            changeUi()
        }

        card_3.setOnClickListener {
            value = 3
            PowerPreference.getDefaultFile().setString(Constants.HOBBY, "ART")
            changeUi()
        }

        card_4.setOnClickListener {
            value = 4
            PowerPreference.getDefaultFile().setString(Constants.HOBBY, "WRITING")
            changeUi()
        }

//        next_btn.setOnClickListener {
//            if (value != 0) {
//                showInterstitialAds {
//                    if (Utils().checkAllPermission(this)) {
//                        startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
//                    } else {
//                        startActivity(
//                            Intent(this, PermissionActivity1::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
//                    }
//                }
//            } else {
//                Toast.makeText(applicationContext, "Select Your Hobby", Toast.LENGTH_SHORT).show()
//            }
//        }
        next_btn.setOnClickListener {
            if (value != 0) {
                if (AppManage.question_answer == "1" && !PowerPreference.getDefaultFile()
                        .getBoolean(Constants.IsQueFinish)
                ) {
                    startActivity(Intent(this@HobbyActivity, McqActitivity::class.java))
                    finish()
                } else {
                    if (Utils().checkAllPermission(this@HobbyActivity)) {
                        startActivity(
                            Intent(
                                this@HobbyActivity,
                                MainActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        )
                        finish()
                    } else {
                        startActivity(
                            Intent(
                                this@HobbyActivity,
                                AllPermissionActivity::class.java
                            ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        )
                        finish()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Select Your Hobby", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }

    private fun changeUi() {
        when (value) {
            1 -> {
                card_1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
                card_2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_4.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))

                tick_1.visibility = VISIBLE
                tick_2.visibility = GONE
                tick_3.visibility = GONE
                tick_4.visibility = GONE
            }
            2 -> {
                card_1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
                card_3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_4.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))

                tick_1.visibility = GONE
                tick_2.visibility = VISIBLE
                tick_3.visibility = GONE
                tick_4.visibility = GONE
            }
            3 -> {
                card_1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
                card_4.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))

                tick_1.visibility = GONE
                tick_2.visibility = GONE
                tick_3.visibility = VISIBLE
                tick_4.visibility = GONE
            }
            4 -> {
                card_1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white))
                card_4.setCardBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))

                tick_1.visibility = GONE
                tick_2.visibility = GONE
                tick_3.visibility = GONE
                tick_4.visibility = VISIBLE
            }
        }
    }

    override fun onBackPressed() {
        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }

    }

}