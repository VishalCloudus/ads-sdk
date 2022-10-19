package com.livechat.friendvideo.calltalk.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.Web_Call_Activity
import com.livechat.friendvideo.calltalk.activity.adapter.RoomAdapter
import com.livechat.friendvideo.calltalk.activity.adapter.RoomModel
import com.livechat.friendvideo.calltalk.activity.permission.AllPermissionActivity
import com.livechat.friendvideo.calltalk.extras.Constants
import com.pesonal.adsdk.AppManage
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.activity_vip.*
import java.util.*

class VipActivity : AppCompatActivity() {
    var RoomName = ArrayList(
        Arrays.asList(
            "Room 1",
            "Room 2",
            "Room 3",
            "Room 4",
            "Room 5",
            "Room 6",
            "Room 7",
            "Room 8",
            "Room 9",
            "Room 10"
        )
    )
    var RoomDesc = ArrayList(
        Arrays.asList(
            "Spain - Cool Chat",
            "India - Desi Girls",
            "Brazil - Sexy Chat",
            "USA - Sexy Girls",
            "New Zealand - Hotty Girl",
            "Brazil - Cutie Girl",
            "Germany - Hot Girl",
            "India - Sexy Girl",
            "USA - Sexy Girls",
            "India - Model"
        )
    )
    var RoomImage = ArrayList(
        Arrays.asList<Int>(
            R.drawable.room1,
            R.drawable.room2,
            R.drawable.room3,
            R.drawable.room4,
            R.drawable.room5,
            R.drawable.room6,
            R.drawable.room7,
            R.drawable.room8,
            R.drawable.room9,
            R.drawable.room10
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vip)
        AppManage.getInstance(this).loadInterstitialAd(this)

        val finallist: ArrayList<RoomModel> = ArrayList<RoomModel>()
        for (i in RoomName.indices) {
            val item = RoomModel()
            item.room = RoomName[i]
            item.roomImage = RoomImage[i]
            item.roomDesc = RoomDesc[i]
            finallist.add(item)
        }
        addAdvertise(finallist)
    }

    private fun addAdvertise(pathArrayList: ArrayList<RoomModel>) {
        var newGirlList: ArrayList<RoomModel> = ArrayList<RoomModel>()
        if (newGirlList.isNotEmpty()) {
            newGirlList.clear()
        }
        if (PowerPreference.getDefaultFile().getBoolean(Constants.ADS_ONOFF)) {
            var i = 1
            for (i1 in pathArrayList.indices) {
                newGirlList.add(pathArrayList[i1])
                if (i % 3 == 0) {
                    val girlData = RoomModel()
                    girlData.isAd = true
                    newGirlList.add(girlData)
                }
                i++
            }
        } else {
            newGirlList = pathArrayList
        }
        setAdapter(newGirlList!!)
    }

    private fun setAdapter(newGirlList: ArrayList<RoomModel>) {
        val recycle_adapter = RoomAdapter(this, newGirlList, object : MyAdpCallback {
            override fun callbackCallAdp() {
                AppManage.getInstance(this@VipActivity).showInterstitialAd(
                    this@VipActivity,
                    true, {
                        if (ContextCompat.checkSelfPermission(
                                this@VipActivity,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(
                                this@VipActivity,
                                Manifest.permission.RECORD_AUDIO
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            startActivity(Intent(this@VipActivity, Web_Call_Activity::class.java))
                        } else {
                            startActivity(
                                Intent(
                                    this@VipActivity,
                                    AllPermissionActivity::class.java
                                ).putExtra("isWebCalling", "1")
                            )
                        }


                    }, AppManage.app_innerClickCntSwAd
                )

            }
        })
        val rvLayoutManager = LinearLayoutManager(this@VipActivity)
        room_recycle.layoutManager = rvLayoutManager
        room_recycle.adapter = recycle_adapter
    }

    override fun onBackPressed() {

        AppManage.getInstance(this).showBackPressAd(
            this
        ) { finish() }
    }

    interface MyAdpCallback {
        fun callbackCallAdp()
    }

}