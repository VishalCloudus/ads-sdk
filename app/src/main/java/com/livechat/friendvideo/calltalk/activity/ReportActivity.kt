package com.livechat.friendvideo.calltalk.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.VPN.DialogVpn
import com.livechat.friendvideo.calltalk.api.model.room
import com.livechat.friendvideo.calltalk.api.retrofit.ApiUtils
import com.livechat.friendvideo.calltalk.extras.Constants
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_report.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        rb1.isChecked = true

        if (intent.extras != null) {
            var name = intent.extras!!.getString("name")
            var image = intent.extras!!.getString("image")

            Log.e("Tiger", "image: " + image)
            Log.e("Tiger", "name: " + name)

            profile_name.text = name

            val url =
                PowerPreference.getDefaultFile().getString(Constants.MAIN_DATA_URL)
                    .replace("call/", "") + Constants.JOIN_OP_PROFILE

            Glide.with(this).load(url).placeholder(R.drawable.user_male).into(profile_picture)

        }


        report_1.setOnClickListener {
            reportuser()
        }

        report_2.setOnClickListener {
            reportuser()
        }
    }

    private fun reportuser() {
        DialogVpn.progressDialog(this);
        if (PowerPreference.getDefaultFile()
                .getString(Constants.APP_RTC_URL) != null && !PowerPreference.getDefaultFile()
                .getString(Constants.APP_RTC_URL).isEmpty()
        ) {
            ApiUtils().getWebService(
                PowerPreference.getDefaultFile().getString(Constants.APP_RTC_URL)
            )!!.reportUser(
                PowerPreference.getDefaultFile().getString(Constants.REPORT_USER),
                Constants.JOIN_ID.toString()
            ).enqueue(object : Callback<room?> {
                override fun onResponse(call: Call<room?>, response: Response<room?>) {
                    if (response.body() != null) {
                        Toast.makeText(
                            this@ReportActivity,
                            "User Reported Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                    DialogVpn.isProgressDismiss();
                }

                override fun onFailure(call: Call<room?>, t: Throwable) {
                    DialogVpn.isProgressDismiss();
                    finish()
                }
            })
        } else {
            DialogVpn.isProgressDismiss();
            finish()
        }

    }


    override fun onBackPressed() {
        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }

    }

}