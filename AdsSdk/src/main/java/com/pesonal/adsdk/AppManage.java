package com.pesonal.adsdk;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.number.Precision;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anythink.banner.api.ATBannerExListener;
import com.anythink.banner.api.ATBannerView;
import com.anythink.core.api.ATAdConst;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATNetworkConfirmInfo;
import com.anythink.core.api.ATSDK;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialExListener;
import com.anythink.nativead.api.ATNative;
import com.anythink.nativead.api.ATNativeAdView;
import com.anythink.nativead.api.ATNativeDislikeListener;
import com.anythink.nativead.api.ATNativeEventExListener;
import com.anythink.nativead.api.ATNativeNetworkListener;
import com.anythink.nativead.banner.api.ATNativeBannerConfig;
import com.anythink.nativead.banner.api.ATNativeBannerListener;
import com.anythink.nativead.banner.api.ATNativeBannerSize;
import com.anythink.nativead.banner.api.ATNativeBannerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.ads.AbstractAdListener;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;


public class AppManage {
    public static AppPreferenceVpn appPreferenceVpn;

    public static String ADMOB = "Admob";
    public static String FACEBOOK = "Facebookaudiencenetwork";
    public static String MyCustomAds = "MyCustomAds";
    public static String TopOn = "TopOn";

    public static int count_banner = -1;
    public static int count_native = -1;
    public static int count_click = -1;
    public static int count_click_for_alt = -1;
    public static int count_back_click = -1;
    public boolean click_count_flag = true;

    /*VPN*/
    public static String custome_banner = "";
    public static String connect_vpn = "";
    public static String vpn_sec = "";
    public static String vpn_flag = "";
    public static String country_list = "";
    public static String vpn_base_host = "";
    public static String vpn_base_carrier_id = "";
    public static String vpn_star_ads = "";
    public static String force_vpn = "";
    public static String splash_start_vpn = "";

    public static String Hydra_OneConnect = "";
    public static String One_Onnect_connect_vpn = "";
    public static String One_Onnect_API_Key = "";
    public static ArrayList<String> ListOneConnectCountry;
    /**/

    public static String Continue_Screen_Native_MoreApp = "";
    public static String banner_on_off = "";

    public static String app_termsServieLink = "";
    public static String app_privacyPolicyLink = "";


    public static String native_scroll_show = "";
    public static String open_ad_interstitial_Specific_flag = "";
    public static String main_data_url = "";
    public static String ReportUser = "";
    public static String app_rtc_url = "";
    public static String GenerateRoom = "";
    public static String DeleteRoom = "";
    public static String question_answer = "";
    public static String Connecting_Repeat = "";
    public static String WebViewUrl = "";
    public static String WebGenerateRoom = "";
    public static String CallConnectKey = "";
    public static String CallConnectKey1 = "";
    public static String CallConnectSecond = "";
    public static String WebDeleteRoom = "";
    public static String WebConnectingSecond = "";


    public static String app_accountLink = "";
    public static int app_updateAppDialogStatus = 0;
    public static int app_dialogBeforeAdShow = 0;
    public static int app_redirectOtherAppStatus = 0;
    public static int app_adShowStatus = 1;
    public static int app_mainClickCntSwAd = 0;
    public static int app_innerClickCntSwAd = 0;


    public static String ADMOB_APPID = "";
    public static String[] ADMOB_B = {"", "", "", "", ""};
    public static String[] ADMOB_I = {"", "", "", "", ""};
    public static String[] ADMOB_N = {"", "", "", "", ""};
    public static String[] ADMOB_AppOpen = {"", "", "", "", ""};
    public static String ADMOB_RewardedAd = "";


    public static String[] FACEBOOK_I = {"", "", "", "", ""};
    public static String[] FACEBOOK_B = {"", "", "", "", ""};
    public static String[] FACEBOOK_NB = {"", "", "", "", ""};
    public static String[] FACEBOOK_N = {"", "", "", "", ""};

    public static String[] TopOn_I = {"", "", "", "", ""};
    public static String[] TopOn_B = {"", "", "", "", ""};
    public static String[] TopOn_NB = {"", "", "", "", ""};
    public static String[] TopOn_N = {"", "", "", "", ""};

    public static int admob_AdStatus = 0;
    public static int facebook_AdStatus = 0;
    public static int myCustom_AdStatus = 0;
    public static int topon_AdStatus = 0;

    public static int admob_loadAdIdsType = 0;
    public static int facebook_loadAdIdsType = 0;
    public static int topon_loadAdIdsType = 0;
    public static SharedPreferences mysharedpreferences;
    public static int ad_dialog_time_in_second = 2;
    public static int ad_dialog_time_in_second_loadAndShow = 5;
    static Activity activity;
    static MyCallback myCallback;
    static MyCallback myCallbackPress;
    private static AppManage mInstance;

    public String state_admobNative = "Start";
    public String state_fbNative = "Start";
    public String state_admobBanner = "Start";
    public String state_fbinterestial = "Start";
    public String state_admobinterestial = "Start";
    public String state_toponinterestial = "Start";
    public String state_fbBanner = "Start";
    public String state_toponBanner = "Start";
    public String state_fbNativeBanner = "Start";
    public String state_toponNativeBanner = "Start";
    NativeAd admobNativeAd_preLoad = null;
    AdView admobBannerAd_preLoad = null;
    AdView admobRectAd_preLoad = null;
    com.facebook.ads.NativeAd fbNativeAd_preLoad = null;
    com.facebook.ads.AdView fbBannerAd_preLoad = null;
    NativeBannerAd fbNativeBannerAd_preLoad = null;

    private AppOpenManager appopenManager;
    private InterstitialAd mInterstitialAd;
    private String appopen_id_pre = "";
    private String google_i_pre = "", facebook_i_pre = "", topon_i_pre = "";
    String admob_b, facebook_nb, facebook_b, topon_b, topon_nb;
    String admob_n, facebook_n, topon_n;
    ArrayList<String> banner_sequence = new ArrayList<>();
    ArrayList<String> native_sequence = new ArrayList<>();
    ArrayList<String> interstitial_sequence = new ArrayList<>();
    private com.facebook.ads.InterstitialAd fbinterstitialAd1;
    private Dialog dialog;


    public static List<CustomAdModel> myAppMarketingList = new ArrayList<>();
    public static int count_custBannerAd = 0;
    public static int count_custNBAd = 0;
    public static int count_custNativeAd = 0;
    public static int count_custIntAd = 0;
    public static int count_custAppOpenAd = 0;

    //Topon
    public static String topon_app_id = "";
    public static String topon_app_key = "";
    //Interstitial
    ATInterstitial mtoponInterstitialAd;
    //Banner
    ATBannerView toponBannerAd_preLoad;
    //Native
    ATNativeAdView anyThinkNativeAdView;
    ATNative atNatives;
    com.anythink.nativead.api.NativeAd mNativeAd;
    ImageView mCloseView;
    //Admob small Native
    private static boolean admobsmallnativeloaded = false;
    private static NativeAd googlesmallNativeAd;
    public static String myDeviceCountryCode = "";

    public AppManage(Activity activity) {
        appPreferenceVpn = new AppPreferenceVpn(activity);
        AppManage.activity = activity;
        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
        getResponseFromPref();

    }

    public static AppManage getInstance(Activity activity) {
        AppManage.activity = activity;
        if (mInstance == null) {
            mInstance = new AppManage(activity);
        }
        return mInstance;
    }

    public static boolean hasActiveInternetConnection(Context context) {
        @SuppressLint("WrongConstant") NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void getResponseFromPref() {
        String response1 = mysharedpreferences.getString("response", "");

        SharedPreferences.Editor editor_count = mysharedpreferences.edit();
        editor_count.putInt("count_admob_B", 0);
        editor_count.putInt("count_admob_I", 0);
        editor_count.putInt("count_admob_N", 0);
        editor_count.putInt("count_admob_AO", 0);

        editor_count.putInt("count_facebook_B", 0);
        editor_count.putInt("count_facebook_NB", 0);
        editor_count.putInt("count_facebook_I", 0);
        editor_count.putInt("count_facebook_N", 0);

        editor_count.putInt("count_topon_B", 0);
        editor_count.putInt("count_topon_NB", 0);
        editor_count.putInt("count_topon_I", 0);
        editor_count.putInt("count_topon_N", 0);
        editor_count.commit();

        if (!response1.isEmpty()) {
            try {
                JSONObject response = new JSONObject(response1);
                JSONObject settingsJsonObject = response.getJSONObject("APP_SETTINGS");
//

                updateInVariable();

                SharedPreferences.Editor editor = mysharedpreferences.edit();


                editor.putString("app_name", settingsJsonObject.getString("app_name"));
                editor.putString("app_logo", settingsJsonObject.getString("app_logo"));


                editor.putString("app_privacyPolicyLink", app_privacyPolicyLink);
                editor.putString("app_termsServieLink", app_termsServieLink);
                editor.putInt("app_updateAppDialogStatus", app_updateAppDialogStatus);
                editor.putString("app_versionCode", settingsJsonObject.getString("app_versionCode"));
                editor.putInt("app_redirectOtherAppStatus", app_redirectOtherAppStatus);
                editor.putString("app_newPackageName", settingsJsonObject.getString("app_newPackageName"));
                editor.putInt("app_adShowStatus", app_adShowStatus);

                editor.putInt("app_howShowAdInterstitial", settingsJsonObject.getInt("app_howShowAdInterstitial"));
                editor.putString("app_adPlatformSequenceInterstitial", settingsJsonObject.getString("app_adPlatformSequenceInterstitial"));
                editor.putString("app_alernateAdShowInterstitial", settingsJsonObject.getString("app_alernateAdShowInterstitial"));

                editor.putInt("app_howShowAdNative", settingsJsonObject.getInt("app_howShowAdNative"));
                editor.putString("app_adPlatformSequenceNative", settingsJsonObject.getString("app_adPlatformSequenceNative"));
                editor.putString("app_alernateAdShowNative", settingsJsonObject.getString("app_alernateAdShowNative"));

                editor.putInt("app_howShowAdBanner", settingsJsonObject.getInt("app_howShowAdBanner"));
                editor.putString("app_adPlatformSequenceBanner", settingsJsonObject.getString("app_adPlatformSequenceBanner"));
                editor.putString("app_alernateAdShowBanner", settingsJsonObject.getString("app_alernateAdShowBanner"));

                editor.putInt("app_mainClickCntSwAd", app_mainClickCntSwAd);
                editor.putInt("app_innerClickCntSwAd", app_innerClickCntSwAd);

                editor.putInt("app_AppOpenAdStatus", settingsJsonObject.getInt("app_AppOpenAdStatus"));
                editor.putString("app_splashAdType", settingsJsonObject.getString("app_splashAdType"));
                editor.putInt("app_backPressAdStatus", settingsJsonObject.getInt("app_backPressAdStatus"));
                editor.putString("app_backPressAdType", settingsJsonObject.getString("app_backPressAdType"));
                editor.putInt("app_backPressAdLimit", settingsJsonObject.getInt("app_backPressAdLimit"));
                editor.putString("appAdsButtonColor", settingsJsonObject.getString("appAdsButtonColor"));
                editor.putString("appAdsButtonTextColor", settingsJsonObject.getString("appAdsButtonTextColor"));
                editor.putInt("appNativeAdPlaceHolder", settingsJsonObject.getInt("appNativeAdPlaceHolder"));
                editor.putInt("appBannerAdPlaceHolder", settingsJsonObject.getInt("appBannerAdPlaceHolder"));
                editor.putString("appAdPlaceHolderText", settingsJsonObject.getString("appAdPlaceHolderText"));
                editor.putInt("appNativePreLoad", settingsJsonObject.getInt("appNativePreLoad"));
                editor.putInt("appBannerPreLoad", settingsJsonObject.getInt("appBannerPreLoad"));
                editor.putString("appNativeAdSize", settingsJsonObject.getString("appNativeAdSize"));

                editor.commit();

                JSONObject AdmobJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Admob");
                admob_AdStatus = AdmobJsonObject.getInt("ad_showAdStatus");
                admob_loadAdIdsType = AdmobJsonObject.getInt("ad_loadAdIdsType");
                ADMOB_APPID = AdmobJsonObject.getString("AppID");
                ADMOB_B[0] = AdmobJsonObject.getString("Banner1");
                ADMOB_I[0] = AdmobJsonObject.getString("Interstitial1");
                ADMOB_N[0] = AdmobJsonObject.getString("Native1");
                ADMOB_AppOpen[0] = AdmobJsonObject.getString("AppOpen1");
                ADMOB_RewardedAd = AdmobJsonObject.getString("RewardedVideo1");

                SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                editor1.putString("AppOpenID", AdmobJsonObject.getString("AppOpen1"));
                editor1.commit();

                JSONObject FBJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Facebookaudiencenetwork");
                facebook_AdStatus = FBJsonObject.getInt("ad_showAdStatus");
                facebook_loadAdIdsType = FBJsonObject.getInt("ad_loadAdIdsType");
                FACEBOOK_B[0] = FBJsonObject.getString("Banner1");
                FACEBOOK_NB[0] = FBJsonObject.getString("NativeBanner1");
                FACEBOOK_I[0] = FBJsonObject.getString("Interstitial1");
                FACEBOOK_N[0] = FBJsonObject.getString("Native1");


                JSONObject MyAdJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("MyCustomAds");
                myCustom_AdStatus = MyAdJsonObject.getInt("ad_showAdStatus");

                //Topon
                JSONObject ToponJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("TopOn");
                topon_AdStatus = ToponJsonObject.getInt("ad_showAdStatus");
                topon_loadAdIdsType = ToponJsonObject.getInt("ad_loadAdIdsType");
                topon_app_id = ToponJsonObject.getString("AppID");
                topon_app_key = ToponJsonObject.getString("AppOpen1");
                TopOn_B[0] = ToponJsonObject.getString("Banner1");
                TopOn_NB[0] = ToponJsonObject.getString("NativeBanner1");
                TopOn_I[0] = ToponJsonObject.getString("Interstitial1");
                TopOn_N[0] = ToponJsonObject.getString("Native1");

            } catch (Exception e) {
            }
        }
    }

    @SuppressLint("MissingPermission")
    public static boolean checkVPN(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getNetworkInfo(ConnectivityManager.TYPE_VPN).isConnectedOrConnecting();
    }

    private void initAd() {
        if (myDeviceCountryCode == null || myDeviceCountryCode.isEmpty()) {
            getUserCountry(activity, new OnCountryListener() {
                @Override
                public void onCountryFound(String name) {
                    myDeviceCountryCode = name;
                    afterCountryInitAd();
                }
            });
        } else {
            afterCountryInitAd();
        }
    }

    private void afterCountryInitAd() {
        if (!checkVPN(activity) && vpn_flag.equalsIgnoreCase("1") && country_list != null && !country_list.isEmpty()) {
            String[] interestArray;
            interestArray = country_list.split(",");
            String[] interestArray1 = new String[interestArray.length];
            for (int i = 0; i < interestArray.length; i++) {
                interestArray1[i] = interestArray[i];
                if (interestArray[i].equalsIgnoreCase(myDeviceCountryCode)) {
                    Log.e("Tiger", "isCountrySame: " + interestArray[i]);
                    appPreferenceVpn.setIsCountrySame(true);
                    break;
                } else {
                    appPreferenceVpn.setIsCountrySame(false);
                    Log.e("Tiger", "isCountryNotSame: " + interestArray[i]);
                }
            }
        }

        if (AppManage.Hydra_OneConnect.equalsIgnoreCase("1") && !checkVPN(activity) && AppManage.vpn_flag.equalsIgnoreCase("1") && AppManage.One_Onnect_connect_vpn != null && !AppManage.One_Onnect_connect_vpn.isEmpty()) {
            ListOneConnectCountry = new ArrayList<>();

            String[] interestArray = AppManage.One_Onnect_connect_vpn.split(",");
            for (int i = 0; i < interestArray.length; i++) {
                ListOneConnectCountry.add(interestArray[i]);
                Log.e("ONE_VPN_TAG", "One_Onnect_connect_vpn: " + interestArray[i]);
            }
            Log.e("ONE_VPN_TAG", "ListOneConnectCountry : Size " + ListOneConnectCountry.size());
        } else {
            ListOneConnectCountry = null;
        }

        if (admob_AdStatus == 1) {

            MobileAds.initialize(activity, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(InitializationStatus initializationStatus) {
                }
            });

            String appopen_id = getRandomPlacementId(ADMOB, "AO");
            loadAdmobAppOpenAd(activity, appopen_id);
        }


        if (facebook_AdStatus == 1) {
            AudienceNetworkAds.initialize(activity);
//            AdSettings.addTestDevice("a11349f7-eaf6-41b7-924a-fd64289c9cbc");
            AdSettings.addTestDevice("436c3fe2-33e5-45f7-9cf8-a95687676576");
        }

        //Topon
        ATSDK.setBiddingTestDevice("Device ID");
        ATSDK.setNetworkLogDebug(true);
        ATSDK.integrationChecking(activity);
        ATSDK.init(activity, topon_app_id, topon_app_key);
    }

