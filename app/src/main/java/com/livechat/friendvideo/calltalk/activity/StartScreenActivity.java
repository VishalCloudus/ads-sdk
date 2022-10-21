package com.livechat.friendvideo.calltalk.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livechat.friendvideo.calltalk.R;
import com.livechat.friendvideo.calltalk.extras.AdapterMoreApp;
import com.livechat.friendvideo.calltalk.extras.Constants;
import com.pesonal.adsdk.AppManage;
import com.pesonal.adsdk.AppPreferenceVpn;
import com.pesonal.adsdk.vpn.ConstVpn;
import com.pesonal.adsdk.vpn.Countries;
import com.pesonal.adsdk.vpn.DialogVpn;
import com.pesonal.adsdk.vpn.MyUtils;
import com.pesonal.adsdk.vpn.OnVpnEventCapture;
import com.pesonal.adsdk.vpn.VpnUtilitiys;
import com.preference.PowerPreference;

import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Timer;

import top.oneconnectapi.app.OpenVpnApi;
import top.oneconnectapi.app.core.OpenVPNThread;

public class StartScreenActivity extends AppCompatActivity {

    RecyclerView rview;
    private VpnUtilitiys vpnUtilitiys;
    int vpnPermissionAttempt = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        if (AppManage.Continue_Screen_Native_MoreApp.equalsIgnoreCase("1")) {
            rview = findViewById(R.id.rview);
            rview.setLayoutManager(new GridLayoutManager(this, 3));
            if (AppManage.getInstance(this).get_SPLASHMoreAppData() != null && !AppManage.getInstance(this).get_SPLASHMoreAppData().isEmpty()) {
                rview.setLayoutManager(new GridLayoutManager(this, 3));
                AdapterMoreApp adp = new AdapterMoreApp(this, AppManage.getInstance(this).get_SPLASHMoreAppData());
                rview.setAdapter(adp);
            }
        }

