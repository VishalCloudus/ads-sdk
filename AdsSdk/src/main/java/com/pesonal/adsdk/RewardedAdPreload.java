package com.pesonal.adsdk;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardedAdPreload {
    public static String TAG = "Tiger";
    public static RewardedAd mAdmobRewardedAd = null;
    public static boolean isAdmobRewardedAdLoaded = false;

    public static void LoadAllRewardedAd(final Activity activity) {
        if (new AppPreferenceVpn(activity).getIsVpnConnected()){
            Log.e(TAG, "RewardedAdKey: " + AppManage.ADMOB_RewardedAd);
            if (AppManage.app_adShowStatus != 0 && AppManage.ADMOB_RewardedAd != null && !AppManage.ADMOB_RewardedAd.equalsIgnoreCase("")) {
                if (isAdmobRewardedAdLoaded && mAdmobRewardedAd != null) {
                    Log.e(TAG, "RewardedAd Already Loaded..!!");
                    return;
                }
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdmobRewardedAd.load(activity, AppManage.ADMOB_RewardedAd,
                        adRequest, new RewardedAdLoadCallback() {
                            @Override
                            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                // Handle the error.
                                Log.e(TAG, "onAdFailedToLoad   " + loadAdError.getMessage());
                                mAdmobRewardedAd = null;
                                isAdmobRewardedAdLoaded = false;
                                mAdmobRewardedAd = null;
                                LoadAllRewardedAd(activity);
                            }

                            @Override
                            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                mAdmobRewardedAd = rewardedAd;
                                isAdmobRewardedAdLoaded = true;
                                Log.d(TAG, "Ad was loaded.");
                            }
                        });
            } else {
                mAdmobRewardedAd = null;
                isAdmobRewardedAdLoaded = false;
                mAdmobRewardedAd = null;
            }
        }
    }

    public static void ShowAllRewardedAd(final Activity activity, final OnRewardedAdCallBackListener onRewardedAdCallBackListener) {
        if (new AppPreferenceVpn(activity).getIsVpnConnected()){
            if (AppManage.app_adShowStatus != 0 && mAdmobRewardedAd != null) {
                mAdmobRewardedAd.show(activity, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        // Handle the reward.
                        Log.d(TAG, "The user earned the reward.");
                        int rewardAmount = rewardItem.getAmount();
                        String rewardType = rewardItem.getType();
                    }
                });

                mAdmobRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Log.d(TAG, "Ad dismissed fullscreen content.");
                        mAdmobRewardedAd = null;
                        isAdmobRewardedAdLoaded = false;
                        onRewardedAdCallBackListener.onCallBackRewardedAd();
                        LoadAllRewardedAd(activity);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        Log.e(TAG, "Ad failed to show fullscreen content.");
                        mAdmobRewardedAd = null;
                        isAdmobRewardedAdLoaded = false;
                        onRewardedAdCallBackListener.onCallBackRewardedAd();
                        LoadAllRewardedAd(activity);
                    }

                    @Override
                    public void onAdImpression() {
                        Log.d(TAG, "Ad recorded an impression.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content.");
                    }
                });

            } else {
                Log.d(TAG, "The rewarded ad wasn't ready yet.");
                onRewardedAdCallBackListener.onCallBackRewardedAd();
            }
        }else {
            onRewardedAdCallBackListener.onCallBackRewardedAd();
        }

    }

    public interface OnRewardedAdCallBackListener {
        void onCallBackRewardedAd();
    }

}