    public static void getUserCountry(Context context, OnCountryListener onCountryListener) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                onCountryListener.onCountryFound(simCountry.toLowerCase(Locale.US).toUpperCase());
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    onCountryListener.onCountryFound(networkCountry.toLowerCase(Locale.US).toUpperCase());
                }
            }
        } catch (Exception e) {
            Log.e("====", "getUserCountry: " + e.getMessage());
        }
        callCountryCodeApi(onCountryListener);
    }

    public static void callCountryCodeApi(OnCountryListener onCountryListener) {
        APIInterface ipInterface = APIClient.get_ip_clint().create(APIInterface.class);
        ipInterface.getipdata("json").enqueue(new retrofit2.Callback<Pro_IPModel>() {
            @Override
            public void onResponse(Call<Pro_IPModel> call, retrofit2.Response<Pro_IPModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Pro_IPModel pro_ipModel = new Pro_IPModel();
                    pro_ipModel.setCountryCode(response.body().getCountryCode());
                    pro_ipModel.setCountry(response.body().getCountry());
                    onCountryListener.onCountryFound(response.body().getCountryCode());
                } else {
                    onCountryListener.onCountryFound("");
                }
            }

            @Override
            public void onFailure(Call<Pro_IPModel> call, Throwable th) {
                Log.d("kp_response121 ", "onfailure: failed");
                onCountryListener.onCountryFound("");
            }
        });
    }


    private static boolean checkUpdate(int cversion) {


        if (mysharedpreferences.getInt("app_updateAppDialogStatus", 0) == 1) {
            String versions = mysharedpreferences.getString("app_versionCode", "");
            String[] str = versions.split(",");

            try {


                if (Arrays.asList(str).contains(cversion + "")) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public List<CustomAdModel> get_CustomAdData() {
        List<CustomAdModel> data = new ArrayList<>();
        SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        try {

            JSONArray array = new JSONArray(preferences.getString("Advertise_List", ""));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                CustomAdModel customAdModel = new CustomAdModel();
                customAdModel.setAd_id(object.getInt("ad_id"));
                customAdModel.setApp_name(object.getString("app_name"));
                customAdModel.setApp_packageName(object.getString("app_packageName"));
                customAdModel.setApp_logo(object.getString("app_logo"));
                customAdModel.setApp_banner(object.getString("app_banner"));
                customAdModel.setApp_shortDecription(object.getString("app_shortDecription"));
                customAdModel.setApp_rating(object.getString("app_rating"));
                customAdModel.setApp_download(object.getString("app_download"));
                customAdModel.setApp_AdFormat(object.getString("app_AdFormat"));
                customAdModel.setApp_buttonName(object.getString("app_buttonName"));
                data.add(customAdModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public CustomAdModel getMyCustomAd(String adFormat) {

        if (myAppMarketingList.size() == 0) {
            myAppMarketingList = get_CustomAdData();

        }

        List<CustomAdModel> adFormatWiseAd = new ArrayList<>();
        if (myAppMarketingList.size() != 0) {
            for (int i = 0; i < myAppMarketingList.size(); i++) {
                if (!myAppMarketingList.get(i).getApp_AdFormat().isEmpty()) {
                    String[] adFormat_list = myAppMarketingList.get(i).getApp_AdFormat().split(",");
                    if (Arrays.asList(adFormat_list).contains(adFormat)) {
                        adFormatWiseAd.add(myAppMarketingList.get(i));
                    }
                }

            }
        }

        int count_myAd = 0;
        if (adFormat.equals("Banner")) {
            count_myAd = count_custBannerAd;
        } else if (adFormat.equals("NativeBanner")) {
            count_myAd = count_custNBAd;
        } else if (adFormat.equals("Native")) {
            count_myAd = count_custNativeAd;
        } else if (adFormat.equals("Interstitial")) {
            count_myAd = count_custIntAd;
        } else if (adFormat.equals("AppOpen")) {
            count_myAd = count_custAppOpenAd;
        }
        CustomAdModel customAdModel = null;
        if (adFormatWiseAd.size() != 0) {
            for (int j = 0; j <= adFormatWiseAd.size(); j++) {
                if (count_myAd % adFormatWiseAd.size() == j) {
                    customAdModel = adFormatWiseAd.get(j);
                }
            }
        }


        return customAdModel;

    }

    public List<MoreApp_Data> get_SPLASHMoreAppData() {
        List<MoreApp_Data> data = new ArrayList<>();
        SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        try {

            JSONArray array = new JSONArray(preferences.getString("MORE_APP_SPLASH", ""));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                data.add(new MoreApp_Data(object.getString("app_id"), object.getString("app_name"), object.getString("app_packageName"), object.getString("app_logo"), object.getString("app_status")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<MoreApp_Data> get_EXITMoreAppData() {
        List<MoreApp_Data> data = new ArrayList<>();
        SharedPreferences preferences = activity.getSharedPreferences("ad_pref", 0);
        try {

            JSONArray array = new JSONArray(preferences.getString("MORE_APP_EXIT", ""));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                data.add(new MoreApp_Data(object.getString("app_id"), object.getString("app_name"), object.getString("app_packageName"), object.getString("app_logo"), object.getString("app_status")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void getResponseFromPref(getDataListner listner, int cversion) {
        String response1 = mysharedpreferences.getString("response", "");

        SharedPreferences.Editor editor_count = mysharedpreferences.edit();
        editor_count.putInt("count_admob_B", 0);
        editor_count.putInt("count_admob_I", 0);
        editor_count.putInt("count_admob_N", 0);
        editor_count.putInt("count_admob_AO", 0);

        editor_count.putInt("count_facebook_B", 0);
        editor_count.putInt("count_facebook_NB", 0);
        editor_count.putInt("count_facebook_I", 0);
        editor_count.putInt("count_facebook_N", 0);

        editor_count.putInt("count_topon_B", 0);
        editor_count.putInt("count_topon_NB", 0);
        editor_count.putInt("count_topon_I", 0);
        editor_count.putInt("count_topon_N", 0);

        editor_count.commit();

        if (!response1.isEmpty()) {
            try {

                updateInVariable();
                JSONObject response = new JSONObject(response1);
                JSONObject settingsJsonObject = response.getJSONObject("APP_SETTINGS");

                SharedPreferences.Editor editor = mysharedpreferences.edit();
                editor.putString("app_name", settingsJsonObject.getString("app_name"));
                editor.putString("app_logo", settingsJsonObject.getString("app_logo"));


                editor.putString("app_privacyPolicyLink", app_privacyPolicyLink);
                editor.putString("app_termsServieLink", app_termsServieLink);
                editor.putInt("app_updateAppDialogStatus", app_updateAppDialogStatus);
                editor.putString("app_versionCode", settingsJsonObject.getString("app_versionCode"));
                editor.putInt("app_redirectOtherAppStatus", app_redirectOtherAppStatus);
                editor.putString("app_newPackageName", settingsJsonObject.getString("app_newPackageName"));
                editor.putInt("app_adShowStatus", app_adShowStatus);

                editor.putInt("app_howShowAdInterstitial", settingsJsonObject.getInt("app_howShowAdInterstitial"));
                editor.putString("app_adPlatformSequenceInterstitial", settingsJsonObject.getString("app_adPlatformSequenceInterstitial"));
                editor.putString("app_alernateAdShowInterstitial", settingsJsonObject.getString("app_alernateAdShowInterstitial"));

                editor.putInt("app_howShowAdNative", settingsJsonObject.getInt("app_howShowAdNative"));
                editor.putString("app_adPlatformSequenceNative", settingsJsonObject.getString("app_adPlatformSequenceNative"));
                editor.putString("app_alernateAdShowNative", settingsJsonObject.getString("app_alernateAdShowNative"));

                editor.putInt("app_howShowAdBanner", settingsJsonObject.getInt("app_howShowAdBanner"));
                editor.putString("app_adPlatformSequenceBanner", settingsJsonObject.getString("app_adPlatformSequenceBanner"));
                editor.putString("app_alernateAdShowBanner", settingsJsonObject.getString("app_alernateAdShowBanner"));

                editor.putInt("app_mainClickCntSwAd", app_mainClickCntSwAd);
                editor.putInt("app_innerClickCntSwAd", app_innerClickCntSwAd);

                editor.putInt("app_AppOpenAdStatus", settingsJsonObject.getInt("app_AppOpenAdStatus"));
                editor.putString("app_splashAdType", settingsJsonObject.getString("app_splashAdType"));
                editor.putInt("app_backPressAdStatus", settingsJsonObject.getInt("app_backPressAdStatus"));
                editor.putString("app_backPressAdType", settingsJsonObject.getString("app_backPressAdType"));
                editor.putInt("app_backPressAdLimit", settingsJsonObject.getInt("app_backPressAdLimit"));
                editor.putString("appAdsButtonColor", settingsJsonObject.getString("appAdsButtonColor"));
                editor.putString("appAdsButtonTextColor", settingsJsonObject.getString("appAdsButtonTextColor"));
                editor.putInt("appNativeAdPlaceHolder", settingsJsonObject.getInt("appNativeAdPlaceHolder"));
                editor.putInt("appBannerAdPlaceHolder", settingsJsonObject.getInt("appBannerAdPlaceHolder"));
                editor.putString("appAdPlaceHolderText", settingsJsonObject.getString("appAdPlaceHolderText"));
                editor.putInt("appNativePreLoad", settingsJsonObject.getInt("appNativePreLoad"));
                editor.putInt("appBannerPreLoad", settingsJsonObject.getInt("appBannerPreLoad"));
                editor.putString("appNativeAdSize", settingsJsonObject.getString("appNativeAdSize"));

                editor.commit();

                JSONObject AdmobJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Admob");
                admob_AdStatus = AdmobJsonObject.getInt("ad_showAdStatus");
                admob_loadAdIdsType = AdmobJsonObject.getInt("ad_loadAdIdsType");
                ADMOB_APPID = AdmobJsonObject.getString("AppID");
                ADMOB_B[0] = AdmobJsonObject.getString("Banner1");
                ADMOB_I[0] = AdmobJsonObject.getString("Interstitial1");
                ADMOB_N[0] = AdmobJsonObject.getString("Native1");
                ADMOB_AppOpen[0] = AdmobJsonObject.getString("AppOpen1");
                ADMOB_RewardedAd = AdmobJsonObject.getString("RewardedVideo1");

                SharedPreferences.Editor editor1 = mysharedpreferences.edit();
                editor1.putString("AppOpenID", AdmobJsonObject.getString("AppOpen1"));
                editor1.commit();

                JSONObject FBJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("Facebookaudiencenetwork");
                facebook_AdStatus = FBJsonObject.getInt("ad_showAdStatus");
                facebook_loadAdIdsType = FBJsonObject.getInt("ad_loadAdIdsType");
                FACEBOOK_B[0] = FBJsonObject.getString("Banner1");
                FACEBOOK_NB[0] = FBJsonObject.getString("NativeBanner1");
                FACEBOOK_I[0] = FBJsonObject.getString("Interstitial1");
                FACEBOOK_N[0] = FBJsonObject.getString("Native1");

                JSONObject TopOnJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("TopOn");
                topon_AdStatus = TopOnJsonObject.getInt("ad_showAdStatus");
                topon_loadAdIdsType = TopOnJsonObject.getInt("ad_loadAdIdsType");
                TopOn_B[0] = TopOnJsonObject.getString("Banner1");
                TopOn_NB[0] = TopOnJsonObject.getString("NativeBanner1");
                TopOn_I[0] = TopOnJsonObject.getString("Interstitial1");
                TopOn_N[0] = TopOnJsonObject.getString("Native1");

                JSONObject MyAdJsonObject = response.getJSONObject("PLACEMENT").getJSONObject("MyCustomAds");
                myCustom_AdStatus = MyAdJsonObject.getInt("ad_showAdStatus");

                try {
                    listner.onGetExtradata(response.getJSONObject("EXTRA_DATA"));
                    Log.e("EXTRA_DATA", response.getJSONObject("EXTRA_DATA").toString());
                } catch (Exception e) {
                    Log.e("extradata_error", e.getMessage());
                }

            } catch (Exception e) {
                Log.e("=====", "getResponseFromPref: " + e.getMessage());
            }

            if (app_redirectOtherAppStatus == 1) {
                String redirectNewPackage = mysharedpreferences.getString("app_newPackageName", "");
                listner.onRedirect(redirectNewPackage);
            } else if (app_updateAppDialogStatus == 1 && checkUpdate(cversion)) {
                listner.onUpdate("https://play.google.com/store/apps/details?id=" + activity.getPackageName());
            } else {
                initAd();
                listner.onSuccess();

//                if (myCallback != null) {
//                    myCallback.callbackCall();
//                    myCallback = null;
//                }
            }
        }
    }

    private static void updateInVariable() {
        try {
            String response1 = mysharedpreferences.getString("response", "");

            JSONObject response = new JSONObject(response1);
            JSONObject settingsJsonObject = response.getJSONObject("APP_SETTINGS");


            Continue_Screen_Native_MoreApp = settingsJsonObject.getString("Continue_Screen_Native_MoreApp");

            native_scroll_show = settingsJsonObject.getString("native_scroll_show");
            open_ad_interstitial_Specific_flag = settingsJsonObject.getString("open_ad_interstitial_Specific_flag");
            main_data_url = settingsJsonObject.getString("main_data_url");
            ReportUser = settingsJsonObject.getString("ReportUser");
            app_rtc_url = settingsJsonObject.getString("app_rtc_url");
            GenerateRoom = settingsJsonObject.getString("GenerateRoom");
            DeleteRoom = settingsJsonObject.getString("DeleteRoom");
            question_answer = settingsJsonObject.getString("question_answer");
            Connecting_Repeat = settingsJsonObject.getString("Connecting_Repeat");
            WebViewUrl = settingsJsonObject.getString("WebViewUrl");
            CallConnectKey = settingsJsonObject.getString("CallConnectKey");
            CallConnectKey1 = settingsJsonObject.getString("CallConnectKey1");
            CallConnectSecond = settingsJsonObject.getString("CallConnectSecond");
            WebConnectingSecond = settingsJsonObject.getString("WebConnectingSecond");

            if (BuildConfig.DEBUG) {
                WebGenerateRoom = settingsJsonObject.getString("TestGenerateRoom");
                WebDeleteRoom = settingsJsonObject.getString("TestDeleteRoom");
            } else {
                WebGenerateRoom = settingsJsonObject.getString("WebGenerateRoom");
                WebDeleteRoom = settingsJsonObject.getString("WebDeleteRoom");
            }

            banner_on_off = settingsJsonObject.getString("banner_on_off");
            custome_banner = settingsJsonObject.getString("custome_banner");

            One_Onnect_API_Key = settingsJsonObject.getString("One_Onnect_API_Key");
// one connect vpn coutry list
            One_Onnect_connect_vpn = settingsJsonObject.getString("One_Onnect_connect_vpn");
// 0 - hydra vpn and  1 - one connect vpn
            Hydra_OneConnect = settingsJsonObject.getString("Hydra_OneConnect");
            Hydra_OneConnect = "0";
// ask vpn permission        0 - ask vpn permission in splash screen 1 - ask vpn permission on star button clickl
            splash_start_vpn = settingsJsonObject.getString("splash_start_vpn");
            splash_start_vpn = "1";
//             0 - don't start vpn 1 - start vpn
            vpn_flag = settingsJsonObject.getString("vpn_flag");
//            vpn_flag = "1";
//            0 - cancel vpn start then also use app 1 - cancel vpn start again ask to start vpn
            force_vpn = settingsJsonObject.getString("force_vpn");
//            force_vpn = "1";
//            county list-select the vpn from given country for hydra
            connect_vpn = settingsJsonObject.getString("connect_vpn").toUpperCase();
//            after given second wait vpn connect or not jump to next process
            vpn_sec = settingsJsonObject.getString("vpn_sec");
//            from given country is your device country then vpn not need to start
            country_list = settingsJsonObject.getString("country_list");

//            country_list = country_list+",IN";

            vpn_base_host = settingsJsonObject.getString("BASE_HOST");
            vpn_star_ads = settingsJsonObject.getString("vpn_star_ads");
            vpn_base_carrier_id = settingsJsonObject.getString("BASE_CARRIER_ID");

            app_accountLink = settingsJsonObject.getString("app_accountLink");
            app_privacyPolicyLink = settingsJsonObject.getString("app_privacyPolicyLink");
            app_termsServieLink = settingsJsonObject.getString("app_termsServieLink");
            app_updateAppDialogStatus = settingsJsonObject.getInt("app_updateAppDialogStatus");
            app_redirectOtherAppStatus = settingsJsonObject.getInt("app_redirectOtherAppStatus");
            app_dialogBeforeAdShow = settingsJsonObject.getInt("app_dialogBeforeAdShow");
            app_adShowStatus = settingsJsonObject.getInt("app_adShowStatus");
            app_mainClickCntSwAd = settingsJsonObject.getInt("app_mainClickCntSwAd");
            app_innerClickCntSwAd = settingsJsonObject.getInt("app_innerClickCntSwAd");
        } catch (Exception e) {
            Log.e("=====", "getResponseFromPref: " + e.getMessage());
        }

    }

    private String implode(String[] placementList) {
        String str_ads = "";
        for (int i = 1; i < placementList.length; i++) {
            if (!placementList[i].isEmpty()) {
                if (str_ads.isEmpty()) {
                    str_ads = placementList[i];
                } else {
                    str_ads = str_ads + "," + placementList[i];
                }

            }
        }
        return str_ads;
    }

    private String getHigheCPMAdId(String platform, String adFormat, String whichOne) {
        String adId = "";

        if (whichOne.equals("First")) {
            SharedPreferences.Editor editor = mysharedpreferences.edit();
            if (platform.equals(ADMOB)) {
                if (adFormat.equals("I")) {
                    adId = ADMOB_I[0];
                    String ADMOB_I_list = implode(ADMOB_I);
                    editor.putString("ADMOB_I", ADMOB_I_list);
                }

            } else if (platform.equals(FACEBOOK)) {
                if (adFormat.equals("I")) {
                    adId = FACEBOOK_I[0];
                    String FACEBOOK_I_list = implode(FACEBOOK_I);
                    editor.putString("FACEBOOK_I", FACEBOOK_I_list);
                }
            } else if (platform.equals(TopOn)) {
                if (adFormat.equals("I")) {
                    adId = TopOn_I[0];
                    String TOPON_I_list = implode(TopOn_I);
                    editor.putString("TOPON_I", TOPON_I_list);
                }
            }
            editor.commit();
        } else if (whichOne.equals("Next")) {
            SharedPreferences.Editor editor = mysharedpreferences.edit();
            if (platform.equals(ADMOB)) {
                if (adFormat.equals("I")) {
                    String ADMOB_I_list = mysharedpreferences.getString("ADMOB_I", "");
                    if (!ADMOB_I_list.isEmpty()) {
                        String[] intA_list = ADMOB_I_list.split(",");
                        adId = intA_list[0];
                        ADMOB_I_list = implode(intA_list);
                        editor.putString("ADMOB_I", ADMOB_I_list);
                    }

                }

            } else if (platform.equals(FACEBOOK)) {
                if (adFormat.equals("I")) {
                    String FACEBOOK_I_list = mysharedpreferences.getString("FACEBOOK_I", "");
                    if (!FACEBOOK_I_list.isEmpty()) {
                        String[] intF_list = FACEBOOK_I_list.split(",");
                        adId = intF_list[0];
                        FACEBOOK_I_list = implode(intF_list);
                        editor.putString("FACEBOOK_I", FACEBOOK_I_list);
                    }
                }
            } else if (platform.equals(TopOn)) {
                if (adFormat.equals("I")) {
                    String TOPON_I_list = mysharedpreferences.getString("TOPON_I", "");
                    if (!TOPON_I_list.isEmpty()) {
                        String[] intF_list = TOPON_I_list.split(",");
                        adId = intF_list[0];
                        TOPON_I_list = implode(intF_list);
                        editor.putString("TOPON_I", TOPON_I_list);
                    }
                }
            }
            editor.commit();
        }
        return adId;
    }

    public void loadInterstitialAd(Activity activity, String google_i, String facebook_i, String topon_i) {
        if (admob_loadAdIdsType == 2) {
            google_i = getHigheCPMAdId(ADMOB, "I", "First");
        }
        if (facebook_loadAdIdsType == 2) {
            facebook_i = getHigheCPMAdId(FACEBOOK, "I", "First");
        }
        if (topon_loadAdIdsType == 2) {
            topon_i = getHigheCPMAdId(TopOn, "I", "First");
        }
        turnLoadInterstitialAd(activity, google_i, facebook_i, topon_i);
    }

    public void loadInterstitialAd(Activity activity) {
        if (app_adShowStatus == 0) {
            return;
        }
        if (appPreferenceVpn != null) {

            String google_i = "";
            String facebook_i = "";
            String topon_i = "";

            if (admob_loadAdIdsType == 2 && appPreferenceVpn.getIsVpnConnected()) {
                google_i = getHigheCPMAdId(ADMOB, "I", "First");
            } else {
                google_i = getRandomPlacementId(ADMOB, "I");
            }
            if (facebook_loadAdIdsType == 2) {
                facebook_i = getHigheCPMAdId(FACEBOOK, "I", "First");
            } else {
                facebook_i = getRandomPlacementId(FACEBOOK, "I");
            }
            if (topon_loadAdIdsType == 2) {
                topon_i = getHigheCPMAdId(TopOn, "I", "First");
            } else {
                topon_i = getRandomPlacementId(TopOn, "I");
            }

            turnLoadInterstitialAd(activity, google_i, facebook_i, topon_i);
        }
    }

    public void turnLoadInterstitialAd(Activity activity, String google_i, String facebook_i, String topon_i) {
        if (app_adShowStatus == 0) {
            return;
        }

        if (admob_AdStatus == 1 && !google_i.isEmpty() && !google_i_pre.equals(google_i)) {
            loadAdmobInterstitial(activity, google_i);
        }
        if (facebook_AdStatus == 1 && !facebook_i.isEmpty() && !facebook_i_pre.equals(facebook_i)) {
            loadFacebookInterstitial(activity, facebook_i);
        }
        if (topon_AdStatus == 1 && !topon_i.isEmpty() && !topon_i_pre.equals(topon_i)) {
            loadToponInterstitial(activity, topon_i);
        }
    }

    private void loadFacebookInterstitial(final Activity activity, String facebook_i) {

        if ((fbinterstitialAd1 != null && fbinterstitialAd1.isAdLoaded())
                || state_fbinterestial.equalsIgnoreCase("Loading")) {
            return;
        }


        if (facebook_loadAdIdsType == 0) {
            facebook_i = getRandomPlacementId(FACEBOOK, "I");
        }
        facebook_i_pre = facebook_i;

        state_fbinterestial = "Loading";
        fbinterstitialAd1 = new com.facebook.ads.InterstitialAd(activity, facebook_i);
        fbinterstitialAd1.loadAd(fbinterstitialAd1.buildLoadAdConfig().withAdListener(new AbstractAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                super.onError(ad, error);
                state_fbinterestial = "Fail";
                if (facebook_loadAdIdsType == 2) {
                    facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "Next");
                    if (!facebook_i_pre.isEmpty()) {
                        loadFacebookInterstitial(activity, facebook_i_pre);
                    }
                }
            }

            @Override
            public void onAdLoaded(Ad ad) {
                super.onAdLoaded(ad);
                state_fbinterestial = "Loaded";
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                super.onInterstitialDismissed(ad);
//                fbinterstitialAd1.loadAd();
                if (facebook_loadAdIdsType == 2) {
                    facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                }
                loadFacebookInterstitial(activity, facebook_i_pre);
                interstitialCallBack();
            }
        }).build());
    }

    private void loadToponInterstitial(final Activity activity, String topon_i) {
        if ((mtoponInterstitialAd != null && mtoponInterstitialAd.checkAdStatus().isReady())
                || state_toponinterestial.equalsIgnoreCase("Loading")) {
            return;
        }

        if (topon_loadAdIdsType == 0) {
            topon_i = getRandomPlacementId(TopOn, "I");
        }
        topon_i_pre = topon_i;
        state_toponinterestial = "Loading";
        mtoponInterstitialAd = new ATInterstitial(activity, topon_i);
        mtoponInterstitialAd.setAdListener(new ATInterstitialExListener() {

            @Override
            public void onDeeplinkCallback(ATAdInfo adInfo, boolean isSuccess) {
                Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
            }

            @Override
            public void onDownloadConfirm(Context context, ATAdInfo atAdInfo, ATNetworkConfirmInfo atNetworkConfirmInfo) {

            }

            @Override
            public void onInterstitialAdLoaded() {
                Log.i(TAG, "onInterstitialAdLoaded");
            }

            @Override
            public void onInterstitialAdLoadFail(com.anythink.core.api.AdError adError) {
                state_toponinterestial = "Fail";
                Log.i(TAG, "onInterstitialAdLoadFail:\n" + adError.getFullErrorInfo());
                if (topon_loadAdIdsType == 2) {
                    topon_i_pre = getHigheCPMAdId(TopOn, "I", "Next");
                    if (!topon_i_pre.isEmpty()) {
                        loadToponInterstitial(activity, topon_i_pre);
                    }
                }
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdClicked:\n" + entity.toString());
            }

            @Override
            public void onInterstitialAdShow(ATAdInfo entity) {
                state_toponinterestial = "Loaded";
                Log.i(TAG, "onInterstitialAdShow:\n" + entity.toString());
            }

            @Override
            public void onInterstitialAdClose(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdClose:\n" + entity.toString());
                if (topon_loadAdIdsType == 2) {
                    topon_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                }
                loadToponInterstitial(activity, topon_i_pre);
                interstitialCallBack();
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdVideoStart:\n" + entity.toString());
            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdVideoEnd:\n" + entity.toString());
            }

            @Override
            public void onInterstitialAdVideoError(com.anythink.core.api.AdError adError) {
                Log.i(TAG, "onInterstitialAdVideoError:\n" + adError.getFullErrorInfo());
            }

        });
        mtoponInterstitialAd.load();
    }

    private void loadAdmobInterstitial(final Activity activity, String google_i) {
        if (mInterstitialAd != null || state_admobinterestial.equalsIgnoreCase("Loading")) {
            return;
        }
        if (admob_loadAdIdsType == 0) {
            google_i = getRandomPlacementId(ADMOB, "I");
        }
        this.google_i_pre = google_i;
        state_admobinterestial = "Loading";
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(activity, google_i, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                state_admobinterestial = "Loaded";
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.d("TAG", "The ad was dismissed.");
                        if (admob_loadAdIdsType == 2) {
                            google_i_pre = getHigheCPMAdId(ADMOB, "I", "First");
                        }
                        loadAdmobInterstitial(activity, google_i_pre);
                        interstitialCallBack();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.d("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.d("TAG", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                state_admobinterestial = "Fail";
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;

                if (admob_loadAdIdsType == 2) {
                    google_i_pre = getHigheCPMAdId(ADMOB, "I", "Next");
                    if (!google_i_pre.isEmpty()) {
                        loadAdmobInterstitial(activity, google_i_pre);
                    }
                }
            }
        });
    }

    private String getRandomPlacementId(String platform, String adFormat) {
        String return_adId = "";

        SharedPreferences.Editor editor_count = mysharedpreferences.edit();
        int count = 0;
        String[] platform_Format_Ids = {"", "", "", "", ""};
        if (platform.equals(ADMOB)) {
            if (adFormat.equals("B")) {
                platform_Format_Ids = ADMOB_B;

                count = mysharedpreferences.getInt("count_admob_B", 0) + 1;
                editor_count.putInt("count_admob_B", count);
            } else if (adFormat.equals("I")) {
                platform_Format_Ids = ADMOB_I;

                count = mysharedpreferences.getInt("count_admob_I", 0) + 1;
                editor_count.putInt("count_admob_I", count);
            } else if (adFormat.equals("N")) {
                platform_Format_Ids = ADMOB_N;

                count = mysharedpreferences.getInt("count_admob_N", 0) + 1;
                editor_count.putInt("count_admob_N", count);
            } else if (adFormat.equals("AO")) {
                platform_Format_Ids = ADMOB_AppOpen;

                count = mysharedpreferences.getInt("count_admob_AO", 0) + 1;
                editor_count.putInt("count_admob_AO", count);
            }

        } else if (platform.equals(FACEBOOK)) {
            if (adFormat.equals("B")) {
                platform_Format_Ids = FACEBOOK_B;

                count = mysharedpreferences.getInt("count_facebook_B", 0) + 1;
                editor_count.putInt("count_facebook_B", count);
            } else if (adFormat.equals("I")) {
                platform_Format_Ids = FACEBOOK_I;

                count = mysharedpreferences.getInt("count_facebook_I", 0) + 1;
                editor_count.putInt("count_facebook_I", count);
            } else if (adFormat.equals("N")) {
                platform_Format_Ids = FACEBOOK_N;

                count = mysharedpreferences.getInt("count_facebook_N", 0) + 1;
                editor_count.putInt("count_facebook_N", count);
            } else if (adFormat.equals("NB")) {
                platform_Format_Ids = FACEBOOK_NB;

                count = mysharedpreferences.getInt("count_facebook_NB", 0) + 1;
                editor_count.putInt("count_facebook_NB", count);
            }
        } else if (platform.equals(TopOn)) {
            if (adFormat.equals("B")) {
                platform_Format_Ids = TopOn_B;
                count = mysharedpreferences.getInt("count_topon_B", 0) + 1;
                editor_count.putInt("count_topon_B", count);
            } else if (adFormat.equals("I")) {
                platform_Format_Ids = TopOn_I;
                count = mysharedpreferences.getInt("count_topon_I", 0) + 1;
                editor_count.putInt("count_topon_I", count);
            } else if (adFormat.equals("N")) {
                platform_Format_Ids = TopOn_N;
                count = mysharedpreferences.getInt("count_topon_N", 0) + 1;
                editor_count.putInt("count_topon_N", count);
            } else if (adFormat.equals("NB")) {
                platform_Format_Ids = TopOn_NB;
                count = mysharedpreferences.getInt("count_topon_NB", 0) + 1;
                editor_count.putInt("count_topon_NB", count);
            }
        }
        editor_count.commit();
        ArrayList<String> placemnt_Ids = new ArrayList<String>();
        for (int i = 0; i < platform_Format_Ids.length; i++) {
            if (!platform_Format_Ids[i].isEmpty()) {
                placemnt_Ids.add(platform_Format_Ids[i]);
            }
        }

        if (placemnt_Ids.size() != 0) {
            if (count % placemnt_Ids.size() == 0) {
                return_adId = placemnt_Ids.get(0);
            } else if (count % placemnt_Ids.size() == 1) {
                return_adId = placemnt_Ids.get(1);
            } else if (count % placemnt_Ids.size() == 2) {
                return_adId = placemnt_Ids.get(2);
            } else if (count % placemnt_Ids.size() == 3) {
                return_adId = placemnt_Ids.get(3);
            } else if (count % placemnt_Ids.size() == 4) {
                return_adId = placemnt_Ids.get(4);
            }
        }
        return return_adId;
    }

    public void showCustomAppOpenAd(Context context, MyCallback myCallback) {
        turnCustomAppOpenAd(context, myCallback, 0);
    }

    public void showCustomAppOpenAd(Context context, MyCallback myCallback, int how_many_clicks) {
        turnCustomAppOpenAd(context, myCallback, how_many_clicks);
    }

    public void turnCustomAppOpenAd(Context context, MyCallback myCallback2, int how_many_clicks) {
        myCallback = myCallback2;

        count_click++;

        if (app_adShowStatus == 0) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }
        if (how_many_clicks != 0) {
            if (count_click % how_many_clicks != 0) {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                return;
            }
        }

        mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);

        CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("AppOpen");
        if (customAdModel != null) {
            try {
                final CustomAppOpenAds customAppOpenAds = new CustomAppOpenAds(activity, R.style.Theme_AppOpen_Dialog, customAdModel);
                customAppOpenAds.setCanceledOnTouchOutside(false);
                customAppOpenAds.setCancelable(false);
                customAppOpenAds.setOnCloseListener(new CustomAppOpenAds.OnCloseListener() {
                    public void onAdsCloseClick() {
                        customAppOpenAds.dismiss();
                        interstitialCallBack();
                    }

                    public void setOnKeyListener() {
                        customAppOpenAds.dismiss();
                        interstitialCallBack();
                    }
                });
                customAppOpenAds.show();
            } catch (Exception e) {
                e.printStackTrace();

                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
            }
        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }
    }

    public void showInterstitialBackAd(Context context, MyCallback myCallback) {
        click_count_flag = false;
        turnInterstitialAd(context, myCallback, 0, "");
    }
/*
    public void showInterstitialAd(Context context, MyCallback myCallback) {
        if (new AppPreferenceVpn(context).getIsVpnConnected() && !new AppPreferenceVpn(context).getIsForceShow()) {
            new AppPreferenceVpn(context).setIsForceShow(true);
            onClickLoadAndShowInterstitialAd(context, true, myCallback, "", 0);
        } else {
            click_count_flag = true;
            turnInterstitialAd(context, myCallback, 0, "");
        }

    }*/

//    public void showInterstitialAd(Context context, MyCallback myCallback, String specific_platform) {
//        click_count_flag = true;
//        turnInterstitialAd(context, myCallback, 0, specific_platform);
//    }

    public void showInterstitialAd(Context context, boolean stat_button_other_button, MyCallback myCallback, int how_many_clicks) {
        // stat_button_other_button = false first time ads loading valo dialog display nai thay
        // stat_button_other_button = true first time ads loading valo dialog display show thase
        if (stat_button_other_button && !new AppPreferenceVpn(context).getIsForceShow()) {
            loadAndShowInterstitialAd(context, true, myCallback, "", 0);
        } else {
            click_count_flag = true;
            turnInterstitialAd(context, myCallback, how_many_clicks, "");
        }
    }

//    public void showInterstitialAd(Context context, MyCallback myCallback, int how_many_clicks, String specific_platform) {
//        click_count_flag = true;
//        turnInterstitialAd(context, myCallback, how_many_clicks, specific_platform);
//    }
/*
    public void showInterstitialAd(Context context, MyCallback myCallback, String specific_platform, int how_many_clicks) {
        if (new AppPreferenceVpn(context).getIsVpnConnected() && !new AppPreferenceVpn(context).getIsForceShow()) {
            new AppPreferenceVpn(context).setIsForceShow(true);
            onClickLoadAndShowInterstitialAd(context, true, myCallback, "", 0);
        } else {
            click_count_flag = true;
            turnInterstitialAd(context, myCallback, how_many_clicks, specific_platform);
        }

    }*/

    public void turnInterstitialAd(Context context, MyCallback myCallback2, int how_many_clicks, String specific_platform) {
        myCallback = myCallback2;

        if (app_adShowStatus == 0) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }

        if (click_count_flag == true) {
            count_click++;
            if (how_many_clicks != 0) {
                if (count_click % how_many_clicks != 0) {
                    if (myCallback != null) {
                        myCallback.callbackCall();
                        myCallback = null;
                    }
                    return;
                }
            }
        }
        count_click_for_alt++;

        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdInterstitial", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceInterstitial", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowInterstitial", "");

        if (!specific_platform.isEmpty()) {
            app_howShowAd = 0;
            adPlatformSequence = specific_platform;
        }


        interstitial_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");

            for (int i = 0; i < adSequence.length; i++) {
                interstitial_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_click_for_alt % alernateAd.length == j) {
                    index = j;
                    interstitial_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (interstitial_sequence.size() != 0) {
                    if (!interstitial_sequence.get(0).equals(adSequence[j])) {
                        interstitial_sequence.add(adSequence[j]);
                    }
                }

            }
        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }

        if (interstitial_sequence.size() != 0) {
            displayInterstitialAd(interstitial_sequence.get(0), context);
        }

        /*if (appPreferenceVpn != null && appPreferenceVpn.getIsVpnConnected()) {

        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }*/
    }

    private void displayInterstitialAd(String platform, final Context context) {
        dialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        if (platform.equals(ADMOB) && admob_AdStatus == 1 && new AppPreferenceVpn(activity).getIsVpnConnected()) {
            if (mInterstitialAd != null) {
                if (app_dialogBeforeAdShow == 1) {
                    dialog.show();

                    new CountDownTimer(ad_dialog_time_in_second * 1000, 10) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;

                        }

                        @Override
                        public void onFinish() {

                            dialog.dismiss();
                            mInterstitialAd.show((Activity) context);
                        }
                    }.start();

                } else {
                    mInterstitialAd.show((Activity) context);
                }
            } else {
                if (admob_loadAdIdsType == 2) {
                    google_i_pre = getHigheCPMAdId(ADMOB, "I", "First");
                }
                if (!google_i_pre.isEmpty()) {
                    loadAdmobInterstitial((Activity) context, google_i_pre);
                }

                nextInterstitialPlatform(context);
            }
        } else if (platform.equals(FACEBOOK) && facebook_AdStatus == 1 && fbinterstitialAd1 != null) {

            if (fbinterstitialAd1.isAdLoaded()) {
                if (app_dialogBeforeAdShow == 1) {

                    dialog.show();

                    new CountDownTimer(ad_dialog_time_in_second * 1000, 100) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;
                        }

                        @Override
                        public void onFinish() {
                            dialog.dismiss();
                            fbinterstitialAd1.show();
                        }
                    }.start();

                } else {
                    fbinterstitialAd1.show();
                }
            } else {
//                fbinterstitialAd1.loadAd();
                if (facebook_loadAdIdsType == 2) {
                    facebook_i_pre = getHigheCPMAdId(FACEBOOK, "I", "First");
                }
                loadFacebookInterstitial((Activity) context, facebook_i_pre);
                nextInterstitialPlatform(context);
            }

        } else if (platform.equals(TopOn) && topon_AdStatus == 1 && mtoponInterstitialAd != null) {
            if (mtoponInterstitialAd.checkAdStatus().isReady()) {
                if (app_dialogBeforeAdShow == 1) {
                    dialog.show();
                    new CountDownTimer(ad_dialog_time_in_second * 1000, 100) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            double time = (millisUntilFinished / 10) / ad_dialog_time_in_second;
                        }

                        @Override
                        public void onFinish() {
                            dialog.dismiss();
                            mtoponInterstitialAd.show(activity);
                        }
                    }.start();

                } else {
                    mtoponInterstitialAd.show(activity);
                }
            } else {
                if (topon_loadAdIdsType == 2) {
                    topon_i_pre = getHigheCPMAdId(TopOn, "I", "First");
                }
                loadToponInterstitial((Activity) context, topon_i_pre);
                nextInterstitialPlatform(context);
            }
        } else if (platform.equals(MyCustomAds) && myCustom_AdStatus == 1) {
            CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("Interstitial");
            if (customAdModel != null) {
                try {
                    final CustomIntAds customIntAds = new CustomIntAds(activity, customAdModel);
                    customIntAds.setCanceledOnTouchOutside(false);
                    customIntAds.setCancelable(false);
                    customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                        public void onAdsCloseClick() {
                            customIntAds.dismiss();
                            interstitialCallBack();
                        }

                        public void setOnKeyListener() {
                            customIntAds.dismiss();
                            interstitialCallBack();
                        }
                    });
                    customIntAds.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    nextInterstitialPlatform(context);
                }
            } else {
                nextInterstitialPlatform(context);
            }

        } else {
            nextInterstitialPlatform(context);
        }
    }

    private void nextInterstitialPlatform(Context context) {

        if (interstitial_sequence.size() != 0) {
            interstitial_sequence.remove(0);

            if (interstitial_sequence.size() != 0) {
                displayInterstitialAd(interstitial_sequence.get(0), context);
            } else {
                interstitialCallBack();
            }

        } else {
            interstitialCallBack();

        }
    }

    public void interstitialCallBack() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (myCallback != null) {
            myCallback.callbackCall();
            myCallback = null;
        }
    }

    public void showBanner(ViewGroup banner_container, String admob_b, String facebook_b, String topon_b) {
        turnShowBanner(banner_container, admob_b, facebook_b, topon_b);
    }

    public void showBanner(ViewGroup banner_container) {
        /*if (appPreferenceVpn != null && appPreferenceVpn.getIsVpnConnected() && banner_on_off.equalsIgnoreCase("0")) {
            turnShowBanner(banner_container, "random", "random", "random");
        }*/
        if (appPreferenceVpn != null && banner_on_off.equalsIgnoreCase("0")) {
            turnShowBanner(banner_container, "random", "random", "random");
        }
    }

    public void turnShowBanner(ViewGroup banner_container, String admob_b, String facebook_b, String topon_b) {
        this.admob_b = admob_b;
        this.facebook_b = facebook_b;
        this.topon_b = topon_b;

        if (!hasActiveInternetConnection(activity)) {
            return;
        }

        if (app_adShowStatus == 0) {
            return;
        }

        count_banner++;


        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdBanner", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceBanner", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowBanner", "");


        banner_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                banner_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_banner % alernateAd.length == j) {
                    index = j;
                    banner_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (banner_sequence.size() != 0) {
                    if (!banner_sequence.get(0).equals(adSequence[j])) {
                        banner_sequence.add(adSequence[j]);
                    }
                }
            }
        }
        if (banner_sequence.size() != 0) {
            new Inflate_ADS(activity).setBannerAdPlaceholder(banner_container);
            displayBanner(banner_sequence.get(0), banner_container);
        }
    }

    public void displayBanner(String platform, ViewGroup banner_container) {
        if (appPreferenceVpn != null && banner_on_off.equalsIgnoreCase("0")) {
            if (platform.equals(ADMOB) && admob_AdStatus == 1 && appPreferenceVpn.getIsVpnConnected()) {
                if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_b.equals("random")) && !admob_b.isEmpty()) {
                    admob_b = getRandomPlacementId(ADMOB, "B");
                }
                showAdmobBanner(banner_container);
            } else if ((platform.equals(FACEBOOK) && facebook_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
                if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_b.equals("random")) && !facebook_b.isEmpty()) {
                    facebook_b = getRandomPlacementId(FACEBOOK, "B");
                }
                showFacebookBanner(banner_container);
            } else if ((platform.equals(TopOn) && topon_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
                if ((topon_loadAdIdsType == 0 || topon_loadAdIdsType == 2 || topon_b.equals("random")) && !topon_b.isEmpty()) {
                    topon_b = getRandomPlacementId(TopOn, "B");
                }
                showToponBanner(banner_container);
            } else if ((platform.equals(MyCustomAds) && myCustom_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
                showMyCustomBanner(banner_container);
            } else {
                nextBannerPlatform(banner_container);
            }
        }
    }

    private void nextBannerPlatform(ViewGroup banner_container) {
        if (appPreferenceVpn != null && banner_on_off.equalsIgnoreCase("0")) {
            if (banner_sequence.size() != 0) {
                banner_sequence.remove(0);
                if (banner_sequence.size() != 0) {
                    displayBanner(banner_sequence.get(0), banner_container);
                }
            }
        }
    }

    private void showMyCustomBanner(final ViewGroup banner_container) {
        final CustomAdModel appModal = getMyCustomAd("Banner");
        if (appModal != null) {
            View inflate2 = LayoutInflater.from(activity).inflate(R.layout.cust_banner, banner_container, false);
            ImageView imageView2 = inflate2.findViewById(R.id.icon);
            TextView textView = inflate2.findViewById(R.id.primary);
            TextView textView2 = inflate2.findViewById(R.id.secondary);
            Button btn_call_to_action = inflate2.findViewById(R.id.cta);

            try {
                String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
                String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

                if (!appNativeButtonColor.isEmpty()) {
                    btn_call_to_action.setBackgroundColor(Color.parseColor(appNativeButtonColor));
                }
                if (!appNativeTextColor.isEmpty()) {
                    btn_call_to_action.setTextColor(Color.parseColor(appNativeTextColor));
                }
            } catch (Exception e) {

            }

            Glide.with(activity).load(appModal.getApp_logo()).diskCacheStrategy(DiskCacheStrategy.ALL).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    banner_container.removeAllViews();
                    nextBannerPlatform(banner_container);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(imageView2);

            textView.setText(appModal.getApp_name());
            textView2.setText(appModal.getApp_shortDecription());
            btn_call_to_action.setText(appModal.getApp_buttonName());
            btn_call_to_action.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }

                }
            });
            banner_container.removeAllViews();
            banner_container.addView(inflate2);
            count_custBannerAd++;


        } else {
            nextBannerPlatform(banner_container);
        }

    }

    private void showFacebookBanner(final ViewGroup banner_container) {
        if (facebook_b.isEmpty() || facebook_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }
        if (fbBannerAd_preLoad == null) {
            final com.facebook.ads.AdView adView = new com.facebook.ads.AdView(activity, facebook_b, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.e("Tiger", "showFacebookBanner: adError " + adError.getErrorMessage());
                    // Ad error callback
                    banner_container.removeAllViews();
                    nextBannerPlatform(banner_container);

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Ad loaded callback
                    Log.e("Tiger", "showFacebookBanner: onAdLoaded ");
                    banner_container.removeAllViews();
                    banner_container.addView(adView);
                    preLoadBanner(FACEBOOK);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Ad impression logged callback
                }
            };

            // Request an ad
            adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());
        } else {
            banner_container.removeAllViews();
            banner_container.addView(fbBannerAd_preLoad);

            state_fbBanner = "Start";
            preLoadBanner(FACEBOOK);
        }


    }

    private void showToponBanner(final ViewGroup banner_container) {
        if (topon_b.isEmpty() || topon_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }
        if (toponBannerAd_preLoad == null) {
            final ATBannerView toponBannerAd_preLoad = new ATBannerView(activity);
            toponBannerAd_preLoad.setPlacementId(topon_b);
            banner_container.addView(toponBannerAd_preLoad, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            toponBannerAd_preLoad.loadAd();
            toponBannerAd_preLoad.setBannerAdListener(new ATBannerExListener() {
                @Override
                public void onDeeplinkCallback(boolean isRefresh, ATAdInfo adInfo, boolean isSuccess) {
                    Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
                }

                @Override
                public void onDownloadConfirm(Context context, ATAdInfo atAdInfo, ATNetworkConfirmInfo atNetworkConfirmInfo) {

                }

                @Override
                public void onBannerLoaded() {
                    Log.i(TAG, "onBannerLoaded");
                    banner_container.removeAllViews();
                    banner_container.addView(toponBannerAd_preLoad);
                    preLoadBanner(TopOn);
                }

                @Override
                public void onBannerFailed(com.anythink.core.api.AdError adError) {
                    Log.i(TAG, "onBannerFailed: " + adError.getFullErrorInfo());
                    banner_container.removeAllViews();
                    nextBannerPlatform(banner_container);
                }

                @Override
                public void onBannerClicked(ATAdInfo entity) {
                    Log.i(TAG, "onBannerClicked:" + entity.toString());
                }

                @Override
                public void onBannerShow(ATAdInfo entity) {
                    Log.i(TAG, "onBannerShow:" + entity.toString());
                }

                @Override
                public void onBannerClose(ATAdInfo entity) {
                    Log.i(TAG, "onBannerClose:" + entity.toString());
                }

                @Override
                public void onBannerAutoRefreshed(ATAdInfo entity) {
                    Log.i(TAG, "onBannerAutoRefreshed:" + entity.toString());
                }

                @Override
                public void onBannerAutoRefreshFail(com.anythink.core.api.AdError adError) {
                    Log.i(TAG, "onBannerAutoRefreshFail: " + adError.getFullErrorInfo());
                }
            });
        } else {
            banner_container.removeAllViews();
            banner_container.addView(toponBannerAd_preLoad);
            state_toponBanner = "Start";
            preLoadBanner(TopOn);
        }


    }

    private void showAdmobRectangle(final ViewGroup banner_container, View view) {
        /*Tiger*/
        if (app_adShowStatus == 0) {
            return;
        }
        if (admobRectAd_preLoad != null && appPreferenceVpn.getIsVpnConnected()) {
            if (!admobRectAd_preLoad.isLoading()) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
                banner_container.removeAllViews();
                banner_container.addView(admobRectAd_preLoad);
                admobRectAd_preLoad = null;
            }
        }
        preLoadRactengal();
    }

    public void preLoadRactengal() {
        if (app_adShowStatus == 0 || !new AppPreferenceVpn(activity).getIsVpnConnected()) {
            return;
        }
        if (admobRectAd_preLoad == null) {
            if (mysharedpreferences.getInt("appBannerPreLoad", 0) == 1) {
                admob_b = getRandomPlacementId(ADMOB, "B");

                if (admob_b.isEmpty()) {
                    return;
                }
                Log.e("Tiger", "preLoadRactengal: " + admob_b);
                final AdView mAdView = new AdView(activity);
                mAdView.setAdSize(AdSize.MEDIUM_RECTANGLE);
                mAdView.setAdUnitId(admob_b);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
                mAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        admobRectAd_preLoad = null;
                        preLoadRactengal();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        admobRectAd_preLoad = null;
                        preLoadRactengal();
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        admobRectAd_preLoad = mAdView;

                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });
            } else {
                Log.e("state_admobBanner", "proccess");
            }
        }


    }


    private void showAdmobBanner(final ViewGroup banner_container) {

        if (admob_b.isEmpty() || admob_AdStatus == 0) {
            nextBannerPlatform(banner_container);
            return;
        }
        if (admobBannerAd_preLoad == null) {

            final AdView mAdView = new AdView(activity);

            mAdView.setAdSize(AdSize.BANNER);

            mAdView.setAdUnitId(admob_b);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Log.e("Tiger", "showAdmobBanner: onAdFailedToLoad " + loadAdError.getMessage());
                    banner_container.removeAllViews();
                    nextBannerPlatform(banner_container);

                }

                @Override
                public void onAdOpened() {
                    super.onAdOpened();
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    Log.e("Tiger", "showAdmobBanner: onAdLoaded ");
                    if (!mAdView.isLoading()) {
                        banner_container.removeAllViews();
                        banner_container.addView(mAdView);
                    }

                    preLoadBanner(ADMOB);
                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                }
            });
        } else {
            if (!admobBannerAd_preLoad.isLoading()) {
                banner_container.removeAllViews();
                banner_container.addView(admobBannerAd_preLoad);
            }
            state_admobBanner = "Start";
            preLoadBanner(ADMOB);
        }


    }

    private void preLoadBanner(String platform) {
        if (appPreferenceVpn != null && appPreferenceVpn.getIsVpnConnected() && banner_on_off.equalsIgnoreCase("0")) {
            if (platform.equals(ADMOB)) {
                admobBannerAd_preLoad = null;
                if (mysharedpreferences.getInt("appBannerPreLoad", 0) == 1 && (state_admobBanner.equals("Start")) || state_admobBanner.equals("Fail")) {

                    if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_b.equals("random")) && !admob_b.isEmpty()) {
                        admob_b = getRandomPlacementId(ADMOB, "B");
                    }
                    if (admob_b.isEmpty()) {
                        return;
                    }
                    state_admobBanner = "Loading";

                    final AdView mAdView = new AdView(activity);
                    mAdView.setAdSize(AdSize.BANNER);
                    mAdView.setAdUnitId(admob_b);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);
                    mAdView.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            state_admobBanner = "Fail";

                        }

                        @Override
                        public void onAdOpened() {
                            super.onAdOpened();
                        }

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            state_admobBanner = "Loaded";
                            admobBannerAd_preLoad = mAdView;

                        }

                        @Override
                        public void onAdClicked() {
                            super.onAdClicked();
                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                        }
                    });
                } else {
                    Log.e("state_admobBanner", "proccess");
                }
            } else if (platform.equals(FACEBOOK)) {
                fbBannerAd_preLoad = null;
                if (mysharedpreferences.getInt("appBannerPreLoad", 0) == 1 && (state_fbBanner.equals("Start")) || state_fbBanner.equals("Fail")) {

                    if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_b.equals("random")) && !facebook_b.isEmpty()) {
                        facebook_b = getRandomPlacementId(FACEBOOK, "B");
                    }
                    if (facebook_b.isEmpty()) {
                        return;
                    }

                    state_fbBanner = "Loading";
                    final com.facebook.ads.AdView adView = new com.facebook.ads.AdView(activity, facebook_b, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                    com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
                        @Override
                        public void onError(Ad ad, AdError adError) {
                            // Ad error callback
                            state_fbBanner = "Fail";

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Ad loaded callback
                            state_fbBanner = "Loaded";
                            fbBannerAd_preLoad = adView;
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Ad clicked callback
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Ad impression logged callback
                        }
                    };

                    // Request an ad
                    adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());
                } else {
                    Log.e("state_fbBanner", "proccess");
                }
            } else if (platform.equals(TopOn)) {
                fbBannerAd_preLoad = null;
                if (mysharedpreferences.getInt("appBannerPreLoad", 0) == 1 && (state_toponBanner.equals("Start")) || state_toponBanner.equals("Fail")) {

                    if ((topon_loadAdIdsType == 0 || topon_loadAdIdsType == 2 || topon_b.equals("random")) && !topon_b.isEmpty()) {
                        topon_b = getRandomPlacementId(TopOn, "B");
                    }
                    if (topon_b.isEmpty()) {
                        return;
                    }

                    state_toponBanner = "Loading";

                    final ATBannerView mBannerView = new ATBannerView(activity);
                    mBannerView.setPlacementId(topon_b);
                    mBannerView.loadAd();

                    mBannerView.setBannerAdListener(new ATBannerExListener() {
                        @Override
                        public void onDeeplinkCallback(boolean isRefresh, ATAdInfo adInfo, boolean isSuccess) {
                            Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
                        }

                        @Override
                        public void onDownloadConfirm(Context context, ATAdInfo atAdInfo, ATNetworkConfirmInfo atNetworkConfirmInfo) {

                        }

                        @Override
                        public void onBannerLoaded() {
                            Log.i(TAG, "onBannerLoaded");
                            state_toponBanner = "Loaded";
                            toponBannerAd_preLoad = mBannerView;
                        }

                        @Override
                        public void onBannerFailed(com.anythink.core.api.AdError adError) {
                            Log.i(TAG, "onBannerFailed: " + adError.getFullErrorInfo());
                            state_toponBanner = "Fail";
                        }

                        @Override
                        public void onBannerClicked(ATAdInfo entity) {
                            Log.i(TAG, "onBannerClicked:" + entity.toString());
                        }

                        @Override
                        public void onBannerShow(ATAdInfo entity) {
                            Log.i(TAG, "onBannerShow:" + entity.toString());
                        }

                        @Override
                        public void onBannerClose(ATAdInfo entity) {
                            Log.i(TAG, "onBannerClose:" + entity.toString());
                        }

                        @Override
                        public void onBannerAutoRefreshed(ATAdInfo entity) {
                            Log.i(TAG, "onBannerAutoRefreshed:" + entity.toString());
                        }

                        @Override
                        public void onBannerAutoRefreshFail(com.anythink.core.api.AdError adError) {
                            Log.i(TAG, "onBannerAutoRefreshFail: " + adError.getFullErrorInfo());

                        }
                    });

                } else {
                    Log.e("state_toponBanner", "proccess");
                }
            }
        }
    }

    public void showNativeBanner(ViewGroup banner_container, String admobB, String facebookNB, String toponNB) {
        /*if (appPreferenceVpn != null && appPreferenceVpn.getIsVpnConnected()) {
            turnShowNativeBanner(banner_container, admobB, facebookNB, toponNB);
        }*/
        turnShowNativeBanner(banner_container, admobB, facebookNB, toponNB);
    }

    public void showNativeBanner(ViewGroup banner_container) {
        /*if (appPreferenceVpn != null && appPreferenceVpn.getIsVpnConnected()) {
            turnShowNativeBanner(banner_container, "random", "random", "random");
        }*/
        turnShowNativeBanner(banner_container, "random", "random", "random");
    }

    public void turnShowNativeBanner(ViewGroup banner_container, String admobB, String facebookNB, String toponNB) {
        this.admob_b = admobB;
        admob_n = admobB;
        this.facebook_nb = facebookNB;
        this.topon_nb = toponNB;
        if (app_adShowStatus == 0) {
            return;
        }

        count_banner++;
        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdBanner", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceBanner", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowBanner", "");


        banner_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                banner_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_banner % alernateAd.length == j) {
                    index = j;
                    banner_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (banner_sequence.size() != 0) {
                    if (!banner_sequence.get(0).equals(adSequence[j])) {
                        banner_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (banner_sequence.size() != 0) {
            new Inflate_ADS(activity).setBannerAdPlaceholder(banner_container);
            displayNativeBanner(banner_sequence.get(0), banner_container);
        }
    }

    public void displayNativeBanner(String platform, ViewGroup banner_container) {
        if (platform.equals(ADMOB) && admob_AdStatus == 1 && appPreferenceVpn.getIsVpnConnected()) {
            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_b = getRandomPlacementId(ADMOB, "B");
            }

            showNativeAdmobBanner(banner_container);
//            inflate_Small_NATIV_ADMOB1(banner_container);
        } else if ((platform.equals(FACEBOOK) && facebook_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_nb.equals("random")) && !facebook_nb.isEmpty()) {
                facebook_nb = getRandomPlacementId(FACEBOOK, "NB");
            }

            showNativeFacebookBanner(banner_container);
        } else if ((platform.equals(TopOn) && topon_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            if ((topon_loadAdIdsType == 0 || topon_loadAdIdsType == 2 || topon_nb.equals("random")) && !topon_nb.isEmpty()) {
                topon_nb = getRandomPlacementId(TopOn, "NB");
            }
            showNativeToponBanner(banner_container);
        } else if ((platform.equals(MyCustomAds) && myCustom_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            showMyCustomNativeBanner(banner_container);
        } else {
            nextNativeBannerPlatform(banner_container);
        }
    }

    private void nextNativeBannerPlatform(ViewGroup banner_container) {
        if (banner_sequence.size() != 0) {
            banner_sequence.remove(0);
            if (banner_sequence.size() != 0) {
                displayNativeBanner(banner_sequence.get(0), banner_container);
            }
        }
    }

    private void showMyCustomNativeBanner(final ViewGroup nbanner_container) {
        final CustomAdModel appModal = getMyCustomAd("NativeBanner");
        if (appModal != null) {

            View inflate2 = LayoutInflater.from(activity).inflate(R.layout.cust_native_banner, nbanner_container, false);
            ImageView imageView2 = inflate2.findViewById(R.id.icon);
            TextView textView = inflate2.findViewById(R.id.primary);
            TextView textView2 = inflate2.findViewById(R.id.secondary);

            TextView txt_rate = inflate2.findViewById(R.id.txt_rate);
            TextView txt_download = inflate2.findViewById(R.id.txt_download);
            Button btn_call_to_action = inflate2.findViewById(R.id.cta);

            try {
                String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
                String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

                if (!appNativeButtonColor.isEmpty()) {
                    btn_call_to_action.setBackgroundColor(Color.parseColor(appNativeButtonColor));
                }
                if (!appNativeTextColor.isEmpty()) {
                    btn_call_to_action.setTextColor(Color.parseColor(appNativeTextColor));
                }
            } catch (Exception e) {

            }

            Glide.with(activity).load(appModal.getApp_logo()).diskCacheStrategy(DiskCacheStrategy.ALL).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    nbanner_container.removeAllViews();
                    nextNativeBannerPlatform(nbanner_container);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            }).into(imageView2);

            textView.setText(appModal.getApp_name());
            textView2.setText(appModal.getApp_shortDecription());
            txt_rate.setText(appModal.getApp_rating());
            txt_download.setText(appModal.getApp_download());
            btn_call_to_action.setText(appModal.getApp_buttonName());
            btn_call_to_action.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }

                }
            });
            nbanner_container.removeAllViews();
            nbanner_container.addView(inflate2);
            count_custNBAd++;


        } else {
            nextNativeBannerPlatform(nbanner_container);
        }

    }

    private void showNativeFacebookBanner(final ViewGroup container) {
        if (facebook_nb.isEmpty() || facebook_AdStatus == 0) {
            nextNativeBannerPlatform(container);
            return;
        }

        if (fbNativeBannerAd_preLoad == null) {
            final NativeBannerAd nativeAd1 = new NativeBannerAd(activity, facebook_nb);
            nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                    inflate_NB_FB(nativeAd1, container);
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    container.removeAllViews();
                    nextNativeBannerPlatform(container);


                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd1 == null || nativeAd1 != ad) {
                        return;
                    }
                    nativeAd1.downloadMedia();
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            }).build());
        } else {
            state_fbNativeBanner = "Start";
            inflate_NB_FB(fbNativeBannerAd_preLoad, container);
        }
    }

    private void showNativeToponBanner(final ViewGroup container) {
        if (topon_nb.isEmpty() || topon_AdStatus == 0) {
            nextNativeBannerPlatform(container);
            return;
        }
        state_toponNativeBanner = "Start";
        final ATNativeBannerView bannerView = new ATNativeBannerView(activity);
        ATNativeBannerConfig config640 = new ATNativeBannerConfig();
        config640.bannerSize = ATNativeBannerSize.BANNER_SIZE_640x150;
        config640.ctaBgColor = 0xff000000;
        bannerView.setBannerConfig(config640);

        bannerView.setUnitId(topon_nb);
        bannerView.setVisibility(View.GONE);
        LinearLayout.LayoutParams params640 = new LinearLayout.LayoutParams(dip2px(activity, 360), dip2px(activity, 75));
        params640.topMargin = dip2px(activity, 10);
        bannerView.setBackgroundColor(0xffffffff);
        container.addView(bannerView, params640);

        state_toponNativeBanner = "Loading";
        bannerView.setAdListener(new ATNativeBannerListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "320---onAdLoaded");
                state_toponNativeBanner = "Loaded";
                bannerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdError(String errorMsg) {
                Log.i(TAG, "320---onAdError:" + errorMsg);
                state_toponNativeBanner = "Fail";
                container.removeAllViews();
                nextNativeBannerPlatform(container);
            }

            @Override
            public void onAdClick(ATAdInfo entity) {
                Log.i(TAG, "320---onAdClick:n" + entity.toString());
            }

            @Override
            public void onAdClose() {
                Log.i(TAG, "320---onAdClose----");
            }

            @Override
            public void onAdShow(ATAdInfo entity) {
                Log.i(TAG, "320---onAdShow:\n" + entity.toString());
            }

            @Override
            public void onAutoRefresh(ATAdInfo entity) {
                Log.i(TAG, "320---onAutoRefresh:\n" + entity.toString());
            }

            @Override
            public void onAutoRefreshFail(String errorMsg) {
                Log.i(TAG, "auto---onAutoRefreshFail:" + errorMsg);
            }
        });

        bannerView.loadAd(null);

    }

    public void inflate_NB_FB(NativeBannerAd nativeBannerAd, ViewGroup cardView) {
        cardView.removeAllViews();
        cardView.setVisibility(View.VISIBLE);

        nativeBannerAd.unregisterView();

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = null;
        view = inflater.inflate(R.layout.ads_nb_fb, null);
        cardView.addView(view);

        NativeAdLayout nativeAdLayout = view.findViewById(R.id.nativview);
        LinearLayout adChoicesContainer = view.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        TextView nativeAdCallToAction = view.findViewById(R.id.nb_ad_call_to_action);

        try {
            SharedPreferences mysharedpreferences = activity.getSharedPreferences(activity.getPackageName(), Context.MODE_PRIVATE);
            String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
            String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

            if (!appNativeButtonColor.isEmpty()) {
                nativeAdCallToAction.setBackgroundColor(Color.parseColor(appNativeButtonColor));
            }
            if (!appNativeTextColor.isEmpty()) {
                nativeAdCallToAction.setTextColor(Color.parseColor(appNativeTextColor));
            }
        } catch (Exception e) {

        }

        TextView nativeAdTitle = view.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = view.findViewById(R.id.native_ad_social_context);
        MediaView nativeAdIconView = view.findViewById(R.id.native_icon_view);
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdBodyText());

        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdIconView);
        clickableViews.add(nativeAdSocialContext);
        nativeBannerAd.registerViewForInteraction(view, nativeAdIconView, clickableViews);


        fbNativeBannerAd_preLoad = null;

        if (mysharedpreferences.getInt("appBannerPreLoad", 0) == 1 && (state_fbNativeBanner.equals("Start")) || state_fbNativeBanner.equals("Fail")) {

            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_nb.equals("random")) && !facebook_nb.isEmpty()) {
                facebook_nb = getRandomPlacementId(FACEBOOK, "NB");
            }
            if (facebook_nb.isEmpty()) {
                return;
            }
            state_fbNativeBanner = "Loading";

            final NativeBannerAd nativeAd1 = new NativeBannerAd(activity, facebook_nb);
            nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                    state_fbNativeBanner = "Loaded";
                    fbNativeBannerAd_preLoad = nativeAd1;
                }

                @Override
                public void onError(Ad ad, AdError adError) {

                    state_fbNativeBanner = "Fail";
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd1 == null || nativeAd1 != ad) {
                        return;
                    }
                    nativeAd1.downloadMedia();
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            }).build());

        } else {
            Log.e("fb_nativebanner_state", "proccess");
        }
    }

    private void showNativeAdmobBanner(final ViewGroup banner_container) {

        if (admob_n.isEmpty() || admob_AdStatus == 0) {
            nextNativeBannerPlatform(banner_container);
            return;
        }

        if (googlesmallNativeAd == null) {
            AdLoader.Builder builder = new AdLoader.Builder(activity, ADMOB_N[0]);

            builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
                    banner_container.removeAllViews();
                    inflate_Small_NATIV_ADMOB1(unifiedNativeAd, banner_container);
                    preloadAdmobSmallnative();
                }

            });

            VideoOptions videoOptions = new VideoOptions.Builder().setStartMuted(true).build();

            com.google.android.gms.ads.formats.NativeAdOptions adOptions = new com.google.android.gms.ads.formats.NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

            builder.withNativeAdOptions(adOptions);

            AdLoader adLoader = builder.withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError errorCode) {
                    googlesmallNativeAd = null;
                    Log.e(TAG, "onAdFailedToLoad: " + errorCode.getMessage());
                    banner_container.removeAllViews();
                    nextNativeBannerPlatform(banner_container);
                }

            }).build();

            adLoader.loadAd(new AdManagerAdRequest.Builder().build());
        } else {
            banner_container.removeAllViews();
            inflate_Small_NATIV_ADMOB1(googlesmallNativeAd, banner_container);
        }
    }

