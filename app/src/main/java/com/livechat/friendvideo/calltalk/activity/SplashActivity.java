package com.livechat.friendvideo.calltalk.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.anchorfree.sdk.UnifiedSDK;
import com.anchorfree.vpnsdk.callbacks.Callback;
import com.anchorfree.vpnsdk.exceptions.VpnException;
import com.anchorfree.vpnsdk.vpnservice.VPNState;
import com.livechat.friendvideo.calltalk.R;
import com.livechat.friendvideo.calltalk.VPN.ConstVpn;
import com.livechat.friendvideo.calltalk.VPN.Countries;
import com.livechat.friendvideo.calltalk.VPN.VpnBaseActivity;
import com.livechat.friendvideo.calltalk.VPN.VpnMainActivity;
import com.livechat.friendvideo.calltalk.VPN.VpnSplashActivity;
import com.livechat.friendvideo.calltalk.extras.Constants;
import com.pesonal.adsdk.ADS_SplashActivity;
import com.pesonal.adsdk.AppManage;
import com.pesonal.adsdk.AppPreferenceVpn;
import com.pesonal.adsdk.OnSplashAdCallBack;
import com.pesonal.adsdk.getDataListner;
import com.preference.PowerPreference;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import top.oneconnectapi.app.OpenVpnApi;
import top.oneconnectapi.app.api.OneConnect;
import top.oneconnectapi.app.core.OpenVPNThread;