        findViewById(R.id.ivStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVpn();
                /*if (AppManage.Hydra_OneConnect.equalsIgnoreCase("0")
                        && !ConstVpn.checkVPN(StartScreenActivity.this)
                        && !new AppPreferenceVpn(StartScreenActivity.this).getVpnPermission()
                        && AppManage.splash_start_vpn.equalsIgnoreCase("1")) {

                    HydraVpnToConnect();

                } else if (AppManage.Hydra_OneConnect.equalsIgnoreCase("1")
                        && !ConstVpn.checkVPN(StartScreenActivity.this)
                        && !new AppPreferenceVpn(StartScreenActivity.this).getVpnPermission()
                        && AppManage.splash_start_vpn.equalsIgnoreCase("1")) {
                    OneVpnToConnect();
                } else {
                    ConstVpn.LoadAllAds(StartScreenActivity.this);
                    AppManage.getInstance(StartScreenActivity.this).showInterstitialAd(StartScreenActivity.this, false, new AppManage.MyCallback() {
                        @Override
                        public void callbackCall() {
                            MoveToNextActivity();
                        }
                    }, AppManage.app_innerClickCntSwAd);
                }*/

            }
        });

    }


    private void checkVpn() {
        Boolean isOnVpn = MyUtils.getVpnOnOff(this);
        if (!isOnVpn) {
            jumpWithInterestial();
        } else {
            if (MyUtils.getVpnForce() || vpnPermissionAttempt <= 0) {
                vpnUtilitiys = VpnUtilitiys.Companion.getInstance(this, launcher, new OnVpnEventCapture() {
                    @Override
                    public void onNextPageJump() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DialogVpn.isProgressDismiss();
                                jumpWithInterestial();
                            }
                        });

                    }

                    @Override
                    public void onVpnConnected(@NonNull String msg) {
                        Log.e("AAA", "onVpnConnected: ");
                        DialogVpn.isProgressDismiss();
                    }

                    @Override
                    public void onVpnFail(@NonNull String msg) {
                        Log.e("AAA", "onVpnFail: " + msg);

                    }
                });
                DialogVpn.progressDialog(this);
                vpnUtilitiys.enableMyVpn();
            } else {
                jumpWithInterestial();
            }

        }

    }

    private void jumpWithInterestial() {
//        ConstVpn.LoadAllAds(StartScreenActivity.this);
        AppManage.getInstance(StartScreenActivity.this).showInterstitialAd(StartScreenActivity.this, false, new AppManage.MyCallback() {
            @Override
            public void callbackCall() {
                MoveToNextActivity();
            }
        }, AppManage.app_innerClickCntSwAd);
    }

    private void jumpNextPage() {
        MoveToNextActivity();
    }

    private void MoveToNextActivity() {
//        ConstVpn.isVpnAutoConnect = true;
        if (PowerPreference.getDefaultFile().getBoolean(Constants.flagnext)) {
            startActivity(new Intent(StartScreenActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(StartScreenActivity.this, PreferredAgeActivity.class));
        }
    }

    /*==================================== ONE CONNECT VPN CODE====================================*/
    public static String STATUS = "DISCONNECTED";
    public Countries selectedCountry = null;
    public static OpenVPNThread vpnThread = new OpenVPNThread();
    public static Activity activity;
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

    public void OneVpnToConnect() {
        if (ConstVpn.isOnline(StartScreenActivity.this)
                && ConstVpn.servers != null
                && !ConstVpn.servers.isEmpty()
                && !new AppPreferenceVpn(this).getIsCountrySame()
                && AppManage.vpn_flag.equalsIgnoreCase("1")) {

            Random random = new Random();
            int listSize = ConstVpn.servers.size();
            int randomIndex = random.nextInt(listSize);
            selectedCountry = ConstVpn.servers.get(randomIndex);
            Intent intent = VpnService.prepare(StartScreenActivity.this);
            if (intent != null) {
                launcher.launch(intent);
            } else {
                DialogVpn.progressDialog(StartScreenActivity.this);
                startVpn();
            }
        } else {
            DialogVpn.isProgressDismiss();
//            ConstVpn.LoadAllAds(StartScreenActivity.this);
            AppManage.getInstance(StartScreenActivity.this).showInterstitialAd(StartScreenActivity.this, false, new AppManage.MyCallback() {
                @Override
                public void callbackCall() {
                    MoveToNextActivity();
                }
            }, AppManage.app_innerClickCntSwAd);
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
                    DialogVpn.isProgressDismiss();
                    MoveToNextActivity();
                }
            }, Integer.parseInt(AppManage.vpn_sec) * 1000);

            OneConnectVpnUpDate("LOAD");
            OpenVpnApi.startVpn(this, selectedCountry.getOvpn(), selectedCountry.getCountry(), selectedCountry.getOvpnUserName(), selectedCountry.getOvpnUserPassword());
        } catch (RemoteException e) {
            DialogVpn.isProgressDismiss();
//            ConstVpn.LoadAllAds(StartScreenActivity.this);
            MoveToNextActivity();
            e.printStackTrace();
            Log.e(ConstVpn.ONE_VPN_TAG, "startVpn: " + e.getMessage());
        }
    }


    public static void OneConnectVpnDisconnect() {
        try {
            vpnThread.stop();
            OneConnectVpnUpDate("DISCONNECTED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("connectionState"));
    }


    /*================================= END ONE CONNECT VPN CODE =============================*/

    /*====================================VPN CODE====================================*/

    boolean connected = false;
    private AppPreferenceVpn appPreferenceVpn;
    private Timer timer = new Timer();




    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    vpnPermissionAttempt++;
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        new AppPreferenceVpn(StartScreenActivity.this).setVpnPermission(true);
                        if (MyUtils.getVpnHydra()) {
                            DialogVpn.progressDialog(StartScreenActivity.this);
                            vpnUtilitiys.connectHydra(true);
                        } else {
                            vpnUtilitiys.connectOneConnect(true);
                        }
                    } else {
                        DialogVpn.isProgressDismiss();
                        new AppPreferenceVpn(StartScreenActivity.this).setVpnPermission(false);
                        Boolean isForce = MyUtils.getVpnForce();
                        if (!isForce) {
//                            ConstVpn.LoadAllAds(StartScreenActivity.this);
                            if (AppManage.Continue_Screen_Native_MoreApp.equalsIgnoreCase("0")) {
                                AppManage.getInstance(StartScreenActivity.this).showNative1(findViewById(R.id.native_layout), findViewById(R.id.llBg));
                            }
                            AppManage.getInstance(StartScreenActivity.this).showInterstitialAd(StartScreenActivity.this, false, new AppManage.MyCallback() {
                                @Override
                                public void callbackCall() {
                                    DialogVpn.isProgressDismiss();
                                    MoveToNextActivity();
                                }
                            }, AppManage.app_innerClickCntSwAd);
                        }
//                            DialogVpn.isProgressDismiss();
//                        if (AppManage.force_vpn.equals("0")) {
//
//                        }
                        //  Toast.makeText(StartScreenActivity.this, "Permission Deny !! ", Toast.LENGTH_SHORT).show();
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
    }

    public void onConnectBtnClick() {
        DialogVpn.progressDialog(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppManage.getInstance(StartScreenActivity.this).showInterstitialAd(StartScreenActivity.this, false, new AppManage.MyCallback() {
                    @Override
                    public void callbackCall() {
                        DialogVpn.isProgressDismiss();
                        MoveToNextActivity();
                    }
                }, AppManage.app_innerClickCntSwAd);
            }
        }, Integer.parseInt(AppManage.vpn_sec) * 1000);

    }


    protected void showMessage(String msg) {
//        Toast.makeText(ContinueActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    public void ontermsclick(View view) {
        showCustomPopup(0);
    }

    public void onprivacyclick(View view) {
        showCustomPopup(1);
    }

    private void showCustomPopup(int i) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabColorSchemeParams.Builder params = new CustomTabColorSchemeParams.Builder();
        params.setToolbarColor(ContextCompat.getColor(this, R.color.purple_500));
        builder.setDefaultColorSchemeParams(params.build());
        builder.setShowTitle(true);

        // setShareState(CustomTabsIntent.SHARE_STATE_ON) will add a menu to share the web-page
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON);
        builder.setInstantAppsEnabled(true);
        //  To use animations use -
        //  builder.setStartAnimations(this, android.R.anim.start_in_anim, android.R.anim.start_out_anim)
        //  builder.setExitAnimations(this, android.R.anim.exit_in_anim, android.R.anim.exit_out_anim)
        CustomTabsIntent customBuilder = builder.build();
        // if chrome is available use chrome custom tabs
