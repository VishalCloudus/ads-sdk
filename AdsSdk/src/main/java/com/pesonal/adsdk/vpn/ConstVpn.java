package com.pesonal.adsdk.vpn;

import static com.pesonal.adsdk.AppManage.ADMOB_N;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

import com.anchorfree.partner.api.ClientInfo;
import com.anchorfree.sdk.HydraTransportConfig;
import com.anchorfree.sdk.NotificationConfig;
import com.anchorfree.sdk.TransportConfig;
import com.anchorfree.sdk.UnifiedSDK;
import com.anchorfree.sdk.UnifiedSDKConfig;
import com.anchorfree.vpnsdk.callbacks.CompletableCallback;
import com.northghost.caketube.OpenVpnTransportConfig;
import com.pesonal.adsdk.AppManage;
import com.pesonal.adsdk.AppPreferenceVpn;
import com.pesonal.adsdk.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConstVpn {
    public static UnifiedSDK unifiedSDK;
    public static final String HYDRA_VPN_TAG = "HYDRA_VPN_TAG";
    public static boolean isVpnLogin = false;
    public static boolean isVpnAutoConnect = false;

    /*OneConnect*/
    public static final String ONE_VPN_TAG = "ONE_VPN_TAG";
    public static String FREE_SERVERS = "";
    public static String PREMIUM_SERVERS = "";
    public static ArrayList<Countries> servers = new ArrayList<>();

    @SuppressLint("MissingPermission")
    public static boolean checkVPN(Context activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getNetworkInfo(ConnectivityManager.TYPE_VPN).isConnectedOrConnecting();
    }

    public static void initHydraSdk(Activity activity) {
        createNotificationChannel(activity);
        SharedPreferences prefs = getPrefs(activity);
        ClientInfo clientInfo = ClientInfo.newBuilder()
                .addUrl(prefs.getString(BuildConfig.STORED_HOST_URL_KEY, AppManage.vpn_base_host))
                .carrierId(prefs.getString(BuildConfig.STORED_CARRIER_ID_KEY, AppManage.vpn_base_carrier_id))
                .build();
        List<TransportConfig> transportConfigList = new ArrayList<>();
        transportConfigList.add(HydraTransportConfig.create());
        transportConfigList.add(OpenVpnTransportConfig.tcp());
        transportConfigList.add(OpenVpnTransportConfig.udp());
        UnifiedSDK.update(transportConfigList, CompletableCallback.EMPTY);
        UnifiedSDKConfig config = UnifiedSDKConfig.newBuilder().idfaEnabled(false).build();
        unifiedSDK = UnifiedSDK.getInstance(clientInfo, config);
        NotificationConfig notificationConfig = NotificationConfig.newBuilder()
                .title(activity.getResources().getString(R.string.app_name))
                .channelId(activity.getPackageName())
                .build();
        UnifiedSDK.update(notificationConfig);
        UnifiedSDK.setLoggingLevel(Log.VERBOSE);
    }

    public static SharedPreferences getPrefs(Activity activity) {
        return activity.getSharedPreferences(BuildConfig.SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public static void createNotificationChannel(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = activity.getResources().getString(R.string.app_name) + "";
            String description = activity.getResources().getString(R.string.app_name) + "notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(activity.getPackageName(), name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = activity.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void CallVpnLogin(Activity activity) {
        /*if (AppManage.vpn_flag.equalsIgnoreCase("1")) {
            AuthMethod authMethod = AuthMethod.anonymous();
            UnifiedSDK.getInstance().getBackend().login(authMethod, new Callback<User>() {
                @Override
                public void success(@NonNull User user) {
                    isVpnLogin = true;
                    Log.e(ConstVpn.HYDRA_VPN_TAG, "loginToVpn: success");
                }

                @Override
                public void failure(@NonNull VpnException e) {
                    isVpnLogin = false;
                    Log.e(ConstVpn.HYDRA_VPN_TAG, "loginToVpn: failure");
                    LoadAllAds(activity);
                }
            });
        }*/
    }


    public static void LoadAllAds(Activity activity) {
        new AppPreferenceVpn(activity).setIsVpnConnected(true);
        AppManage.getInstance(activity).loadInterstitialAd(activity);
        AppManage.getInstance(activity).preloadAdmobNativeBanner(ADMOB_N[0]);
        if (AppManage.custome_banner.equalsIgnoreCase("0")) {
            AppManage.getInstance(activity).preLoadRactengal();
        } else {
            AppManage.getInstance(activity).preloadAdmobNative(ADMOB_N[0]);
        }

    }

    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            return nInfo != null && nInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getRandomOneConnectCountry() {
        String VpnSelectCountry = "";
        if (AppManage.ListOneConnectCountry != null && !AppManage.ListOneConnectCountry.isEmpty()) {
            Random random = new Random();
            int listSize = AppManage.ListOneConnectCountry.size();
            int randomIndex = random.nextInt(listSize);
            VpnSelectCountry = AppManage.ListOneConnectCountry.get(randomIndex);
            Log.e(ONE_VPN_TAG, "getRandomOneConnectCountry: " + VpnSelectCountry);
            return VpnSelectCountry;
        } else {
            return "";
        }
    }


}