//    private void showNativeAdmobBanner(final ViewGroup banner_container) {
//
//        if (admob_b.isEmpty() || admob_AdStatus == 0) {
//            nextNativeBannerPlatform(banner_container);
//            return;
//        }
//
//        if (admobBannerAd_preLoad == null) {
//            final AdView mAdView = new AdView(activity);
//            mAdView.setAdSize(AdSize.SMART_BANNER);
//            mAdView.setAdUnitId(admob_b);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);
//            mAdView.setAdListener(new AdListener() {
//                @Override
//                public void onAdClosed() {
//                    super.onAdClosed();
//                }
//
//                @Override
//                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
//                    super.onAdFailedToLoad(loadAdError);
//                    banner_container.removeAllViews();
//
//                    nextNativeBannerPlatform(banner_container);
//
//                }
//
//                @Override
//                public void onAdOpened() {
//                    super.onAdOpened();
//                }
//
//                @Override
//                public void onAdLoaded() {
//                    super.onAdLoaded();
//                    banner_container.removeAllViews();
//                    banner_container.addView(mAdView);
//
//                    preLoadBanner(ADMOB);
//                }
//
//                @Override
//                public void onAdClicked() {
//                    super.onAdClicked();
//                }
//
//                @Override
//                public void onAdImpression() {
//                    super.onAdImpression();
//                }
//            });
//        } else {
//            banner_container.removeAllViews();
//            banner_container.addView(admobBannerAd_preLoad);
//
//            state_admobBanner = "Start";
//            preLoadBanner(ADMOB);
//        }
//
//
//    }


    public void inflate_Small_NATIV_ADMOB1(NativeAd nativeAd, ViewGroup cardView) {

        cardView.setVisibility(View.VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.ads_nativ_admob_small_main, null);
        cardView.removeAllViews();
        cardView.addView(view);

        NativeAdView adView = view.findViewById(R.id.uadview);
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }


        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);
    }

    public void showNative(ViewGroup nativeAdContainer, String admobN, String facebookN, String toponN) {
        turnShowNative(nativeAdContainer, admobN, facebookN, toponN);
    }

    public void showNative(ViewGroup nativeAdContainer) {
        if (appPreferenceVpn != null) {
            if (custome_banner.equalsIgnoreCase("0") && appPreferenceVpn.getIsVpnConnected()) {
                admob_b = getRandomPlacementId(ADMOB, "B");
                showAdmobRectangle(nativeAdContainer, null);
            } else {
                turnShowNative(nativeAdContainer, "random", "random", "random");
            }
        }

      /*  if (custome_banner.equalsIgnoreCase("0")) {
            admob_b = getRandomPlacementId(ADMOB, "B");
            showAdmobRectangle(nativeAdContainer, null);
        } else {
            turnShowNative(nativeAdContainer, "random", "random", "random");
        }*/
    }

    public void showNative1(ViewGroup nativeAdContainer, View view) {
        if (appPreferenceVpn != null) {
            if (custome_banner.equalsIgnoreCase("0") && appPreferenceVpn.getIsVpnConnected()) {
                admob_b = getRandomPlacementId(ADMOB, "B");
                showAdmobRectangle(nativeAdContainer, view);
            } else {
                turnShowNative(nativeAdContainer, "random", "random", "random", view);
            }
        }
    }

    public void turnShowNative(ViewGroup nativeAdContainer, String admobN, String facebookN, String toponN, View view) {
        this.admob_n = admobN;
        this.facebook_n = facebookN;
        this.topon_n = toponN;
        if (app_adShowStatus == 0) {
            return;
        }

        count_native++;
        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdNative", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceNative", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowNative", "");


        native_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                native_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_native % alernateAd.length == j) {
                    index = j;
                    native_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (native_sequence.size() != 0) {
                    if (!native_sequence.get(0).equals(adSequence[j])) {
                        native_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (native_sequence.size() != 0) {
            new Inflate_ADS(activity).setNativeAdPlaceholder(nativeAdContainer);
            displayNative(native_sequence.get(0), nativeAdContainer, view);
        }
    }

    private void displayNative(String platform, ViewGroup nativeAdContainer, View view) {

        if (platform.equals(ADMOB) && admob_AdStatus == 1 && appPreferenceVpn.getIsVpnConnected()) {

            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_n = getRandomPlacementId(ADMOB, "N");
            }

            showAdmobNative(nativeAdContainer, view);
        } else if ((platform.equals(FACEBOOK) && facebook_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_n.equals("random")) && !facebook_n.isEmpty()) {
                facebook_n = getRandomPlacementId(FACEBOOK, "N");
            }

            showFacebookNative(nativeAdContainer, view);
        } else if ((platform.equals(TopOn) && topon_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            if ((topon_loadAdIdsType == 0 || topon_loadAdIdsType == 2 || topon_n.equals("random")) && !topon_n.isEmpty()) {
                topon_n = getRandomPlacementId(TopOn, "N");
            }

            showToponNative(nativeAdContainer, view);
        } else if ((platform.equals(MyCustomAds) && myCustom_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            showMyCustomNative(nativeAdContainer, view);
        } else {
            nextNativePlatform(nativeAdContainer, view);
        }
    }

    private void nextNativePlatform(ViewGroup nativeAdContainer, View view) {
        if (native_sequence.size() != 0) {
            native_sequence.remove(0);
            if (native_sequence.size() != 0) {
                displayNative(native_sequence.get(0), nativeAdContainer, view);
            }
        }
    }

    private void showMyCustomNative(final ViewGroup nativeAdContainer, final View view) {

        final CustomAdModel appModal = getMyCustomAd("Native");
        if (appModal != null) {
            final View inflate = LayoutInflater.from(activity).inflate(R.layout.cust_native, nativeAdContainer, false);
            ImageView imageView = inflate.findViewById(R.id.media_view);
            ImageView imageView2 = inflate.findViewById(R.id.icon);
            TextView textView = inflate.findViewById(R.id.primary);
            TextView textView2 = inflate.findViewById(R.id.body);
            TextView txt_rate = inflate.findViewById(R.id.txt_rate);
            TextView txt_download = inflate.findViewById(R.id.txt_download);
            Button btn_call_to_action = inflate.findViewById(R.id.cta);
            LinearLayout ll_native = inflate.findViewById(R.id.ll_native);
            try {
                String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
                String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

                if (!appNativeButtonColor.isEmpty()) {
                    btn_call_to_action.setBackgroundColor(Color.parseColor(appNativeButtonColor));
                }
                if (!appNativeTextColor.isEmpty()) {
                    btn_call_to_action.setTextColor(Color.parseColor(appNativeTextColor));
                }
            } catch (Exception e) {

            }


            textView.setText(appModal.getApp_name());
            textView2.setText(appModal.getApp_shortDecription());
            txt_rate.setText(appModal.getApp_rating());
            txt_download.setText(appModal.getApp_download());
            btn_call_to_action.setText(appModal.getApp_buttonName());

            ll_native.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }
                }
            });
            btn_call_to_action.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }
                }
            });

            Glide.with(activity).load(appModal.getApp_banner()).diskCacheStrategy(DiskCacheStrategy.ALL).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    nativeAdContainer.removeAllViews();
                    nextNativePlatform(nativeAdContainer, view);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                    return false;
                }
            }).into(imageView);

            Glide.with(activity).load(appModal.getApp_logo()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView2);

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }
                }
            });
            if (view != null) {
                view.setVisibility(View.GONE);
            }
            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(inflate);
            count_custNativeAd++;


        } else {
            nextNativePlatform(nativeAdContainer, view);
        }


    }

    private void showToponNative(final ViewGroup nativeAdContainer, final View view) {
        if (topon_n.isEmpty() || topon_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer, view);
            return;
        }

        initCloseView();
        int padding = dip2px(10);
        final int containerHeight = dip2px(340);
        final int adViewWidth = activity.getResources().getDisplayMetrics().widthPixels - 2 * padding;
        final int adViewHeight = containerHeight - 2 * padding;

        final NativeDemoRender anyThinkRender = new NativeDemoRender(activity);
        anyThinkRender.setCloseView(mCloseView);

        atNatives = new ATNative(activity, topon_n, new ATNativeNetworkListener() {
            @Override
            public void onNativeAdLoaded() {
                Log.i(TAG, "onNativeAdLoaded");
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
                nativeAdContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNativeAdLoadFail(com.anythink.core.api.AdError adError) {
                Log.i(TAG, "onNativeAdLoadFail, " + adError.getFullErrorInfo());
                nextNativePlatform(nativeAdContainer, view);
            }
        });
        if (anyThinkNativeAdView == null) {
            anyThinkNativeAdView = new ATNativeAdView(activity);
        }

        Map<String, Object> localMap = new HashMap<>();

        // since v5.6.4
        localMap.put(ATAdConst.KEY.AD_WIDTH, adViewWidth);
        localMap.put(ATAdConst.KEY.AD_HEIGHT, adViewHeight);
        atNatives.setLocalExtra(localMap);
        atNatives.makeAdRequest();

        com.anythink.nativead.api.NativeAd nativeAd = atNatives.getNativeAd();
        if (nativeAd != null) {
            if (anyThinkNativeAdView != null) {
                anyThinkNativeAdView.removeAllViews();

                if (anyThinkNativeAdView.getParent() == null) {
                    nativeAdContainer.addView(anyThinkNativeAdView, new LinearLayout.LayoutParams(activity.getResources().getDisplayMetrics().widthPixels, containerHeight));
                }
            }
            if (mNativeAd != null) {
                mNativeAd.destory();
            }
            mNativeAd = nativeAd;
            mNativeAd.setNativeEventListener(new ATNativeEventExListener() {
                @Override
                public void onDeeplinkCallback(ATNativeAdView view, ATAdInfo adInfo, boolean isSuccess) {
                    Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
                }

                @Override
                public void onAdImpressed(ATNativeAdView view, ATAdInfo entity) {
                    Log.i(TAG, "native ad onAdImpressed:\n" + entity.toString());
                }

                @Override
                public void onAdClicked(ATNativeAdView view, ATAdInfo entity) {
                    Log.i(TAG, "native ad onAdClicked:\n" + entity.toString());
                }

                @Override
                public void onAdVideoStart(ATNativeAdView view) {
                    Log.i(TAG, "native ad onAdVideoStart");
                }

                @Override
                public void onAdVideoEnd(ATNativeAdView view) {
                    Log.i(TAG, "native ad onAdVideoEnd");
                }

                @Override
                public void onAdVideoProgress(ATNativeAdView view, int progress) {
                    Log.i(TAG, "native ad onAdVideoProgress:" + progress);
                }
            });
            mNativeAd.setDislikeCallbackListener(new ATNativeDislikeListener() {
                @Override
                public void onAdCloseButtonClick(ATNativeAdView view, ATAdInfo entity) {
                    Log.i(TAG, "native ad onAdCloseButtonClick");
                    if (view.getParent() != null) {
                        ((ViewGroup) view.getParent()).removeView(view);
                        view.removeAllViews();
                    }
                }
            });
            try {
                mNativeAd.renderAdView(anyThinkNativeAdView, anyThinkRender);
            } catch (Exception e) {

            }
            anyThinkNativeAdView.addView(mCloseView);
            anyThinkNativeAdView.setVisibility(View.VISIBLE);
            mNativeAd.prepare(anyThinkNativeAdView, anyThinkRender.getClickView(), null);
        }
        anyThinkNativeAdView.setPadding(padding, padding, padding, padding);
    }

    private void showFacebookNative(final ViewGroup nativeAdContainer, final View view) {
        if (facebook_n.isEmpty() || facebook_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer, view);
            return;
        }


        if (fbNativeAd_preLoad == null) {
            final com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(activity, facebook_n);

            nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    nextNativePlatform(nativeAdContainer, view);


                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }
                    if (nativeAd == null || nativeAd != ad) {
                        return;
                    }

                    inflate_NATIV_FB(nativeAd, nativeAdContainer, view);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            }).build());
        } else {
            state_fbNative = "Start";
            inflate_NATIV_FB(fbNativeAd_preLoad, nativeAdContainer, view);
        }

    }

    public void inflate_NATIV_FB(com.facebook.ads.NativeAd nativeAd, ViewGroup card, View view1) {

        card.setVisibility(View.VISIBLE);
        nativeAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        View adView;
        if (mysharedpreferences.getString("appNativeAdSize", "").equals("small")) {
            adView = inflater.inflate(R.layout.ads_nativ_fb_small, null);
        } else {
            adView = inflater.inflate(R.layout.ads_nativ_fb, null);
        }
        if (view1 != null) {
            view1.setVisibility(View.GONE);
        }
        card.removeAllViews();
        card.addView(adView);

        NativeAdLayout nativeAdLayout = adView.findViewById(R.id.nativview);

        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        try {
            String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
            String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

            if (!appNativeButtonColor.isEmpty()) {
                nativeAdCallToAction.setBackgroundColor(Color.parseColor(appNativeButtonColor));
            }
            if (!appNativeTextColor.isEmpty()) {
                nativeAdCallToAction.setTextColor(Color.parseColor(appNativeTextColor));
            }
        } catch (Exception e) {

        }

        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);

        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
