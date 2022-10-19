package com.livechat.friendvideo.calltalk.VPN;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anchorfree.vpnsdk.callbacks.Callback;


public abstract class VpnBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void isLoggedIn(Callback<Boolean> callback);

    public abstract void loginToVpn();

    public abstract void isConnected(Callback<Boolean> callback);

    public abstract void connectToVpn();

    public abstract void disconnectFromVnp();

    public abstract void chooseServer();

    public abstract void getCurrentServer(Callback<String> callback);

    public abstract void checkRemainingTraffic();
}