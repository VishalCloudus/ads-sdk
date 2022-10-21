package com.pesonal.adsdk.vpn

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.VpnService
import android.os.Handler
import android.os.Looper
import android.os.RemoteException
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.anchorfree.partner.api.data.Country
import com.anchorfree.partner.api.response.AvailableCountries
import com.anchorfree.reporting.TrackingConstants
import com.anchorfree.sdk.SessionConfig
import com.anchorfree.sdk.UnifiedSDK
import com.anchorfree.sdk.rules.TrafficRule
import com.anchorfree.vpnsdk.callbacks.Callback
import com.anchorfree.vpnsdk.callbacks.CompletableCallback
import com.anchorfree.vpnsdk.exceptions.VpnException
import com.anchorfree.vpnsdk.transporthydra.HydraTransport
import com.northghost.caketube.CaketubeTransport
import com.pesonal.adsdk.AppPreferenceVpn
import com.pesonal.adsdk.OnCountryListener
import com.preference.PowerPreference
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import top.oneconnectapi.app.OpenVpnApi
import top.oneconnectapi.app.api.OneConnect
import top.oneconnectapi.app.core.OpenVPNThread
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class VpnUtilitiys {

    private val TAG: String = "AAA_VPN_UTILITY"

    companion object {
        var vpnUtilitiys: VpnUtilitiys? = null
        var activity: Activity? = null
        var launcher: ActivityResultLauncher<Intent>? = null
        var vpnEventCapture: OnVpnEventCapture? = null
        var timer: Timer? = null
        var oneConnectThread: OpenVPNThread? = null
        var serversHydra = ArrayList<Country>()
        private var selectedCountryhydra: String = ""


        fun getInstance(
            activity: Activity?,
            launcher: ActivityResultLauncher<Intent>?,
            vpnEventCapture: OnVpnEventCapture?
        ): VpnUtilitiys {

            if (vpnUtilitiys == null) vpnUtilitiys = VpnUtilitiys()
            else if (timer == null) {
                timer = Timer()
            } else {
                timer!!.cancel()
            }

            this.activity = activity
            this.launcher = launcher
            this.vpnEventCapture = vpnEventCapture
            vpnUtilitiys!!.goForHydra(true)
            return vpnUtilitiys as VpnUtilitiys
        }

        fun getInstance(activity: Activity?): VpnUtilitiys {
            this.activity = activity
            if (vpnUtilitiys == null) vpnUtilitiys = VpnUtilitiys()
            return vpnUtilitiys as VpnUtilitiys
        }
    }

    fun enableMyVpn() {
        var isHydra = MyUtils.getVpnHydra()
        var isAlreadyConnect = MyUtils.getVpnAlreadyConnect(activity!!)
        Log.e(TAG, "enableMyVpn: isAlreadyConnect $isAlreadyConnect")
        if (!isAlreadyConnect) {
            AppPreferenceVpn(activity).isVpnConnected = false
            if (isHydra) {
//                goForHydra(false)
                loadHydraServers(false, null)
            } else {
                goForOneConnect()
            }
        } else {
            AppPreferenceVpn(activity).isVpnConnected = true
            jumpNextPage()
        }
    }

    private fun goForHydra(onlyLoad: Boolean) {
        MyUtils.initHydraSdk(activity!!)
        MyUtils.CallVpnLogin(activity) { loadHydraServers(onlyLoad, null) }
    }

    private fun loadHydraServers(onlyLoad: Boolean, listener: OnCountryListener?) {
        if (serversHydra.isNullOrEmpty()) {
            UnifiedSDK.getInstance().backend.countries(object : Callback<AvailableCountries> {
                override fun success(countries: AvailableCountries) {
                    serversHydra.clear()
                    serversHydra.addAll(countries.countries)
                    if (listener != null) {
                        listener?.onCountryFound("")
                    } else {
                        if (!onlyLoad) {
                            startVpnService()
                            checkEveryMinuteForHydraStartOrNot()
                        }
                    }
                    Log.e(TAG, "success: ")

                }

                override fun failure(e: VpnException) {
                    Log.e(TAG, "failure: ")
                    if (listener != null) {
                        listener?.onCountryFound("")
                    } else {
                        if (!onlyLoad) {
                            startVpnService()
                            checkEveryMinuteForHydraStartOrNot()
                        }
                    }
                    onFailureCall("hydra server load error  : ${e.gprReason}")
                }
            })
        } else {
            if (!onlyLoad) {
                startVpnService()
                checkEveryMinuteForHydraStartOrNot()
            }
        }

    }

    private fun checkEveryMinuteForHydraStartOrNot() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (ConstVpn.checkVPN(activity)) {
                    if (timer != null) timer!!.cancel()
                    timer = null
                    activity?.runOnUiThread { ConstVpn.LoadAllAds(activity) }
                } else {
                    val intent = VpnService.prepare(activity)
                    if (intent == null) {
                        connectHydra(false)
                    } else {
                        if (timer != null) timer!!.cancel()
                        timer = null
                    }
                }
            }
        }, 1000, 1000)
    }

    private fun startVpnService() {
        if (!MyUtils.getVpnAlreadyConnect(activity!!)) {
            val intent = VpnService.prepare(activity)
            if (intent != null) {
                launcher!!.launch(intent)
            } else {
                connectHydra(true)
            }
        } else {
            ConstVpn.LoadAllAds(activity)
            vpnEventCapture?.onNextPageJump()
        }
    }

    fun connectHydra(jumpPage: Boolean) {
        if (jumpPage) jumpAfterVpnSecond()
        Log.e(TAG, "hydra check to login")
        UnifiedSDK.getInstance().backend.isLoggedIn(object : Callback<Boolean> {
            override fun success(p0: Boolean) {
                if (serversHydra.isNullOrEmpty()) {
                    loadHydraServers(false, object : OnCountryListener {
                        override fun onCountryFound(name: String?) {
                            connectHydraWithServer()
                        }
                    })
                } else {
                    connectHydraWithServer()
                }
            }

            override fun failure(p0: VpnException) {
                Log.e(TAG, "failure: login hydra")
                onFailureCall("vpn login fail ${p0.gprReason}")
            }
        })
    }

    private fun connectHydraWithServer() {
        var randomFromMyServer: ArrayList<String> = getHydraCountryList()
        if (randomFromMyServer.isNotEmpty()) {
            var city = MyUtils.getRandomItem(randomFromMyServer).toString().toLowerCase();
            var allhydracountry: ArrayList<String> = ArrayList()
            for (country in serversHydra) {
                allhydracountry.add(country.country)
            }
            do {
                if (allhydracountry.contains(city)) {
                    selectedCountryhydra = city
                    break
                } else {
                    if (randomFromMyServer.isNotEmpty()) {
                        city = MyUtils.getRandomItem(randomFromMyServer).toLowerCase()
                        if (randomFromMyServer.isNotEmpty()) {
                            randomFromMyServer.remove(city)
                        } else break
                    }
                }
            } while (randomFromMyServer.size > 0)
        }
        if (selectedCountryhydra.isEmpty()) {
            if (serversHydra.size > 0) {
                selectedCountryhydra = MyUtils.getRandomItem(serversHydra).country
            } else {
                selectedCountryhydra = MyUtils.getRandomItem(getHydraCountryList())
            }
        }


        val fallbackOrder: MutableList<String> = ArrayList()
        fallbackOrder.add(HydraTransport.TRANSPORT_ID)
        fallbackOrder.add(CaketubeTransport.TRANSPORT_ID_TCP)
        fallbackOrder.add(CaketubeTransport.TRANSPORT_ID_UDP)
        val bypassDomains: MutableList<String> = LinkedList()
        bypassDomains.add("*facebook.com")
        bypassDomains.add("*wtfismyip.com")
        UnifiedSDK.getInstance().vpn.start(SessionConfig.Builder()
            .withReason(TrackingConstants.GprReasons.M_UI).withTransportFallback(fallbackOrder)
            .withVirtualLocation(selectedCountryhydra).withTransport(HydraTransport.TRANSPORT_ID)
            .addDnsRule(TrafficRule.Builder.bypass().fromDomains(bypassDomains)).build(),
            object : CompletableCallback {
                override fun complete() {
                    Log.e(ConstVpn.HYDRA_VPN_TAG, "complete: ")
//                        startUIUpdateTask()
                    onVpnConnected("connection done")
                }

                override fun error(e: VpnException) {
                    Log.e(TAG, "error:  connectioon faill hydra")
                    onFailureCall("connection fail ${e.gprReason}")
                }
            })
    }


    fun getHydraCountryList(): ArrayList<String> {
        var country_list: List<String> = MyUtils.getHydraVpn().split(",".toRegex())
        var all = kotlin.collections.ArrayList<String>()
        all.addAll(country_list)
        return all
    }

    private fun onVpnConnected(msg: String) {
        vpnEventCapture?.onVpnConnected(msg)
        AppPreferenceVpn(activity).isVpnConnected = true
    }

    private fun onFailureCall(msg: String) {
        vpnEventCapture?.onVpnFail(msg)
        AppPreferenceVpn(activity).isVpnConnected = true
    }

    private fun destroyHydra() {
        try {
            UnifiedSDK.getInstance().vpn.stop(
                TrackingConstants.GprReasons.M_UI,
                object : CompletableCallback {
                    override fun complete() {
//                        stopUIUpdateTask()
                    }

                    override fun error(e: VpnException) {
                    }
                })
        } catch (e: Exception) {
        }
    }

    public fun ondestroy() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
        AppPreferenceVpn(activity).isVpnConnected = false
        AppPreferenceVpn(activity).isForceShow = false
        AppPreferenceVpn(activity).isCountrySame = false
        Log.e("=====", "vpnDisconnect33: ")
        destroyHydra()
        destroyOneConnect()
    }

    private fun jumpAfterVpnSecond() {
        Handler(Looper.myLooper()!!).postDelayed({
            Log.e(TAG, "after vpn second jum page")
            jumpNextPage()
        }, (MyUtils.getVpnSecond() * 1000).toLong())
    }

    private fun jumpNextPage() {
        vpnEventCapture?.onNextPageJump()
    }

    //***************************ONE_CONNECT_VPN***************************//
    private var selectedCountry: Countries? = null
    var PREMIUM_SERVERS: String = ""
    var serversOneConnect = ArrayList<Countries>()
    var STATUS_ONECONNECT: String = "DISCONNECTED"

    fun destroyOneConnect() {
        try {
            if (oneConnectThread != null) oneConnectThread!!.stopProcess()
            OpenVPNThread.stop()
            OneConnectVpnUpDate("DISCONNECTED")
            LocalBroadcastManager.getInstance(activity!!.applicationContext)
                .unregisterReceiver(broadcastReceiver)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun goForOneConnect() {
        LocalBroadcastManager.getInstance(activity!!.applicationContext)
            .registerReceiver(broadcastReceiver, IntentFilter("connectionState"))

        selectedCountry = null
        oneConnectThread = OpenVPNThread()

        val oneConnect = OneConnect()
        oneConnect.initialize(
            activity, PowerPreference.getDefaultFile().getString(Constants.ONE_CONNECT_API_KEY)
        ) // Put Your OneConnect API Key for Work Server

        if (PREMIUM_SERVERS.isNotEmpty() && PREMIUM_SERVERS != "[]") {
            loadServers()
        } else {
            val thread = Thread {
                /* val oneConnect = OneConnect()
                 oneConnect.initialize(
                     activity,
                     PowerPreference.getDefaultFile().getString(Constants.ONE_CONNECT_API_KEY)
                 ) // Put Your OneConnect API Key for Work Server*/
                try {
//                        ConstVpn.FREE_SERVERS = oneConnect.fetch(true)
                    PREMIUM_SERVERS = oneConnect.fetch(false)
                    activity!!.runOnUiThread { loadServers() }
                } catch (e: Exception) {
                    e.printStackTrace()
                    onFailureCall("goforOneconnect 1 : " + e.message)
                    jumpNextPage()
                }

            }
            thread.start()
        }
    }

    private fun loadServers() {
        Log.e(TAG, "PREMIUM_SERVERS: " + PREMIUM_SERVERS)
        ConstVpn.servers = ArrayList()
        try {
            val jsonArray = JSONArray(PREMIUM_SERVERS)
            for (i in 0 until jsonArray.length()) {
                val `object` = jsonArray[i] as JSONObject
                serversOneConnect.add(
                    Countries(
                        `object`.getString("serverName"),
                        `object`.getString("flag_url"),
                        `object`.getString("ovpnConfiguration"),
                        `object`.getString("vpnUserName"),
                        `object`.getString("vpnPassword")
                    )
                )
            }
            var randomFromMyServer: ArrayList<String> = getOneConnectCountryList()
            if (randomFromMyServer.isNotEmpty()) {
                var city = MyUtils.getRandomItem(randomFromMyServer)
                do {
                    var selectedServer = MyUtils.getSelectedCityOneConnect(serversOneConnect, city)
                    if (selectedServer != null) {
                        selectedCountry = selectedServer
                        break
                    } else {
                        if (randomFromMyServer.isNotEmpty()) {
                            city = MyUtils.getRandomItem(randomFromMyServer)
                            if (randomFromMyServer.isNotEmpty()) {
                                randomFromMyServer.remove(city)
                            } else break
                        }
                    }
                } while (randomFromMyServer.size > 0)
            }
            if (selectedCountry == null) {
                selectedCountry = MyUtils.getRandomItem(serversOneConnect)
            }
            Log.e(TAG, "loadServers: " + selectedCountry!!.country)
            prepareVpn()
        } catch (e: JSONException) {
            e.printStackTrace()
            onFailureCall("one connect loadServers json error : " + e.message)
        }
    }

    private fun prepareVpn() {
        if (selectedCountry != null) {
            OneConnectVpnUpDate("LOAD")
            val intent = VpnService.prepare(activity)
            if (intent != null) {
                launcher!!.launch(intent)
            } else {
                connectOneConnect(true)
            }
        } else {
            onFailureCall("one connect country is null")
            ConstVpn.LoadAllAds(activity)
            jumpNextPage()
        }
    }

    public fun connectOneConnect(jumpPage: Boolean) {
        try {
            if (jumpPage) jumpAfterVpnSecond()
            OpenVpnApi.startVpn(
                activity,
                selectedCountry!!.ovpn,
                selectedCountry!!.country,
                selectedCountry!!.ovpnUserName,
                selectedCountry!!.ovpnUserPassword
            )
        } catch (e: RemoteException) {
            onFailureCall("one connect start server error : " + e.message)
            e.printStackTrace()
        }
    }

    fun getOneConnectCountryList(): ArrayList<String> {
        var country_list: List<String> = MyUtils.getOneConnectVpn().split(",".toRegex())
        var all = kotlin.collections.ArrayList<String>()
        all.addAll(country_list)
        return all
    }


    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                OneConnectVpnUpDate(intent.getStringExtra("state")!!)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun OneConnectVpnUpDate(status: String) {
        if (status.equals("DISCONNECTED", ignoreCase = true)) {
            STATUS_ONECONNECT = "DISCONNECTED"
            Log.e(TAG, STATUS_ONECONNECT)
            AppPreferenceVpn(activity).isVpnConnected = true
        } else if (status.equals("CONNECTED", ignoreCase = true)) {
            STATUS_ONECONNECT = "CONNECTED"
            Log.e(TAG, STATUS_ONECONNECT)
            ConstVpn.LoadAllAds(activity)
            /*if (!SplashActivity.isOneTimeMoveToNext) {
                SplashActivity.MoveToNextActivity()
            }*/
        } else if (status.equals("WAIT", ignoreCase = true)) {
            STATUS_ONECONNECT = "WAITING"
            Log.e(TAG, STATUS_ONECONNECT)
            AppPreferenceVpn(activity).isVpnConnected = false
        } else if (status.equals("AUTH", ignoreCase = true)) {
            STATUS_ONECONNECT = "AUTHENTICATION"
            AppPreferenceVpn(activity).isVpnConnected = false
            Log.e(TAG, STATUS_ONECONNECT)
        } else if (status.equals("RECONNECTING", ignoreCase = true)) {
            STATUS_ONECONNECT = "RECONNECTING"
            AppPreferenceVpn(activity).isVpnConnected = false
            Log.e(TAG, STATUS_ONECONNECT)
        } else if (status.equals("NONETWORK", ignoreCase = true)) {
            STATUS_ONECONNECT = "DISCONNECTED"
            AppPreferenceVpn(activity).isVpnConnected = true
            Log.e(TAG, STATUS_ONECONNECT)
        } else if (status.equals("LOAD", ignoreCase = true)) {
            STATUS_ONECONNECT = "LOAD"
            Log.e(TAG, STATUS_ONECONNECT)
            AppPreferenceVpn(activity).isVpnConnected = false
        }
    }
}