//        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
//        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdBody);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdIcon);
//        clickableViews.add(nativeAdSocialContext);

        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);


        fbNativeAd_preLoad = null;

        if (mysharedpreferences.getInt("appNativePreLoad", 0) == 1 && (state_fbNative.equals("Start")) || state_fbNative.equals("Fail")) {

            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_n.equals("random")) && !facebook_n.isEmpty()) {
                facebook_n = getRandomPlacementId(FACEBOOK, "N");
            }
            if (facebook_n.isEmpty()) {
                return;
            }
            state_fbNative = "Loading";

            final com.facebook.ads.NativeAd nativeAd1 = new com.facebook.ads.NativeAd(activity, facebook_n);

            nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                    state_fbNative = "Fail";
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd1 == null || nativeAd1 != ad) {
                        return;
                    }
                    fbNativeAd_preLoad = nativeAd1;
                    state_fbNative = "Loaded";
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            }).build());
        } else {
            Log.e("fb_state", "proccess");
        }
    }


    private void showAdmobNative(final ViewGroup nativeAdContainer, final View view) {
        if (admob_n.isEmpty() || admob_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer, view);
            return;
        }


        if (admobNativeAd_preLoad == null) {
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    // Show the ad.
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }
                    state_admobNative = "Start";
                    inflate_NATIV_ADMOB(nativeAd, nativeAdContainer, view);
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Handle the failure by logging, altering the UI, and so on.

                    nextNativePlatform(nativeAdContainer, view);
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } else {
            state_admobNative = "Start";
            inflate_NATIV_ADMOB(admobNativeAd_preLoad, nativeAdContainer, view);
        }
    }

    public void inflate_NATIV_ADMOB(com.google.android.gms.ads.nativead.NativeAd nativeAd, ViewGroup cardView, View view1) {
        cardView.setVisibility(View.VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(activity);

        View view;

        if (mysharedpreferences.getString("appNativeAdSize", "").equals("small")) {
            view = inflater.inflate(R.layout.ads_nativ_admob_small, null);
        } else {
            view = inflater.inflate(R.layout.ads_nativ_admob, null);
        }
        if (view1 != null) {
            view1.setVisibility(View.GONE);
        }
        cardView.removeAllViews();
        cardView.addView(view);

        NativeAdView adView = view.findViewById(R.id.uadview);
        TextView ad_call_to_action = adView.findViewById(R.id.ad_call_to_action);

        try {
            String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
            String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

            if (!appNativeButtonColor.isEmpty()) {
                ad_call_to_action.setBackgroundColor(Color.parseColor(appNativeButtonColor));
            }
            if (!appNativeTextColor.isEmpty()) {
                ad_call_to_action.setTextColor(Color.parseColor(appNativeTextColor));
            }
        } catch (Exception e) {
        }

        adView.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(ad_call_to_action);
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }


        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);

        admobNativeAd_preLoad = null;

        if (mysharedpreferences.getInt("appNativePreLoad", 0) == 1 && (state_admobNative.equals("Start")) || state_admobNative.equals("Fail")) {

            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_n = getRandomPlacementId(ADMOB, "N");
            }
            if (admob_n.isEmpty()) {
                return;
            }
            state_admobNative = "Loading";
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    // Show the ad.
                    Log.e("====", "onNativeAdLoaded: 1");
                    admobNativeAd_preLoad = nativeAd;
                    state_admobNative = "Loaded";
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Handle the failure by logging, altering the UI, and so on.
                    state_admobNative = "Fail";
                }
            }).withNativeAdOptions(new com.google.android.gms.ads.nativead.NativeAdOptions.Builder().setRequestCustomMuteThisAd(true).build()).build();


            adLoader.loadAd(new AdRequest.Builder().build());


        } else {
            Log.e("admob_state", "proccess");
        }

    }


    public void turnShowNative(ViewGroup nativeAdContainer, String admobN, String facebookN, String toponN) {
        this.admob_n = admobN;
        this.facebook_n = facebookN;
        this.topon_n = toponN;
        if (app_adShowStatus == 0) {
            return;
        }

        count_native++;
        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdNative", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceNative", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowNative", "");


        native_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");
            for (int i = 0; i < adSequence.length; i++) {
                native_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_native % alernateAd.length == j) {
                    index = j;
                    native_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (native_sequence.size() != 0) {
                    if (!native_sequence.get(0).equals(adSequence[j])) {
                        native_sequence.add(adSequence[j]);
                    }
                }
            }
        }

        if (native_sequence.size() != 0) {
            new Inflate_ADS(activity).setNativeAdPlaceholder(nativeAdContainer);
            displayNative(native_sequence.get(0), nativeAdContainer);
        }
    }

    private void displayNative(String platform, ViewGroup nativeAdContainer) {

        if (platform.equals(ADMOB) && admob_AdStatus == 1 && appPreferenceVpn.getIsVpnConnected()) {

            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_n = getRandomPlacementId(ADMOB, "N");
            }

            showAdmobNative(nativeAdContainer);
        } else if ((platform.equals(FACEBOOK) && facebook_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_n.equals("random")) && !facebook_n.isEmpty()) {
                facebook_n = getRandomPlacementId(FACEBOOK, "N");
            }

            showFacebookNative(nativeAdContainer);
        } else if ((platform.equals(TopOn) && topon_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            if ((topon_loadAdIdsType == 0 || topon_loadAdIdsType == 2 || topon_n.equals("random")) && !topon_n.isEmpty()) {
                topon_n = getRandomPlacementId(TopOn, "N");
            }

            showToponNative(nativeAdContainer);
        } else if ((platform.equals(MyCustomAds) && myCustom_AdStatus == 1) || !appPreferenceVpn.getIsVpnConnected()) {
            showMyCustomNative(nativeAdContainer);
        } else {
            nextNativePlatform(nativeAdContainer);
        }
    }

    private void nextNativePlatform(ViewGroup nativeAdContainer) {
        if (native_sequence.size() != 0) {
            native_sequence.remove(0);
            if (native_sequence.size() != 0) {
                displayNative(native_sequence.get(0), nativeAdContainer);
            }
        }
    }

    private void showMyCustomNative(final ViewGroup nativeAdContainer) {

        final CustomAdModel appModal = getMyCustomAd("Native");
        if (appModal != null) {
            final View inflate = LayoutInflater.from(activity).inflate(R.layout.cust_native, nativeAdContainer, false);
            ImageView imageView = inflate.findViewById(R.id.media_view);
            ImageView imageView2 = inflate.findViewById(R.id.icon);
            TextView textView = inflate.findViewById(R.id.primary);
            TextView textView2 = inflate.findViewById(R.id.body);
            TextView txt_rate = inflate.findViewById(R.id.txt_rate);
            TextView txt_download = inflate.findViewById(R.id.txt_download);
            Button btn_call_to_action = inflate.findViewById(R.id.cta);
            LinearLayout ll_native = inflate.findViewById(R.id.ll_native);
            try {
                String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
                String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

                if (!appNativeButtonColor.isEmpty()) {
                    btn_call_to_action.setBackgroundColor(Color.parseColor(appNativeButtonColor));
                }
                if (!appNativeTextColor.isEmpty()) {
                    btn_call_to_action.setTextColor(Color.parseColor(appNativeTextColor));
                }
            } catch (Exception e) {

            }


            textView.setText(appModal.getApp_name());
            textView2.setText(appModal.getApp_shortDecription());
            txt_rate.setText(appModal.getApp_rating());
            txt_download.setText(appModal.getApp_download());
            btn_call_to_action.setText(appModal.getApp_buttonName());

            ll_native.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }
                }
            });
            btn_call_to_action.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }
                }
            });

            Glide.with(activity).load(appModal.getApp_banner()).diskCacheStrategy(DiskCacheStrategy.ALL).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    nativeAdContainer.removeAllViews();
                    nextNativePlatform(nativeAdContainer);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                    return false;
                }
            }).into(imageView);

            Glide.with(activity).load(appModal.getApp_logo()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView2);

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String action_str = appModal.getApp_packageName();
                    if (action_str.contains("http")) {
                        Uri marketUri = Uri.parse(action_str);
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                        activity.startActivity(marketIntent);
                    } else {
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + action_str)));
                    }
                }
            });

            nativeAdContainer.removeAllViews();
            nativeAdContainer.addView(inflate);
            count_custNativeAd++;


        } else {
            nextNativePlatform(nativeAdContainer);
        }


    }

    private void showFacebookNative(final ViewGroup nativeAdContainer) {
        if (facebook_n.isEmpty() || facebook_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer);
            return;
        }


        if (fbNativeAd_preLoad == null) {
            final com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(activity, facebook_n);

            nativeAd.loadAd(nativeAd.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    nextNativePlatform(nativeAdContainer);


                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd == null || nativeAd != ad) {
                        return;
                    }

                    inflate_NATIV_FB(nativeAd, nativeAdContainer);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            }).build());
        } else {
            state_fbNative = "Start";
            inflate_NATIV_FB(fbNativeAd_preLoad, nativeAdContainer);
        }

    }

    private void showToponNative(final ViewGroup nativeAdContainer) {
        if (topon_n.isEmpty() || topon_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer);
            return;
        }

        initCloseView();
        int padding = dip2px(10);
        final int containerHeight = dip2px(340);
        final int adViewWidth = activity.getResources().getDisplayMetrics().widthPixels - 2 * padding;
        final int adViewHeight = containerHeight - 2 * padding;

        final NativeDemoRender anyThinkRender = new NativeDemoRender(activity);
        anyThinkRender.setCloseView(mCloseView);

        atNatives = new ATNative(activity, topon_n, new ATNativeNetworkListener() {
            @Override
            public void onNativeAdLoaded() {
                Log.i(TAG, "onNativeAdLoaded");
                nativeAdContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNativeAdLoadFail(com.anythink.core.api.AdError adError) {
                Log.i(TAG, "onNativeAdLoadFail, " + adError.getFullErrorInfo());
                nextNativePlatform(nativeAdContainer);
            }
        });
        if (anyThinkNativeAdView == null) {
            anyThinkNativeAdView = new ATNativeAdView(activity);
        }

        Map<String, Object> localMap = new HashMap<>();

        // since v5.6.4
        localMap.put(ATAdConst.KEY.AD_WIDTH, adViewWidth);
        localMap.put(ATAdConst.KEY.AD_HEIGHT, adViewHeight);
        atNatives.setLocalExtra(localMap);
        atNatives.makeAdRequest();

        com.anythink.nativead.api.NativeAd nativeAd = atNatives.getNativeAd();
        if (nativeAd != null) {
            if (anyThinkNativeAdView != null) {
                anyThinkNativeAdView.removeAllViews();

                if (anyThinkNativeAdView.getParent() == null) {
                    nativeAdContainer.addView(anyThinkNativeAdView, new LinearLayout.LayoutParams(activity.getResources().getDisplayMetrics().widthPixels, containerHeight));
                }
            }
            if (mNativeAd != null) {
                mNativeAd.destory();
            }
            mNativeAd = nativeAd;
            mNativeAd.setNativeEventListener(new ATNativeEventExListener() {
                @Override
                public void onDeeplinkCallback(ATNativeAdView view, ATAdInfo adInfo, boolean isSuccess) {
                    Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
                }

                @Override
                public void onAdImpressed(ATNativeAdView view, ATAdInfo entity) {
                    Log.i(TAG, "native ad onAdImpressed:\n" + entity.toString());
                }

                @Override
                public void onAdClicked(ATNativeAdView view, ATAdInfo entity) {
                    Log.i(TAG, "native ad onAdClicked:\n" + entity.toString());
                }

                @Override
                public void onAdVideoStart(ATNativeAdView view) {
                    Log.i(TAG, "native ad onAdVideoStart");
                }

                @Override
                public void onAdVideoEnd(ATNativeAdView view) {
                    Log.i(TAG, "native ad onAdVideoEnd");
                }

                @Override
                public void onAdVideoProgress(ATNativeAdView view, int progress) {
                    Log.i(TAG, "native ad onAdVideoProgress:" + progress);
                }
            });
            mNativeAd.setDislikeCallbackListener(new ATNativeDislikeListener() {
                @Override
                public void onAdCloseButtonClick(ATNativeAdView view, ATAdInfo entity) {
                    Log.i(TAG, "native ad onAdCloseButtonClick");
                    if (view.getParent() != null) {
                        ((ViewGroup) view.getParent()).removeView(view);
                        view.removeAllViews();
                    }
                }
            });
            try {
                mNativeAd.renderAdView(anyThinkNativeAdView, anyThinkRender);
            } catch (Exception e) {

            }
            anyThinkNativeAdView.addView(mCloseView);
            anyThinkNativeAdView.setVisibility(View.VISIBLE);
            mNativeAd.prepare(anyThinkNativeAdView, anyThinkRender.getClickView(), null);
        }
        anyThinkNativeAdView.setPadding(padding, padding, padding, padding);
    }

    public void inflate_NATIV_FB(com.facebook.ads.NativeAd nativeAd, ViewGroup card) {

        card.setVisibility(View.VISIBLE);
        nativeAd.unregisterView();
        LayoutInflater inflater = LayoutInflater.from(activity);
        View adView;
        if (mysharedpreferences.getString("appNativeAdSize", "").equals("small")) {
            adView = inflater.inflate(R.layout.ads_nativ_fb_small, null);
        } else {
            adView = inflater.inflate(R.layout.ads_nativ_fb, null);
        }

        card.removeAllViews();
        card.addView(adView);

        NativeAdLayout nativeAdLayout = adView.findViewById(R.id.nativview);

        TextView nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        try {
            String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
            String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

            if (!appNativeButtonColor.isEmpty()) {
                nativeAdCallToAction.setBackgroundColor(Color.parseColor(appNativeButtonColor));
            }
            if (!appNativeTextColor.isEmpty()) {
                nativeAdCallToAction.setTextColor(Color.parseColor(appNativeTextColor));
            }
        } catch (Exception e) {

        }

        LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);

        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
