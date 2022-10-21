package com.livechat.friendvideo.calltalk.VPN;

/*

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.anchorfree.partner.api.auth.AuthMethod;
import com.anchorfree.partner.api.data.Country;
import com.anchorfree.partner.api.response.RemainingTraffic;
import com.anchorfree.partner.api.response.User;
import com.anchorfree.reporting.TrackingConstants;
import com.anchorfree.sdk.SessionConfig;
import com.anchorfree.sdk.SessionInfo;
import com.anchorfree.sdk.UnifiedSDK;
import com.anchorfree.sdk.exceptions.PartnerApiException;
import com.anchorfree.sdk.rules.TrafficRule;
import com.anchorfree.vpnsdk.callbacks.Callback;
import com.anchorfree.vpnsdk.callbacks.CompletableCallback;
import com.anchorfree.vpnsdk.callbacks.TrafficListener;
import com.anchorfree.vpnsdk.callbacks.VpnStateListener;
import com.anchorfree.vpnsdk.compat.CredentialsCompat;
import com.anchorfree.vpnsdk.exceptions.NetworkRelatedException;
import com.anchorfree.vpnsdk.exceptions.VpnException;
import com.anchorfree.vpnsdk.exceptions.VpnPermissionDeniedException;
import com.anchorfree.vpnsdk.exceptions.VpnPermissionRevokedException;
import com.anchorfree.vpnsdk.transporthydra.HydraTransport;
import com.anchorfree.vpnsdk.transporthydra.HydraVpnTransportException;
import com.anchorfree.vpnsdk.vpnservice.VPNState;
import com.google.gson.Gson;
import com.livechat.friendvideo.calltalk.activity.SplashActivity;
import com.northghost.caketube.CaketubeTransport;
import com.pesonal.adsdk.AppManage;
import com.pesonal.adsdk.AppPreferenceVpn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class VpnSplashActivity extends AppCompatActivity {
    private String vpnselectcountry = "";
    private Timer timer = new Timer();
    */
/*Get Country List*//*

    private String countryName = "de,no,hk,ru,ch,bg,jp,dk,mx,it,ua,fr,es,br,se,au,sg,cz,gb,ie,ro,ca,tr,nl,us";
    private String countryServer = "38,14,24,8,10,6,16,12,2,10,8,30,12,14,12,12,27,8,26,8,10,140,6,28,18";
    ArrayList<String> country_list;
    boolean value;
    boolean value1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppManage.connect_vpn != null && !AppManage.connect_vpn.isEmpty()) {
            vpnselectcountry = AppManage.connect_vpn;
            Log.e("====", "connect_vpn: " + AppManage.connect_vpn);
        } else {
            vpnselectcountry = "sg,de,no,ru,hk,ch,bg,jp,dk,mx,it,fr,ua,es,br,gb";
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }




    private <T> T getRandomItem(List<T> list) {
        Random random = new Random();
        int listSize = list.size();
        int randomIndex = random.nextInt(listSize);
        Log.e("VPN_MainActivity", "onCreate2: random Index : " + randomIndex);
        vpnselectcountry = country_list.get(randomIndex);
        Log.e("VPN_MainActivity", "onCreate2: random Country : " + vpnselectcountry);
        return list.get(randomIndex);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3000) {
            if (resultCode == RESULT_OK) {
                Gson gson = new Gson();
                Bundle args = data.getBundleExtra("Bundle");
                CountryData item = gson.fromJson(args.getString("Country_data"), CountryData.class);
            }
        }
    }

}*/