//        customBuilder.intent.setPackage(package_name)
        customBuilder.intent.setPackage("com.android.chrome");
        if (i == 0) {
            customBuilder.launchUrl(this, Uri.parse(PowerPreference.getDefaultFile().getString(Constants.TERMS_OF_SERVICE)));
        }
        if (i == 1) {
            customBuilder.launchUrl(this, Uri.parse(PowerPreference.getDefaultFile().getString(Constants.PRIVACY_POLICY)));
        }
        Log.e("Tiger", "PRIVACY_POLICY: " + PowerPreference.getDefaultFile().getString(Constants.PRIVACY_POLICY));
        Log.e("Tiger", "TERMS_OF_SERVICE: " + PowerPreference.getDefaultFile().getString(Constants.TERMS_OF_SERVICE));
    }

    @Override
    public void onBackPressed() {
        showDialog();
    }

    public void showDialog() {
        Dialog dialogExitApp = new Dialog(this);
        dialogExitApp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogExitApp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogExitApp.setContentView(R.layout.dialog_exit);
        dialogExitApp.setCancelable(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogExitApp.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogExitApp.getWindow().setAttributes(lp);

        LinearLayout nativeLinear = dialogExitApp.findViewById(R.id.native_layout);
        AppManage.getInstance(this).showNative(nativeLinear);

        Button btnNo = dialogExitApp.findViewById(R.id.btnNo);
        Button btnYes = dialogExitApp.findViewById(R.id.btnYes);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogExitApp.dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogExitApp.dismiss();
                finishAffinity();
            }
        });
        if (!isFinishing()) {
            dialogExitApp.show();
        }
    }

    @Override
    protected void onDestroy() {
        VpnUtilitiys.Companion.getInstance(this).ondestroy();
        super.onDestroy();
    }
}