//        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
//        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdBody);
        clickableViews.add(nativeAdCallToAction);
        clickableViews.add(nativeAdIcon);
//        clickableViews.add(nativeAdSocialContext);

        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);


        fbNativeAd_preLoad = null;

        if (mysharedpreferences.getInt("appNativePreLoad", 0) == 1 && (state_fbNative.equals("Start")) || state_fbNative.equals("Fail")) {

            if ((facebook_loadAdIdsType == 0 || facebook_loadAdIdsType == 2 || facebook_n.equals("random")) && !facebook_n.isEmpty()) {
                facebook_n = getRandomPlacementId(FACEBOOK, "N");
            }
            if (facebook_n.isEmpty()) {
                return;
            }
            state_fbNative = "Loading";

            final com.facebook.ads.NativeAd nativeAd1 = new com.facebook.ads.NativeAd(activity, facebook_n);

            nativeAd1.loadAd(nativeAd1.buildLoadAdConfig().withAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                    state_fbNative = "Fail";
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    if (nativeAd1 == null || nativeAd1 != ad) {
                        return;
                    }
                    fbNativeAd_preLoad = nativeAd1;
                    state_fbNative = "Loaded";
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            }).build());
        } else {
            Log.e("fb_state", "proccess");
        }
    }

    private void showAdmobNative(final ViewGroup nativeAdContainer) {
        if (admob_n.isEmpty() || admob_AdStatus == 0) {
            nextNativePlatform(nativeAdContainer);
            return;
        }


        if (admobNativeAd_preLoad == null) {
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    // Show the ad.

                    inflate_NATIV_ADMOB(nativeAd, nativeAdContainer);
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Handle the failure by logging, altering the UI, and so on.

                    nextNativePlatform(nativeAdContainer);
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        } else {
            state_admobNative = "Start";
            inflate_NATIV_ADMOB(admobNativeAd_preLoad, nativeAdContainer);
        }
    }

    public void inflate_NATIV_ADMOB(com.google.android.gms.ads.nativead.NativeAd nativeAd, ViewGroup cardView) {
        cardView.setVisibility(View.VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(activity);

        View view;
        if (mysharedpreferences.getString("appNativeAdSize", "").equals("small")) {
            view = inflater.inflate(R.layout.ads_nativ_admob_small, null);
        } else {
            view = inflater.inflate(R.layout.ads_nativ_admob, null);
        }
        cardView.removeAllViews();
        cardView.addView(view);

        NativeAdView adView = view.findViewById(R.id.uadview);
        TextView ad_call_to_action = adView.findViewById(R.id.ad_call_to_action);

        try {
            String appNativeButtonColor = mysharedpreferences.getString("appAdsButtonColor", "");
            String appNativeTextColor = mysharedpreferences.getString("appAdsButtonTextColor", "");

            if (!appNativeButtonColor.isEmpty()) {
                ad_call_to_action.setBackgroundColor(Color.parseColor(appNativeButtonColor));
            }
            if (!appNativeTextColor.isEmpty()) {
                ad_call_to_action.setTextColor(Color.parseColor(appNativeTextColor));
            }
        } catch (Exception e) {
        }

        adView.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(ad_call_to_action);
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((TextView) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }


        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.setNativeAd(nativeAd);

        admobNativeAd_preLoad = null;

        if (mysharedpreferences.getInt("appNativePreLoad", 0) == 1 && (state_admobNative.equals("Start")) || state_admobNative.equals("Fail")) {

            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_n = getRandomPlacementId(ADMOB, "N");
            }
            if (admob_n.isEmpty()) {
                return;
            }
            state_admobNative = "Loading";
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    // Show the ad.
                    admobNativeAd_preLoad = nativeAd;
                    state_admobNative = "Loaded";
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Handle the failure by logging, altering the UI, and so on.
                    state_admobNative = "Fail";
                }
            }).withNativeAdOptions(new com.google.android.gms.ads.nativead.NativeAdOptions.Builder().setRequestCustomMuteThisAd(true).build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());

        } else {
            Log.e("admob_state", "proccess");
        }

    }


    public void loadAndShowInterstitialAd(Context context, boolean loading_dialog, MyCallback myCallback, String specific_platform, int how_many_clicks) {
        turnLoadAndShowInterstitialAd(context, myCallback, loading_dialog, how_many_clicks, specific_platform, "", "", "");
    }

   /* public void onClickLoadAndShowInterstitialAd(Context context, boolean loading_dialog, MyCallback myCallback, String specific_platform, int how_many_clicks) {
        onClickTurnLoadAndShowInterstitialAd(context, myCallback, loading_dialog, how_many_clicks, specific_platform, "", "", "");
    }*/

    public void loadAndShowInterstitialAd(Context context, boolean loading_dialog, MyCallback myCallback, String specific_platform, int how_many_clicks, String admob_i, String facebook_i, String topon_i) {
        turnLoadAndShowInterstitialAd(context, myCallback, loading_dialog, how_many_clicks, specific_platform, admob_i, facebook_i, topon_i);
    }


    public void onClickTurnLoadAndShowInterstitialAd(final Context context, MyCallback myCallback2, final boolean loading_dialog, int how_many_clicks, String specific_platform, String admob_i, String facebook_i, String topon_i) {
        myCallback = myCallback2;

        count_click++;

        if (app_adShowStatus == 0) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }
        if (how_many_clicks != 0) {
            if (count_click % how_many_clicks != 0) {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                return;
            }
        }

        count_click_for_alt++;


        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdInterstitial", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceInterstitial", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowInterstitial", "");

        if (!specific_platform.isEmpty()) {
            app_howShowAd = 0;
            adPlatformSequence = specific_platform;
        }


        interstitial_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");

            for (int i = 0; i < adSequence.length; i++) {
                interstitial_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_click_for_alt % alernateAd.length == j) {
                    index = j;
                    interstitial_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (interstitial_sequence.size() != 0) {
                    if (!interstitial_sequence.get(0).equals(adSequence[j])) {
                        interstitial_sequence.add(adSequence[j]);
                    }
                }

            }
        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }

        if (interstitial_sequence.size() != 0) {
            String platform = interstitial_sequence.get(0);
            final boolean[] is_callback = {true};

            dialog = new Dialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
            dialog.setContentView(view);
            dialog.setCancelable(true);
            Window window = dialog.getWindow();
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            if (loading_dialog == true) {
                dialog.show();
            }


            if (platform.equals(ADMOB) && admob_AdStatus == 1) {

                if (admob_i.isEmpty()) {
                    admob_i = getRandomPlacementId(ADMOB, "I");
                }
                if (admob_loadAdIdsType == 0) {
                    admob_i = getRandomPlacementId(ADMOB, "I");
                }

                final InterstitialAd[] mInterstitialAd = new InterstitialAd[1];
                mInterstitialAd[0] = null;
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(activity, admob_i, adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd[0] = interstitialAd;

                        is_callback[0] = false;
                        mInterstitialAd[0].show((Activity) context);
                        mInterstitialAd[0].setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.

                                interstitialCallBack();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                // Called when fullscreen content failed to show.

                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.

                                mInterstitialAd[0] = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd[0] = null;
                        is_callback[0] = false;

                        interstitialCallBack();
                    }
                });