public class SplashActivity extends VpnBaseActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        activity = this;
        setContentView(R.layout.activity_splash);
        if (isInterNetAvailable(this, true)) {
            CallSplashData();
        }
    }

    public static void MoveToNextActivity() {
        isOneTimeMoveToNext = true;

        PowerPreference.getDefaultFile()
                .setString(Constants.AppRtcWebOnline, AppManage.WebGenerateRoom);
        PowerPreference.getDefaultFile()
                .setString(Constants.AppRtcWebOffline, AppManage.WebDeleteRoom);
        PowerPreference.getDefaultFile()
                .setString(Constants.FLAG_NATIVE_SCROLL, AppManage.native_scroll_show);
        PowerPreference.getDefaultFile()
                .setString(Constants.MAIN_DATA_URL, AppManage.main_data_url);
        PowerPreference.getDefaultFile()
                .setString(Constants.REPORT_USER, AppManage.ReportUser);
        PowerPreference.getDefaultFile()
                .setString(Constants.DELETE_ROOM, AppManage.DeleteRoom);
        PowerPreference.getDefaultFile()
                .setString(Constants.GENERATE_ROOM, AppManage.GenerateRoom);
        PowerPreference.getDefaultFile()
                .setString(Constants.APP_RTC_URL, AppManage.app_rtc_url);
        PowerPreference.getDefaultFile()
                .setString(Constants.PRIVACY_POLICY, AppManage.app_privacyPolicyLink);
        PowerPreference.getDefaultFile()
                .setString(Constants.TERMS_OF_SERVICE, AppManage.app_termsServieLink);

        if (AppManage.app_adShowStatus == 0) {
            PowerPreference.getDefaultFile().setBoolean(Constants.ADS_ONOFF, false);
        } else {
            PowerPreference.getDefaultFile().setBoolean(Constants.ADS_ONOFF, true);
        }

        if (AppManage.Hydra_OneConnect.equalsIgnoreCase("0") && AppManage.vpn_flag.equalsIgnoreCase("1") && AppManage.splash_start_vpn != null && AppManage.splash_start_vpn.equalsIgnoreCase("0")) {
            ConstVpn.isVpnAutoConnect = true;
        }

        ADS_SplashActivity.showSplashAdMain(activity, new OnSplashAdCallBack() {
            @Override
            public void onAdCallBackSplash() {
                activity.startActivity(new Intent(activity, VpnMainActivity.class));
            }
        });
    }

    private void CallSplashData() {
        ADS_SplashActivity.ADSinit(SplashActivity.this, getCurrentVersionCode(), new getDataListner() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ADS_SplashActivity.splashopen = true;
                        if (AppManage.Hydra_OneConnect.equalsIgnoreCase("0")) {
                            ConstVpn.initHydraSdk(SplashActivity.this);
                            ConstVpn.CallVpnLogin(SplashActivity.this);
                            if (AppManage.vpn_flag.equalsIgnoreCase("1")
                                    && AppManage.splash_start_vpn.equalsIgnoreCase("0")) {
                                initHydraVpn();
                            } else {
                                if (AppManage.vpn_flag.equalsIgnoreCase("1")
                                        && new AppPreferenceVpn(SplashActivity.this).getVpnPermission()) {
                                    initHydraVpn();
                                } else {
//                                    ConstVpn.LoadAllAds(SplashActivity.this);
                                    MoveToNextActivity();
                                }
                            }
                        } else if (AppManage.Hydra_OneConnect.equalsIgnoreCase("1")
                                && AppManage.vpn_flag.equalsIgnoreCase("1")
                                && !ConstVpn.checkVPN(SplashActivity.this)) {
                            initOneConnectVpn();
                        } else {
//                            ConstVpn.LoadAllAds(SplashActivity.this);
                            MoveToNextActivity();
                        }
                    }
                });

            }

            @Override
            public void onUpdate(String url) {
                Log.e("my_log", "onUpdate: " + url);
                showUpdateDialog(url);
            }

            @Override
            public void onRedirect(String url) {
                Log.e("my_log", "onRedirect: " + url);
                showRedirectDialog(url);
            }

            @Override
            public void onReload() {
                startActivity(new Intent(SplashActivity.this, SplashActivity.class));
                finish();
            }

            @Override
            public void onGetExtradata(JSONObject extraData) {
                Log.e("my_log", "ongetExtradata: " + extraData.toString());
            }
        });
    }

    public void showRedirectDialog(final String url) {
        final Dialog dialog = new Dialog(SplashActivity.this);
        dialog.setCancelable(false);
        View view = getLayoutInflater().inflate(R.layout.installnewappdialog, null);
        dialog.setContentView(view);
        TextView update = view.findViewById(R.id.update);
        TextView txt_title = view.findViewById(R.id.txt_title);
        TextView txt_decription = view.findViewById(R.id.txt_decription);

        update.setText("Install Now");
        txt_title.setText("Install our new app now and enjoy");
        txt_decription.setText("We have transferred our server, so install our new app by clicking the button below to enjoy the new features of app.");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri marketUri = Uri.parse(url);
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                    startActivity(marketIntent);
                } catch (ActivityNotFoundException ignored1) {
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public void showUpdateDialog(final String url) {

        final Dialog dialog = new Dialog(SplashActivity.this);
        dialog.setCancelable(false);
        View view = getLayoutInflater().inflate(R.layout.installnewappdialog, null);
        dialog.setContentView(view);
        TextView update = view.findViewById(R.id.update);
        TextView txt_title = view.findViewById(R.id.txt_title);
        TextView txt_decription = view.findViewById(R.id.txt_decription);

        update.setText("Update Now");
        txt_title.setText("Update our new app now and enjoy");
        txt_decription.setText("");
        txt_decription.setVisibility(View.GONE);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri marketUri = Uri.parse(url);
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                    startActivity(marketIntent);
                } catch (ActivityNotFoundException ignored1) {
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

    }

    public int getCurrentVersionCode() {
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    getPackageName(), 0);
            return info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static boolean isInterNetAvailable(final Activity context, boolean b) {
        boolean isInterNAvailable = false;
        if (context != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                boolean mobileNetwork = false;
                boolean wifiNetwork = false;

                boolean mobileNetworkConnecetd = false;
                boolean wifiNetworkConnecetd = false;

                final NetworkInfo mobileNetworkInfo = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                final NetworkInfo wifiNetworkInfo = connectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (mobileNetworkInfo != null) {
                    mobileNetwork = mobileNetworkInfo.isAvailable();
                }

                if (wifiNetworkInfo != null) {
                    wifiNetwork = wifiNetworkInfo.isAvailable();
                }

                if (wifiNetwork || mobileNetwork) {
                    if (mobileNetworkInfo != null)
                        mobileNetworkConnecetd = mobileNetworkInfo
                                .isConnectedOrConnecting();
                    wifiNetworkConnecetd = wifiNetworkInfo.isConnectedOrConnecting();
                }

                isInterNAvailable = (mobileNetworkConnecetd || wifiNetworkConnecetd);
            }

            if (!isInterNAvailable && b) {
                if (context instanceof Activity) {
                    AlertDialog(context, context.getString(R.string.app_name), "No internet connection");
                }
            }
        }
        return isInterNAvailable;
    }

    public static void AlertDialog(Activity activity, String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg)
                .setCancelable(false)
                .setTitle(title)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        activity.finishAffinity();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*==================================== ONE CONNECT VPN CODE====================================*/
    public static String STATUS = "DISCONNECTED";
    public Countries selectedCountry = null;
    public static OpenVPNThread vpnThread = new OpenVPNThread();
    public static Activity activity;
    public static boolean isOneTimeMoveToNext = false;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                OneConnectVpnUpDate(intent.getStringExtra("state"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String duration = intent.getStringExtra("duration");
                String lastPacketReceive = intent.getStringExtra("lastPacketReceive");
                String byteIn = intent.getStringExtra("byteIn");
                String byteOut = intent.getStringExtra("byteOut");

                if (duration == null) duration = "00:00:00";
                if (lastPacketReceive == null) lastPacketReceive = "0";
                if (byteIn == null) byteIn = " ";
                if (byteOut == null) byteOut = " ";

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };

    private void initOneConnectVpn() {
        if (!new AppPreferenceVpn(this).getIsCountrySame()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OneConnect oneConnect = new OneConnect();
                        oneConnect.initialize(SplashActivity.this, AppManage.One_Onnect_API_Key); // Put Your OneConnect API Key for Work Server
                        try {
                            ConstVpn.FREE_SERVERS = oneConnect.fetch(true);
                            ConstVpn.PREMIUM_SERVERS = oneConnect.fetch(false);
                            loadServers();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } else {
            MoveToNextActivity();
        }

    }

    public void prepareVpn() {
        if (ConstVpn.isOnline(activity) && selectedCountry != null) {
            OneConnectVpnUpDate("LOAD");
            Intent intent = VpnService.prepare(SplashActivity.this);
            if (intent != null) {
                launcher.launch(intent);
            } else {
                startVpn();
            }
        } else {
//            ConstVpn.LoadAllAds(SplashActivity.this);
            MoveToNextActivity();
        }
    }

    public static void OneConnectVpnUpDate(@NotNull String status) {

        if (status.equalsIgnoreCase("DISCONNECTED")) {
            STATUS = "DISCONNECTED";
            Log.e(ConstVpn.ONE_VPN_TAG, STATUS);
        } else if (status.equalsIgnoreCase("CONNECTED")) {
            STATUS = "CONNECTED";
            Log.e(ConstVpn.ONE_VPN_TAG, STATUS);
//            ConstVpn.LoadAllAds(activity);
            if (!isOneTimeMoveToNext) {
                MoveToNextActivity();
            }
        } else if (status.equalsIgnoreCase("WAIT")) {
            STATUS = "WAITING";
            Log.e(ConstVpn.ONE_VPN_TAG, STATUS);

        } else if (status.equalsIgnoreCase("AUTH")) {
            STATUS = "AUTHENTICATION";
            Log.e(ConstVpn.ONE_VPN_TAG, STATUS);

        } else if (status.equalsIgnoreCase("RECONNECTING")) {
            STATUS = "RECONNECTING";
            Log.e(ConstVpn.ONE_VPN_TAG, STATUS);

        } else if (status.equalsIgnoreCase("NONETWORK")) {
            STATUS = "DISCONNECTED";
            Log.e(ConstVpn.ONE_VPN_TAG, STATUS);

        } else if (status.equalsIgnoreCase("LOAD")) {
            STATUS = "LOAD";
            Log.e(ConstVpn.ONE_VPN_TAG, STATUS);
        }
    }

    public void startVpn() {
        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e(ConstVpn.ONE_VPN_TAG, "startVpn: Move To Next");
                    MoveToNextActivity();
                }
            }, Integer.parseInt(AppManage.vpn_sec) * 1000);

            OpenVpnApi.startVpn(this, selectedCountry.getOvpn(), selectedCountry.getCountry(), selectedCountry.getOvpnUserName(), selectedCountry.getOvpnUserPassword());
