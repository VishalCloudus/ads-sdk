package com.pesonal.adsdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferenceVpn {
    private final String USER_PREFS = "ADS_VPN";
    private SharedPreferences appSharedPref;
    private SharedPreferences.Editor prefEditor;

    private String isVpnConnected = "isVpnConnected";
    private String isCountrySame = "isCountrySame";
    private String isForceShow = "isForceShow";
    private String isVpnPermission = "isVpnPermission";

    public AppPreferenceVpn(Context context) {
        this.appSharedPref = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
        this.prefEditor = appSharedPref.edit();
    }

    public boolean getIsVpnConnected() {
        return appSharedPref.getBoolean(isVpnConnected, false);
    }

    public void setIsVpnConnected(boolean isFlag) {
        this.prefEditor.putBoolean(isVpnConnected, isFlag).commit();
    }

    public boolean getIsForceShow() {
        return appSharedPref.getBoolean(isForceShow, false);
    }

    public void setIsForceShow(boolean isFlag) {
        this.prefEditor.putBoolean(isForceShow, isFlag).commit();
    }


    public boolean getIsCountrySame() {
        return appSharedPref.getBoolean(isCountrySame, false);
    }

    public void setIsCountrySame(boolean isFlag) {
        this.prefEditor.putBoolean(isCountrySame, isFlag).commit();
    }

    public boolean getVpnPermission() {
        return appSharedPref.getBoolean(isVpnPermission, false);
    }

    public void setVpnPermission(boolean isFlag) {
        this.prefEditor.putBoolean(isVpnPermission, isFlag).commit();
    }


}