//                new CountDownTimer(ad_dialog_time_in_second_loadAndShow * 1000, 10) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second_loadAndShow;
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        if (loading_dialog == true) {
//                            dialog.dismiss();
//                        }
//                        if (mInterstitialAd[0] != null) {
//                            mInterstitialAd[0].show((Activity) context);
//                        } else {
//                            if (is_callback[0] == true) {
//                                interstitialCallBack();
//                            }
//                        }
//                    }
//                }.start();


            } else if (platform.equals(FACEBOOK) && facebook_AdStatus == 1) {

                if (facebook_i.isEmpty()) {
                    facebook_i = getRandomPlacementId(FACEBOOK, "I");
                }
                if (facebook_loadAdIdsType == 0) {
                    facebook_i = getRandomPlacementId(FACEBOOK, "I");
                }

                final com.facebook.ads.InterstitialAd fbinterstitialAd1 = new com.facebook.ads.InterstitialAd(activity, facebook_i);
                fbinterstitialAd1.loadAd(fbinterstitialAd1.buildLoadAdConfig().withAdListener(new AbstractAdListener() {
                    @Override
                    public void onError(Ad ad, AdError error) {
                        super.onError(ad, error);
                        is_callback[0] = false;
                        interstitialCallBack();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        super.onAdLoaded(ad);

                        is_callback[0] = false;
                        if (fbinterstitialAd1.isAdLoaded()) {
                            fbinterstitialAd1.show();
                        } else {
//                            interstitialCallBack();
                        }


                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        super.onInterstitialDismissed(ad);
                        interstitialCallBack();
                    }
                }).build());


//                new CountDownTimer(ad_dialog_time_in_second_loadAndShow * 1000, 10) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second_loadAndShow;
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        if (loading_dialog == true) {
//                            dialog.dismiss();
//                        }
//                        if (fbinterstitialAd1.isAdLoaded()) {
//                            fbinterstitialAd1.show();
//                        } else {
//                            if (is_callback[0] == true) {
//                                interstitialCallBack();
//                            }
//                        }
//                    }
//                }.start();

            } else if (platform.equals(TopOn) && topon_AdStatus == 1) {

                if (topon_i.isEmpty()) {
                    topon_i = getRandomPlacementId(TopOn, "I");
                }
                if (topon_loadAdIdsType == 0) {
                    topon_i = getRandomPlacementId(TopOn, "I");
                }

                final ATInterstitial mtoponInterstitialAd = new ATInterstitial(activity, topon_i);
                mtoponInterstitialAd.setAdListener(new ATInterstitialExListener() {

                    @Override
                    public void onDeeplinkCallback(ATAdInfo adInfo, boolean isSuccess) {
                        Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
                    }

                    @Override
                    public void onDownloadConfirm(Context context, ATAdInfo atAdInfo, ATNetworkConfirmInfo atNetworkConfirmInfo) {

                    }

                    @Override
                    public void onInterstitialAdLoaded() {
                        Log.i(TAG, "onInterstitialAdLoaded");

                        is_callback[0] = false;
                        if (mtoponInterstitialAd.checkAdStatus().isReady()) {
                            mtoponInterstitialAd.show(activity);
                        } else {
//                            interstitialCallBack();
                        }
                    }

                    @Override
                    public void onInterstitialAdLoadFail(com.anythink.core.api.AdError adError) {
                        Log.i(TAG, "onInterstitialAdLoadFail:\n" + adError.getFullErrorInfo());
                        is_callback[0] = false;
                        interstitialCallBack();
                    }

                    @Override
                    public void onInterstitialAdClicked(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdClicked:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdShow(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdShow:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdClose(ATAdInfo entity) {
                        interstitialCallBack();
                        Log.i(TAG, "onInterstitialAdClose:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdVideoStart(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdVideoStart:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdVideoEnd(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdVideoEnd:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdVideoError(com.anythink.core.api.AdError adError) {
                        Log.i(TAG, "onInterstitialAdVideoError:\n" + adError.getFullErrorInfo());
                    }

                });
                mtoponInterstitialAd.load();

//                new CountDownTimer(ad_dialog_time_in_second_loadAndShow * 1000, 10) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second_loadAndShow;
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        if (loading_dialog == true) {
//                            dialog.dismiss();
//                        }
//                        if (mtoponInterstitialAd.checkAdStatus().isReady()) {
//                            mtoponInterstitialAd.show(activity);
//                        } else {
//                            if (is_callback[0] == true) {
//                                interstitialCallBack();
//                            }
//                        }
//                    }
//                }.start();

            } else if (platform.equals(MyCustomAds) && myCustom_AdStatus == 1) {

                CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("Interstitial");
                if (customAdModel != null) {
                    try {

                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }

                        final CustomIntAds customIntAds = new CustomIntAds(activity, customAdModel);
                        customIntAds.setCanceledOnTouchOutside(false);
                        customIntAds.setCancelable(false);
                        customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                            public void onAdsCloseClick() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }

                            public void setOnKeyListener() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }
                        });
                        customIntAds.show();
                    } catch (Exception e) {
                        e.printStackTrace();

                        interstitialCallBack();
                    }
                } else {
                    interstitialCallBack();
                }

            } else {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                return;
            }
        }
    }


    public void turnLoadAndShowInterstitialAd(final Context context, MyCallback myCallback2, final boolean loading_dialog, int how_many_clicks, String specific_platform, String admob_i, String facebook_i, String topon_i) {
        myCallback = myCallback2;

        count_click++;
        if (app_adShowStatus == 0) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }
        if (how_many_clicks != 0) {
            if (count_click % how_many_clicks != 0) {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                return;
            }
        }

        count_click_for_alt++;


        int app_howShowAd = mysharedpreferences.getInt("app_howShowAdInterstitial", 0);
        String adPlatformSequence = mysharedpreferences.getString("app_adPlatformSequenceInterstitial", "");
        String alernateAdShow = mysharedpreferences.getString("app_alernateAdShowInterstitial", "");

        if (!specific_platform.isEmpty()) {
            app_howShowAd = 0;
            adPlatformSequence = specific_platform;
        }


        interstitial_sequence = new ArrayList<String>();
        if (app_howShowAd == 0 && !adPlatformSequence.isEmpty()) {
            String[] adSequence = adPlatformSequence.split(",");

            for (int i = 0; i < adSequence.length; i++) {
                interstitial_sequence.add(adSequence[i]);
            }

        } else if (app_howShowAd == 1 && !alernateAdShow.isEmpty()) {
            String[] alernateAd = alernateAdShow.split(",");

            int index = 0;
            for (int j = 0; j <= 10; j++) {
                if (count_click_for_alt % alernateAd.length == j) {
                    index = j;
                    interstitial_sequence.add(alernateAd[index]);
                }
            }

            String[] adSequence = adPlatformSequence.split(",");
            for (int j = 0; j < adSequence.length; j++) {
                if (interstitial_sequence.size() != 0) {
                    if (!interstitial_sequence.get(0).equals(adSequence[j])) {
                        interstitial_sequence.add(adSequence[j]);
                    }
                }

            }
        } else {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
        }

        new AppPreferenceVpn(context).setIsForceShow(true);
        if (interstitial_sequence.size() != 0) {
            String platform = interstitial_sequence.get(0);
            final boolean[] is_callback = {true};

            dialog = new Dialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.ad_progress_dialog, null);
            dialog.setContentView(view);
            dialog.setCancelable(false);
            Window window = dialog.getWindow();
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            if (loading_dialog == true) {
                dialog.show();
            }

            if (platform.equals(ADMOB) && admob_AdStatus == 1 && new AppPreferenceVpn(context).getIsVpnConnected()) {

                if (admob_i.isEmpty()) {
                    admob_i = getRandomPlacementId(ADMOB, "I");
                }
                if (admob_loadAdIdsType == 0) {
                    admob_i = getRandomPlacementId(ADMOB, "I");
                }
                final InterstitialAd[] mInterstitialAd = new InterstitialAd[1];
                mInterstitialAd[0] = null;
                AdRequest adRequest = new AdRequest.Builder().build();
                InterstitialAd.load(activity, admob_i, adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd[0] = interstitialAd;
                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }
                        is_callback[0] = false;
                        mInterstitialAd[0].show((Activity) context);
                        mInterstitialAd[0].setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                interstitialCallBack();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.

                                mInterstitialAd[0] = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd[0] = null;
                        is_callback[0] = false;
                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }
                        interstitialCallBack();
                    }
                });

