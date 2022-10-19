package com.livechat.friendvideo.calltalk.activity.splash

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anchorfree.partner.api.ClientInfo
import com.anchorfree.partner.api.auth.AuthMethod
import com.anchorfree.partner.api.response.User
import com.anchorfree.sdk.*
import com.anchorfree.vpnsdk.callbacks.Callback
import com.anchorfree.vpnsdk.callbacks.CompletableCallback
import com.anchorfree.vpnsdk.exceptions.VpnException
import com.livechat.friendvideo.calltalk.BuildConfig
import com.livechat.friendvideo.calltalk.R
import com.livechat.friendvideo.calltalk.VPN.ConstVpn
import com.livechat.friendvideo.calltalk.VPN.Countries
import com.livechat.friendvideo.calltalk.api.retrofit.ApiClient
import com.livechat.friendvideo.calltalk.extras.Constants
import com.northghost.caketube.OpenVpnTransportConfig
import com.pesonal.adsdk.AppManage
import com.pesonal.adsdk.AppPreferenceVpn
import com.pesonal.adsdk.OnCountryListener
import com.preference.PowerPreference
import java.util.*

class MyUtils {
    companion object {

        var myDeviceCountryCode = AppManage.myDeviceCountryCode
        fun getCurrentVersionCode(cn: Context): Int {
            val manager = cn.packageManager
            var info: PackageInfo? = null
            try {
                info = manager.getPackageInfo(
                    cn.packageName, 0
                )
                return info.versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return 0
        }


        fun isInterNetAvailable(context: Activity?, b: Boolean): Boolean {
            var isInterNAvailable = false
            if (context != null) {
                val connectivityManager =
                    context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (connectivityManager != null) {
                    var mobileNetwork = false
                    var wifiNetwork = false
                    var mobileNetworkConnecetd = false
                    var wifiNetworkConnecetd = false
                    val mobileNetworkInfo =
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    val wifiNetworkInfo =
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    if (mobileNetworkInfo != null) {
                        mobileNetwork = mobileNetworkInfo.isAvailable
                    }
                    if (wifiNetworkInfo != null) {
                        wifiNetwork = wifiNetworkInfo.isAvailable
                    }
                    if (wifiNetwork || mobileNetwork) {
                        if (mobileNetworkInfo != null) mobileNetworkConnecetd =
                            mobileNetworkInfo.isConnectedOrConnecting
                        wifiNetworkConnecetd = wifiNetworkInfo!!.isConnectedOrConnecting
                    }
                    isInterNAvailable = mobileNetworkConnecetd || wifiNetworkConnecetd
                }
                if (!isInterNAvailable && b) {
                    if (context is Activity) {
                        AlertDialog(
                            context,
                            context.resources.getString(R.string.app_name),
                            "No internet connection"
                        )
                    }
                }
            }
            return isInterNAvailable
        }

        fun AlertDialog(activity: Activity, title: String?, msg: String?) {
            val builder = androidx.appcompat.app.AlertDialog.Builder(activity)
            builder.setMessage(msg).setCancelable(false).setTitle(title)
                .setPositiveButton("ok") { dialog, id -> //do things
                    activity.finishAffinity()
                }
            val alert = builder.create()
            alert.show()
        }


        fun showUpdateDialog(cn: Context, url: String?) {
            val dialog = Dialog(cn)
            dialog.setCancelable(false)
            val view = ((cn) as Activity).layoutInflater.inflate(R.layout.installnewappdialog, null)
            dialog.setContentView(view)
            val update = view.findViewById<TextView>(R.id.update)
            val txt_title = view.findViewById<TextView>(R.id.txt_title)
            val txt_decription = view.findViewById<TextView>(R.id.txt_decription)
            update.text = "Update Now"
            txt_title.text = "Update our new app now and enjoy"
            txt_decription.text = ""
            txt_decription.visibility = View.GONE
            update.setOnClickListener {
                try {
                    val marketUri = Uri.parse(url)
                    val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                    cn.startActivity(marketIntent)
                } catch (ignored1: ActivityNotFoundException) {
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.create()
            }
            dialog.show()
            val window = dialog.window
            window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }


        fun showRedirectDialog(cn: Context, url: String?) {
            val dialog = Dialog(cn)
            dialog.setCancelable(false)
            val view = ((cn) as Activity).layoutInflater.inflate(R.layout.installnewappdialog, null)
            dialog.setContentView(view)
            val update = view.findViewById<TextView>(R.id.update)
            val txt_title = view.findViewById<TextView>(R.id.txt_title)
            val txt_decription = view.findViewById<TextView>(R.id.txt_decription)
            update.text = "Install Now"
            txt_title.text = "Install our new app now and enjoy"
            txt_decription.text =
                "We have transferred our server, so install our new app by clicking the button below to enjoy the new features of app."
            update.setOnClickListener {
                try {
                    val marketUri = Uri.parse(url)
                    val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
                    cn.startActivity(marketIntent)
                } catch (ignored1: ActivityNotFoundException) {
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dialog.create()
            }
            dialog.show()
            val window = dialog.window
            window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        @JvmStatic
        fun getVpnOnOff(cn: Context): Boolean {
            var sonoff =
                PowerPreference.getDefaultFile().getString(Constants.VPN_ON_OFF, "0") as String
            if (sonoff.equals("1")) {
                var isInMyCountry = getVpnUseInMyCountry(cn)
                if (!isInMyCountry) sonoff = "0"
            }
            return sonoff == "1"
        }

        fun getVpnUseInMyCountry(cn: Context): Boolean {
            var country_list =
                PowerPreference.getDefaultFile().getString(Constants.VPN_COUNTRY_NOT_USE, "")
            var allCountry = country_list.split(",".toRegex()).toTypedArray()
            var isMyCountryEnable = false
            var index = allCountry.indexOf(myDeviceCountryCode)
            isMyCountryEnable = index < 0
            return isMyCountryEnable
        }

        @JvmStatic
        fun getVpnHydra(): Boolean {
            var ishdraoneconnect =
                PowerPreference.getDefaultFile().getString(Constants.VPN_Hydra_OneConnect)
            return ishdraoneconnect.equals("0")
        }

        fun getVpnAlreadyConnect(cn: Context): Boolean {
            val cm = cn.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.getNetworkInfo(ConnectivityManager.TYPE_VPN)!!.isConnectedOrConnecting
        }

        fun initHydraSdk(activity: Activity) {
            ConstVpn.createNotificationChannel(activity)
            val prefs = ConstVpn.getPrefs(activity)
            val clientInfo = ClientInfo.newBuilder().addUrl(
                prefs.getString(
                    BuildConfig.STORED_HOST_URL_KEY,
                    PowerPreference.getDefaultFile().getString(Constants.VPN_HYDRA_HOST)
                )!!
            ).carrierId(
                prefs.getString(
                    BuildConfig.STORED_CARRIER_ID_KEY,
                    PowerPreference.getDefaultFile().getString(Constants.VPN_HYDRA_CARRIER_ID)
                )!!
            ).build()
            val transportConfigList: MutableList<TransportConfig> = ArrayList()
            transportConfigList.add(HydraTransportConfig.create())
            transportConfigList.add(OpenVpnTransportConfig.tcp())
            transportConfigList.add(OpenVpnTransportConfig.udp())
            UnifiedSDK.update(transportConfigList, CompletableCallback.EMPTY)
            val config = UnifiedSDKConfig.newBuilder().idfaEnabled(false).build()
            ConstVpn.unifiedSDK = UnifiedSDK.getInstance(clientInfo, config)
            val notificationConfig = NotificationConfig.newBuilder()
                .title(activity.resources.getString(R.string.app_name))
                .channelId(activity.packageName).build()
            UnifiedSDK.update(notificationConfig)
            UnifiedSDK.setLoggingLevel(Log.VERBOSE)
        }

        fun CallVpnLogin(activity: Activity?, listener: OnCountryListener) {
            val authMethod = AuthMethod.anonymous()
            UnifiedSDK.getInstance().backend.login(authMethod, object : Callback<User> {
                override fun success(user: User) {
                    ConstVpn.isVpnLogin = true
                    listener.onCountryFound("1")
                    Log.e(ConstVpn.HYDRA_VPN_TAG, "loginToVpn: success")

                }

                override fun failure(e: VpnException) {
                    ConstVpn.isVpnLogin = false
                    Log.e(ConstVpn.HYDRA_VPN_TAG, "loginToVpn: failure")
                    ConstVpn.LoadAllAds(activity)
                    listener.onCountryFound("0")
                }
            })
        }

        fun getVpnSecond(): Int {
            var sec: String =
                PowerPreference.getDefaultFile().getString(Constants.VPN_IGNORE_AFTER_SEC, "4")
            return sec.toInt()
        }

        fun getHydraVpn(): String {
            return PowerPreference.getDefaultFile().getString(Constants.VPN_LIST_HYDRA, "")
        }

        fun getOneConnectVpn(): String {
            return PowerPreference.getDefaultFile().getString(Constants.VPN_LIST_ONECONNECT, "")
        }


        fun <T> getRandomItem(list: List<T>): T {
            val random = Random()
            val listSize = list.size
            val randomIndex = random.nextInt(listSize)
            Log.e("VPN_MainActivity", "onCreate2: random Index : $randomIndex")
//            vpnselectcountry = list.get(randomIndex)
            Log.e("VPN_MainActivity", "onCreate2: random Country : ${list[randomIndex]}")
            return list[randomIndex]
        }

        fun getVpnFromSplash(): Boolean {
            var splash = PowerPreference.getDefaultFile().getString(Constants.VPN_FROM_SPLASH)
            return splash.equals("0")
        }

        @JvmStatic
        fun getVpnForce(): Boolean {
            var sforce = PowerPreference.getDefaultFile().getString(Constants.VPN_FORCE)
            return sforce.equals("1")
        }

        fun getSelectedCityOneConnect(
            serversOneConnect: ArrayList<Countries>,
            city: String
        ): Countries? {
            var selected: Countries? = null
            for (item in serversOneConnect) {
                if (item.country.contains(city)) {
                    selected = item
                    break
                }
            }
            return selected
        }


    }
}