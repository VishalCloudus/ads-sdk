package com.livechat.friendvideo.calltalk.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class room {


    @SerializedName("uid")
    @Expose
    var uid = 0

    @SerializedName("flag")
    @Expose
    var flag: String? = null

    @SerializedName("channel")
    @Expose
    var channel: String? = null

    @SerializedName("profile")
    @Expose
    var profile: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null



    override fun toString(): String {
        return "ClassPojo [uid = $uid, flag = $flag, channel = $channel]"
    }


}