//                new CountDownTimer(ad_dialog_time_in_second_loadAndShow * 1000, 10) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second_loadAndShow;
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        if (loading_dialog == true) {
//                            dialog.dismiss();
//                        }
//                        if (mInterstitialAd[0] != null) {
//                            mInterstitialAd[0].show((Activity) context);
//                        } else {
//                            if (is_callback[0] == true) {
//                                interstitialCallBack();
//                            }
//                        }
//                    }
//                }.start();


            } else if ((platform.equals(FACEBOOK) && facebook_AdStatus == 1) || !new AppPreferenceVpn(activity).getIsVpnConnected()) {

                if (facebook_i.isEmpty()) {
                    facebook_i = getRandomPlacementId(FACEBOOK, "I");
                }
                if (facebook_loadAdIdsType == 0) {
                    facebook_i = getRandomPlacementId(FACEBOOK, "I");
                }

                final com.facebook.ads.InterstitialAd fbinterstitialAd1 = new com.facebook.ads.InterstitialAd(activity, facebook_i);
                fbinterstitialAd1.loadAd(fbinterstitialAd1.buildLoadAdConfig().withAdListener(new AbstractAdListener() {
                    @Override
                    public void onError(Ad ad, AdError error) {
                        super.onError(ad, error);
                        is_callback[0] = false;
                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }
                        interstitialCallBack();
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        super.onAdLoaded(ad);
                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }
                        is_callback[0] = false;
                        if (fbinterstitialAd1.isAdLoaded()) {
                            fbinterstitialAd1.show();
                        } else {
                            interstitialCallBack();
                        }
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        super.onInterstitialDismissed(ad);
                        interstitialCallBack();
                    }
                }).build());