//            OpenVpnApi.startVpn(this, selectedCountry.getOvpn(), selectedCountry.getCountry(), selectedCountry.getOvpnUserName(), selectedCountry.getOvpnUserPassword());
        } catch (RemoteException e) {
            Log.e(ConstVpn.ONE_VPN_TAG, "startVpn: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void loadServers() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(ConstVpn.ONE_VPN_TAG, "PREMIUM_SERVERS: " + ConstVpn.PREMIUM_SERVERS);
                ConstVpn.servers = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(ConstVpn.PREMIUM_SERVERS);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = (JSONObject) jsonArray.get(i);
                        if (object.getString("serverName").contains(ConstVpn.getRandomOneConnectCountry())) {
                            Log.e(ConstVpn.ONE_VPN_TAG, "Selected Country: " + object.getString("serverName"));
                            ConstVpn.servers.clear();
                            ConstVpn.servers.add(new Countries(object.getString("serverName"),
                                    object.getString("flag_url"),
                                    object.getString("ovpnConfiguration"),
                                    object.getString("vpnUserName"),
                                    object.getString("vpnPassword")
                            ));
                            break;
                        } else {
                            ConstVpn.servers.add(new Countries(object.getString("serverName"),
                                    object.getString("flag_url"),
                                    object.getString("ovpnConfiguration"),
                                    object.getString("vpnUserName"),
                                    object.getString("vpnPassword")
                            ));
                        }
                    }
                    Log.e(ConstVpn.ONE_VPN_TAG, "loadServers: " + ConstVpn.servers.size());
                    Random random = new Random();
                    int listSize = ConstVpn.servers.size();
                    int randomIndex = random.nextInt(listSize);
                    selectedCountry = ConstVpn.servers.get(randomIndex);

                    if (AppManage.splash_start_vpn.equalsIgnoreCase("0")
                            && !ConstVpn.checkVPN(SplashActivity.this)) {
                        prepareVpn();
                    } else {
                        if (!ConstVpn.checkVPN(SplashActivity.this)
                                && new AppPreferenceVpn(SplashActivity.this).getVpnPermission()
                                && AppManage.vpn_flag.equalsIgnoreCase("1")) {
                            prepareVpn();
                        } else {
                            MoveToNextActivity();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("connectionState"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*================================= END ONE CONNECT VPN CODE =============================*/

    /*==================================== HYDRA VPN CODE====================================*/

    boolean connected = false;
    private Timer timer = new Timer();
    private Handler mUIHandler = new Handler(Looper.getMainLooper());

    final Runnable mUIUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            updateUI();
            checkRemainingTraffic();
            mUIHandler.postDelayed(mUIUpdateRunnable, 10000);
        }
    };


    private void initHydraVpn() {
        if (AppManage.vpn_flag.equalsIgnoreCase("1") && !ConstVpn.checkVPN(SplashActivity.this)) {
            if (!new AppPreferenceVpn(this).getIsCountrySame()) {
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (ConstVpn.checkVPN(SplashActivity.this)) {
                                    if (timer != null)
                                        timer.cancel();
                                    timer = null;
//                                    ConstVpn.LoadAllAds(SplashActivity.this);
                                    Log.e("Tiger", "Ads Showing");
                                } else {
                                    if (ConstVpn.isVpnLogin && VpnService.prepare(SplashActivity.this) == null && ConstVpn.isVpnAutoConnect) {
                                        isConnected(new Callback<Boolean>() {
                                            @Override
                                            public void success(@NonNull Boolean aBoolean) {
                                                if (aBoolean) {
                                                    disconnectFromVnp();
                                                } else {
                                                    connectToVpn();
                                                }
                                            }

                                            @Override
                                            public void failure(@NonNull VpnException e) {
                                                Log.e(ConstVpn.HYDRA_VPN_TAG, "onConnectBtnClick failure: " + e.getMessage());
//                                                ConstVpn.LoadAllAds(SplashActivity.this);
                                            }
                                        });

                                    }
                                }
                            }
                        });
                    }
                }, 1000, 1000);
                StartVpnToConnect();
            } else {
//                ConstVpn.LoadAllAds(SplashActivity.this);
                MoveToNextActivity();
            }
        } else {
//            ConstVpn.LoadAllAds(SplashActivity.this);
            MoveToNextActivity();
        }
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        new AppPreferenceVpn(SplashActivity.this).setVpnPermission(true);
                        if (AppManage.Hydra_OneConnect.equalsIgnoreCase("1")) {
                            startVpn();
                        } else {
                            onConnectBtnClick();
                        }
                    } else {
                        new AppPreferenceVpn(SplashActivity.this).setVpnPermission(false);
//                        ConstVpn.LoadAllAds(SplashActivity.this);
                        MoveToNextActivity();
//                        Toast.makeText(SplashActivity.this, "Permission Deny !! ", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUIUpdateTask();
    }

    public void onConnectBtnClick() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(ConstVpn.HYDRA_VPN_TAG, "onConnectBtnClick: Move To Next");
                MoveToNextActivity();
            }
        }, Integer.parseInt(AppManage.vpn_sec) * 1000);

        isConnected(new Callback<Boolean>() {
            @Override
            public void success(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    disconnectFromVnp();
                } else {
                    connectToVpn();
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Log.e("Tiger", "failure: " + e.getMessage());
//                ConstVpn.LoadAllAds(SplashActivity.this);
            }
        });
    }

    protected void startUIUpdateTask() {
        stopUIUpdateTask();
        mUIHandler.post(mUIUpdateRunnable);
    }

    protected void stopUIUpdateTask() {
        mUIHandler.removeCallbacks(mUIUpdateRunnable);
        updateUI();
    }


    protected void updateUI() {
        UnifiedSDK.getVpnState(new Callback<VPNState>() {
            @Override
            public void success(@NonNull VPNState vpnState) {
                switch (vpnState) {
                    case IDLE: {
                        Log.e(ConstVpn.HYDRA_VPN_TAG, "success: IDLE");
                        if (connected) {
                            connected = false;
                        }
                        break;
                    }
                    case CONNECTED: {
                        Log.e(ConstVpn.HYDRA_VPN_TAG, "success: CONNECTED");
                        if (!connected) {
                            connected = true;
                        }
                        break;
                    }
                    case CONNECTING_VPN:
                    case CONNECTING_CREDENTIALS:
                    case CONNECTING_PERMISSIONS: {
                        break;
                    }
                    case PAUSED: {
                        Log.e(ConstVpn.HYDRA_VPN_TAG, "success: PAUSED");
                        break;
                    }
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {

            }
        });
        getCurrentServer(new Callback<String>() {
            @Override
            public void success(@NonNull final String currentServer) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void failure(@NonNull VpnException e) {

            }
        });
    }


    protected void showMessage(String msg) {
//        Toast.makeText(ContinueActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isLoggedIn(Callback<Boolean> callback) {

    }

    @Override
    public void loginToVpn() {

    }

    @Override
    public void isConnected(Callback<Boolean> callback) {

    }

    @Override
    public void connectToVpn() {

    }

    @Override
    public void disconnectFromVnp() {

    }

    @Override
    public void chooseServer() {

    }

    @Override
    public void getCurrentServer(Callback<String> callback) {

    }

    @Override
    public void checkRemainingTraffic() {

    }

    public void StartVpnToConnect() {
        if (!ConstVpn.checkVPN(SplashActivity.this)) {
            Intent intent = VpnService.prepare(SplashActivity.this);
            if (intent != null) {
                launcher.launch(intent);
            } else {
                onConnectBtnClick();
            }
        } else {
//            ConstVpn.LoadAllAds(SplashActivity.this);
            MoveToNextActivity();
        }
    }
    /*====================================END HYDRA VPN CODE====================================*/

}
