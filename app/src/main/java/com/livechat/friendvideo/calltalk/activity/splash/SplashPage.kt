package com.livechat.friendvideo.calltalk.activity.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.livechat.friendvideo.calltalk.activity.StartScreenActivity
import com.livechat.friendvideo.calltalk.databinding.ActivitySplashPageBinding
import com.livechat.friendvideo.calltalk.extras.Constants
import com.pesonal.adsdk.*
import com.pesonal.adsdk.vpn.ConstVpn
import com.pesonal.adsdk.vpn.MyUtils
import com.pesonal.adsdk.vpn.OnVpnEventCapture
import com.pesonal.adsdk.vpn.VpnUtilitiys
import com.preference.PowerPreference
import org.json.JSONObject

class SplashPage : AppCompatActivity() {

    private var vpnUtilitiys: VpnUtilitiys? = null
    lateinit var binding: ActivitySplashPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManage.getUserCountry(this, object : OnCountryListener {
            override fun onCountryFound(name: String?) {
                AppManage.myDeviceCountryCode = name
            }

        })
        binding = ActivitySplashPageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        if (MyUtils.isInterNetAvailable(this, true)) {
            init()
        }
    }

    fun init() {
        ADS_SplashActivity.ADSinit(this,
            MyUtils.getCurrentVersionCode(this),
            object : getDataListner {
                override fun onSuccess() {
                    ADS_SplashActivity.splashopen = true
                    afterAllDataFromAdsSdk()
                }

                override fun onUpdate(url: String) {
                    Log.e("my_log", "onUpdate: $url")
                    MyUtils.showUpdateDialog(this@SplashPage, url)
                }

                override fun onRedirect(url: String) {
                    Log.e("my_log", "onRedirect: $url")
                    MyUtils.showRedirectDialog(this@SplashPage, url)
                }

                override fun onReload() {
                    startActivity(Intent(this@SplashPage, SplashPage::class.java))
                    finish()
                }

                override fun onGetExtradata(extraData: JSONObject) {
                    Log.e("my_log", "ongetExtradata: $extraData")
                }
            })
    }

    private fun afterAllDataFromAdsSdk() {
//        setupAdsDataToMyPref()
        checkVpn()
    }

    private fun checkVpn() {
        var isOnVpn = MyUtils.getVpnOnOff(this)
        if (!isOnVpn) jumpNextPage()
        else {
            vpnUtilitiys = VpnUtilitiys.getInstance(this, launcher, object : OnVpnEventCapture {
                override fun onNextPageJump() {
                    runOnUiThread { jumpNextPage() }
                }

                override fun onVpnConnected(msg: String) {
                    Log.e("AAA", "onVpnConnected: ")
                }

                override fun onVpnFail(msg: String) {
                    Log.e("AAA", "onVpnFail: $msg")
                }
            })
            if (MyUtils.getVpnFromSplash()) {
                vpnUtilitiys!!.enableMyVpn()
            } else {
                if (AppPreferenceVpn(this@SplashPage).vpnPermission) vpnUtilitiys!!.enableMyVpn()
                else jumpNextPage()
            }
        }
    }

    /* private fun enableMyVpn() {
         var isHydra = MyUtils.getVpnHydra()
         var isAlreadyConnect = MyUtils.getVpnAlreadyConnect(this)
         if (!isAlreadyConnect) {
             if (isHydra) {
                 connectHydra()
             } else {
                 connectOneConnect()
             }
         } else {
             jumpNextPage()
         }
     }

     private fun connectOneConnect() {

     }

     private fun connectHydra() {
         MyUtils.initHydraSdk(this)
         MyUtils.CallVpnLogin(this)
         startVpnService()
     }

     private fun startVpnService() {
         if (!MyUtils.getVpnAlreadyConnect(this)) {
             val intent = VpnService.prepare(this)
             if (intent != null) {
                 launcher.launch(intent)
             } else {
                 onConnectBtnClick()
             }
         } else {
             ConstVpn.LoadAllAds(this@SplashPage)
             SplashActivity.MoveToNextActivity()
         }
     }*/

    private fun jumpNextPage() {
        ADS_SplashActivity.showSplashAdMain(this) {
            startActivity(Intent(this, StartScreenActivity::class.java))
        }
    }

    private fun setupAdsDataToMyPref() {
        PowerPreference.getDefaultFile()
            .setString(Constants.AppRtcWebOnline, AppManage.WebGenerateRoom)
        PowerPreference.getDefaultFile()
            .setString(Constants.AppRtcWebOffline, AppManage.WebDeleteRoom)
        PowerPreference.getDefaultFile()
            .setString(Constants.FLAG_NATIVE_SCROLL, AppManage.native_scroll_show)
        PowerPreference.getDefaultFile().setString(Constants.MAIN_DATA_URL, AppManage.main_data_url)
        PowerPreference.getDefaultFile().setString(Constants.REPORT_USER, AppManage.ReportUser)
        PowerPreference.getDefaultFile().setString(Constants.DELETE_ROOM, AppManage.DeleteRoom)
        PowerPreference.getDefaultFile().setString(Constants.GENERATE_ROOM, AppManage.GenerateRoom)
        PowerPreference.getDefaultFile().setString(Constants.APP_RTC_URL, AppManage.app_rtc_url)
        PowerPreference.getDefaultFile()
            .setString(Constants.PRIVACY_POLICY, AppManage.app_privacyPolicyLink)
        PowerPreference.getDefaultFile()
            .setString(Constants.TERMS_OF_SERVICE, AppManage.app_termsServieLink)

        PowerPreference.getDefaultFile()
            .setString(Constants.VPN_Hydra_OneConnect, AppManage.Hydra_OneConnect)
        PowerPreference.getDefaultFile()
            .setString(Constants.VPN_LIST_ONECONNECT, AppManage.One_Onnect_connect_vpn)
        PowerPreference.getDefaultFile().setString(Constants.VPN_LIST_HYDRA, AppManage.connect_vpn)
        PowerPreference.getDefaultFile()
            .setString(Constants.VPN_COUNTRY_NOT_USE, AppManage.country_list)
        PowerPreference.getDefaultFile().setString(Constants.VPN_FORCE, AppManage.force_vpn)
        PowerPreference.getDefaultFile()
            .setString(Constants.VPN_FROM_SPLASH, AppManage.splash_start_vpn)
        PowerPreference.getDefaultFile().setString(Constants.VPN_ON_OFF, AppManage.vpn_flag)
        PowerPreference.getDefaultFile()
            .setString(Constants.VPN_IGNORE_AFTER_SEC, AppManage.vpn_sec)
        PowerPreference.getDefaultFile()
            .setString(Constants.VPN_HYDRA_HOST, AppManage.vpn_base_host)
        PowerPreference.getDefaultFile()
            .setString(Constants.VPN_HYDRA_CARRIER_ID, AppManage.vpn_base_carrier_id)

        //  oneConnectVpn
        PowerPreference.getDefaultFile()
            .setString(Constants.ONE_CONNECT_API_KEY, AppManage.One_Onnect_API_Key)

        if (AppManage.app_adShowStatus == 0) {
            PowerPreference.getDefaultFile().setBoolean(Constants.ADS_ONOFF, false)
        } else {
            PowerPreference.getDefaultFile().setBoolean(Constants.ADS_ONOFF, true)
        }
    }

    var launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            AppPreferenceVpn(this@SplashPage).vpnPermission = true
            if (MyUtils.getVpnHydra()) {
                vpnUtilitiys?.connectHydra(true)
            } else {
                vpnUtilitiys?.connectOneConnect(true)
            }
        } else {
            AppPreferenceVpn(this@SplashPage).vpnPermission = false
            ConstVpn.LoadAllAds(this@SplashPage)
            jumpNextPage()
        }
    }

    /*private fun onConnectBtnClick() {
        jumpAfterVpnSecond()
        UnifiedSDK.getInstance().backend.isLoggedIn(object : Callback<Boolean> {
            override fun success(p0: Boolean) {
                var country_list = MyUtils.getHydraVpn().split(",".toRegex())
                *//*var country_list = ArrayList(
                    listOf<String>(all.)
                )*//*
                val fallbackOrder: MutableList<String> = ArrayList()
                fallbackOrder.add(HydraTransport.TRANSPORT_ID)
                fallbackOrder.add(CaketubeTransport.TRANSPORT_ID_TCP)
                fallbackOrder.add(CaketubeTransport.TRANSPORT_ID_UDP)
                val bypassDomains: MutableList<String> = LinkedList()
                bypassDomains.add("*facebook.com")
                bypassDomains.add("*wtfismyip.com")
                UnifiedSDK.getInstance().vpn.start(SessionConfig.Builder()
                    .withReason(TrackingConstants.GprReasons.M_UI)
                    .withTransportFallback(fallbackOrder)
                    .withVirtualLocation(MyUtils.getRandomItem<String>(country_list).toString())
                    .withTransport(HydraTransport.TRANSPORT_ID)
                    .addDnsRule(TrafficRule.Builder.bypass().fromDomains(bypassDomains)).build(),
                    object : CompletableCallback {
                        override fun complete() {
                            Log.e(ConstVpn.HYDRA_VPN_TAG, "complete: ")
//                        startUIUpdateTask()
                        }

                        override fun error(e: VpnException) {
                            Log.e(ConstVpn.HYDRA_VPN_TAG, "error: ")
//                        updateUI()
//                        handleError(e)
                        }
                    })
            }

            override fun failure(p0: VpnException) {
                Log.e(ConstVpn.HYDRA_VPN_TAG, "failure: ")
            }
        })
    }*/

    /*  private fun jumpAfterVpnSecond() {
          Handler(Looper.myLooper()!!).postDelayed({
              Log.e(ConstVpn.HYDRA_VPN_TAG, "onConnectBtnClick: Move To Next")
              jumpNextPage()
          }, (MyUtils.getVpnSecond() * 1000).toLong())
      }*/
}