//                new CountDownTimer(ad_dialog_time_in_second_loadAndShow * 1000, 10) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second_loadAndShow;
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        if (loading_dialog == true) {
//                            dialog.dismiss();
//                        }
//                        if (fbinterstitialAd1.isAdLoaded()) {
//                            fbinterstitialAd1.show();
//                        } else {
//                            if (is_callback[0] == true) {
//                                interstitialCallBack();
//                            }
//                        }
//                    }
//                }.start();

            } else if (platform.equals(TopOn) && topon_AdStatus == 1) {

                if (topon_i.isEmpty()) {
                    topon_i = getRandomPlacementId(TopOn, "I");
                }
                if (topon_loadAdIdsType == 0) {
                    topon_i = getRandomPlacementId(TopOn, "I");
                }

                final ATInterstitial mtoponInterstitialAd = new ATInterstitial(activity, topon_i);
                mtoponInterstitialAd.setAdListener(new ATInterstitialExListener() {

                    @Override
                    public void onDeeplinkCallback(ATAdInfo adInfo, boolean isSuccess) {
                        Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
                    }

                    @Override
                    public void onDownloadConfirm(Context context, ATAdInfo atAdInfo, ATNetworkConfirmInfo atNetworkConfirmInfo) {

                    }

                    @Override
                    public void onInterstitialAdLoaded() {
                        Log.i(TAG, "onInterstitialAdLoaded");
                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }
                        is_callback[0] = false;
                        if (mtoponInterstitialAd.checkAdStatus().isReady()) {
                            mtoponInterstitialAd.show(activity);
                        } else {
                            interstitialCallBack();
                        }
                    }

                    @Override
                    public void onInterstitialAdLoadFail(com.anythink.core.api.AdError adError) {
                        Log.i(TAG, "onInterstitialAdLoadFail:\n" + adError.getFullErrorInfo());
                        is_callback[0] = false;
                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }
                        interstitialCallBack();
                    }

                    @Override
                    public void onInterstitialAdClicked(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdClicked:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdShow(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdShow:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdClose(ATAdInfo entity) {
                        interstitialCallBack();
                        Log.i(TAG, "onInterstitialAdClose:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdVideoStart(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdVideoStart:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdVideoEnd(ATAdInfo entity) {
                        Log.i(TAG, "onInterstitialAdVideoEnd:\n" + entity.toString());
                    }

                    @Override
                    public void onInterstitialAdVideoError(com.anythink.core.api.AdError adError) {
                        Log.i(TAG, "onInterstitialAdVideoError:\n" + adError.getFullErrorInfo());
                    }

                });
                mtoponInterstitialAd.load();

//                new CountDownTimer(ad_dialog_time_in_second_loadAndShow * 1000, 10) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        double time = (millisUntilFinished / 10) / ad_dialog_time_in_second_loadAndShow;
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        if (loading_dialog == true) {
//                            dialog.dismiss();
//                        }
//                        if (mtoponInterstitialAd.checkAdStatus().isReady()) {
//                            mtoponInterstitialAd.show(activity);
//                        } else {
//                            if (is_callback[0] == true) {
//                                interstitialCallBack();
//                            }
//                        }
//                    }
//                }.start();

            } else if (platform.equals(MyCustomAds) && myCustom_AdStatus == 1) {

                CustomAdModel customAdModel = AppManage.getInstance(activity).getMyCustomAd("Interstitial");
                if (customAdModel != null) {
                    try {

                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }

                        final CustomIntAds customIntAds = new CustomIntAds(activity, customAdModel);
                        customIntAds.setCanceledOnTouchOutside(false);
                        customIntAds.setCancelable(false);
                        customIntAds.setOnCloseListener(new CustomIntAds.OnCloseListener() {
                            public void onAdsCloseClick() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }

                            public void setOnKeyListener() {
                                customIntAds.dismiss();
                                interstitialCallBack();
                            }
                        });
                        customIntAds.show();
                    } catch (Exception e) {
                        e.printStackTrace();

                        if (loading_dialog == true) {
                            dialog.dismiss();
                        }
                        interstitialCallBack();
                    }
                } else {
                    if (loading_dialog == true) {
                        dialog.dismiss();
                    }
                    interstitialCallBack();
                }

            } else {
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
                return;
            }
        }


    }

    public void showBackPressAd(Activity context, MyCallback myCallback2) {
        myCallbackPress = myCallback2;
        if (appPreferenceVpn != null && appPreferenceVpn.getIsVpnConnected()) {
            count_back_click++;

            int appBackPressAd = mysharedpreferences.getInt("app_backPressAdStatus", 0);
            String appBackPressAdFormat = mysharedpreferences.getString("app_backPressAdType", "");
            int how_many_clicks = mysharedpreferences.getInt("app_backPressAdLimit", 0);
            if (how_many_clicks == 0) {
                if (myCallbackPress != null) {
                    myCallbackPress.callbackCall();
                    myCallbackPress = null;
                }
                return;
            }
            if (app_adShowStatus == 0) {
                if (myCallbackPress != null) {
                    myCallbackPress.callbackCall();
                    myCallbackPress = null;
                }
                return;
            }
            if (appBackPressAd == 0) {
                if (myCallbackPress != null) {
                    myCallbackPress.callbackCall();
                    myCallbackPress = null;
                }
                return;
            }

            if (how_many_clicks != 0) {
                if (count_back_click % how_many_clicks != 0) {
                    if (myCallbackPress != null) {
                        myCallbackPress.callbackCall();
                        myCallbackPress = null;
                    }
                    return;
                }
            }

            if (appBackPressAdFormat.equals("Interstitial")) {

                AppManage.getInstance(context).showInterstitialBackAd(context, new AppManage.MyCallback() {
                    public void callbackCall() {
                        if (myCallbackPress != null) {
                            myCallbackPress.callbackCall();
                            myCallbackPress = null;
                        }
                    }
                });


            } else if (appBackPressAdFormat.equals("AppOpen")) {

                AppManage.getInstance(context).showAppOpenAd(context, new MyCallback() {
                    @Override
                    public void callbackCall() {
                        if (myCallbackPress != null) {
                            myCallbackPress.callbackCall();
                            myCallbackPress = null;
                        }
                    }
                });

            } else if (appBackPressAdFormat.equals("Alternate")) {
                if (count_back_click % 2 == 0) {
                    AppManage.getInstance(context).showAppOpenAd(context, new MyCallback() {
                        @Override
                        public void callbackCall() {
                            if (myCallbackPress != null) {
                                myCallbackPress.callbackCall();
                                myCallbackPress = null;
                            }
                        }
                    });
                } else {
                    AppManage.getInstance(context).showInterstitialBackAd(context, new AppManage.MyCallback() {
                        public void callbackCall() {
                            if (myCallbackPress != null) {
                                myCallbackPress.callbackCall();
                                myCallbackPress = null;
                            }
                        }
                    });

                }

            }
        } else {
            if (myCallbackPress != null) {
                myCallbackPress.callbackCall();
                myCallbackPress = null;
            }
        }
    }

    public void loadAdmobAppOpenAd(final Context context, String appopen_id) {
        if (appopen_id.isEmpty() || admob_loadAdIdsType == 0) {
            appopen_id = getRandomPlacementId(ADMOB, "AO");
        }
        if (admob_AdStatus == 0 || appopen_id.isEmpty()) {
            return;
        }
        if (appopenManager != null) {
            if (appopen_id_pre.equals(appopen_id)) {
                return;
            }
        }
        appopen_id_pre = appopen_id;
        appopenManager = new AppOpenManager((Activity) context, appopen_id);
        appopenManager.fetchAd(new AppOpenManager.splshADlistner() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String error) {
                appopenManager = null;
            }
        });

    }

    public void showAppOpenAd(final Context context, MyCallback myCallback2) {
        myCallback = myCallback2;
        if (appopenManager == null) {
            if (myCallback != null) {
                myCallback.callbackCall();
                myCallback = null;
            }
            return;
        }

        if (appopen_id_pre.isEmpty() || admob_loadAdIdsType == 0) {
            appopen_id_pre = getRandomPlacementId(ADMOB, "AO");
        }

        appopenManager.showAdIfAvailable(new AppOpenManager.splshADlistner() {
            @Override
            public void onSuccess() {

                appopenManager = null;
                loadAdmobAppOpenAd(context, appopen_id_pre);
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }

            }

            @Override
            public void onError(String error) {


                appopenManager = null;
                loadAdmobAppOpenAd(context, appopen_id_pre);
                if (myCallback != null) {
                    myCallback.callbackCall();
                    myCallback = null;
                }
            }
        });
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int dip2px(float dipValue) {
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void initCloseView() {
        if (mCloseView == null) {
            mCloseView = new ImageView(activity);
            mCloseView.setImageResource(R.drawable.ad_close);

            int padding = dip2px(activity, 5);
            mCloseView.setPadding(padding, padding, padding, padding);

            int size = dip2px(activity, 1);
            int margin = dip2px(activity, 2);

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(size, size);
            layoutParams.topMargin = margin;
            layoutParams.rightMargin = margin;
            layoutParams.gravity = Gravity.TOP | Gravity.RIGHT;

            mCloseView.setLayoutParams(layoutParams);
            mCloseView.setVisibility(View.GONE);
        }
    }

    private void preloadAdmobSmallnative() {
        if (admobsmallnativeloaded) {
            return;
        }
        AdLoader.Builder builder = new AdLoader.Builder(activity, ADMOB_N[0]);

        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
                admobsmallnativeloaded = true;
                googlesmallNativeAd = unifiedNativeAd;
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder().setStartMuted(true).build();

        com.google.android.gms.ads.formats.NativeAdOptions adOptions = new com.google.android.gms.ads.formats.NativeAdOptions.Builder().setVideoOptions(videoOptions).build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError errorCode) {
                Log.e(TAG, "onAdFailedToLoad: " + errorCode.getMessage());
                preloadAdmobSmallnative();
            }

        }).build();

        adLoader.loadAd(new AdManagerAdRequest.Builder().build());
    }


    public interface MyCallback {
        void callbackCall();
    }

    public void loadOpenAd(Activity activity) {
        if (app_adShowStatus == 1 && open_ad_interstitial_Specific_flag.equalsIgnoreCase("1") && appPreferenceVpn.getIsVpnConnected()) {
            appopenManager = new AppOpenManager(activity);
            appopenManager.fetchAd(new AppOpenManager.splshADlistner() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(String error) {

                }
            });
        } else {
            AppManage.getInstance(activity).loadInterstitialAd(activity);
        }
    }

    public void showOpenAndInterAd(Activity activity, MyCallback myCallback1) {
        myCallback = myCallback1;
        if (app_adShowStatus == 1 && appopenManager != null && open_ad_interstitial_Specific_flag.equalsIgnoreCase("1") && appPreferenceVpn.getIsVpnConnected()) {
            appopenManager.showAdIfAvailable(new AppOpenManager.splshADlistner() {
                @Override
                public void onSuccess() {
                    interstitialCallBack();
                }

                @Override
                public void onError(String error) {
                    interstitialCallBack();
                    Log.e("=====", "onError: $error");
                }
            });

        } else {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
            } else {
                interstitialCallBack();
            }

        }

    }


    public void preloadAdmobNative(String admob_n1) {
        if (app_adShowStatus == 0 || !new AppPreferenceVpn(activity).getIsVpnConnected()) {
            return;
        }
        admob_n = admob_n1;
        if (mysharedpreferences.getInt("appNativePreLoad", 0) == 1 && (state_admobNative.equals("Start")) || state_admobNative.equals("Fail")) {

            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_n = getRandomPlacementId(ADMOB, "N");
            }
            if (admob_n.isEmpty()) {
                return;
            }
            state_admobNative = "Loading";
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    // Show the ad.
                    admobNativeAd_preLoad = nativeAd;
                    state_admobNative = "Loaded";
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Handle the failure by logging, altering the UI, and so on.
                    state_admobNative = "Fail";
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());


        } else {
            Log.e("admob_state", "proccess");
        }
    }


    public void preloadAdmobNativeBanner(String admob_n1) {
        if (app_adShowStatus == 0 || !new AppPreferenceVpn(activity).getIsVpnConnected()) {
            return;
        }
        admob_n = admob_n1;
        googlesmallNativeAd = null;

        if (mysharedpreferences.getInt("app_howShowAdBanner", 0) == 1 && (state_admobNative.equals("Start")) || state_admobNative.equals("Fail")) {

            if ((admob_loadAdIdsType == 0 || admob_loadAdIdsType == 2 || admob_n.equals("random")) && !admob_n.isEmpty()) {
                admob_n = getRandomPlacementId(ADMOB, "B");
            }
            if (admob_n.isEmpty()) {
                return;
            }
            state_admobNative = "Loading";
            final AdLoader adLoader = new AdLoader.Builder(activity, admob_n).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    // Show the ad.
                    googlesmallNativeAd = nativeAd;
                    state_admobNative = "Loaded";
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Handle the failure by logging, altering the UI, and so on.
                    state_admobNative = "Fail";
                }
            }).withNativeAdOptions(new NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()).build();
            adLoader.loadAd(new AdRequest.Builder().build());


        } else {
            Log.e("admob_state", "proccess");
        }
    }


}


