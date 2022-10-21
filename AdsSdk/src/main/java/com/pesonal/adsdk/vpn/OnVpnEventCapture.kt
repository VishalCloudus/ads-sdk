package com.pesonal.adsdk.vpn

interface OnVpnEventCapture {
    fun onNextPageJump()
    fun onVpnConnected(msg : String)
    fun onVpnFail(msg : String)
}