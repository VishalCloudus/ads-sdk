package com.livechat.friendvideo.calltalk.activity.splash

interface OnVpnEventCapture {
    fun onNextPageJump()
    fun onVpnConnected(msg : String)
    fun onVpnFail(msg : String)
}