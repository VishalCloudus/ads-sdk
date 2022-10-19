package com.livechat.friendvideo.calltalk.api.retrofit

class ApiUtils {

    fun getAPIService(str: String?): APIInterface? {
        return ApiClient().getClient(str).create(APIInterface::class.java)
    }

    fun getWebService(str: String?): APIInterface? {
        return ApiClient().getwebClient(str).create(APIInterface::class.java)
    }
}