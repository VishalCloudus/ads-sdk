package com.livechat.friendvideo.calltalk.activity.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.Web_Call_Activity
import com.livechat.friendvideo.calltalk.activity.MainActivity
import com.livechat.friendvideo.calltalk.activity.PreconnectingActivity
import com.livechat.friendvideo.calltalk.extras.Constants
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_all_permission.*
import kotlinx.android.synthetic.main.common_native_layout.*

class AllPermissionActivity : AppCompatActivity() {
    var isWebCalling: String? = ""
    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_permission)

        AppManage.getInstance(this).loadInterstitialAd(this)
        AppManage.getInstance(this)!!.showNative(native_layout)

        AppManage.getInstance(this).showBanner(findViewById(R.id.llAdBanner))

        allow_btn.setOnClickListener {
            allowPermissions()
        }
    }

    private fun MoveToNext() {
        AppManage.getInstance(this@AllPermissionActivity).showInterstitialAd(
            this@AllPermissionActivity,
            true, {
                PowerPreference.getDefaultFile().setBoolean(Constants.flagnext, true)
                if (intent != null) {
                    isWebCalling = intent.getStringExtra("isWebCalling")
                    if (isWebCalling == "1") {
                        startActivity(
                            Intent(
                                this@AllPermissionActivity,
                                Web_Call_Activity::class.java
                            )
                        )
                    } else if (isWebCalling == "2") {
                        startActivity(
                            Intent(
                                this@AllPermissionActivity,
                                PreconnectingActivity::class.java
                            )
                        )
                    } else {
                        startActivity(Intent(this@AllPermissionActivity, MainActivity::class.java))
                    }
                } else {
                    startActivity(Intent(this@AllPermissionActivity, MainActivity::class.java))
                }
                finish()
            }, AppManage.app_innerClickCntSwAd
        )


    }

    private fun allowPermissions() {
        val rationale = "Please provide permission...!!"
        val options: Permissions.Options = Permissions.Options()
            .setRationaleDialogTitle("Info")
            .setSettingsDialogTitle("Warning")
        Permissions.check(
            this,
            permissions,
            rationale,
            options,
            object : PermissionHandler() {
                override fun onGranted() {
                    MoveToNext()
                }

                override fun onDenied(
                    context: Context?,
                    deniedPermissions: ArrayList<String?>?
                ) {
                    // permission denied, block the feature.
                    Toast.makeText(context, "permission denied..!!", Toast.LENGTH_SHORT)
                        .show()
                }
            })

    }

    override fun onBackPressed() {
        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }

    }

}